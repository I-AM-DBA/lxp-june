package com.lxp.jdbc.content.model;

import java.time.LocalDateTime;

public class Content {

    private Long contentsId;
    private Long sectionId;
    private String contentTitle;
    private String contentUrl;
    private Integer time;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private boolean isPublic;
    private boolean isDeleted;
    private boolean isFree;

    public Content() {
    }

    public Content(
            Long contentsId,
            Long sectionId,
            String contentTitle,
            String contentUrl,
            Integer time,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            LocalDateTime deletedAt,
            boolean isPublic,
            boolean isDeleted,
            boolean isFree) {
        this.contentsId = contentsId;
        this.sectionId = sectionId;
        this.contentTitle = contentTitle;
        this.contentUrl = contentUrl;
        this.time = time;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.isPublic = isPublic;
        this.isDeleted = isDeleted;
        this.isFree = isFree;
    }

    public Long getContentsId() {
        return contentsId;
    }

    public void setContentsId(Long contentsId) {
        this.contentsId = contentsId;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }
}
