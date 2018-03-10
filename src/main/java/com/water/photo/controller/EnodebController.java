package com.water.photo.controller;

import com.guanweiming.common.ServerResponse;
import com.water.photo.domain.Enodeb;
import com.water.photo.service.EnodebService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chezhu.xin
 */
@Api(description = "Enodeb接口")
@RequestMapping("enodeb")
@RestController
public class EnodebController {
    private final EnodebService enodebService;

    public EnodebController(EnodebService enodebService) {
        this.enodebService = enodebService;
    }

    @ApiOperation("获取所有Enodeb")
    @GetMapping("")
    public ServerResponse<List<Enodeb>> getDeviceList(
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "page", defaultValue = "1") Integer page) {
        return enodebService.getAll(page, size);
    }

}
