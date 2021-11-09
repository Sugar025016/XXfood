package com.xxxx.springsecuritydemo.service.Impl;

import com.xxxx.springsecuritydemo.entity.FileData;
import com.xxxx.springsecuritydemo.repository.IFileDateRepository;
import com.xxxx.springsecuritydemo.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author: IFileService
 * @date: 2021/10/2
 * @description:
 */
@Transactional
@Service
public class FileService implements IFileService {

    @Autowired
    IFileDateRepository iFileDateRepository;

    @Value("${imageGetUrl}")
    String imageGetUrl;
    @Value("${imagePutUrl}")
    String imagePutUrl;
    @Value("${imagePutUrl2}")
    String imagePutUrl2;

    @Override
    public FileData getOne(Integer id) {
//        FileData one = iImageRepository.getOne(id);
        return null;
    }

    @Override
    public FileData getAll() {
        return null;
    }

    @Override
    public FileData save(FileData fileData) {
        return null;
    }


    @Override
    public FileData save(MultipartFile multipartFile) {
//        String[] suffix = multipartFile.getOriginalFilename().split(".");
        String originalFilename=multipartFile.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        Date date = new Date();
        long round = Math.round(Math.random() * 10000);
        long time = date.getTime();
        String fileName= String.valueOf(time)+round;
        String filePath = imagePutUrl+"/"+fileName+suffix;
        File file = new File(filePath);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        String filePath2 = imagePutUrl2+"/"+fileName+suffix;
//
//        File files = new File(filePath2);
//        if(!files.getParentFile().exists()){
//            files.getParentFile().mkdirs();
//        }
//        try {
//            multipartFile.transferTo(files);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }



        FileData fileData = new FileData(multipartFile,suffix,fileName);
        FileData save = iFileDateRepository.save(fileData);
        
        return save;
    }

    @Override
    public List<FileData> saveAll(List<FileData> fileData) {
        return null;
    }

    @Override
    public List<FileData> saveAll(FileData fileData) {
        return null;
    }
}
