package com.water.photo.controller;

import com.guanweiming.common.ServerResponse;
import com.water.photo.domain.BaseStation;
import com.water.photo.service.BaseStationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chezhu.xin
 */
@Api(description = "基站接口")
@RequestMapping("station")
@RestController
public class BaseStationController {
    private final BaseStationService baseStationService;

    public BaseStationController(BaseStationService baseStationService) {
        this.baseStationService = baseStationService;
    }

    @ApiOperation("搜索基站")
    @GetMapping("")
    public ServerResponse<List<BaseStation>> getStationList(
            @ApiParam("地址模糊搜索关键词")@RequestParam(required = false) String address,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "page", defaultValue = "1") Integer page) {
        return baseStationService.getStationList(page, size,address);
    }
}
