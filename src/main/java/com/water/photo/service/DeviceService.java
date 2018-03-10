package com.water.photo.service;

import com.github.pagehelper.PageHelper;
import com.guanweiming.common.ServerResponse;
import com.water.photo.domain.Device;
import com.water.photo.mapper.DeviceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chezhu.xin
 */
@Slf4j
@Service
public class DeviceService {
    private final DeviceMapper deviceMapper;

    public DeviceService(DeviceMapper deviceMapper) {
        this.deviceMapper = deviceMapper;
    }

    public ServerResponse<List<Device>> getAllDevice(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<Device> deviceList = deviceMapper.selectAll();
        return ServerResponse.createBySuccess(deviceList);
    }
}
