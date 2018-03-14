package com.water.photo.vo;

import lombok.Data;

import java.util.List;

/**
 * @author chezhu.xin
 */
@Data
public class ExportVo {
    private String project_id;
    private PortVo ptn_port;
    private String bbu_id;

    private List<ImageVo> photos;
    private List<DeviceVo> devices;

    private String projectName;
    private int id;
}
