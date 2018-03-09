package com.water.photo.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author chezhu.xin
 */
@Data
@Table(name = "tab_device")
public class Device {
    @Id
    @GeneratedValue(generator = "JDBC")
    @Excel(name = "序列")
    private int id;
    @Excel(name = "材料明细名称")
    private String name;
    @Excel(name = "数量")
    private String unit;
}
