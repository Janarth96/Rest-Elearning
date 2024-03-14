package com.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
    
}
