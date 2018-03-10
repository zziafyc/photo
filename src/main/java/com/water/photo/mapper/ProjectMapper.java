package com.water.photo.mapper;

import com.water.photo.BaseMapper;
import com.water.photo.domain.Project;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chezhu.xin
 */
public interface ProjectMapper extends BaseMapper<Project> {
    List<Project> selectByOriginName(@Param("address") String address);
}
