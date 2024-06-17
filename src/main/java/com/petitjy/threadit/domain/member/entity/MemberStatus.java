package com.petitjy.threadit.domain.member.entity;

public enum MemberStatus {
    ACTIVE("ACTIVE"),
    SUSPEND("SUSPEND"),
    DELETED("DELETED");

    final String status;

    MemberStatus(String status) {
        this.status = status;
    }

    public String value() {
        return status;
    }
}
