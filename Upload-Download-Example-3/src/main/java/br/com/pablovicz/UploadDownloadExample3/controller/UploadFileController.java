package br.com.pablovicz.UploadDownloadExample3.controller;

import br.com.pablovicz.UploadDownloadExample3.model.FileModel;
import br.com.pablovicz.UploadDownloadExample3.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class UploadFileController {

    @Autowired
    FileRepository fileRepository;

    @PostMapping("/api/file/upload")
    public String uploadMultipartFile(@RequestParam("uploadfile") MultipartFile file){

        try{

            FileModel filemodel = new FileModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());
            fileRepository.save(filemodel);

            return "File uploaded successfully! -> filename: " + file.getOriginalFilename();

        } catch (Exception e) {

            return "FAIL! Maybe you had uploaded the file before or the file's size > 500KB";
        }
    }
}
