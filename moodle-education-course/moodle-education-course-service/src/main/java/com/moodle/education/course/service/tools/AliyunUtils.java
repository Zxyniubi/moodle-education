package com.moodle.education.course.service.tools;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.moodleeducation.commoncore.enums.FileEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
@Slf4j
public final class  AliyunUtils {
    private static final String bucketName ="moodle-video-bucket";
    private static final String end_point = "cn-beijing.oss.aliyuncs.com";
    private static final String accessKey_id="LTAI5t9h9WU1kjjA6KT8onZe";
    private static final String secret="1tnbpwLhkz9GPNPKpW3l6UGwo3Gk4s";
    private static final String AliyunOSSUrl="https:"+bucketName+"."+"oss-cn-beijing.aliyuncs.com/";
    private AliyunUtils(){}


    public static String uploadPic(File file){
        FileInputStream in =null;
        try{
            in =new FileInputStream(file);
            String name = file.getName();
            String filePath = FileEnum.IMG.name()+"/"+StrUtils.get32UUID()+name.substring(name.lastIndexOf("."));
            getOssClient().putObject(bucketName,filePath,in);
            return AliyunOSSUrl+filePath;
        }catch (Exception e) {
            log.error("上传失败", e);
            return "";
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error(e.toString());
                }
            }
        }
    }

    public static String uploadVideo(File file) {
        // 上传
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            String name = file.getName();
            String filePath = FileEnum.VIDEO.name().toLowerCase() + "/" + StrUtils.get32UUID() + name.substring(name.lastIndexOf("."));
            putObjectForFile(filePath, fileInputStream);
            return AliyunOSSUrl+ filePath;
        } catch (Exception e) {
            log.error("上传失败", e);
            return "";
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    log.error("上传失败", e);
                }
            }
        }
    }


    private static PutObjectResult putObjectForFile( String key, InputStream inputStream) {
        OSS ossClient = getOssClient();
        return ossClient.putObject(bucketName, key, inputStream);
    }


    private static OSS getOssClient(){
        return  new OSSClientBuilder().build(end_point,accessKey_id,secret);
    }


    private static void deleteObject(String bucketName, String key) throws IOException {
        getOssClient().deleteObject(bucketName, key);
    }
}
