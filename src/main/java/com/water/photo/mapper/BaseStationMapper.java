package com.water.photo.mapper;

import com.water.photo.BaseMapper;
import com.water.photo.domain.BaseStation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chezhu.xin
 */
public interface BaseStationMapper extends BaseMapper<BaseStation> {
    List<BaseStation> selectByAddress(@Param("address") String address);
}
