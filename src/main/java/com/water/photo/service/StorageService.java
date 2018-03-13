package com.water.photo.service;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.guanweiming.common.ServerResponse;
import com.water.photo.common.Const;
import com.water.photo.domain.Project;
import com.water.photo.mapper.ProjectMapper;
import com.water.photo.vo.ExportVo;
import com.water.photo.vo.ImageVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author chezhu.xin
 */
@Slf4j
@Service
public class StorageService {

    public static final String UPLOAD_DIR = "E:/upload/";
    public static final String TEMP_DIR = "E:/temp/";

    private final ProjectMapper projectMapper;

    public StorageService(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

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

    public void copyFile(ImageVo imageVo) {
        String name = Const.Flow.getFileName(imageVo.getFlow_id());
        File in = new File(UPLOAD_DIR+imageVo.getPic_path());
        File out = new File(TEMP_DIR+name+".png");
        try {
            FileCopyUtils.copy(in,out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportPhoto(List<ImageVo> photos,int projectId) throws IOException {
        Map<String,ImageVo> map=Maps.newHashMap();
        photos.forEach(imageVo -> {
            imageVo.setImagePath(UPLOAD_DIR+imageVo.getPic_path());
            map.put(imageVo.getFlow_id(),imageVo);
        });
        Project project = projectMapper.selectByPrimaryKey(projectId);
        if(project==null){
            return;
        }
        List<ImageVo> voList=Lists.newArrayList();
        log.info(project.toString());
        ImageVo imageVo=map.get("9");
//        imageVo.setName("1扇区("+project.getAzimuthCommunity1()+"°)");
        imageVo.setName("1扇区("+(int)NumberUtils.toDouble(project.getAzimuthCommunity1()+"")+"°)");
        voList.add(map.get("9"));
        log.info("2小区主方向照片");
        imageVo=map.get("10");
//        imageVo.setName("2扇区("+project.getAzimuthCommunity2()+"°)");
        imageVo.setName("1扇区("+(int)NumberUtils.toDouble(project.getAzimuthCommunity2()+"")+"°)");
        voList.add(imageVo);
        log.info("3小区主方向照片");
        imageVo=map.get("11");
//        imageVo.setName("3扇区("+project.getAzimuthCommunity3()+"°)");
        imageVo.setName("1扇区("+(int)NumberUtils.toDouble(project.getAzimuthCommunity3())+"°)");
        voList.add(imageVo);
        log.info("天面整体照片");
        imageVo=map.get("12");
        imageVo.setName("天面整体照片");
        voList.add(imageVo);
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("照片","主体照片"),
                ImageVo .class, voList);
        FileOutputStream fos = new FileOutputStream(TEMP_DIR+"photo.xls");
        workbook.write(fos);
        fos.close();
    }

    public void exportData(ExportVo exportVo) {

    }

    public static void main(String[] args) {
        System.out.println(NumberUtils.toDouble(""));
        System.out.println(NumberUtils.toDouble(null));
        System.out.println(NumberUtils.toDouble("0"));
        System.out.println(NumberUtils.toDouble("320.00"));
    }
}
