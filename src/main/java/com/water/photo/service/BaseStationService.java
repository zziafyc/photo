package com.water.photo.service;

import com.github.pagehelper.PageHelper;
import com.guanweiming.common.ServerResponse;
import com.water.photo.domain.BaseStation;
import com.water.photo.mapper.BaseStationMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chezhu.xin
 */
@Service
public class BaseStationService {
    private final BaseStationMapper baseStationMapper;

    public BaseStationService(BaseStationMapper baseStationMapper) {
        this.baseStationMapper = baseStationMapper;
    }

    public ServerResponse<List<BaseStation>> getStationList(Integer page, Integer size, String address) {
        PageHelper.startPage(page,size);
        List<BaseStation> stationList = baseStationMapper.selectByAddress(address);
        stationList.forEach(baseStation -> baseStation.setQrcode(baseStation.getQrcode()==null?null:baseStation.getQrcode().trim()));
        return ServerResponse.createBySuccess(stationList);
    }
}
