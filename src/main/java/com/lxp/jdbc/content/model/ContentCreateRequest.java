package com.lxp.jdbc.content.model;

public class ContentCreateRequest {

    private Long sectionId;
    private String contentTitle;
    private String contentUrl;
    private Integer time;
    private boolean isPublic;
    private boolean isFree;

    public ContentCreateRequest() {
    }

    public ContentCreateRequest(
            Long sectionId,
            String contentTitle,
            String contentUrl,
            Integer time,
            boolean isPublic,
            boolean isFree) {
        this.sectionId = sectionId;
        this.contentTitle = contentTitle;
        this.contentUrl = contentUrl;
        this.time = time;
        this.isPublic = isPublic;
        this.isFree = isFree;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }
}
