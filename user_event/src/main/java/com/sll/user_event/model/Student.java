package com.sll.user_event.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("student")
public class Student {
    private Integer id;
    private Integer age;
    private String name;
}
