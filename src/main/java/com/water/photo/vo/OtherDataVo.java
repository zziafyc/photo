package com.water.photo.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * @author chezhu.xin
 */
@Data
public class OtherDataVo {
    @Excel(name = "本端PTN 端口信息")
    private String thisPtn;
    @Excel(name = "上端PTN 端口信息")
    private String topPtn;
    @Excel(name = "备注")
    private String remark;
}
