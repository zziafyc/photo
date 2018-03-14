package com.water.photo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guanweiming.common.ServerResponse;
import com.guanweiming.common.StringUtil;
import com.water.photo.domain.User;
import com.water.photo.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    public PageInfo<User> userList(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        PageHelper.orderBy("create_time desc");
        List<User> userList = userMapper.selectAll();
        return new PageInfo<>(userList);
    }

    public User getUser(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public ServerResponse<String> delete(String id) {
        int resultCount = userMapper.deleteByPrimaryKey(id);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("删除失败");
        }
        return ServerResponse.createBySuccess();
    }

    public ServerResponse<String> update(String id, int status) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            return ServerResponse.createByErrorMessage("未查找到该用户");
        }
        user.setStatus(status);
        int resultCount = userMapper.updateByPrimaryKey(user);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("删除失败");
        }
        return ServerResponse.createBySuccess();
    }
}
