package com.elearning.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elearning.dto.EnrollmentRequestDto;
import com.elearning.entity.Course;
import com.elearning.entity.Enrollment;
import com.elearning.entity.Student;
import com.elearning.exception.CustomException;
import com.elearning.repository.EnrollmentRepository;
import com.elearning.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class StudentImpl implements IStudent {

    private StudentRepository studentRepo;
    private ICourse iCourse;
    private EnrollmentRepository enrollmentdRepo;

    @Autowired
    public StudentImpl(StudentRepository studentRepository, ICourse iCourse, EnrollmentRepository enrollmentdRepo) {
        this.studentRepo = studentRepository;
        this.iCourse = iCourse;
        this.enrollmentdRepo = enrollmentdRepo;
    }

    @Override
    @Transactional
    public Student addStudent(Student student) {
        Student addedStudent = studentRepo.save(student);
        return addedStudent;
    }

    public Student findByStudentId(Integer stutentId) {
        Optional<Student> studentDetail = studentRepo.findById(stutentId);
        if (studentDetail.isPresent()) {
            return studentDetail.get();
        } else {
            throw new CustomException("Given student Id is not available, studentId::" + stutentId);
        }
    }

    @Override
    @Transactional
    public Enrollment enRollStudentToCourse(EnrollmentRequestDto enrollmentdDto) {
        Enrollment enrollment = new Enrollment();
        Course courseDetail = iCourse.findByCourseId(enrollmentdDto.getCourseID());
        Student studentDetail = this.findByStudentId(enrollmentdDto.getStudentId());
        enrollment.setCourse(courseDetail);
        enrollment.setStudent(studentDetail);
        Enrollment addedEnrollment = enrollmentdRepo.save(enrollment);
        return addedEnrollment;
    }

}
