package com.mulesoft.studentManagement.config;

import lombok.Data;

@Data
public class ResponseStructure<T> {

	private String message;
	private int status;
	private T data;
	
}
