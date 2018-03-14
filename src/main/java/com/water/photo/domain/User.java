package com.water.photo.domain;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author chezhu.xin
 */
@Data
@Table(name = "tab_user")
public class User {
    @Id
    private String id;
    private String remark;
    private int status;
    private Date createTime;
}
