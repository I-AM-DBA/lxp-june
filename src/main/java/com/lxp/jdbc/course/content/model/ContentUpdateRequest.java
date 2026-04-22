package com.lxp.jdbc.course.content.model;

public class ContentUpdateRequest {

    private Long contentsId;
    private String contentTitle;
    private String contentUrl;
    private Integer time;
    private Boolean isPublic;
    private Boolean isFree;

    public ContentUpdateRequest() {
    }

    public Long getContentsId() {
        return contentsId;
    }

    public void setContentsId(Long contentsId) {
        this.contentsId = contentsId;
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

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public Boolean getIsFree() {
        return isFree;
    }

    public void setIsFree(Boolean isFree) {
        this.isFree = isFree;
    }
}
