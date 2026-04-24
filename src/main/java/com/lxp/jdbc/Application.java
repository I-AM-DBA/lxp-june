package com.lxp.jdbc;

import com.lxp.jdbc.content.controller.ContentController;
import com.lxp.jdbc.content.model.ContentCreateRequest;
import com.lxp.jdbc.content.model.ContentFreeChangeRequest;
import com.lxp.jdbc.content.model.ContentPublicChangeRequest;
import com.lxp.jdbc.content.model.ContentUpdateRequest;
import com.lxp.jdbc.course.controller.CourseController;
import com.lxp.jdbc.course.model.CourseCreateRequest;
import com.lxp.jdbc.course.model.CoursePublicChangeRequest;
import com.lxp.jdbc.course.model.CourseUpdateRequest;
import com.lxp.jdbc.section.controller.SectionController;
import com.lxp.jdbc.section.model.SectionCreateRequest;
import com.lxp.jdbc.section.model.SectionPublicChangeRequest;
import com.lxp.jdbc.section.model.SectionUpdateRequest;

import java.util.Scanner;

public class Application {

    private static final CourseController courseController = new CourseController();
    private static final SectionController sectionController = new SectionController();
    private static final ContentController contentController = new ContentController();

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            runMainMenu(sc);
        }
    }

    static void runMainMenu(Scanner sc) {
        while (true) {
            System.out.println();
            System.out.println("=== LXP JDBC Master Console ===");
            System.out.println("1. 시스템 헬스체크");
            System.out.println("2. Course 관리 (courses)");
            System.out.println("3. Section 관리 (sections)");
            System.out.println("4. Content 관리 (contents)");
            System.out.println("5. 통합 검증 시나리오");
            System.out.println("exit. 프로그램 종료");
            System.out.print("> ");

            String command = readCommand(sc);
            switch (command) {
                case "1":
                    runHealthCheckMenu(sc);
                    break;
                case "2":
                    runCourseMenu(sc);
                    break;
                case "3":
                    runSectionMenu(sc);
                    break;
                case "4":
                    runContentMenu(sc);
                    break;
                case "5":
                    runIntegrationMenu(sc);
                    break;
                case "exit":
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("지원하지 않는 명령입니다. 다시 입력하세요.");
            }
        }
    }

    static void runHealthCheckMenu(Scanner sc) {
        while (true) {
            System.out.println();
            System.out.println("[시스템 헬스체크]");
            System.out.println("1. DB 연결 테스트");
            System.out.println("2. 테이블 총 건수 조회");
            System.out.println("3. 삭제 제외 유효 건수 조회");
            System.out.println("4. 최근 생성 5건 미리보기");
            System.out.println("back. 이전");
            System.out.print("> ");

            String command = readCommand(sc);
            switch (command) {
                case "1":
                case "2":
                case "3":
                case "4":
                    System.out.println("TODO: 헬스체크 기능 구현 예정");
                    break;
                case "back":
                    return;
                case "exit":
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                    return;
                default:
                    System.out.println("지원하지 않는 명령입니다. 다시 입력하세요.");
            }
        }
    }

    static void runCourseMenu(Scanner sc) {
        while (true) {
            System.out.println();
            System.out.println("[Course 관리]");
            System.out.println("1. Course 생성");
            System.out.println("2. Course 단건 조회(course_id)");
            System.out.println("3. Course 목록 조회(삭제 제외)");
            System.out.println("4. Course 수정(제목/설명/공개)");
            System.out.println("5. Course 소프트 삭제");
            System.out.println("6. Course 공개/비공개 전환");
            System.out.println("back. 이전");
            System.out.print("> ");

            String command = readCommand(sc);
            switch (command) {
                case "1":
                    createCourse(sc);
                    break;
                case "2":
                    getCourse(sc);
                    break;
                case "3":
                    courseController.listAllNotDeleted();
                    break;
                case "4":
                    updateCourse(sc);
                    break;
                case "5":
                    courseController.softDelete(readLong(sc, "course_id"));
                    break;
                case "6":
                    toggleCoursePublic(sc);
                    break;
                case "back":
                    return;
                case "exit":
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                    return;
                default:
                    System.out.println("지원하지 않는 명령입니다. 다시 입력하세요.");
            }
        }
    }

    static void runSectionMenu(Scanner sc) {
        while (true) {
            System.out.println();
            System.out.println("[Section 관리]");
            System.out.println("1. Section 생성");
            System.out.println("2. Section 단건 조회(section_id)");
            System.out.println("3. Course별 Section 목록 조회(course_id)");
            System.out.println("4. Section 수정(제목/공개)");
            System.out.println("5. Section 소프트 삭제");
            System.out.println("6. Section 공개/비공개 전환");
            System.out.println("back. 이전");
            System.out.print("> ");

            String command = readCommand(sc);
            switch (command) {
                case "1":
                    createSection(sc);
                    break;
                case "2":
                    getSection(sc);
                    break;
                case "3":
                    sectionController.listByCourseId(readLong(sc, "course_id"));
                    break;
                case "4":
                    updateSection(sc);
                    break;
                case "5":
                    sectionController.softDelete(readLong(sc, "section_id"));
                    break;
                case "6":
                    toggleSectionPublic(sc);
                    break;
                case "back":
                    return;
                case "exit":
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                    return;
                default:
                    System.out.println("지원하지 않는 명령입니다. 다시 입력하세요.");
            }
        }
    }

    static void runContentMenu(Scanner sc) {
        while (true) {
            System.out.println();
            System.out.println("[Content 관리]");
            System.out.println("1. Content 생성");
            System.out.println("2. Content 단건 조회(contents_id)");
            System.out.println("3. Section별 Content 목록 조회(section_id)");
            System.out.println("4. Content 수정(제목/URL/재생시간/공개/무료)");
            System.out.println("5. Content 소프트 삭제");
            System.out.println("6. Content 공개/비공개 전환");
            System.out.println("7. Content 무료/유료 전환");
            System.out.println("back. 이전");
            System.out.print("> ");

            String command = readCommand(sc);
            switch (command) {
                case "1":
                    createContent(sc);
                    break;
                case "2":
                    getContent(sc);
                    break;
                case "3":
                    contentController.listBySectionId(readLong(sc, "section_id"));
                    break;
                case "4":
                    updateContent(sc);
                    break;
                case "5":
                    contentController.softDelete(readLong(sc, "contents_id"));
                    break;
                case "6":
                    toggleContentPublic(sc);
                    break;
                case "7":
                    toggleContentFree(sc);
                    break;
                case "back":
                    return;
                case "exit":
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                    return;
                default:
                    System.out.println("지원하지 않는 명령입니다. 다시 입력하세요.");
            }
        }
    }

    static void runIntegrationMenu(Scanner sc) {
        while (true) {
            System.out.println();
            System.out.println("[통합 검증 시나리오]");
            System.out.println("1. 샘플 1세트 생성 (Course->Section->Content)");
            System.out.println("2. 계층 조회 점검 (course_id 기준 하위 전체)");
            System.out.println("3. 삭제 전파 정책 점검 (소프트 삭제)");
            System.out.println("4. 정리(샘플 데이터 소프트 삭제)");
            System.out.println("back. 이전");
            System.out.print("> ");

            String command = readCommand(sc);
            switch (command) {
                case "1":
                case "2":
                case "3":
                case "4":
                    System.out.println("TODO: 통합 검증 시나리오 구현 예정");
                    break;
                case "back":
                    return;
                case "exit":
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                    return;
                default:
                    System.out.println("지원하지 않는 명령입니다. 다시 입력하세요.");
            }
        }
    }

    static void createCourse(Scanner sc) {
        String title = readRequiredLine(sc, "course_title");
        String description = readNullableText(sc, "description");
        boolean isPublic = readBoolean(sc, "is_public");
        CourseCreateRequest request = new CourseCreateRequest(title, description, isPublic);
        courseController.create(request);
    }

    static void getCourse(Scanner sc) {
        courseController.findById(readLong(sc, "course_id"));
    }

    static void updateCourse(Scanner sc) {
        Long courseId = readLong(sc, "course_id");
        String title = readRequiredLine(sc, "course_title");
        String description = readNullableText(sc, "description");
        boolean isPublic = readBoolean(sc, "is_public");
        CourseUpdateRequest request = new CourseUpdateRequest(courseId, title, description, isPublic);
        courseController.update(request);
    }

    static void toggleCoursePublic(Scanner sc) {
        Long courseId = readLong(sc, "course_id");
        boolean isPublic = readBoolean(sc, "is_public");
        courseController.changePublic(new CoursePublicChangeRequest(courseId, isPublic));
    }

    static void createSection(Scanner sc) {
        Long courseId = readLong(sc, "course_id");
        String sectionTitle = readRequiredLine(sc, "section_title");
        boolean isPublic = readBoolean(sc, "is_public");
        SectionCreateRequest request = new SectionCreateRequest(courseId, sectionTitle, isPublic);
        sectionController.create(request);
    }

    static void getSection(Scanner sc) {
        sectionController.findById(readLong(sc, "section_id"));
    }

    static void updateSection(Scanner sc) {
        Long sectionId = readLong(sc, "section_id");
        String sectionTitle = readRequiredLine(sc, "section_title");
        boolean isPublic = readBoolean(sc, "is_public");
        SectionUpdateRequest request = new SectionUpdateRequest(sectionId, sectionTitle, isPublic);
        sectionController.update(request);
    }

    static void toggleSectionPublic(Scanner sc) {
        Long sectionId = readLong(sc, "section_id");
        boolean isPublic = readBoolean(sc, "is_public");
        sectionController.changePublic(new SectionPublicChangeRequest(sectionId, isPublic));
    }

    static void createContent(Scanner sc) {
        Long sectionId = readLong(sc, "section_id");
        String contentTitle = readRequiredLine(sc, "content_title");
        String contentUrl = readRequiredLine(sc, "content_url");
        Integer time = readOptionalInteger(sc, "time(초)");
        boolean isPublic = readBoolean(sc, "is_public");
        boolean isFree = readBoolean(sc, "is_free");
        ContentCreateRequest request =
                new ContentCreateRequest(sectionId, contentTitle, contentUrl, time, isPublic, isFree);
        contentController.create(request);
    }

    static void getContent(Scanner sc) {
        contentController.findById(readLong(sc, "contents_id"));
    }

    static void updateContent(Scanner sc) {
        Long contentsId = readLong(sc, "contents_id");
        String contentTitle = readRequiredLine(sc, "content_title");
        String contentUrl = readRequiredLine(sc, "content_url");
        Integer time = readOptionalInteger(sc, "time(초)");
        boolean isPublic = readBoolean(sc, "is_public");
        boolean isFree = readBoolean(sc, "is_free");
        ContentUpdateRequest request =
                new ContentUpdateRequest(contentsId, contentTitle, contentUrl, time, isPublic, isFree);
        contentController.update(request);
    }

    static void toggleContentPublic(Scanner sc) {
        Long contentsId = readLong(sc, "contents_id");
        boolean isPublic = readBoolean(sc, "is_public");
        contentController.changePublic(new ContentPublicChangeRequest(contentsId, isPublic));
    }

    static void toggleContentFree(Scanner sc) {
        Long contentsId = readLong(sc, "contents_id");
        boolean isFree = readBoolean(sc, "is_free");
        contentController.changeFree(new ContentFreeChangeRequest(contentsId, isFree));
    }

    static String readCommand(Scanner sc) {
        return sc.nextLine().trim().toLowerCase();
    }

    static Long readLong(Scanner sc, String label) {
        while (true) {
            System.out.print(label + ": ");
            String input = sc.nextLine().trim();
            try {
                return Long.parseLong(input);
            } catch (NumberFormatException e) {
                System.out.println("숫자 형식만 입력 가능합니다.");
            }
        }
    }

    static Boolean readBoolean(Scanner sc, String label) {
        while (true) {
            System.out.print(label + " (true/false): ");
            String input = sc.nextLine().trim().toLowerCase();
            if ("true".equals(input)) {
                return true;
            }
            if ("false".equals(input)) {
                return false;
            }
            System.out.println("true 또는 false만 입력 가능합니다.");
        }
    }

    static String readNullableText(Scanner sc, String label) {
        System.out.print(label + " (비우면 null): ");
        String input = sc.nextLine();
        return input == null || input.isBlank() ? null : input.trim();
    }

    static String readRequiredLine(Scanner sc, String label) {
        while (true) {
            System.out.print(label + ": ");
            String line = sc.nextLine();
            String trimmed = line == null ? "" : line.trim();
            if (!trimmed.isEmpty()) {
                return trimmed;
            }
            System.out.println("비울 수 없습니다.");
        }
    }

    static Integer readOptionalInteger(Scanner sc, String label) {
        while (true) {
            System.out.print(label + " (비우면 null): ");
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                return null;
            }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("정수만 입력 가능합니다.");
            }
        }
    }
}
