package com.mulesoft.studentManagement.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mulesoft.studentManagement.dto.Student;
import com.mulesoft.studentManagement.repo.StudentRepo;

@Repository  
public class StudentDao {

	@Autowired
	private StudentRepo repo;
	
	public Student saveStudent(Student s) {
		return repo.save(s);
	}
	
	public Student findStudent(int studentId) {
		Optional<Student> optionalStudent=repo.findById(studentId);
		if(optionalStudent.isPresent()) {
			return optionalStudent.get();
		}else {
			return null;
		}
	}
	
	public Student deleteStudent(int studentId) {
		Optional<Student> optionalStudent=repo.findById(studentId);
		if(optionalStudent.isPresent()) {
			repo.delete(optionalStudent.get());
			return optionalStudent.get();
		}else {
			return null;
		}
	}
	
	public Student updateStudent(Student s, int studentId) {
		Optional<Student> optionalStudent=repo.findById(studentId);
		if(optionalStudent.isPresent()) {
			s.setStudentId(studentId);
			return repo.save(s);
		}else {
			return null;
		}
	}
}
