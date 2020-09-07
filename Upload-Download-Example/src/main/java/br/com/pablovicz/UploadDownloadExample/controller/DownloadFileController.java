package br.com.pablovicz.UploadDownloadExample.controller;

import br.com.pablovicz.UploadDownloadExample.business.FileStorage;
import br.com.pablovicz.UploadDownloadExample.model.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.nio.file.FileStore;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DownloadFileController {

    @Autowired
    private FileStorage fileStorage;

    @GetMapping("/files")
    public String getListFiles(Model model){

        List<FileInfo> fileInfos = fileStorage.loadFiles().map(
                path -> {
                    String fileName = path.getFileName().toString();
                    String url = MvcUriComponentsBuilder.fromMethodName(DownloadFileController.class,
                            "downloadFile", path.getFileName().toString()).build().toString();

                    return new FileInfo(fileName, url);
                }
        ).collect(Collectors.toList());

        model.addAttribute("files",fileInfos);

        return "multipartfile/listfiles";
    }

    @GetMapping("/files/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName){

        Resource file = fileStorage.loadFile(fileName);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attahment; fileName=\"" + file.getFilename() + "\"")
                .body(file);
    }




}
