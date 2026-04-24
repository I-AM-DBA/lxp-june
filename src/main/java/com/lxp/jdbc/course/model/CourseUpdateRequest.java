package com.lxp.jdbc.course.model;

public class CourseUpdateRequest {

    private Long courseId;
    private String courseTitle;
    private String description;
    private boolean isPublic;

    public CourseUpdateRequest() {
    }

    public CourseUpdateRequest(Long courseId, String courseTitle, String description, boolean isPublic) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.description = description;
        this.isPublic = isPublic;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
