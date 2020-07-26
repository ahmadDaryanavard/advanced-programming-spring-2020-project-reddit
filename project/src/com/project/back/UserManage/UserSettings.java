package com.project.back.UserManage;

public class UserSettings {

    private boolean sortCommentsByScore ;
    private boolean sortPostsByScore ;
    private boolean showSubRedditPostsInPostPage ;
    private boolean hideRealName ;
    

    public UserSettings() {
        this.hideRealName = false;
        this.showSubRedditPostsInPostPage = true;
        this.sortCommentsByScore = true;
        this.sortPostsByScore = true;
    }

    public boolean isSortCommentsByScore() {
        return sortCommentsByScore;
    }

    public boolean isSortPostsByScore() {
        return sortPostsByScore;
    }

    public boolean isShowSubRedditPostsInPostPage() {
        return showSubRedditPostsInPostPage;
    }

    public boolean isHideRealName() {
        return hideRealName;
    }

    public void changeHideRealName(){
        if (this.isHideRealName()){
            this.hideRealName = false;
        }else {
            this.hideRealName = true;
        }
    }
    public void changePostByScore(){
        if (this.isSortPostsByScore()){
            this.sortPostsByScore = false;
        }else {
            this.sortPostsByScore = true;
        }
    }

    public void changeCommentByScore(){
        if (this.isSortCommentsByScore()){
            this.sortCommentsByScore = false;
        }else {
            this.sortCommentsByScore = true;
        }
    }

    public void changeShowSubReddit(){
        if (this.showSubRedditPostsInPostPage){
            this.showSubRedditPostsInPostPage = false;
        }else {
            this.showSubRedditPostsInPostPage = true;
        }
    }
}
