package com.water.photo.common;

import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * @author chezhu.xin
 */
@Data
public class DataRequest {
    @ApiParam(name = "length", required = true)
    private int length = 10;
    @ApiParam(name = "start", required = true)
    private int start = 10;
    @ApiParam(name = "draw", required = true)
    private int draw;

    public Integer getPage() {
        return start / length + 1;
    }

    public Integer getSize() {
        return length;
    }
}
