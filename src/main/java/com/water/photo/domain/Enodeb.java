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
@Table(name = "tab_enodeb")
public class Enodeb {
    @Id
    @GeneratedValue(generator = "JDBC")
    @Excel(name = "id")
    private int id;
    @Excel(name = "E-NODEB名称")
    private String name;
    @Excel(name = "网管中网元名称")
    private String netName;
    @Excel(name = "所属区县")
    private String areaName;
    @Excel(name = "DW_所属机房/资源点")
    private String addressName;
    @Excel(name = "E_NODEB_ID")
    private String enodebId;
    @Excel(name = "S1_IP地址")
    private String ip1;
    @Excel(name = "X2_IP地址")
    private String ip2;
    @Excel(name = "ESN")
    private String esn;
    @Excel(name = "生产厂家")
    private String factoryName;
    @Excel(name = "使用频段")
    private String spectrum;
    @Excel(name = "设备型号")
    private String deviceNo;
    @Excel(name = "跟踪区码TAC")
    private String tac;
    @Excel(name = "经度")
    private String longitude;
    @Excel(name = "纬度")
    private String latitude;
    @Excel(name = "工程期别")
    private String projectType;
    @Excel(name = "下挂RRU数量")
    private String rru;
    @Excel(name = "二维码")
    private String qrcode;



}
