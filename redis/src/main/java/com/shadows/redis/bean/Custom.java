package com.shadows.redis.bean;

import lombok.Data;

import java.util.Date;

@Data
public class Custom {
    private Long id;
    private String userId;
    private String idCardNo;
    private String password;
    private Date createTime;
    private String name;
    private String schoolName;
    private String schoolCode;
}
