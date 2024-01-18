package com.sll.user_event.controller;

import com.sll.user_event.mapper.StudentMapper;
import com.sll.user_event.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentMapper studentMapper;

    @GetMapping("/user")
    public String se() {
        List<Student> students = studentMapper.selectList(null);
        students.forEach(System.out::println);
        return "123";
    }
}
