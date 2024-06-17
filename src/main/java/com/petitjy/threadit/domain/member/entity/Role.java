package com.petitjy.threadit.domain.member.entity;

public enum Role {
    MEMBER("MEMBER"),
    ANONYMOUS("ANONYMOUS"),
    ADMIN("ADMIN");

    final String role;

    Role(String role) {
        this.role = role;
    }

    public String value() {
        return role;
    }
}