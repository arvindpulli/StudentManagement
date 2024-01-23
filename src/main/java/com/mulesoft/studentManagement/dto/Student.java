package com.mulesoft.studentManagement.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
@Component
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int studentId;
	
	
	@NotBlank(message="studentName can't be blank")
	@NotNull(message="studentName can't be null")
	private String studentName;
	
	
	@NotBlank(message="studentAddress can't be blank")
	@NotNull(message="studentAddress can't be null")
	private String studentAddress;
	
	
	@Positive(message="phone number can't be zero")
	@Column(unique = true )
	@Max(9999999999L)@Min(6666666666L)
	private long studentPhoneNumber;
	
	
	@NotBlank(message="studentEmail can't be blank")
	@NotNull(message="studentEmail can't be null")
	@Column(unique=true)
	@Email
	private String studentEmail;
	
}
