package com.mulesoft.studentManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mulesoft.studentManagement.config.ResponseStructure;
import com.mulesoft.studentManagement.dto.Student;
import com.mulesoft.studentManagement.service.StudentService;

@RestController 
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService service;
	
	@PostMapping
	ResponseEntity<ResponseStructure<Student>> saveStudent(@RequestBody Student s){
		return service.saveStudent(s);
	}   
	
	@GetMapping
	ResponseEntity<ResponseStructure<Student>> findStudent(@RequestParam int studentId){
		return service.findStudent(studentId);
	}
	
	@DeleteMapping
	ResponseEntity<ResponseStructure<Student>> deleteStudent(@RequestParam int studentId){
		return service.deleteStudent(studentId);
	}
	
	@PutMapping
	ResponseEntity<ResponseStructure<Student>> updateStudent(@RequestBody Student s, @RequestParam int studentId){
		return service.updateStudent(s, studentId);
	}
} 
