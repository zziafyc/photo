package com.water.photo.service;

import com.water.photo.mapper.BaseStationMapper;
import com.water.photo.mapper.DeviceMapper;
import com.water.photo.mapper.EnodebMapper;
import com.water.photo.mapper.ProjectMapper;
import org.springframework.stereotype.Service;

/**
 * @author chezhu.xin
 */
@Service
public class PhotoService {
    private final BaseStationMapper baseStationMapper;
    private final DeviceMapper deviceMapper;
    private final EnodebMapper enodebMapper;
    private final ProjectMapper projectMapper;

    public PhotoService(BaseStationMapper baseStationMapper, DeviceMapper deviceMapper, EnodebMapper enodebMapper, ProjectMapper projectMapper) {
        this.baseStationMapper = baseStationMapper;
        this.deviceMapper = deviceMapper;
        this.enodebMapper = enodebMapper;
        this.projectMapper = projectMapper;
    }
}
