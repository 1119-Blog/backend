package com.petitjy.threadit.domain.comment.entity;

public enum CommentVisibility {
    PUBLIC("PUBLIC"),
    PRIVATE("PRIVATE");

    private final String visibility;

    CommentVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String value() {
        return visibility;
    }
}
