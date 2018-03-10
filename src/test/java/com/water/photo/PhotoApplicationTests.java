package com.water.photo;

import com.water.photo.mapper.BaseStationMapper;
import com.water.photo.mapper.DeviceMapper;
import com.water.photo.mapper.EnodebMapper;
import com.water.photo.mapper.ProjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class PhotoApplicationTests {

    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private EnodebMapper enodebMapper;
    @Autowired
    private BaseStationMapper baseStationMapper;
    @Autowired
    private DeviceMapper deviceMapper;

    @Test
    public void contextLoads() {
    }

//    @Test
//    public void test2() {
//        ImportParams params = new ImportParams();
//        params.setTitleRows(0);
//        params.setHeadRows(1);
//        List<Project> list = ExcelImportUtil.importExcel(
//                new File("/D:/水印照片/工程数据.xlsx"),
//                Project.class, params);
//        list.forEach(project -> projectMapper.insert(project));
//        System.out.println(list.size());
//        System.out.println(list.get(0));
//        System.out.println(list.get(1));
//    }
//    @Test
//    public void test3() {
//        ImportParams params = new ImportParams();
//        params.setTitleRows(0);
//        params.setHeadRows(1);
//        List<Enodeb> list = ExcelImportUtil.importExcel(
//                new File("/D:/水印照片/ENODEB信息表.xlsx"),
//                Enodeb.class, params);
//        list.forEach(enodeb -> enodebMapper.insert(enodeb));
//    }
//    @Test
//    public void test4() {
//        ImportParams params = new ImportParams();
//        params.setTitleRows(0);
//        params.setHeadRows(1);
//        List<BaseStation> list = ExcelImportUtil.importExcel(
//                new File("/D:/水印照片/基站数据.xlsx"),
//                BaseStation.class, params);
//        list.forEach(station -> {
//            baseStationMapper.insert(station);
//            log.info("insert:\t"+station.toString());
//        });
//    }
//    @Test
//    public void test5() {
//        ImportParams params = new ImportParams();
//        params.setTitleRows(0);
//        params.setHeadRows(1);
//        List<Device> list = ExcelImportUtil.importExcel(
//                new File("/D:/水印照片/设备数据.xlsx"),
//                Device.class, params);
//        list.forEach(device -> {
//            deviceMapper.insert(device);
//            log.info("insert:\t"+device.toString());
//        });
//    }

}
