package com.water.photo.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author chezhu.xin
 */
@lombok.Data
@Table(name = "tab_data")
public class Data {
    @Id
    @GeneratedValue(generator = "JDBC")
    private int id;
    private String content;
    private String name;
    private Date createTime;
}
