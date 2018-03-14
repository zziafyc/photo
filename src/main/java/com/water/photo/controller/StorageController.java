package com.water.photo.controller;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.guanweiming.common.ServerResponse;
import com.water.photo.common.DataRequest;
import com.water.photo.common.DataResponse;
import com.water.photo.common.FileUtil;
import com.water.photo.common.ZipUtils;
import com.water.photo.domain.Data;
import com.water.photo.service.DataService;
import com.water.photo.service.StorageService;
import com.water.photo.vo.ExportVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 关
 */
@Slf4j
@Api(description = "上传文件接口")
@RestController
@RequestMapping("storage")
public class StorageController {


    private final StorageService storageService;
    private final DataService dataService;

    public StorageController(StorageService storageService, DataService dataService) {
        this.storageService = storageService;
        this.dataService = dataService;
    }

    @PostMapping("upload")
    public ServerResponse<String> upload(MultipartFile file) throws IOException {
        if (file == null) {
            return ServerResponse.createByErrorMessage("文件不能为空");
        }
        return storageService.upload(file);
    }

    @GetMapping("{fileName:.+}")
    public void showImage(HttpServletResponse response, @PathVariable("fileName") String fileName) {
        storageService.showImage(response, fileName);
    }

    @GetMapping("export")
    public void exportZip(HttpServletResponse response, int id) throws IOException {
        Data data = dataService.get(id);
        String str = data == null ? "{}" : data.getContent();
        JSONObject object = new JSONObject(str);
        Gson gson = new Gson();
        String content = object.getString("content");
        ExportVo exportVo = gson.fromJson(content == null ? "{}" : content, ExportVo.class);
        log.info(exportVo.toString());
        String originSiteName = storageService.exportData(exportVo);
        storageService.exportPhoto(exportVo.getPhotos(), NumberUtils.toInt(exportVo.getProject_id()), originSiteName);
        exportVo.getPhotos().forEach(photo -> storageService.copyFile(photo, originSiteName));
        ZipUtils.toZip(storageService.TEMP_DIR(originSiteName), response.getOutputStream(), true);
        FileUtil.deleteDir(storageService.TEMP_DIR(originSiteName));
    }

    @ApiOperation("设备数据")
    @GetMapping("data")
    public DataResponse<ExportVo> dataList(DataRequest request,String name) {
        PageInfo<ExportVo> page = storageService.dataList(request.getPage(), request.getSize(),name);
        return new DataResponse<>(page, request.getDraw());
    }

    @GetMapping("content")
    public String getContent(int id) {
        Data data = dataService.get(id);
        String str = data == null ? "{}" : data.getContent();
        log.debug(str);
        JSONObject object = new JSONObject(str);
        Gson gson = new Gson();
        String content = object.getString("content");
        ExportVo exportVo = gson.fromJson(content == null ? "{}" : content, ExportVo.class);
        log.debug(exportVo.toString());
        return content;
    }


}
