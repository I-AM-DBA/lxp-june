package com.lxp.jdbc.course.model;

public class CoursePublicChangeRequest {

    private Long courseId;
    private boolean isPublic;

    public CoursePublicChangeRequest() {
    }

    public CoursePublicChangeRequest(Long courseId, boolean isPublic) {
        this.courseId = courseId;
        this.isPublic = isPublic;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
