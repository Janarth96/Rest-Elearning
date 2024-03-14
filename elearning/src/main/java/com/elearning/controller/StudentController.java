package com.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearning.constants.CommonConstants;
import com.elearning.dto.EnrollmentRequestDto;
import com.elearning.entity.Course;
import com.elearning.entity.Enrollment;
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
    public ResponseEntity<String> enRollStudentToCourse(@RequestBody EnrollmentRequestDto enrollment) {
        Enrollment courseEnRolled = iStudent.enRollStudentToCourse(enrollment);
        if (courseEnRolled != null) {
            return new ResponseEntity<String>("Successfully created id::" + courseEnRolled.getId(),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Failed to enroll", HttpStatus.BAD_REQUEST);

    }
}
