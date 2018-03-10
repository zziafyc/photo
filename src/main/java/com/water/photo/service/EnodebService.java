package com.water.photo.service;

import com.github.pagehelper.PageHelper;
import com.guanweiming.common.ServerResponse;
import com.water.photo.domain.Enodeb;
import com.water.photo.mapper.EnodebMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chezhu.xin
 */
@Slf4j
@Service
public class EnodebService {
    private final EnodebMapper enodebMapper;

    public EnodebService(EnodebMapper enodebMapper) {
        this.enodebMapper = enodebMapper;
    }

    public ServerResponse<List<Enodeb>> getAll(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Enodeb> deviceList = enodebMapper.selectAll();
        return ServerResponse.createBySuccess(deviceList);
    }
}
