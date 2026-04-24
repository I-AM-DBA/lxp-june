package com.lxp.jdbc.content.repository;

import com.lxp.jdbc.content.model.Content;
import com.lxp.jdbc.util.QueryUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContentRepository {

    private final Connection connection;

    public ContentRepository(Connection connection) {
        this.connection = connection;
    }

    public Long insert(
            long sectionId,
            String contentTitle,
            String contentUrl,
            Integer time,
            boolean isPublic,
            boolean isFree) {
        String sql = QueryUtil.getQuery("content.insert");
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, sectionId);
            ps.setString(2, contentTitle);
            ps.setString(3, contentUrl);
            if (time == null) {
                ps.setNull(4, Types.INTEGER);
            } else {
                ps.setInt(4, time);
            }
            ps.setBoolean(5, isPublic);
            ps.setBoolean(6, isFree);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getLong(1);
                }
            }
            throw new IllegalStateException("content insert generated key 없음");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Content> findById(long contentsId) {
        String sql = QueryUtil.getQuery("content.findById");
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, contentsId);
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

    public List<Content> findBySectionIdNotDeleted(long sectionId) {
        String sql = QueryUtil.getQuery("content.findBySectionIdNotDeleted");
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, sectionId);
            try (ResultSet rs = ps.executeQuery()) {
                List<Content> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
                return list;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int update(
            long contentsId,
            String contentTitle,
            String contentUrl,
            Integer time,
            boolean isPublic,
            boolean isFree) {
        String sql = QueryUtil.getQuery("content.update");
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, contentTitle);
            ps.setString(2, contentUrl);
            if (time == null) {
                ps.setNull(3, Types.INTEGER);
            } else {
                ps.setInt(3, time);
            }
            ps.setBoolean(4, isPublic);
            ps.setBoolean(5, isFree);
            ps.setLong(6, contentsId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int softDelete(long contentsId) {
        String sql = QueryUtil.getQuery("content.softDelete");
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, contentsId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int togglePublic(long contentsId, boolean isPublic) {
        String sql = QueryUtil.getQuery("content.togglePublic");
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setBoolean(1, isPublic);
            ps.setLong(2, contentsId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int toggleFree(long contentsId, boolean isFree) {
        String sql = QueryUtil.getQuery("content.toggleFree");
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setBoolean(1, isFree);
            ps.setLong(2, contentsId);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsActiveById(long contentsId) {
        String sql = QueryUtil.getQuery("content.existsActiveById");
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, contentsId);
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

    private static Content mapRow(ResultSet rs) throws SQLException {
        int timeCol = rs.getInt("time");
        boolean timeNull = rs.wasNull();
        return new Content(
                rs.getLong("contents_id"),
                rs.getLong("section_id"),
                rs.getString("content_title"),
                rs.getString("content_url"),
                timeNull ? null : timeCol,
                rs.getObject("created_at", LocalDateTime.class),
                rs.getObject("updated_at", LocalDateTime.class),
                rs.getObject("deleted_at", LocalDateTime.class),
                rs.getBoolean("is_public"),
                rs.getBoolean("is_deleted"),
                rs.getBoolean("is_free"));
    }
}
