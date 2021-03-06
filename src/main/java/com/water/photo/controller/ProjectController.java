package com.water.photo.controller;

import com.guanweiming.common.ServerResponse;
import com.water.photo.domain.Project;
import com.water.photo.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chezhu.xin
 */
@Api(description = "项目接口")
@RequestMapping("project")
@RestController
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @ApiOperation("获取所有项目")
    @GetMapping("")
    public ServerResponse<List<Project>> getDeviceList(
            @ApiParam("地址模糊搜索关键词")@RequestParam(required = false) String address,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "page", defaultValue = "1") Integer page) {
        return projectService.getAll(page, size,address);
    }

}
