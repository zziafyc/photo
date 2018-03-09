package com.water.photo;

import tk.mybatis.mapper.common.base.delete.DeleteByPrimaryKeyMapper;
import tk.mybatis.mapper.common.base.insert.InsertMapper;
import tk.mybatis.mapper.common.base.select.SelectAllMapper;
import tk.mybatis.mapper.common.base.select.SelectByPrimaryKeyMapper;
import tk.mybatis.mapper.common.base.update.UpdateByPrimaryKeyMapper;
import tk.mybatis.mapper.common.ids.SelectByIdsMapper;

/**
 * @author chezhu.xin
 */
public interface BaseMapper<T> extends
        InsertMapper<T>,
        DeleteByPrimaryKeyMapper<T>,
        UpdateByPrimaryKeyMapper<T>,
        SelectByPrimaryKeyMapper<T>,
        SelectByIdsMapper<T>,
        SelectAllMapper<T> {
}
