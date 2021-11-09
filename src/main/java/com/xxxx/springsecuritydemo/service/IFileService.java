package com.xxxx.springsecuritydemo.service;

import com.xxxx.springsecuritydemo.entity.FileData;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IFileService {
    FileData getOne(Integer Id);

    FileData getAll();

    FileData save(FileData fileData);

    FileData save(MultipartFile multipartFile);

    List<FileData> saveAll(List<FileData> fileData);

    List<FileData>  saveAll(FileData fileData);

}
