package com.elearning.controller;

import org.springframework.web.bind.annotation.RestController;

import com.elearning.constants.CommonConstants;
import com.elearning.entity.Course;
import com.elearning.entity.Module;
import com.elearning.exception.CustomException;
import com.elearning.service.ICourse;

import jakarta.validation.constraints.NotEmpty;
import jakarta.websocket.server.PathParam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(CommonConstants.COUSES_COMMON_PATH)
public class CourseController {

    private ICourse iCourse;

    @Autowired
    public CourseController(ICourse iCourse) {
        this.iCourse = iCourse;
    }

    @GetMapping("/")
    public List<Course> getMethodName() {
        return iCourse.findAll();
    }

    @PostMapping("/")
    public Course addCourse(@RequestBody Course course) {
        // System.out.println(course);
        Course addedCourse = iCourse.save(course);
        return addedCourse;

    }

    @PostMapping("/{courseId}/modules")
    public Module addModuleToCourse(@RequestBody Module module, @PathVariable("courseId") Integer courseId) {
        Course course = iCourse.findByCourseId(courseId);
        if (course != null) {
            Module addedModule = iCourse.addModule(module, course);
            return addedModule;
        }else {
            throw new CustomException("Given course Id is not available, courseId::"+courseId);
        }

    }

}
