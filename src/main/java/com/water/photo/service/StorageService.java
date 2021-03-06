package com.water.photo.service;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.guanweiming.common.ServerResponse;
import com.guanweiming.common.StringUtil;
import com.water.photo.common.Const;
import com.water.photo.domain.BaseStation;
import com.water.photo.domain.Data;
import com.water.photo.domain.Project;
import com.water.photo.mapper.BaseStationMapper;
import com.water.photo.mapper.DataMapper;
import com.water.photo.mapper.ProjectMapper;
import com.water.photo.vo.DeviceVo;
import com.water.photo.vo.ExportVo;
import com.water.photo.vo.ImageVo;
import com.water.photo.vo.OtherDataVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author chezhu.xin
 */
@Slf4j
@Service
public class StorageService {

    @Value("${spring.profiles.active}")
    private String run;

    public String UPLOAD_DIR() {
        if ("dev".equals(run)) {
            return "e:/upload/";
        } else {
            return "c:/upload/";
        }
    }

    public String TEMP_DIR(String projectName) {
        if ("dev".equals(run)) {
            if(StringUtil.isBlank(projectName)){
                return "e:/temp/";
            }
            return "e:/temp/" + projectName + "/";
        } else {
            if(StringUtil.isBlank(projectName)){
                return "c:/temp/";
            }
            return "c:/temp/" + projectName + "/";
        }
    }

    private final ProjectMapper projectMapper;
    private final BaseStationMapper baseStationMapper;
    private final DataMapper dataMapper;

    public StorageService(ProjectMapper projectMapper, BaseStationMapper baseStationMapper, DataMapper dataMapper) {
        this.projectMapper = projectMapper;
        this.baseStationMapper = baseStationMapper;
        this.dataMapper = dataMapper;
    }

