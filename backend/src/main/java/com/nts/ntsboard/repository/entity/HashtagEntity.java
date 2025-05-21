package com.nts.ntsboard.repository.entity;

import com.nts.ntsboard.domain.Hashtag;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;


@Getter
@Entity
@Table(name = "hashtag")
public class HashtagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tagName;

    public static HashtagEntity from(Hashtag hashtag) {
        HashtagEntity hashtagEntity = new HashtagEntity();
        hashtagEntity.id = hashtag.getId();
        hashtagEntity.tagName = hashtag.getTagName();
        return hashtagEntity;
    }

    public Hashtag toModel() {
        return new Hashtag(id, tagName);
    }
}
