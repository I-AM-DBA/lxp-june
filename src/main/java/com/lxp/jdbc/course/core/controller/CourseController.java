package com.lxp.jdbc.course.core.controller;

import com.lxp.jdbc.course.core.model.CourseCreateRequest;
import com.lxp.jdbc.course.core.model.CourseResponse;
import com.lxp.jdbc.course.core.model.CourseUpdateRequest;
import com.lxp.jdbc.course.core.service.CourseService;

import java.sql.SQLException;
import java.util.List;

public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    public void createCourse(CourseCreateRequest request) {
        if (request.getCourseId() == null) {
            System.out.println("course_id는 필수입니다.");
            return;
        }
        if (request.getCourseTitle() == null || request.getCourseTitle().isBlank()) {
            System.out.println("course_title은 필수입니다.");
            return;
        }

        try {
            courseService.createCourse(request);
            System.out.println("Course 생성 완료: course_id=" + request.getCourseId());
        } catch (SQLException e) {
            System.out.println("Course 생성 실패: " + e.getMessage());
        }
    }

    public void getCourse(Long courseId) {
        if (courseId == null) {
            System.out.println("course_id는 필수입니다.");
            return;
        }

        try {
            CourseResponse response = courseService.getCourse(courseId);
            if (response == null) {
                System.out.println("조회 결과가 없습니다. course_id=" + courseId);
                return;
            }
            printCourse(response);
        } catch (SQLException e) {
            System.out.println("Course 조회 실패: " + e.getMessage());
        }
    }

    public void listActiveCourses() {
        try {
            List<CourseResponse> courses = courseService.getActiveCourses();
            System.out.println("[Course List] total=" + courses.size());
            if (courses.isEmpty()) {
                return;
            }

            int idx = 1;
            for (CourseResponse course : courses) {
                System.out.println(idx++ + ") courseId=" + course.getCourseId()
                        + ", title=" + course.getCourseTitle()
                        + ", public=" + course.getIsPublic()
                        + ", deleted=" + course.getIsDeleted());
            }
        } catch (SQLException e) {
            System.out.println("Course 목록 조회 실패: " + e.getMessage());
        }
    }

    public void updateCourse(CourseUpdateRequest request) {
        if (request.getCourseId() == null) {
            System.out.println("course_id는 필수입니다.");
            return;
        }
        if (request.getCourseTitle() == null || request.getCourseTitle().isBlank()) {
            System.out.println("course_title은 필수입니다.");
            return;
        }
        if (request.getIsPublic() == null) {
            System.out.println("is_public은 필수입니다.");
            return;
        }

        try {
            boolean updated = courseService.updateCourse(request);
            if (!updated) {
                System.out.println("수정 대상이 없습니다. course_id=" + request.getCourseId());
                return;
            }
            System.out.println("Course 수정 완료: course_id=" + request.getCourseId());
        } catch (SQLException e) {
            System.out.println("Course 수정 실패: " + e.getMessage());
        }
    }

    public void softDeleteCourse(Long courseId) {
        if (courseId == null) {
            System.out.println("course_id는 필수입니다.");
            return;
        }

        try {
            boolean deleted = courseService.softDeleteCourse(courseId);
            if (!deleted) {
                System.out.println("삭제 대상이 없습니다. course_id=" + courseId);
                return;
            }
            System.out.println("Course 소프트 삭제 완료: course_id=" + courseId);
        } catch (SQLException e) {
            System.out.println("Course 소프트 삭제 실패: " + e.getMessage());
        }
    }

    public void toggleCoursePublic(Long courseId, boolean isPublic) {
        if (courseId == null) {
            System.out.println("course_id는 필수입니다.");
            return;
        }

        try {
            boolean updated = courseService.updateCoursePublicStatus(courseId, isPublic);
            if (!updated) {
                System.out.println("수정 대상이 없습니다. course_id=" + courseId);
                return;
            }
            System.out.println("Course 공개 상태 변경 완료: course_id=" + courseId + ", is_public=" + isPublic);
        } catch (SQLException e) {
            System.out.println("Course 공개 상태 변경 실패: " + e.getMessage());
        }
    }

    private void printCourse(CourseResponse course) {
        System.out.println("[Course]");
        System.out.println("- id: " + course.getCourseId());
        System.out.println("- title: " + course.getCourseTitle());
        System.out.println("- description: " + course.getDescription());
        System.out.println("- public: " + course.getIsPublic());
        System.out.println("- deleted: " + course.getIsDeleted());
        System.out.println("- createdAt: " + course.getCreatedAt());
        System.out.println("- updatedAt: " + course.getUpdatedAt());
        System.out.println("- deletedAt: " + course.getDeletedAt());
    }
}
