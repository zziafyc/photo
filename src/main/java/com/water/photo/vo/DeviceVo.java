package com.water.photo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * @author https://github.com/zziaguan/
 */
@Data
public class DeviceVo {
    @Excel(name = "序列")
    private String id;
    @Excel(name = "名称")
    private String name;
    @Excel(name = "数量")
    private String num;
    @Excel(name = "单位")
    private String unit;
}
