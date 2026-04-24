package com.lxp.jdbc.section.model;

public class SectionPublicChangeRequest {

    private Long sectionId;
    private boolean isPublic;

    public SectionPublicChangeRequest() {
    }

    public SectionPublicChangeRequest(Long sectionId, boolean isPublic) {
        this.sectionId = sectionId;
        this.isPublic = isPublic;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
