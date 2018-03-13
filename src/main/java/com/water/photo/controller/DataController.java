package com.water.photo.controller;

import com.guanweiming.common.ServerResponse;
import com.water.photo.service.DataService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chezhu.xin
 */
@Api(description = "数据接口")
@RequestMapping("data")
@RestController
public class DataController {

    private final DataService dataService;

    public DataController(DataService dataService) {
        this.dataService = dataService;
    }

    @PostMapping("add")
    public ServerResponse<String> saveData(@RequestBody String content){
        return dataService.add(content);
    }
}
