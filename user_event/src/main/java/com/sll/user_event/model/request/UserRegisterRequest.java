package com.sll.user_event.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRegisterRequest implements Serializable {
    private String userAccount;

    private String userPassword;

    private String checkPassword;
}
