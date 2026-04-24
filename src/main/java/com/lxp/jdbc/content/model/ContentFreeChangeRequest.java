package com.lxp.jdbc.content.model;

public class ContentFreeChangeRequest {

    private Long contentsId;
    private boolean isFree;

    public ContentFreeChangeRequest() {
    }

    public ContentFreeChangeRequest(Long contentsId, boolean isFree) {
        this.contentsId = contentsId;
        this.isFree = isFree;
    }

    public Long getContentsId() {
        return contentsId;
    }

    public void setContentsId(Long contentsId) {
        this.contentsId = contentsId;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }
}
