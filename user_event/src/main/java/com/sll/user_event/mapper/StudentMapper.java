package com.sll.user_event.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sll.user_event.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    @Select("select * from student")
    List<Student> find1();
}
