package com.mulesoft.studentManagement.exception;

import org.springframework.http.ResponseEntity;

import com.mulesoft.studentManagement.config.ResponseStructure;
import com.mulesoft.studentManagement.dto.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StudentIdNotFoundException extends RuntimeException {

	private String message;
}
