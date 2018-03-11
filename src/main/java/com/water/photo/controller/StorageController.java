package com.water.photo.controller;

import com.guanweiming.common.ServerResponse;
import com.water.photo.service.StorageService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author 关
 */
@Api(description = "上传文件接口")
@RestController
@RequestMapping("storage")
public class StorageController {

    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("upload")
    public ServerResponse<String> upload(MultipartFile file,String fileName) throws IOException {
        if (file == null) {
            return ServerResponse.createByErrorMessage("文件不能为空");
        }
        return storageService.upload(file,fileName);
    }


}
