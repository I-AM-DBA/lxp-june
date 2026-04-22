package com.lxp.jdbc.course.core.service;

import com.lxp.jdbc.course.core.model.CourseCreateRequest;
import com.lxp.jdbc.course.core.model.CourseResponse;
import com.lxp.jdbc.course.core.model.CourseUpdateRequest;
import com.lxp.jdbc.course.core.repository.CourseRepository;

import java.sql.SQLException;
import java.util.List;

public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void createCourse(CourseCreateRequest request) throws SQLException {
        courseRepository.create(request);
    }

    public CourseResponse getCourse(Long courseId) throws SQLException {
        return courseRepository.findById(courseId);
    }

    public List<CourseResponse> getActiveCourses() throws SQLException {
        return courseRepository.findAllActive();
    }

    public boolean updateCourse(CourseUpdateRequest request) throws SQLException {
        return courseRepository.update(request) > 0;
    }

    public boolean softDeleteCourse(Long courseId) throws SQLException {
        return courseRepository.softDelete(courseId) > 0;
    }

    public boolean updateCoursePublicStatus(Long courseId, boolean isPublic) throws SQLException {
        return courseRepository.updatePublicStatus(courseId, isPublic) > 0;
    }
}
