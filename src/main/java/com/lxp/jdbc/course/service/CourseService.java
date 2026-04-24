package com.lxp.jdbc.course.service;

import com.lxp.jdbc.config.JDBCConnection;
import com.lxp.jdbc.course.model.Course;
import com.lxp.jdbc.course.model.CourseCreateRequest;
import com.lxp.jdbc.course.model.CoursePublicChangeRequest;
import com.lxp.jdbc.course.model.CourseUpdateRequest;
import com.lxp.jdbc.course.repository.CourseRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CourseService {

    public Long create(CourseCreateRequest request) {
        try (Connection connection = JDBCConnection.getConnection()) {
            CourseRepository repo = new CourseRepository(connection);
            return repo.insert(
                    request.getCourseTitle(),
                    request.getDescription(),
                    request.isPublic());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Course> findById(Long courseId) {
        if (courseId == null) {
            throw new IllegalArgumentException("course_id 는 필수입니다.");
        }
        try (Connection connection = JDBCConnection.getConnection()) {
            CourseRepository repo = new CourseRepository(connection);
            Optional<Course> found = repo.findById(courseId);
            if (found.isPresent() && found.get().isDeleted()) {
                return Optional.empty();
            }
            return found;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Course> findAllNotDeleted() {
        try (Connection connection = JDBCConnection.getConnection()) {
            CourseRepository repo = new CourseRepository(connection);
            return repo.findAllNotDeleted();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(CourseUpdateRequest request) {
        if (request.getCourseId() == null) {
            throw new IllegalArgumentException("course_id 는 필수입니다.");
        }
        try (Connection connection = JDBCConnection.getConnection()) {
            CourseRepository repo = new CourseRepository(connection);
            if (!repo.existsActiveById(request.getCourseId())) {
                throw new IllegalArgumentException("존재하지 않거나 삭제된 Course 입니다.");
            }
            int rows = repo.update(
                    request.getCourseId(),
                    request.getCourseTitle(),
                    request.getDescription(),
                    request.isPublic());
            if (rows != 1) {
                throw new IllegalArgumentException("Course 수정에 실패했습니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void softDelete(Long courseId) {
        if (courseId == null) {
            throw new IllegalArgumentException("course_id 는 필수입니다.");
        }
        try (Connection connection = JDBCConnection.getConnection()) {
            CourseRepository repo = new CourseRepository(connection);
            if (!repo.existsActiveById(courseId)) {
                throw new IllegalArgumentException("존재하지 않거나 삭제된 Course 입니다.");
            }
            int rows = repo.softDelete(courseId);
            if (rows != 1) {
                throw new IllegalArgumentException("Course 소프트 삭제에 실패했습니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void changePublic(CoursePublicChangeRequest request) {
        if (request.getCourseId() == null) {
            throw new IllegalArgumentException("course_id 는 필수입니다.");
        }
        try (Connection connection = JDBCConnection.getConnection()) {
            CourseRepository repo = new CourseRepository(connection);
            if (!repo.existsActiveById(request.getCourseId())) {
                throw new IllegalArgumentException("존재하지 않거나 삭제된 Course 입니다.");
            }
            int rows = repo.togglePublic(request.getCourseId(), request.isPublic());
            if (rows != 1) {
                throw new IllegalArgumentException("Course 공개 설정 변경에 실패했습니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
