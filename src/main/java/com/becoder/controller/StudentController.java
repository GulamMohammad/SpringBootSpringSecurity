package com.becoder.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.becoder.model.Student;

@RestController
public class StudentController {
	
	@GetMapping("/students")
	public List<Student> getAllStudent(){
		
		return List.of(
				new Student(1,"Usman",60),
				new Student(2,"Mariyam",98),
				new Student(3,"Raja",70),
				new Student(4,"Parvej",65),
				new Student(5,"Noor Alam",80)
				);
	}
	
	@PostMapping("/addStudent")
	public Student addStudent(@RequestBody Student student) {
		
		return student;
	}

}
