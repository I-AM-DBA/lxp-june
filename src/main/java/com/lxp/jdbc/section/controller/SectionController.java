package com.lxp.jdbc.section.controller;

import com.lxp.jdbc.section.model.Section;
import com.lxp.jdbc.section.model.SectionCreateRequest;
import com.lxp.jdbc.section.model.SectionPublicChangeRequest;
import com.lxp.jdbc.section.model.SectionUpdateRequest;
import com.lxp.jdbc.section.service.SectionService;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class SectionController {

    private static final DateTimeFormatter DT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private final SectionService sectionService = new SectionService();

    public void create(SectionCreateRequest request) {
        try {
            validateCreate(request);
            Long id = sectionService.create(request);
            System.out.println("생성 완료. section_id=" + id);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void findById(Long sectionId) {
        try {
            if (sectionId == null) {
                throw new IllegalArgumentException("section_id 는 필수입니다.");
            }
            Optional<Section> found = sectionService.findById(sectionId);
            if (found.isEmpty()) {
                System.out.println("조회 결과가 없습니다.");
                return;
            }
            printSection(found.get());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void listByCourseId(Long courseId) {
        try {
            if (courseId == null) {
                throw new IllegalArgumentException("course_id 는 필수입니다.");
            }
            List<Section> list = sectionService.findByCourseId(courseId);
            if (list.isEmpty()) {
                System.out.println("조회 결과가 없습니다.");
                return;
            }
            for (Section s : list) {
                printSection(s);
                System.out.println("---");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void update(SectionUpdateRequest request) {
        try {
            validateUpdate(request);
            sectionService.update(request);
            System.out.println("수정 완료.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void softDelete(Long sectionId) {
        try {
            if (sectionId == null) {
                throw new IllegalArgumentException("section_id 는 필수입니다.");
            }
            sectionService.softDelete(sectionId);
            System.out.println("소프트 삭제 완료.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void changePublic(SectionPublicChangeRequest request) {
        try {
            if (request.getSectionId() == null) {
                throw new IllegalArgumentException("section_id 는 필수입니다.");
            }
            sectionService.changePublic(request);
            System.out.println("공개 설정 변경 완료.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void validateCreate(SectionCreateRequest request) {
        if (request.getCourseId() == null) {
            throw new IllegalArgumentException("course_id 는 필수입니다.");
        }
        if (request.getSectionTitle() == null || request.getSectionTitle().isBlank()) {
            throw new IllegalArgumentException("section_title 은 비울 수 없습니다.");
        }
    }

    private static void validateUpdate(SectionUpdateRequest request) {
        if (request.getSectionId() == null) {
            throw new IllegalArgumentException("section_id 는 필수입니다.");
        }
        if (request.getSectionTitle() == null || request.getSectionTitle().isBlank()) {
            throw new IllegalArgumentException("section_title 은 비울 수 없습니다.");
        }
    }

    private static void printSection(Section s) {
        System.out.println("section_id=" + s.getSectionId());
        System.out.println("course_id=" + s.getCourseId());
        System.out.println("section_title=" + s.getSectionTitle());
        System.out.println("created_at=" + formatDt(s.getCreatedAt()));
        System.out.println("updated_at=" + formatDt(s.getUpdatedAt()));
        System.out.println("deleted_at=" + formatDt(s.getDeletedAt()));
        System.out.println("is_public=" + s.isPublic());
        System.out.println("is_deleted=" + s.isDeleted());
    }

    private static String formatDt(java.time.LocalDateTime t) {
        return t == null ? "null" : DT.format(t);
    }
}
