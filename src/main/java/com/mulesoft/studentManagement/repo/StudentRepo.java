package com.mulesoft.studentManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mulesoft.studentManagement.dto.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {

}
