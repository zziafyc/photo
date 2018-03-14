package com.water.photo.vo;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @author chezhu.xin
 */
@Data
public class NavVo {
    private String title;
    private String url;
    private List<NavVo> child = Lists.newArrayList();

    public NavVo(String title, String url) {
        this.title = title;
        this.url = url;
    }
}
