package com.water.photo.service;

import com.github.pagehelper.PageHelper;
import com.guanweiming.common.ServerResponse;
import com.water.photo.domain.Project;
import com.water.photo.mapper.ProjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chezhu.xin
 */
@Slf4j
@Service
public class ProjectService {
    private final ProjectMapper projectMapper;

    public ProjectService(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    public ServerResponse<List<Project>> getAll(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<Project> deviceList = projectMapper.selectAll();
        return ServerResponse.createBySuccess(deviceList);
    }
}
