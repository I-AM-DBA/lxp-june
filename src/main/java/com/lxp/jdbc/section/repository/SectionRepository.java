package com.lxp.jdbc.section.repository;

import com.lxp.jdbc.section.model.Section;
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

public class SectionRepository {

    private final Connection connection;

    public SectionRepository(Connection connection) {
        this.connection = connection;
    }

    public Long insert(long courseId, String sectionTitle, boolean isPublic) {
        String sql = QueryUtil.getQuery("section.insert");
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, courseId);
            ps.setString(2, sectionTitle);
            ps.setBoolean(3, isPublic);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getLong(1);
                }
            }
            throw new IllegalStateException("section insert generated key 없음");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Section> findById(long sectionId) {
        String sql = QueryUtil.getQuery("section.findById");
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, sectionId);
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

    public List<Section> findByCourseIdNotDeleted(long courseId) {
        String sql = QueryUtil.getQuery("section.findByCourseIdNotDeleted");
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, courseId);
            try (ResultSet rs = ps.executeQuery()) {
                List<Section> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
                return list;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int update(long sectionId, String sectionTitle, boolean isPublic) {
        String sql = QueryUtil.getQuery("section.update");
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, sectionTitle);
            ps.setBoolean(2, isPublic);
            ps.setLong(3, sectionId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int softDelete(long sectionId) {
        String sql = QueryUtil.getQuery("section.softDelete");
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, sectionId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int togglePublic(long sectionId, boolean isPublic) {
        String sql = QueryUtil.getQuery("section.togglePublic");
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setBoolean(1, isPublic);
            ps.setLong(2, sectionId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsActiveById(long sectionId) {
        String sql = QueryUtil.getQuery("section.existsActiveById");
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, sectionId);
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

    private static Section mapRow(ResultSet rs) throws SQLException {
        return new Section(
                rs.getLong("section_id"),
                rs.getLong("course_id"),
                rs.getString("section_title"),
                rs.getObject("created_at", LocalDateTime.class),
                rs.getObject("updated_at", LocalDateTime.class),
                rs.getObject("deleted_at", LocalDateTime.class),
                rs.getBoolean("is_public"),
                rs.getBoolean("is_deleted"));
    }
}
