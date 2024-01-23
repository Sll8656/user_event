package com.sll.user_event.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;

    private String userAccount;

    private String avatarURL;

    private Integer gender;

    private String userPassword;

    private String phone;

    private String email;

    private Integer userStatus;

    private Date createTime;

    private Date updateTime;

    @TableLogic
    private Integer isDelete;

    private Integer userRole;

    private String planetCode;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}