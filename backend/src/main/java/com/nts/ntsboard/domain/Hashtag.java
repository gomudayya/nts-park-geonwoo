package com.nts.ntsboard.domain;

import lombok.Getter;

@Getter
public class Hashtag {
    private Long id;
    private String tagName;

    public Hashtag(String tagName) {
        this.tagName = tagName;
    }

    public Hashtag(Long id, String tagName) {
        this.id = id;
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return "Hashtag{" +
                "id=" + id +
                ", tagName='" + tagName + '\'' +
                '}';
    }
}
