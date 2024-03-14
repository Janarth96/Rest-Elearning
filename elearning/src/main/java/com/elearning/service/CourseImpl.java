package com.elearning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elearning.entity.Course;
import com.elearning.entity.Module;
import com.elearning.exception.CustomException;
import com.elearning.repository.CourseRepository;
import com.elearning.repository.ModuleRepository;

import jakarta.transaction.Transactional;

@Service
public class CourseImpl implements ICourse {

    private CourseRepository courseRepo;
    private ModuleRepository moduleRepo;

    @Autowired
    public CourseImpl(CourseRepository courseRepo, ModuleRepository moduleRepo) {
        this.courseRepo = courseRepo;
        this.moduleRepo = moduleRepo;
    }

    @Override
    public List<Course> findAll() {
        List<Course> allCourses = courseRepo.findAll();
        if (!allCourses.isEmpty()) {
            return allCourses;
        } else {
            throw new CustomException("No Data Available");
        }
    }

    @Override
    public Course findByCourseId(Integer courseId) {
        Optional<Course> course = courseRepo.findById(courseId);
        if (course.isPresent()) {
            return course.get();
        } else {
            throw new CustomException("Given course Id is not available, courseId::"+courseId);
        }

    }

    @Override
    @Transactional
    public Course save(Course course) {
        if (course != null) {
            Course addedCourse = courseRepo.save(course);
            return addedCourse;
        } else {
            throw new CustomException("Course should not be blank/empty/null");
        }
    }

    @Override
    @Transactional
    public Module addModule(Module module, Course course) {
        module.setCourse(course);
        Module addedModule = moduleRepo.save(module);
        return addedModule;
    }

}
