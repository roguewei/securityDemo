package com.winston.web.controller;

import com.winston.mydemo.domain.FileInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * @ClassName FileController
 * @Description
 * @Author Winston
 * @Date 2019/4/14 20:06
 * @Version 1.0
 **/
@RestController
@RequestMapping("/file")
public class FileController {

    String folder = "I:/IDEAworkspace2/winstonsecurity/winston-security-demo/src/main/java/com/winston/web/controller";

    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());

        File localFile = new File(folder, new Date().getTime()+".txt");

        // 把传过来的文件写到本地文件
        file.transferTo(localFile);

        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response){
        try(
                InputStream inputStream = new FileInputStream(new File(folder, id+".txt"));
                OutputStream outputStream = response.getOutputStream();
        ){
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=test.txt");

            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        }catch (Exception e){

        }
    }
}
