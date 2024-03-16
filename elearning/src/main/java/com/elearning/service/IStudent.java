package com.elearning.service;

import com.elearning.dto.AssignmentsRequestDto;
import com.elearning.dto.EnrollmentRequestDto;
import com.elearning.dto.ProgressResponseDto;
import com.elearning.dto.QuizSubmissionRequestDto;
import com.elearning.entity.AssignmentSubmission;
import com.elearning.entity.Enrollment;
import com.elearning.entity.QuizSubmission;
import com.elearning.entity.Student;

public interface IStudent {

    Student addStudent(Student student);

    Enrollment enrollStudentToCourse(EnrollmentRequestDto enrollment);

    QuizSubmission quizStudentToCourse(QuizSubmissionRequestDto quizDto);

    AssignmentSubmission assignmentStudentToCourse(AssignmentsRequestDto assignmentDto);

    ProgressResponseDto getStudentProgress(Integer studentId);
    
}
