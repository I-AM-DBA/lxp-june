-- 강의 courses
CREATE TABLE courses (
                         course_id BIGINT PRIMARY KEY,
                         course_title VARCHAR(255) NOT NULL,
                         description TEXT,
                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP,
                         deleted_at TIMESTAMP,
                         is_public BOOLEAN NOT NULL DEFAULT FALSE,
                         is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);

-- 섹션 sections
CREATE TABLE sections (
                          section_id BIGINT PRIMARY KEY,
                          course_id BIGINT NOT NULL,
                          section_title VARCHAR(255) NOT NULL,
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP,
                          deleted_at TIMESTAMP,
                          is_public BOOLEAN NOT NULL DEFAULT FALSE,
                          is_deleted BOOLEAN NOT NULL DEFAULT FALSE,

                          CONSTRAINT fk_sections_course
                              FOREIGN KEY (course_id)
                                  REFERENCES courses(course_id)
);

-- 콘텐츠 contents
CREATE TABLE contents (
                          contents_id BIGINT PRIMARY KEY,
                          section_id BIGINT NOT NULL,
                          content_title VARCHAR(255) NOT NULL,
                          content_url VARCHAR(255) NOT NULL,
                          time INT,
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP,
                          deleted_at TIMESTAMP,
                          is_public BOOLEAN NOT NULL DEFAULT FALSE,
                          is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
                          is_free BOOLEAN NOT NULL DEFAULT FALSE,

                          CONSTRAINT fk_contents_section
                              FOREIGN KEY (section_id)
                                  REFERENCES sections(section_id)
);

-- 1. 외래키 체크 비활성화
SET FOREIGN_KEY_CHECKS = 0;

-- 2. 원하는 테이블 드랍
DROP TABLE IF EXISTS `contents`;
DROP TABLE IF EXISTS `sections`;
DROP TABLE IF EXISTS `courses`;

-- 3. 외래키 체크 다시 활성화
SET FOREIGN_KEY_CHECKS = 1;

select * from contents;
