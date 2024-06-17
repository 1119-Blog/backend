package com.petitjy.threadit.domain.article.entity;

public enum ArticleVisibility {
    PUBLIC("PUBLIC"),
    PRIVATE("PRIVATE");

    private final String visibility;

    ArticleVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String value() {
        return visibility;
    }
}
