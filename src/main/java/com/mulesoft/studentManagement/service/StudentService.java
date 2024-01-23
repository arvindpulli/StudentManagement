package com.mulesoft.studentManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mulesoft.studentManagement.config.ResponseStructure;
import com.mulesoft.studentManagement.dao.StudentDao;
import com.mulesoft.studentManagement.dto.Student;
import com.mulesoft.studentManagement.exception.StudentIdNotFoundException;

@Service
public class StudentService {

	@Autowired
	 private StudentDao dao;
 
	public ResponseEntity<ResponseStructure<Student>> saveStudent(Student s){
		ResponseStructure<Student> structure=new ResponseStructure<Student>();
		structure.setData(dao.saveStudent(s));
		structure.setMessage("Student has been saved");
		structure.setStatus(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Student>> findStudent(int studentId){
		Student student=dao.findStudent(studentId);
		if(student!=null) {
			ResponseStructure<Student> structure=new ResponseStructure<Student>();
			structure.setData(student);
			structure.setMessage("Student found successfully !");
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.FOUND);
		}else {
			throw new StudentIdNotFoundException("Student Id Not Found, Enter Valid Id");
		}
	}
	
	public ResponseEntity<ResponseStructure<Student>> deleteStudent(int studentId){
		Student deleteStudent=dao.deleteStudent(studentId);
		if(deleteStudent!=null) {
			ResponseStructure<Student> structure=new ResponseStructure<Student>();
			structure.setData(deleteStudent);
			structure.setMessage("Student Removed Successfully !");
			structure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.OK);
		}else {
			throw new StudentIdNotFoundException("Student Id Not Found, Enter Valid Id");
		}
	}
	
	public ResponseEntity<ResponseStructure<Student>> updateStudent(Student updatedStudent, int studentId){
		Student existingStudent=dao.findStudent(studentId);
		if(existingStudent!=null) {
			if(updatedStudent.getStudentName()==null) {
				updatedStudent.setStudentName(existingStudent.getStudentName());
			}
			if(updatedStudent.getStudentAddress()==null) {
				updatedStudent.setStudentAddress(existingStudent.getStudentAddress());
			}
			if(updatedStudent.getStudentEmail()==null) {
				updatedStudent.setStudentEmail(existingStudent.getStudentEmail());
			}
			if(updatedStudent.getStudentPhoneNumber()<=0) {
				updatedStudent.setStudentPhoneNumber(existingStudent.getStudentPhoneNumber());
			}		
		}else {
			throw new StudentIdNotFoundException("Student Id Not Found, Enter Valid Id");
		}
		ResponseStructure<Student> structure=new ResponseStructure<Student>();
		structure.setData(dao.updateStudent(updatedStudent, studentId));
		structure.setMessage("Student updated successfully !");
		structure.setStatus(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Student>>(structure,HttpStatus.OK);
	}
} 
