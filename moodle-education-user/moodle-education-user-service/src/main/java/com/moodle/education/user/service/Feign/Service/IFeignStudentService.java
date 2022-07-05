package com.moodle.education.user.service.Feign.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moodle.education.user.feign.interfaces.Bo.StudentSearchBo;
import com.moodle.education.user.service.Mapper.StudentMapper;
import com.moodleeducation.commoncore.base.PageUtils;
import com.moodleeducation.commoncore.base.Result;
import com.moodleeducation.commoncore.entity.Student;
import com.moodleeducation.commoncore.tools.MD5Util;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
public class IFeignStudentService {
    private final static Logger log  = LoggerFactory.getLogger(IFeignStudentService.class);
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    public PageInfo<Student> getAllStudent(Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        Page<Student> allStudent = studentMapper.getAllStudent();
        return new PageInfo<Student>(allStudent);
    }
    public int insertStudent(Student student){
         String password = MD5Util.MD5Utils(student.getPassword(), student.getStudentId(), 2);
         student.setPassword(password);
        int i = studentMapper.insertStudent(student);

        IndexQuery query = new IndexQueryBuilder().withObject(student).build();
        elasticsearchRestTemplate.index(query, IndexCoordinates.of("student"));
        return i;
    }
    public int deleteStudent(Integer studentId){
        elasticsearchRestTemplate.delete(studentId.toString(),Student.class);
        return studentMapper.deleteById(studentId);
    }
    public int updateStudent(Student student){
        return studentMapper.updateStudent(student);
    }
    public Student getById(String studentId){
        return studentMapper.getById(studentId);
    }

    public Result<PageUtils<Student>> searchList(StudentSearchBo bo) {
        if (bo.getPageCurrent() <= 0) {
            bo.setPageCurrent(1);
        }
        if (bo.getPageSize() <= 0) {
            bo.setPageSize(20);
        }
        if (StringUtils.isEmpty(bo.getUsername())) {
            return Result.success(new PageUtils<Student>());
        }
        HighlightBuilder.Field field = null;
        // 1 =高亮
        if (bo.getIsHighLight() != null && bo.getIsHighLight().equals(1)) {
            field = new HighlightBuilder.Field("name").preTags("<mark>").postTags("</mark>");
        }
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        if (bo.getIsHighLight() != null && bo.getIsHighLight().equals(1)) {
            nativeSearchQueryBuilder.withHighlightFields(field);
        }
        //分页
        nativeSearchQueryBuilder.withPageable(PageRequest.of(bo.getPageCurrent()-1, bo.getPageSize()));
        //复核查询
         BoolQueryBuilder qb = QueryBuilders.boolQuery();
        // 模糊查询multiMatchQuery，最佳字段best_fields
        qb.must(QueryBuilders.multiMatchQuery(bo.getUsername(),"name").type(MultiMatchQueryBuilder.Type.BEST_FIELDS));
        nativeSearchQueryBuilder.withQuery(qb);
        SearchHits<Student>searchHits =elasticsearchRestTemplate.search(nativeSearchQueryBuilder.build(),Student.class,IndexCoordinates.of("student"));
        List<Student> studentList =new ArrayList<>();
        for(SearchHit<Student> hits:searchHits){
            log.info(hits.toString());
            studentList.add(hits.getContent());
        }
         PageUtils<Student> pageStudent = new PageUtils<>();
        pageStudent.setList(studentList);
        return Result.success(pageStudent);
    }
}
