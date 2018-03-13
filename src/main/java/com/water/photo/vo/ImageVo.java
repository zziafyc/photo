package com.water.photo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * @author https://github.com/zziaguan/
 */
@Data
public class ImageVo {
    @Excel(name = "图片名称",width = 20)
    private String name;
    @Excel(name = "图片", type = 2, width = 40, height = 60)
    private String imagePath;
    private String flow_id;
    private String pic_path;
}
