package com.moodle.education.course.service.service;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moodle.education.course.feign.interfaces.Qo.VideoAuditQo;
import com.moodle.education.course.feign.interfaces.Qo.VideoEsQo;
import com.moodle.education.course.feign.interfaces.Qo.VideoQo;
import com.moodle.education.course.feign.interfaces.Vo.VideoVo;
import com.moodle.education.course.feign.interfaces.entity.Video;
import com.moodle.education.course.service.mapper.CourseMapper;
import com.moodle.education.course.service.tools.AliyunUtils;
import com.moodle.education.course.service.tools.StrUtils;
import com.moodleeducation.commoncore.base.PageUtils;
import com.moodleeducation.commoncore.base.Result;
import com.moodleeducation.commoncore.entity.Student;
import com.moodleeducation.commoncore.enums.CourseEnum;
import com.moodleeducation.commoncore.enums.ResultEnum;
import com.moodleeducation.commoncore.tools.IdWorker;
import com.moodleeducation.commoncore.tools.SystemUtils;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.moodleeducation.commoncore.base.ThreadPool.callbackExecutor;


@Service
@Slf4j
public class IFeignCourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    public PageInfo<VideoVo> listForPage(VideoQo videoQo){
        if (videoQo.getPageNum()<0){
            videoQo.setPageNum(1);
        }
        if(videoQo.getPageSize()<10){
            videoQo.setPageSize(10);
        }
        PageHelper.startPage(videoQo.getPageNum(),videoQo.getPageSize());
        List<VideoVo> videoVoList = courseMapper.listForPage(videoQo);
        log.info(String.valueOf(videoVoList.get(0).getCreateTime().getTime()));
        return new PageInfo<>(videoVoList);
    }

    public Result<Integer> audit(VideoAuditQo auditQo){
        if (auditQo.getVideoId()<=0){
            return Result.error("参数有误");
        }

         Video byId = courseMapper.getById(auditQo.getVideoId());
        if(byId.getVideoType().equals(CourseEnum.PASS.getCode())){
            return Result.error(ResultEnum.COURSE_AUDIT_SUCCESS_ALREADY);
        }
        if(byId.getVideoType().equals(CourseEnum.FAIL.getCode())){
            return Result.error(ResultEnum.COURSE_AUDIT_FAIL_ALREADY);
        }
        if(auditQo.getVideoType()==1){
            auditQo.setRemark("审核通过");
        }
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = sdf.format(date);
        try {
            Date time = sdf.parse( nowTime );
            auditQo.setAuditTime(time);
             int audit = courseMapper.audit(auditQo);
             if(audit>0){
                 return Result.success(audit);
             }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Result.error(ResultEnum.COURSE_AUDIT_FAIL);
    }

    public Result<String> uploadVideo(MultipartFile videoFile){
        if(videoFile==null || videoFile.isEmpty()){
            return Result.error("请选择视频上传");
        }
        String fileName = videoFile.getOriginalFilename();
        boolean fileSuffix = true;
        List<String> fileTypes = Arrays.asList("avi", "mp4", "flv", "mpg", "mov", "asf", "3gp", "f4v", "wmv");
        for(String filetype : fileTypes){
            if(fileName.toLowerCase().endsWith(filetype)){
                fileSuffix = false;
                break;
            }
        }
        if (fileSuffix){
            Result.error("文件格式有误");
        }
        Long videoId = IdWorker.getId();
        File targetFile = new File(SystemUtils.VIDEO_STORAGE_PATH+videoId.toString()+"."+ StrUtils.getSuffix(fileName));
        targetFile.setLastModified(System.currentTimeMillis());
        if(!targetFile.getParentFile().exists()){
            targetFile.getParentFile().mkdirs();
        }
        try {
            videoFile.transferTo(targetFile);
        }catch (Exception e){
            log.error("上传本地失败");
            return Result.error("上传文件出错，请重新上传");
        }
        Video video = new Video();
        video.setVideoId(videoId);
        video.setVideoType(0);
        video.setVideoName("templateName");
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = sdf.format(date);
        try {
            Date time = sdf.parse(nowTime);
            video.setCreateTime(time);
        }catch (Exception e){
            log.error(e.getMessage());
        }
         int result = courseMapper.uploadVideo(video);
         if(result>0){
             callbackExecutor.execute(new Runnable() {
                 @Override
                 public void run() {
                      String s = AliyunUtils.uploadVideo(targetFile);
                      video.setVideoUrl(s);
                      courseMapper.update(video);

                     if (targetFile.isFile() && targetFile.exists()) {
                         targetFile.delete();
                     }
                 }
             });
         }else {
             return Result.error("系统异常，请重试");
         }
         return Result.success(String.valueOf(video.getVideoId()));
    }

    public Result<Integer> uploadImg(MultipartFile imgFile,Long videoId){
        if (imgFile==null || imgFile.isEmpty()){
            return Result.error("请选择图片上传");
        }
        File targetFile = new File(SystemUtils.PIC_STORAGE_PATH+videoId.toString()+"."+StrUtils.getSuffix(imgFile.getOriginalFilename()));
        targetFile.setLastModified(System.currentTimeMillis());
        if(!targetFile.getParentFile().exists()){
            targetFile.getParentFile().mkdirs();
        }
        try {
            imgFile.transferTo(targetFile);
        }catch (Exception e){
            log.error("上传本地失败");
            return Result.error("上传文件出错，请重新上传");
        }
        Video video = new Video();
        video.setVideoId(videoId);
        String imgUrl = AliyunUtils.uploadPic(targetFile);
        video.setImgUrl(imgUrl);
         int update = courseMapper.update(video);
        if (targetFile.isFile() && targetFile.exists()) {
            targetFile.delete();
        }
        if(update>0){
            return Result.success(update);
        }

        return Result.error("更新失败");
    }

    public Result<Integer> submit(Video video){
        if (video.getVideoId()== null){
            return Result.error("参数有误");
        }
        int update = courseMapper.update(video);
        if (update<=0){
            return Result.error("提交失败，请稍后再试");
        }
        return Result.success(update);
    }


    public Result<PageUtils<VideoVo>> searchList(VideoEsQo bo) {
        if (bo.getPageNum() <= 0) {
            bo.setPageNum(1);
        }
        if (bo.getPageSize() <= 0) {
            bo.setPageSize(20);
        }
        if (StringUtils.isEmpty(bo.getVideoName())) {
            return Result.success(new PageUtils<VideoVo>());
        }
        HighlightBuilder.Field field = null;
        // 1 =高亮
        if (bo.getIsHighLightField() != null && bo.getIsHighLightField().equals(1)) {
            field = new HighlightBuilder.Field("name").preTags("<mark>").postTags("</mark>");
        }
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        if (bo.getIsHighLightField() != null && bo.getIsHighLightField().equals(1)) {
            nativeSearchQueryBuilder.withHighlightFields(field);
        }
        //分页
        nativeSearchQueryBuilder.withPageable(PageRequest.of(bo.getPageNum()-1, bo.getPageSize()));
        //复核查询
        BoolQueryBuilder qb = QueryBuilders.boolQuery();
        // 模糊查询multiMatchQuery，最佳字段best_fields
        qb.must(QueryBuilders.multiMatchQuery(bo.getVideoName(),"name").type(MultiMatchQueryBuilder.Type.BEST_FIELDS));
        nativeSearchQueryBuilder.withQuery(qb);
        SearchHits<Video> searchHits =elasticsearchRestTemplate.search(nativeSearchQueryBuilder.build(),Video.class, IndexCoordinates.of("video"));
        List<VideoVo> videoVoList =new ArrayList<>();
        for(SearchHit<Video> hits:searchHits){
            log.info(hits.toString());
             Video content = hits.getContent();
            videoVoList.add(BeanUtil.copyProperties(content,VideoVo.class));
        }
        PageUtils<VideoVo> pageVideo = new PageUtils<>();
        pageVideo.setList(videoVoList);
        return Result.success(pageVideo);
    }
}
