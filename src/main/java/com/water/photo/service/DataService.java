package com.water.photo.service;

import com.guanweiming.common.ServerResponse;
import com.water.photo.domain.Data;
import com.water.photo.mapper.DataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author chezhu.xin
 */
@Slf4j
@Service
public class DataService {
    private final DataMapper dataMapper;

    public DataService(DataMapper dataMapper) {
        this.dataMapper = dataMapper;
    }

    public ServerResponse<String> add(String content) {
        Data data=new Data();
        data.setContent(content);
        data.setCreateTime(new Date());
        int resultCount = dataMapper.insert(data);
        if(resultCount==0){
            return ServerResponse.createByErrorMessage("插入失败");
        }
        return ServerResponse.createBySuccess();
    }

    public Data get(int id) {
        return dataMapper.selectByPrimaryKey(id);
    }
}