    public ServerResponse<String> upload(MultipartFile file) throws IOException {
        String prefix = new SimpleDateFormat("yyyyMMddHHmmssSSSS").format(new Date());
        String fileName = prefix + "_" + file.getOriginalFilename();
        File save = new File(UPLOAD_DIR() + fileName);
        FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(save));
        return ServerResponse.createBySuccess(fileName);
    }

    public void showImage(HttpServletResponse response, String fileName) {
        response.setContentType("image/png");


        FileInputStream fis = null;
        OutputStream os = null;
        try {
            fis = new FileInputStream(UPLOAD_DIR() + fileName);
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

    public void copyFile(ImageVo imageVo, String projectName) {
        if (StringUtil.isBlank(projectName)) {
            projectName = "photo";
        }
        String name = Const.Flow.getFileName(imageVo.getFlow_id());
        File in = new File(UPLOAD_DIR() + imageVo.getPic_path());
        File photoDir = new File(TEMP_DIR(projectName) + "photos/");
        if (!photoDir.exists()) {
            photoDir.mkdirs();
        }
        File out = new File(TEMP_DIR(projectName) + "photos/" + name + ".png");
        try {
            FileCopyUtils.copy(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportPhoto(List<ImageVo> photos, int projectId, String stationName) throws IOException {
        Map<String, ImageVo> map = Maps.newHashMap();
        photos.forEach(imageVo -> {
            imageVo.setImagePath(UPLOAD_DIR() + imageVo.getPic_path());
            map.put(imageVo.getFlow_id(), imageVo);
        });
        Project project = projectMapper.selectByPrimaryKey(projectId);
        if (project == null) {
            return;
        }
        List<ImageVo> voList = Lists.newArrayList();
        log.info(project.toString());
        ImageVo imageVo = map.get("9");
        if (imageVo != null) {
            imageVo.setName("1扇区(" + (int) NumberUtils.toDouble(project.getAzimuthCommunity1() + "") + "°)");
            voList.add(map.get("9"));
        }

        log.info("2小区主方向照片");
        imageVo = map.get("10");
        if (imageVo != null) {
            imageVo.setName("2扇区(" + (int) NumberUtils.toDouble(project.getAzimuthCommunity2() + "") + "°)");
            voList.add(imageVo);
        }

        log.info("3小区主方向照片");
        imageVo = map.get("11");
        if (imageVo != null) {
            imageVo.setName("3扇区(" + (int) NumberUtils.toDouble(project.getAzimuthCommunity3()) + "°)");
            voList.add(imageVo);
        }

        log.info("天面整体照片");
        if (imageVo != null) {
            imageVo = map.get("12");
            imageVo.setName("天面整体照片");
            voList.add(imageVo);
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("照片", "主体照片"),
                ImageVo.class, voList);
        File fileDir = new File(TEMP_DIR(stationName));
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        File file = new File(TEMP_DIR(stationName) + "photo.xls");
        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        fos.close();
    }

    public String exportData(ExportVo exportVo) throws IOException {
        Project project = projectMapper.selectByPrimaryKey(NumberUtils.toInt(exportVo.getProject_id()));
        BaseStation baseStation = baseStationMapper.selectByPrimaryKey(NumberUtils.toInt(exportVo.getBbu_id()));

        if (project == null || baseStation == null) {
            return null;
        }
        String originSiteName = project.getOriginalSiteName();

        OtherDataVo otherDataVo = new OtherDataVo();
        BeanUtils.copyProperties(exportVo.getPtn_port(), otherDataVo);
        Workbook workbook = exportSheets(project, baseStation, exportVo.getDevices(), otherDataVo);
        File outputDir = new File(TEMP_DIR(originSiteName));
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(TEMP_DIR(originSiteName) + "设备数据.xls");
        workbook.write(fos);
        fos.close();
        return originSiteName;
    }

    private Workbook exportSheets(Project project, BaseStation baseStation, List<DeviceVo> deviceVoList, OtherDataVo otherDataVo) {
        // 查询数据,此处省略
        List<Project> list1 = new ArrayList<>();
        list1.add(project);
        List<BaseStation> list2 = new ArrayList<>();
        list2.add(baseStation);
        // 设置导出配置
        // 获取导出excel指定模版
        // 创建参数对象（用来设定excel得sheet得内容等信息）
        ExportParams params1 = new ExportParams();
        // 设置sheet得名称
        params1.setSheetName("工程数据");
        ExportParams params2 = new ExportParams();
        params2.setSheetName("基站数据");
        // 创建sheet1使用得map
        Map<String, Object> dataMap1 = new HashMap<>();
        // title的参数为ExportParams类型，目前仅仅在ExportParams中设置了sheetName
        dataMap1.put("title", params1);
        // 模版导出对应得实体类型
        dataMap1.put("entity", Project.class);
        // sheet中要填充得数据
        dataMap1.put("data", list1);
        // 创建sheet2使用得map
        Map<String, Object> dataMap2 = new HashMap<>();
        dataMap2.put("title", params2);
        dataMap2.put("entity", BaseStation.class);
        dataMap2.put("data", list2);

        ExportParams params3 = new ExportParams();
        params3.setSheetName("设备数据");
        Map<String, Object> dataMap3 = new HashMap<>();
        dataMap3.put("title", params3);
        dataMap3.put("entity", DeviceVo.class);
        dataMap3.put("data", deviceVoList);

        ExportParams params4 = new ExportParams();
        params4.setSheetName("其他数据");
        Map<String, Object> dataMap4 = new HashMap<>();
        dataMap4.put("title", params4);
        dataMap4.put("entity", OtherDataVo.class);
        dataMap4.put("data", Lists.newArrayList(otherDataVo));

        // 将sheet1和sheet2使用得map进行包装
        List<Map<String, Object>> sheetsList = new ArrayList<>();
        sheetsList.add(dataMap1);
        sheetsList.add(dataMap2);
        sheetsList.add(dataMap3);
        sheetsList.add(dataMap4);
        // 执行方法
        return ExcelExportUtil.exportExcel(sheetsList, ExcelType.HSSF);
    }

    public static void main(String[] args) {
        System.out.println(NumberUtils.toDouble(""));
        System.out.println(NumberUtils.toDouble(null));
        System.out.println(NumberUtils.toDouble("0"));
        System.out.println(NumberUtils.toDouble("320.00"));
    }

    public PageInfo<ExportVo> dataList(Integer page, Integer size, String name) {
        PageHelper.startPage(page, size);
        PageHelper.orderBy("id desc");
        List<Data> dataList = dataMapper.selectByName(name);
        PageInfo pageInfo=new PageInfo<>(dataList);

        List<ExportVo> exportVoList = Lists.newArrayList();
        Gson gson = new Gson();
        dataList.forEach(data -> {
            String str = data == null ? "{}" : data.getContent();
            if (data != null) {
                JSONObject object = new JSONObject(str);

                String content = object.getString("content");
                ExportVo exportVo = gson.fromJson(content == null ? "{}" : content, ExportVo.class);
                if(StringUtil.isBlank(data.getName())){
                    data.setName(getProjectName(exportVo.getProject_id()));
                    dataMapper.updateByPrimaryKey(data);
                }
                exportVo.setId(data.getId());
                exportVo.setProjectName(data.getName());
                exportVoList.add(exportVo);
            }
        });
        pageInfo.setList(exportVoList);
        return pageInfo;
    }

    public static final Cache<String, String> CACHE = CacheBuilder.newBuilder()
            .initialCapacity(100)
            .maximumSize(2000)
            .expireAfterAccess(10, TimeUnit.MINUTES)
            .build();

    private String getProjectName(String bbuId) {
        if (CACHE.getIfPresent(bbuId) != null) {
            return CACHE.getIfPresent(bbuId);
        }
//        BaseStation station = baseStationMapper.selectByPrimaryKey(NumberUtils.toInt(bbuId));
        Project project = projectMapper.selectByPrimaryKey(NumberUtils.toInt(bbuId));
        if(project==null){
            return null;
        }
        CACHE.put(bbuId, project.getOriginalSiteName());
        return CACHE.getIfPresent(bbuId);
    }


}
