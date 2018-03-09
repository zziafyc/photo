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
@Table(name = "tab_base_station")
public class BaseStation {
    @Id
    @GeneratedValue(generator = "JDBC")
    @Excel(name = "id")
    private int id;
    @Excel(name = "装机位置编号")
    private String  addressNo;
    @Excel(name = "所属机房")
    private String room;
    @Excel(name = "二维码")
    private String qrcode;
}
