package com.lxp.jdbc.content.model;

public class ContentUpdateRequest {

    private Long contentsId;
    private String contentTitle;
    private String contentUrl;
    private Integer time;
    private boolean isPublic;
    private boolean isFree;

    public ContentUpdateRequest() {
    }

    public ContentUpdateRequest(
            Long contentsId,
            String contentTitle,
            String contentUrl,
            Integer time,
            boolean isPublic,
            boolean isFree) {
        this.contentsId = contentsId;
        this.contentTitle = contentTitle;
        this.contentUrl = contentUrl;
        this.time = time;
        this.isPublic = isPublic;
        this.isFree = isFree;
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
