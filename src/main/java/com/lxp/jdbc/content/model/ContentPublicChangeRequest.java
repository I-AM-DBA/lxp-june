package com.lxp.jdbc.content.model;

public class ContentPublicChangeRequest {

    private Long contentsId;
    private boolean isPublic;

    public ContentPublicChangeRequest() {
    }

    public ContentPublicChangeRequest(Long contentsId, boolean isPublic) {
        this.contentsId = contentsId;
        this.isPublic = isPublic;
    }

    public Long getContentsId() {
        return contentsId;
    }

    public void setContentsId(Long contentsId) {
        this.contentsId = contentsId;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
