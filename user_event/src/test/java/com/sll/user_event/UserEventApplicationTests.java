package com.sll.user_event;

import com.sll.user_event.mapper.StudentMapper;
import com.sll.user_event.model.Student;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserEventApplicationTests {

	@Autowired
	private StudentMapper studentMapper;

	@Test
	void contextLoads() {
	}

	@Test
	public void testSelect() {
		System.out.println("sssssssss");

//		Student student = studentMapper.selectById(1);
//
//		System.out.println(student);

		List<Student> students = studentMapper.find1();
//		Assert.assertEquals(5,students.size());
		Assert.assertEquals(16,students.size());
		students.forEach(System.out::println);
	}
}
