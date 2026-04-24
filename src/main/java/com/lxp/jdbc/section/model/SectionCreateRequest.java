package com.lxp.jdbc.section.model;

public class SectionCreateRequest {

    private Long courseId;
    private String sectionTitle;
    private boolean isPublic;

    public SectionCreateRequest() {
    }

    public SectionCreateRequest(Long courseId, String sectionTitle, boolean isPublic) {
        this.courseId = courseId;
        this.sectionTitle = sectionTitle;
        this.isPublic = isPublic;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getSectionTitle() {
        return sectionTitle;
    }

    public void setSectionTitle(String sectionTitle) {
        this.sectionTitle = sectionTitle;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
