package com.water.photo.controller;

import com.guanweiming.common.ServerResponse;
import com.water.photo.domain.Device;
import com.water.photo.service.DeviceService;
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
@Api(description = "设备接口")
@RequestMapping("device")
@RestController
public class DeviceController {
    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @ApiOperation("获取所有设备信息")
    @GetMapping("")
    public ServerResponse<List<Device>> getDeviceList(
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "page", defaultValue = "1") Integer page) {
        return deviceService.getAllDevice(page, size);
    }

}
