CREATE TABLE courses (
    course_id BIGINT NOT NULL AUTO_INCREMENT,
    course_title VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT NULL,
    deleted_at TIMESTAMP NULL DEFAULT NULL,
    is_public TINYINT(1) NOT NULL DEFAULT 0,
    is_deleted TINYINT(1) NOT NULL DEFAULT 0,
    PRIMARY KEY (course_id)
);

CREATE TABLE sections (
    section_id BIGINT NOT NULL AUTO_INCREMENT,
    course_id BIGINT NOT NULL,
    section_title VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT NULL,
    deleted_at TIMESTAMP NULL DEFAULT NULL,
    is_public TINYINT(1) NOT NULL DEFAULT 0,
    is_deleted TINYINT(1) NOT NULL DEFAULT 0,
    PRIMARY KEY (section_id),
    CONSTRAINT fk_sections_course
        FOREIGN KEY (course_id)
            REFERENCES courses (course_id)
);

CREATE TABLE contents (
    contents_id BIGINT NOT NULL AUTO_INCREMENT,
    section_id BIGINT NOT NULL,
    content_title VARCHAR(255) NOT NULL,
    content_url VARCHAR(255) NOT NULL,
    time INT NULL DEFAULT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT NULL,
    deleted_at TIMESTAMP NULL DEFAULT NULL,
    is_public TINYINT(1) NOT NULL DEFAULT 0,
    is_deleted TINYINT(1) NOT NULL DEFAULT 0,
    is_free TINYINT(1) NOT NULL DEFAULT 0,
    PRIMARY KEY (contents_id),
    CONSTRAINT fk_contents_section
        FOREIGN KEY (section_id)
            REFERENCES sections (section_id)
);