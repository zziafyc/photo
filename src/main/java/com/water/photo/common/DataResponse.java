package com.water.photo.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.pagehelper.PageInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author chezhu.xin
 */
@Slf4j
@Data
public class DataResponse<T> {
    private long draw;
    private long recordsTotal;
    private long recordsFiltered;
    private List<T> data;
    private String error;
    @JsonIgnore
    private PageInfo<T> pageInfo;

    public DataResponse(PageInfo<T> pageInfo, long draw) {
        this.draw = draw;
        recordsFiltered = pageInfo.getTotal();
        recordsTotal = pageInfo.getTotal();
        data = pageInfo.getList();
        this.pageInfo = pageInfo;
    }

    public long getDraw() {
        return draw;
    }

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public long getRecordsFiltered() {
        return recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public String getError() {
        return error;
    }
}
