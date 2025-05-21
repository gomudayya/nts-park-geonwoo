package com.nts.ntsboard.repository;

import com.nts.ntsboard.domain.Hashtag;
import com.nts.ntsboard.repository.entity.HashtagEntity;
import com.nts.ntsboard.repository.jpa.HashtagJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class HashtagRepository {
    private final HashtagJpaRepository hashtagJpaRepository;

    public List<Hashtag> findAllByTagNameIn(List<String> tagNames) {
        List<HashtagEntity> hashtagEntities = hashtagJpaRepository.findAllByTagNameIn(tagNames);
        return hashtagEntities.stream()
                .map(HashtagEntity::toModel)
                .toList();
    }

    public List<Hashtag> saveAll(List<Hashtag> hashtags) {
        List<HashtagEntity> hashtagEntities = hashtags.stream().map(HashtagEntity::from).toList();
        hashtagJpaRepository.saveAll(hashtagEntities);
        return hashtagEntities.stream().map(HashtagEntity::toModel).toList();
    }
}
