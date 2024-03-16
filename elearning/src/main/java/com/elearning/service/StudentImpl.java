package com.elearning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elearning.dto.AssignmentsRequestDto;
import com.elearning.dto.EnrollmentRequestDto;
import com.elearning.dto.ProgressResponseDto;
import com.elearning.dto.QuizSubmissionRequestDto;
import com.elearning.entity.AssignmentSubmission;
import com.elearning.entity.Course;
import com.elearning.entity.Enrollment;
import com.elearning.entity.QuizSubmission;
import com.elearning.entity.Student;
import com.elearning.exception.CustomException;
import com.elearning.repository.AssignmentRepository;
import com.elearning.repository.EnrollmentRepository;
import com.elearning.repository.QuizRepository;
import com.elearning.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class StudentImpl implements IStudent {

    private StudentRepository studentRepo;
    private ICourse iCourse;
    private EnrollmentRepository enrollmentdRepo;
    private QuizRepository quizRepo;
    private AssignmentRepository assignmentRepo;

    @Autowired
    public StudentImpl(StudentRepository studentRepository, ICourse iCourse, EnrollmentRepository enrollmentdRepo,
            QuizRepository quizRepo, AssignmentRepository assignmentRepo) {
        this.studentRepo = studentRepository;
        this.iCourse = iCourse;
        this.enrollmentdRepo = enrollmentdRepo;
        this.quizRepo = quizRepo;
        this.assignmentRepo = assignmentRepo;
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
    public Enrollment enrollStudentToCourse(EnrollmentRequestDto enrollmentdDto) {
        Enrollment enrollment = new Enrollment();
        Course courseDetail = iCourse.findByCourseId(enrollmentdDto.getCourseID());
        Student studentDetail = this.findByStudentId(enrollmentdDto.getStudentId());
        enrollment.setCourse(courseDetail);
        enrollment.setStudent(studentDetail);
        Enrollment addedEnrollment = enrollmentdRepo.save(enrollment);
        return addedEnrollment;
    }

    @Override
    @Transactional
    public QuizSubmission quizStudentToCourse(QuizSubmissionRequestDto quizDto) {
        QuizSubmission quiz = new QuizSubmission();
        Course courseDetail = iCourse.findByCourseId(quizDto.getCourseID());
        Student studentDetail = this.findByStudentId(quizDto.getStudentId());
        quiz.setCourse(courseDetail);
        quiz.setStudent(studentDetail);
        QuizSubmission addedQuizSubmission = quizRepo.save(quiz);
        return addedQuizSubmission;
    }

    @Override
    public AssignmentSubmission assignmentStudentToCourse(AssignmentsRequestDto assignmentDto) {
        AssignmentSubmission assignment = new AssignmentSubmission();
        Course courseDetail = iCourse.findByCourseId(assignmentDto.getCourseID());
        Student studentDetail = this.findByStudentId(assignmentDto.getStudentId());
        assignment.setCourse(courseDetail);
        assignment.setStudent(studentDetail);
        AssignmentSubmission assignmentSubmitted = assignmentRepo.save(assignment);
        return assignmentSubmitted;
    }

    @Override
    public ProgressResponseDto getStudentProgress(Integer studentId) {
        ProgressResponseDto progressResponseDto = new ProgressResponseDto();
        Student studentDetail = studentRepo.findById(studentId).orElse(new Student());
        if (studentDetail.getId() == null)
            throw new CustomException("Student details not available, studentId::" + studentId);
        List<AssignmentSubmission> assignmentsMapped = assignmentRepo.findByStudentId(studentId);
        List<QuizSubmission> quizzesMapped = quizRepo.findByStudentId(studentId);
        progressResponseDto.setStudentId(studentDetail.getId());
        progressResponseDto.setQuizzesMapped(quizzesMapped);
        progressResponseDto.setAssignmentsMapped(assignmentsMapped);
        return progressResponseDto;
    }

}
