package com.lxp.jdbc.course.core.repository;

import com.lxp.jdbc.config.JDBCConnection;
import com.lxp.jdbc.course.core.model.CourseCreateRequest;
import com.lxp.jdbc.course.core.model.CourseResponse;
import com.lxp.jdbc.course.core.model.CourseUpdateRequest;
import com.lxp.jdbc.util.QueryUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository {

    public void create(CourseCreateRequest request) throws SQLException {
        String sql = QueryUtil.getQuery("course.create");

        try (Connection conn = JDBCConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, request.getCourseId());
            pstmt.setString(2, request.getCourseTitle());
            pstmt.setString(3, request.getDescription());
            pstmt.setBoolean(4, request.getIsPublic());
            pstmt.executeUpdate();
        }
    }

    public CourseResponse findById(Long courseId) throws SQLException {
        String sql = QueryUtil.getQuery("course.findById");

        try (Connection conn = JDBCConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, courseId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }
                return mapRow(rs);
            }
        }
    }

    public List<CourseResponse> findAllActive() throws SQLException {
        String sql = QueryUtil.getQuery("course.findAllActive");

        List<CourseResponse> courses = new ArrayList<>();
        try (Connection conn = JDBCConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                courses.add(mapRow(rs));
            }
        }
        return courses;
    }

    public int update(CourseUpdateRequest request) throws SQLException {
        String sql = QueryUtil.getQuery("course.update");

        try (Connection conn = JDBCConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, request.getCourseTitle());
            pstmt.setString(2, request.getDescription());
            pstmt.setBoolean(3, request.getIsPublic());
            pstmt.setLong(4, request.getCourseId());
            return pstmt.executeUpdate();
        }
    }

    public int softDelete(Long courseId) throws SQLException {
        String sql = QueryUtil.getQuery("course.softDelete");

        try (Connection conn = JDBCConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, courseId);
            return pstmt.executeUpdate();
        }
    }

    public int updatePublicStatus(Long courseId, boolean isPublic) throws SQLException {
        String sql = QueryUtil.getQuery("course.togglePublic");

        try (Connection conn = JDBCConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBoolean(1, isPublic);
            pstmt.setLong(2, courseId);
            return pstmt.executeUpdate();
        }
    }

    private CourseResponse mapRow(ResultSet rs) throws SQLException {
        CourseResponse response = new CourseResponse();
        response.setCourseId(rs.getLong("course_id"));
        response.setCourseTitle(rs.getString("course_title"));
        response.setDescription(rs.getString("description"));

        Timestamp createdAt = rs.getTimestamp("created_at");
        Timestamp updatedAt = rs.getTimestamp("updated_at");
        Timestamp deletedAt = rs.getTimestamp("deleted_at");

        response.setCreatedAt(createdAt == null ? null : createdAt.toLocalDateTime());
        response.setUpdatedAt(updatedAt == null ? null : updatedAt.toLocalDateTime());
        response.setDeletedAt(deletedAt == null ? null : deletedAt.toLocalDateTime());
        response.setIsPublic(rs.getBoolean("is_public"));
        response.setIsDeleted(rs.getBoolean("is_deleted"));
        return response;
    }
}
