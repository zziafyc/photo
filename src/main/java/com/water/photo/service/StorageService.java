package com.water.photo.service;

import com.guanweiming.common.ServerResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chezhu.xin
 */
@Service
public class StorageService {

    private static final String UPLOAD_DIR = "d:/upload/";

    public ServerResponse<String> upload(MultipartFile file, String fileName) throws IOException {
        String prefix = new SimpleDateFormat("yyyyMMddHHmmssSSSS").format(new Date());
        File save = new File(UPLOAD_DIR + prefix + "_" + file.getOriginalFilename());
        FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(save));
        return ServerResponse.createBySuccess();
    }
}
