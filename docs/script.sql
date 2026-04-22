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