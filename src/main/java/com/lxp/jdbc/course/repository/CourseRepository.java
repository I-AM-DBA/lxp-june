package com.lxp.jdbc.course.repository;

import com.lxp.jdbc.course.model.Course;
import com.lxp.jdbc.util.QueryUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepository {

    private final Connection connection;

    public CourseRepository(Connection connection) {
        this.connection = connection;
    }

    public Long insert(String courseTitle, String description, boolean isPublic) {
        String sql = QueryUtil.getQuery("course.insert");
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, courseTitle);
            ps.setString(2, description);
            ps.setBoolean(3, isPublic);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getLong(1);
                }
            }
            throw new IllegalStateException("course insert generated key 없음");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Course> findById(long courseId) {
        String sql = QueryUtil.getQuery("course.findById");
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, courseId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Course> findAllNotDeleted() {
        String sql = QueryUtil.getQuery("course.findAllNotDeleted");
        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            List<Course> list = new ArrayList<>();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int update(long courseId, String courseTitle, String description, boolean isPublic) {
        String sql = QueryUtil.getQuery("course.update");
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, courseTitle);
            ps.setString(2, description);
            ps.setBoolean(3, isPublic);
            ps.setLong(4, courseId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int softDelete(long courseId) {
        String sql = QueryUtil.getQuery("course.softDelete");
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, courseId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int togglePublic(long courseId, boolean isPublic) {
        String sql = QueryUtil.getQuery("course.togglePublic");
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setBoolean(1, isPublic);
            ps.setLong(2, courseId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsActiveById(long courseId) {
        String sql = QueryUtil.getQuery("course.existsActiveById");
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, courseId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong("cnt") > 0;
                }
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Course mapRow(ResultSet rs) throws SQLException {
        return new Course(
                rs.getLong("course_id"),
                rs.getString("course_title"),
                rs.getString("description"),
                rs.getObject("created_at", LocalDateTime.class),
                rs.getObject("updated_at", LocalDateTime.class),
                rs.getObject("deleted_at", LocalDateTime.class),
                rs.getBoolean("is_public"),
                rs.getBoolean("is_deleted"));
    }
}
