package com.lxp.jdbc.course.model;

public class CourseCreateRequest {

    private String courseTitle;
    private String description;
    private boolean isPublic;

    public CourseCreateRequest() {
    }

    public CourseCreateRequest(String courseTitle, String description, boolean isPublic) {
        this.courseTitle = courseTitle;
        this.description = description;
        this.isPublic = isPublic;
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
