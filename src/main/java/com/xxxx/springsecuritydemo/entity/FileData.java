package com.xxxx.springsecuritydemo.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
/**
 * @author: FileData
 * @date: 2021/10/2
 * @description:
 */
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor

@Table(name="file_data")


public class FileData {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY, generator="auto_increment")
    @GenericGenerator(name = "auto_increment", strategy = "native")
    private Integer id;

    @Column(name="original_file_name",length =255,nullable =false)
    private String originalFileName;

    @Column(name="file_name",length =255,nullable =false)
    private String fileName;

    @Column(name="suffix",length =255,nullable =false)
    private String suffix;

    @Column(name="content_type",length =255,nullable =false)
    private String contentType;


    public FileData(MultipartFile multipartFile, String suffix, String fileName) {
        this.originalFileName = multipartFile.getOriginalFilename();
        this.fileName = fileName+suffix;
        this.suffix = suffix;
        this.contentType=multipartFile.getContentType();

    }
}
