package com.lxp.jdbc.content.controller;

import com.lxp.jdbc.content.model.Content;
import com.lxp.jdbc.content.model.ContentCreateRequest;
import com.lxp.jdbc.content.model.ContentFreeChangeRequest;
import com.lxp.jdbc.content.model.ContentPublicChangeRequest;
import com.lxp.jdbc.content.model.ContentUpdateRequest;
import com.lxp.jdbc.content.service.ContentService;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class ContentController {

    private static final DateTimeFormatter DT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private final ContentService contentService = new ContentService();

    public void create(ContentCreateRequest request) {
        try {
            validateCreate(request);
            Long id = contentService.create(request);
            System.out.println("생성 완료. contents_id=" + id);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void findById(Long contentsId) {
        try {
            if (contentsId == null) {
                throw new IllegalArgumentException("contents_id 는 필수입니다.");
            }
            Optional<Content> found = contentService.findById(contentsId);
            if (found.isEmpty()) {
                System.out.println("조회 결과가 없습니다.");
                return;
            }
            printContent(found.get());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void listBySectionId(Long sectionId) {
        try {
            if (sectionId == null) {
                throw new IllegalArgumentException("section_id 는 필수입니다.");
            }
            List<Content> list = contentService.findBySectionId(sectionId);
            if (list.isEmpty()) {
                System.out.println("조회 결과가 없습니다.");
                return;
            }
            for (Content c : list) {
                printContent(c);
                System.out.println("---");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void update(ContentUpdateRequest request) {
        try {
            validateUpdate(request);
            contentService.update(request);
            System.out.println("수정 완료.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void softDelete(Long contentsId) {
        try {
            if (contentsId == null) {
                throw new IllegalArgumentException("contents_id 는 필수입니다.");
            }
            contentService.softDelete(contentsId);
            System.out.println("소프트 삭제 완료.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void changePublic(ContentPublicChangeRequest request) {
        try {
            if (request.getContentsId() == null) {
                throw new IllegalArgumentException("contents_id 는 필수입니다.");
            }
            contentService.changePublic(request);
            System.out.println("공개 설정 변경 완료.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void changeFree(ContentFreeChangeRequest request) {
        try {
            if (request.getContentsId() == null) {
                throw new IllegalArgumentException("contents_id 는 필수입니다.");
            }
            contentService.changeFree(request);
            System.out.println("무료 설정 변경 완료.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void validateCreate(ContentCreateRequest request) {
        if (request.getSectionId() == null) {
            throw new IllegalArgumentException("section_id 는 필수입니다.");
        }
        if (request.getContentTitle() == null || request.getContentTitle().isBlank()) {
            throw new IllegalArgumentException("content_title 은 비울 수 없습니다.");
        }
        if (request.getContentUrl() == null || request.getContentUrl().isBlank()) {
            throw new IllegalArgumentException("content_url 은 비울 수 없습니다.");
        }
    }

    private static void validateUpdate(ContentUpdateRequest request) {
        if (request.getContentsId() == null) {
            throw new IllegalArgumentException("contents_id 는 필수입니다.");
        }
        if (request.getContentTitle() == null || request.getContentTitle().isBlank()) {
            throw new IllegalArgumentException("content_title 은 비울 수 없습니다.");
        }
        if (request.getContentUrl() == null || request.getContentUrl().isBlank()) {
            throw new IllegalArgumentException("content_url 은 비울 수 없습니다.");
        }
    }

    private static void printContent(Content c) {
        System.out.println("contents_id=" + c.getContentsId());
        System.out.println("section_id=" + c.getSectionId());
        System.out.println("content_title=" + c.getContentTitle());
        System.out.println("content_url=" + c.getContentUrl());
        System.out.println("time=" + (c.getTime() == null ? "null" : c.getTime()));
        System.out.println("created_at=" + formatDt(c.getCreatedAt()));
        System.out.println("updated_at=" + formatDt(c.getUpdatedAt()));
        System.out.println("deleted_at=" + formatDt(c.getDeletedAt()));
        System.out.println("is_public=" + c.isPublic());
        System.out.println("is_deleted=" + c.isDeleted());
        System.out.println("is_free=" + c.isFree());
    }

    private static String formatDt(java.time.LocalDateTime t) {
        return t == null ? "null" : DT.format(t);
    }
}
