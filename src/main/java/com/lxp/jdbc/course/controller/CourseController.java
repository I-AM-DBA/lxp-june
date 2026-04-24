package com.lxp.jdbc.course.controller;

import com.lxp.jdbc.course.model.Course;
import com.lxp.jdbc.course.model.CourseCreateRequest;
import com.lxp.jdbc.course.model.CoursePublicChangeRequest;
import com.lxp.jdbc.course.model.CourseUpdateRequest;
import com.lxp.jdbc.course.service.CourseService;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class CourseController {

    private static final DateTimeFormatter DT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private final CourseService courseService = new CourseService();

    public void create(CourseCreateRequest request) {
        try {
            validateCreate(request);
            Long id = courseService.create(request);
            System.out.println("생성 완료. course_id=" + id);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void findById(Long courseId) {
        try {
            if (courseId == null) {
                throw new IllegalArgumentException("course_id 는 필수입니다.");
            }
            Optional<Course> found = courseService.findById(courseId);
            if (found.isEmpty()) {
                System.out.println("조회 결과가 없습니다.");
                return;
            }
            printCourse(found.get());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void listAllNotDeleted() {
        try {
            List<Course> list = courseService.findAllNotDeleted();
            if (list.isEmpty()) {
                System.out.println("조회 결과가 없습니다.");
                return;
            }
            for (Course c : list) {
                printCourse(c);
                System.out.println("---");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void update(CourseUpdateRequest request) {
        try {
            validateUpdate(request);
            courseService.update(request);
            System.out.println("수정 완료.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void softDelete(Long courseId) {
        try {
            if (courseId == null) {
                throw new IllegalArgumentException("course_id 는 필수입니다.");
            }
            courseService.softDelete(courseId);
            System.out.println("소프트 삭제 완료.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void changePublic(CoursePublicChangeRequest request) {
        try {
            if (request.getCourseId() == null) {
                throw new IllegalArgumentException("course_id 는 필수입니다.");
            }
            courseService.changePublic(request);
            System.out.println("공개 설정 변경 완료.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void validateCreate(CourseCreateRequest request) {
        if (request.getCourseTitle() == null || request.getCourseTitle().isBlank()) {
            throw new IllegalArgumentException("course_title 은 비울 수 없습니다.");
        }
    }

    private static void validateUpdate(CourseUpdateRequest request) {
        if (request.getCourseId() == null) {
            throw new IllegalArgumentException("course_id 는 필수입니다.");
        }
        if (request.getCourseTitle() == null || request.getCourseTitle().isBlank()) {
            throw new IllegalArgumentException("course_title 은 비울 수 없습니다.");
        }
    }

    private static void printCourse(Course c) {
        System.out.println("course_id=" + c.getCourseId());
        System.out.println("course_title=" + c.getCourseTitle());
        System.out.println("description=" + c.getDescription());
        System.out.println("created_at=" + formatDt(c.getCreatedAt()));
        System.out.println("updated_at=" + formatDt(c.getUpdatedAt()));
        System.out.println("deleted_at=" + formatDt(c.getDeletedAt()));
        System.out.println("is_public=" + c.isPublic());
        System.out.println("is_deleted=" + c.isDeleted());
    }

    private static String formatDt(java.time.LocalDateTime t) {
        return t == null ? "null" : DT.format(t);
    }
}
