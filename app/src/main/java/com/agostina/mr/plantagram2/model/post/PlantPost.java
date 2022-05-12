package com.agostina.mr.plantagram2.model.post;

import java.util.ArrayList;
import java.util.List;

public class PlantPost {
    private String postId;
    private String authorsId;
    private String userName;
    private String userPicture;
    private String picture;
    private String plantName;
    private String plantDescription;
    private String authorComment;
    private List<Comment> comments;
    private List<String> likes;
    private String viewBy;

    public PlantPost() {
        likes = new ArrayList<>();
        comments = new ArrayList<>();
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getAuthorsId() {
        return authorsId;
    }

    public void setAuthorsId(String authorsId) {
        this.authorsId = authorsId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(String userPicture) {
        this.userPicture = userPicture;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getPlantDescription() {
        return plantDescription;
    }

    public void setPlantDescription(String plantDescription) {
        this.plantDescription = plantDescription;
    }

    public String getAuthorComment() {
        return authorComment;
    }

    public void setAuthorComment(String authorComment) {
        this.authorComment = authorComment;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<String> getLikes() {
        return likes;
    }

    public void setLikes(List<String> likes) {
        this.likes = likes;
    }

    public String getViewBy() {
        return viewBy;
    }

    public void setViewBy(String viewBy) {
        this.viewBy = viewBy;
    }

    public boolean doesUserLikeIt(String userUid){
        return likes.stream().anyMatch(s -> s.equals(userUid));
        }
    }



