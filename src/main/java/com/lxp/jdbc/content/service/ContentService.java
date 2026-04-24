package com.lxp.jdbc.content.service;

import com.lxp.jdbc.config.JDBCConnection;
import com.lxp.jdbc.content.model.Content;
import com.lxp.jdbc.content.model.ContentCreateRequest;
import com.lxp.jdbc.content.model.ContentFreeChangeRequest;
import com.lxp.jdbc.content.model.ContentPublicChangeRequest;
import com.lxp.jdbc.content.model.ContentUpdateRequest;
import com.lxp.jdbc.content.repository.ContentRepository;
import com.lxp.jdbc.section.repository.SectionRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ContentService {

    public Long create(ContentCreateRequest request) {
        if (request.getSectionId() == null) {
            throw new IllegalArgumentException("section_id 는 필수입니다.");
        }
        try (Connection connection = JDBCConnection.getConnection()) {
            SectionRepository sectionRepository = new SectionRepository(connection);
            if (!sectionRepository.existsActiveById(request.getSectionId())) {
                throw new IllegalArgumentException("유효한 Section 이 없습니다. section_id 를 확인하세요.");
            }
            ContentRepository contentRepository = new ContentRepository(connection);
            return contentRepository.insert(
                    request.getSectionId(),
                    request.getContentTitle(),
                    request.getContentUrl(),
                    request.getTime(),
                    request.isPublic(),
                    request.isFree());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Content> findById(Long contentsId) {
        if (contentsId == null) {
            throw new IllegalArgumentException("contents_id 는 필수입니다.");
        }
        try (Connection connection = JDBCConnection.getConnection()) {
            ContentRepository repo = new ContentRepository(connection);
            Optional<Content> found = repo.findById(contentsId);
            if (found.isPresent() && found.get().isDeleted()) {
                return Optional.empty();
            }
            return found;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Content> findBySectionId(Long sectionId) {
        if (sectionId == null) {
            throw new IllegalArgumentException("section_id 는 필수입니다.");
        }
        try (Connection connection = JDBCConnection.getConnection()) {
            SectionRepository sectionRepository = new SectionRepository(connection);
            if (!sectionRepository.existsActiveById(sectionId)) {
                throw new IllegalArgumentException("유효한 Section 이 없습니다. section_id 를 확인하세요.");
            }
            ContentRepository contentRepository = new ContentRepository(connection);
            return contentRepository.findBySectionIdNotDeleted(sectionId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(ContentUpdateRequest request) {
        if (request.getContentsId() == null) {
            throw new IllegalArgumentException("contents_id 는 필수입니다.");
        }
        try (Connection connection = JDBCConnection.getConnection()) {
            ContentRepository repo = new ContentRepository(connection);
            if (!repo.existsActiveById(request.getContentsId())) {
                throw new IllegalArgumentException("존재하지 않거나 삭제된 Content 입니다.");
            }
            int rows = repo.update(
                    request.getContentsId(),
                    request.getContentTitle(),
                    request.getContentUrl(),
                    request.getTime(),
                    request.isPublic(),
                    request.isFree());
            if (rows != 1) {
                throw new IllegalArgumentException("Content 수정에 실패했습니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void softDelete(Long contentsId) {
        if (contentsId == null) {
            throw new IllegalArgumentException("contents_id 는 필수입니다.");
        }
        try (Connection connection = JDBCConnection.getConnection()) {
            ContentRepository repo = new ContentRepository(connection);
            if (!repo.existsActiveById(contentsId)) {
                throw new IllegalArgumentException("존재하지 않거나 삭제된 Content 입니다.");
            }
            int rows = repo.softDelete(contentsId);
            if (rows != 1) {
                throw new IllegalArgumentException("Content 소프트 삭제에 실패했습니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void changePublic(ContentPublicChangeRequest request) {
        if (request.getContentsId() == null) {
            throw new IllegalArgumentException("contents_id 는 필수입니다.");
        }
        try (Connection connection = JDBCConnection.getConnection()) {
            ContentRepository repo = new ContentRepository(connection);
            if (!repo.existsActiveById(request.getContentsId())) {
                throw new IllegalArgumentException("존재하지 않거나 삭제된 Content 입니다.");
            }
            int rows = repo.togglePublic(request.getContentsId(), request.isPublic());
            if (rows != 1) {
                throw new IllegalArgumentException("Content 공개 설정 변경에 실패했습니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeFree(ContentFreeChangeRequest request) {
        if (request.getContentsId() == null) {
            throw new IllegalArgumentException("contents_id 는 필수입니다.");
        }
        try (Connection connection = JDBCConnection.getConnection()) {
            ContentRepository repo = new ContentRepository(connection);
            if (!repo.existsActiveById(request.getContentsId())) {
                throw new IllegalArgumentException("존재하지 않거나 삭제된 Content 입니다.");
            }
            int rows = repo.toggleFree(request.getContentsId(), request.isFree());
            if (rows != 1) {
                throw new IllegalArgumentException("Content 무료 설정 변경에 실패했습니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
