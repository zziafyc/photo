package com.water.photo.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author chezhu.xin
 */
@Data
@Table(name = "tab_project")
public class Project {
    @Id
    @GeneratedValue(generator = "JDBC")
    @Excel(name = "id")
    @ApiModelProperty("id")
    private int id;
    @Excel(name = "区县", orderNum = "2")
    @ApiModelProperty("id")
    private String county;
    @Excel(name = "主体工程归属")
    @ApiModelProperty("主体工程归属")
    private String mainProjectAttribute;
    @Excel(name = "原始规划站名")
    @ApiModelProperty("原始规划站名")
    private String originalSiteName;
    @Excel(name = "标准站名")
    @ApiModelProperty("标准站名")
    private String standardSiteName;
    @Excel(name = "共址LTE基站名称")
    @ApiModelProperty("共址LTE基站名称")
    private String siteName;
    @Excel(name = "是否加基带板")
    @ApiModelProperty("是否加基带板")
    private String baseband;
    @Excel(name = "IP地址")
    @ApiModelProperty("IP地址")
    private String ip;
    @Excel(name = "ESN序列号")
    @ApiModelProperty("ESN序列号")
    private String esnNo;
    @Excel(name = "天馈建设方式")
    @ApiModelProperty("天馈建设方式")
    private String buildMethod;
    @Excel(name = "利旧/新增支臂数量及平台数量")
    @ApiModelProperty("利旧/新增支臂数量及平台数量")
    private String numPlatform;
    @Excel(name = "经度")
    @ApiModelProperty("经度")
    private String longitude;
    @Excel(name = "纬度")
    @ApiModelProperty("纬度")
    private String latitude;
    @Excel(name = "小区数量")
    @ApiModelProperty("小区数量")
    private Integer numCommunity;
    @Excel(name = "挂高")
    @ApiModelProperty("挂高")
    private Double height;
    @Excel(name = "小区1方位角")
    @ApiModelProperty("小区1方位角")
    private Double azimuthCommunity1;
    @Excel(name = "小区2方位角")
    @ApiModelProperty("小区2方位角")
    private Double azimuthCommunity2;
    @Excel(name = "小区3方位角")
    @ApiModelProperty("小区3方位角")
    private String azimuthCommunity3;
    @Excel(name = "频段")
    @ApiModelProperty("频段")
    private String spectrum;
    @Excel(name = "天线类型")
    @ApiModelProperty("天线类型")
    private String antennaType;
}
