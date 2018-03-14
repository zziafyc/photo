package com.water.photo.mapper;

import com.water.photo.BaseMapper;
import com.water.photo.domain.Data;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chezhu.xin
 */
public interface DataMapper extends BaseMapper<Data> {
    List<Data> selectByName(@Param("name") String name);
}
