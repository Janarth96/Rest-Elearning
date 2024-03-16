package com.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.elearning.entity.QuizSubmission;
import com.elearning.entity.Student;

@Repository
public interface QuizRepository extends JpaRepository<QuizSubmission, Integer>{

    List<QuizSubmission> findByStudentId(Integer studentId);
    
}
