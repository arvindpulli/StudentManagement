package com.mulesoft.studentManagement.exception;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mulesoft.studentManagement.config.ResponseStructure;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;


@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		List<ObjectError> list=ex.getAllErrors();
		HashMap<String,String> hashmap=new HashMap<>();
		for(ObjectError error:list) {
			String message=error.getDefaultMessage();
			String fieldname=((FieldError)error).getField();
			hashmap.put(message, fieldname);
			
		}
		return new ResponseEntity<Object>(hashmap,HttpStatus.BAD_REQUEST);
		
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<Object>> HandleConstraintViolationException(ConstraintViolationException ex){
		ResponseStructure<Object>structure=new ResponseStructure<Object>();
		HashMap<String, String>hashmap=new HashMap<String, String>();
		
		for(ConstraintViolation<?>violation:ex.getConstraintViolations()) {
			String field=violation.getPropertyPath().toString();
			String msg=violation.getMessage();
			hashmap.put(field, msg);
		}
		structure.setMessage("Add Proper details");
		structure.setStatus(HttpStatus.FORBIDDEN.value());
		structure.setData(hashmap);
		return new ResponseEntity<ResponseStructure<Object>>(structure,HttpStatus.BAD_REQUEST);
		}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> StudentIdNotFound(StudentIdNotFoundException ex){
		ResponseStructure<String>structure=new ResponseStructure<String>();
		structure.setMessage("Id not found for Student");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
	}
}
