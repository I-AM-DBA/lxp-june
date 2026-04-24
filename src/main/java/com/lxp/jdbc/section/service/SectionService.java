package com.lxp.jdbc.section.service;

import com.lxp.jdbc.config.JDBCConnection;
import com.lxp.jdbc.course.repository.CourseRepository;
import com.lxp.jdbc.section.model.Section;
import com.lxp.jdbc.section.model.SectionCreateRequest;
import com.lxp.jdbc.section.model.SectionPublicChangeRequest;
import com.lxp.jdbc.section.model.SectionUpdateRequest;
import com.lxp.jdbc.section.repository.SectionRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SectionService {

    public Long create(SectionCreateRequest request) {
        if (request.getCourseId() == null) {
            throw new IllegalArgumentException("course_id 는 필수입니다.");
        }
        try (Connection connection = JDBCConnection.getConnection()) {
            CourseRepository courseRepository = new CourseRepository(connection);
            if (!courseRepository.existsActiveById(request.getCourseId())) {
                throw new IllegalArgumentException("유효한 Course 가 없습니다. course_id 를 확인하세요.");
            }
            SectionRepository sectionRepository = new SectionRepository(connection);
            return sectionRepository.insert(
                    request.getCourseId(),
                    request.getSectionTitle(),
                    request.isPublic());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Section> findById(Long sectionId) {
        if (sectionId == null) {
            throw new IllegalArgumentException("section_id 는 필수입니다.");
        }
        try (Connection connection = JDBCConnection.getConnection()) {
            SectionRepository repo = new SectionRepository(connection);
            Optional<Section> found = repo.findById(sectionId);
            if (found.isPresent() && found.get().isDeleted()) {
                return Optional.empty();
            }
            return found;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Section> findByCourseId(Long courseId) {
        if (courseId == null) {
            throw new IllegalArgumentException("course_id 는 필수입니다.");
        }
        try (Connection connection = JDBCConnection.getConnection()) {
            CourseRepository courseRepository = new CourseRepository(connection);
            if (!courseRepository.existsActiveById(courseId)) {
                throw new IllegalArgumentException("유효한 Course 가 없습니다. course_id 를 확인하세요.");
            }
            SectionRepository sectionRepository = new SectionRepository(connection);
            return sectionRepository.findByCourseIdNotDeleted(courseId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(SectionUpdateRequest request) {
        if (request.getSectionId() == null) {
            throw new IllegalArgumentException("section_id 는 필수입니다.");
        }
        try (Connection connection = JDBCConnection.getConnection()) {
            SectionRepository repo = new SectionRepository(connection);
            if (!repo.existsActiveById(request.getSectionId())) {
                throw new IllegalArgumentException("존재하지 않거나 삭제된 Section 입니다.");
            }
            int rows = repo.update(
                    request.getSectionId(),
                    request.getSectionTitle(),
                    request.isPublic());
            if (rows != 1) {
                throw new IllegalArgumentException("Section 수정에 실패했습니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void softDelete(Long sectionId) {
        if (sectionId == null) {
            throw new IllegalArgumentException("section_id 는 필수입니다.");
        }
        try (Connection connection = JDBCConnection.getConnection()) {
            SectionRepository repo = new SectionRepository(connection);
            if (!repo.existsActiveById(sectionId)) {
                throw new IllegalArgumentException("존재하지 않거나 삭제된 Section 입니다.");
            }
            int rows = repo.softDelete(sectionId);
            if (rows != 1) {
                throw new IllegalArgumentException("Section 소프트 삭제에 실패했습니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void changePublic(SectionPublicChangeRequest request) {
        if (request.getSectionId() == null) {
            throw new IllegalArgumentException("section_id 는 필수입니다.");
        }
        try (Connection connection = JDBCConnection.getConnection()) {
            SectionRepository repo = new SectionRepository(connection);
            if (!repo.existsActiveById(request.getSectionId())) {
                throw new IllegalArgumentException("존재하지 않거나 삭제된 Section 입니다.");
            }
            int rows = repo.togglePublic(request.getSectionId(), request.isPublic());
            if (rows != 1) {
                throw new IllegalArgumentException("Section 공개 설정 변경에 실패했습니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
