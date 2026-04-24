package com.lxp.jdbc.section.model;

public class SectionUpdateRequest {

    private Long sectionId;
    private String sectionTitle;
    private boolean isPublic;

    public SectionUpdateRequest() {
    }

    public SectionUpdateRequest(Long sectionId, String sectionTitle, boolean isPublic) {
        this.sectionId = sectionId;
        this.sectionTitle = sectionTitle;
        this.isPublic = isPublic;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
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
