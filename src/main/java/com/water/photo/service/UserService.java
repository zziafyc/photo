package com.water.photo.service;

import com.guanweiming.common.ServerResponse;
import com.guanweiming.common.StringUtil;
import com.water.photo.domain.User;
import com.water.photo.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author chezhu.xin
 */
@Service
public class UserService {
    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public ServerResponse<String> login(String id, String remark) {
        User record = userMapper.selectByPrimaryKey(id);
        if (record == null) {
            record = new User();
            record.setId(id);
            record.setRemark(remark);
            record.setCreateTime(new Date());
            userMapper.insert(record);
            return ServerResponse.createByErrorMessage("登录失败");
        }
        if (StringUtil.isNotBlank(remark)) {
            record.setRemark(remark);
            userMapper.updateByPrimaryKey(record);
        }

        return record.getStatus() == 0 ? ServerResponse.createByErrorMessage("待审核") : ServerResponse.createBySuccess("登录成功");
    }
}
