package com.elearning.service;

import java.util.List;

import com.elearning.entity.Course;
import com.elearning.entity.Module;

public interface ICourse {

    Course save(Course course);

    Course findByCourseId(Integer courseId);

    Module addModule(com.elearning.entity.Module module, Course course);

    List<Course> findAll();

}
