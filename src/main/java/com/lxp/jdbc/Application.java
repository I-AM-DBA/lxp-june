package com.lxp.jdbc;

import java.util.Scanner;

public class Application {
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
                    createCourseStub(sc);
                    break;
                case "2":
                    getCourseStub(sc);
                    break;
                case "3":
                    listCoursesStub();
                    break;
                case "4":
                    updateCourseStub(sc);
                    break;
                case "5":
                    softDeleteCourseStub(sc);
                    break;
                case "6":
                    toggleCoursePublicStub(sc);
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
                    createSectionStub(sc);
                    break;
                case "2":
                    getSectionStub(sc);
                    break;
                case "3":
                    listSectionsByCourseStub(sc);
                    break;
                case "4":
                    updateSectionStub(sc);
                    break;
                case "5":
                    softDeleteSectionStub(sc);
                    break;
                case "6":
                    toggleSectionPublicStub(sc);
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
                    createContentStub(sc);
                    break;
                case "2":
                    getContentStub(sc);
                    break;
                case "3":
                    listContentsBySectionStub(sc);
                    break;
                case "4":
                    updateContentStub(sc);
                    break;
                case "5":
                    softDeleteContentStub(sc);
                    break;
                case "6":
                    toggleContentPublicStub(sc);
                    break;
                case "7":
                    toggleContentFreeStub(sc);
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

    static void createCourseStub(Scanner sc) {
        System.out.println("TODO: CourseCreateRequest 생성 후 Controller 호출");
    }

    static void getCourseStub(Scanner sc) {
        readLong(sc, "course_id");
        System.out.println("TODO: Course 단건 조회 Controller 호출");
    }

    static void listCoursesStub() {
        System.out.println("TODO: Course 목록 조회 Controller 호출");
    }

    static void updateCourseStub(Scanner sc) {
        readLong(sc, "course_id");
        System.out.println("TODO: CourseUpdateRequest 생성 후 Controller 호출");
    }

    static void softDeleteCourseStub(Scanner sc) {
        readLong(sc, "course_id");
        System.out.println("TODO: Course 소프트 삭제 Controller 호출");
    }

    static void toggleCoursePublicStub(Scanner sc) {
        readLong(sc, "course_id");
        readBoolean(sc, "is_public");
        System.out.println("TODO: Course 공개/비공개 토글 Controller 호출");
    }

    static void createSectionStub(Scanner sc) {
        System.out.println("TODO: SectionCreateRequest 생성 후 Controller 호출");
    }

    static void getSectionStub(Scanner sc) {
        readLong(sc, "section_id");
        System.out.println("TODO: Section 단건 조회 Controller 호출");
    }

    static void listSectionsByCourseStub(Scanner sc) {
        readLong(sc, "course_id");
        System.out.println("TODO: Course별 Section 목록 조회 Controller 호출");
    }

    static void updateSectionStub(Scanner sc) {
        readLong(sc, "section_id");
        System.out.println("TODO: SectionUpdateRequest 생성 후 Controller 호출");
    }

    static void softDeleteSectionStub(Scanner sc) {
        readLong(sc, "section_id");
        System.out.println("TODO: Section 소프트 삭제 Controller 호출");
    }

    static void toggleSectionPublicStub(Scanner sc) {
        readLong(sc, "section_id");
        readBoolean(sc, "is_public");
        System.out.println("TODO: Section 공개/비공개 토글 Controller 호출");
    }

    static void createContentStub(Scanner sc) {
        System.out.println("TODO: ContentCreateRequest 생성 후 Controller 호출");
    }

    static void getContentStub(Scanner sc) {
        readLong(sc, "contents_id");
        System.out.println("TODO: Content 단건 조회 Controller 호출");
    }

    static void listContentsBySectionStub(Scanner sc) {
        readLong(sc, "section_id");
        System.out.println("TODO: Section별 Content 목록 조회 Controller 호출");
    }

    static void updateContentStub(Scanner sc) {
        readLong(sc, "contents_id");
        System.out.println("TODO: ContentUpdateRequest 생성 후 Controller 호출");
    }

    static void softDeleteContentStub(Scanner sc) {
        readLong(sc, "contents_id");
        System.out.println("TODO: Content 소프트 삭제 Controller 호출");
    }

    static void toggleContentPublicStub(Scanner sc) {
        readLong(sc, "contents_id");
        readBoolean(sc, "is_public");
        System.out.println("TODO: Content 공개/비공개 토글 Controller 호출");
    }

    static void toggleContentFreeStub(Scanner sc) {
        readLong(sc, "contents_id");
        readBoolean(sc, "is_free");
        System.out.println("TODO: Content 무료/유료 토글 Controller 호출");
    }
}
