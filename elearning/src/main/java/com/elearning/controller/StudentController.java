package com.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearning.constants.CommonConstants;
import com.elearning.dto.AssignmentsRequestDto;
import com.elearning.dto.EnrollmentRequestDto;
import com.elearning.dto.ProgressResponseDto;
import com.elearning.dto.QuizSubmissionRequestDto;
import com.elearning.entity.AssignmentSubmission;
import com.elearning.entity.Course;
import com.elearning.entity.Enrollment;
import com.elearning.entity.QuizSubmission;
import com.elearning.entity.Student;
import com.elearning.service.IStudent;

@RestController
@RequestMapping(CommonConstants.STUDENTS_COMMON_PATH)
public class StudentController {

    private IStudent iStudent;

    @Autowired
    public StudentController(IStudent iStudent) {
        this.iStudent = iStudent;
    }

    @PostMapping("/")
    public Student createStudent(@RequestBody Student student) {
        // System.out.println(course);
        Student createdStudent = iStudent.addStudent(student);
        return createdStudent;
    }

    @PostMapping("/enrollments")
    public ResponseEntity<String> enrollStudentToCourse(@RequestBody EnrollmentRequestDto enrollment) {
        Enrollment courseEnRolled = iStudent.enrollStudentToCourse(enrollment);
        if (courseEnRolled != null) {
            return new ResponseEntity<String>("Successfully created id::" + courseEnRolled.getId(),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Failed to enroll", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/quizzes")
    public ResponseEntity<String> quizStudentToCourse(@RequestBody QuizSubmissionRequestDto quizDto) {
        QuizSubmission quizSubmitted = iStudent.quizStudentToCourse(quizDto);
        if (quizSubmitted != null) {
            return new ResponseEntity<String>("Successfully created id::" + quizSubmitted.getId(),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Failed to add quiz details", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/assignments")
    public ResponseEntity<String> assignmentsStudentToCourse(@RequestBody AssignmentsRequestDto assignmentDto) {
        AssignmentSubmission assignmentSubmitted = iStudent.assignmentStudentToCourse(assignmentDto);
        if (assignmentSubmitted != null) {
            return new ResponseEntity<String>("Successfully created id::" + assignmentSubmitted.getId(),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Failed to add assignment details", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{studentId}/progress")
    public ProgressResponseDto getStudentProgress(@PathVariable("studentId") Integer studentId) {
        ProgressResponseDto progressResponseDto = iStudent.getStudentProgress(studentId);

        return progressResponseDto;

    }
}
