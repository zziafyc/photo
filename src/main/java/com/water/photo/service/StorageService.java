package com.water.photo.service;

import com.guanweiming.common.ServerResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chezhu.xin
 */
@Service
public class StorageService {

    private static final String UPLOAD_DIR = "d:/upload/";

    public ServerResponse<String> upload(MultipartFile file) throws IOException {
        String prefix = new SimpleDateFormat("yyyyMMddHHmmssSSSS").format(new Date());
        String fileName = prefix + "_" + file.getOriginalFilename();
        File save = new File(UPLOAD_DIR + fileName);
        FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(save));
        return ServerResponse.createBySuccess(fileName);
    }

    public void showImage(HttpServletResponse response, String fileName) {
        response.setContentType("image/png");


        FileInputStream fis = null;
        OutputStream os = null;
        try {
            fis = new FileInputStream(UPLOAD_DIR + fileName);
            os = response.getOutputStream();
            int count = 0;
            byte[] buffer = new byte[1024 * 8];
            while ((count = fis.read(buffer)) != -1) {
                os.write(buffer, 0, count);
                os.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(fis);
            close(os);
        }
    }

    private void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
