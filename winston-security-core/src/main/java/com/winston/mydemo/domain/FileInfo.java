package com.winston.mydemo.domain;

import lombok.Data;

/**
 * @ClassName FileInfo
 * @Description
 * @Author Winston
 * @Date 2019/4/14 20:07
 * @Version 1.0
 **/
@Data
public class FileInfo {

    public FileInfo(String path){
        this.path = path;
    }

    private String path;

}
