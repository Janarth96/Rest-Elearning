package com.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elearning.entity.Enrollment;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer>{
    
}
