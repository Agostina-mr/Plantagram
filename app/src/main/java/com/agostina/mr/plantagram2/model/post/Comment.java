package com.agostina.mr.plantagram2.model.post;

public class Comment {

    private String postAuthorId;
    private String commentsAuthorId;
    private String commentsAuthorName;
    private String body;


    public Comment() {
    }

    public Comment(String commentsAuthorId, String commentsAuthorName, String body) {
        this.commentsAuthorId = commentsAuthorId;
        this.commentsAuthorName = commentsAuthorName;
        this.body = body;
    }

    public String getPostAuthorId() {
        return postAuthorId;
    }

    public void setPostAuthorId(String postAuthorId) {
        this.postAuthorId = postAuthorId;
    }

    public String getCommentsAuthorId() {
        return commentsAuthorId;
    }

    public void setCommentsAuthorId(String commentsAuthorId) {
        this.commentsAuthorId = commentsAuthorId;
    }

    public String getCommentsAuthorName() {
        return commentsAuthorName;
    }

    public void setCommentsAuthorName(String commentsAuthorName) {
        this.commentsAuthorName = commentsAuthorName;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
