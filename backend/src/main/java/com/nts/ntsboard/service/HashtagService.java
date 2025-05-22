package com.nts.ntsboard.service;

import com.nts.ntsboard.domain.Hashtag;
import com.nts.ntsboard.repository.HashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class HashtagService {

    private final HashtagRepository hashtagRepository;

    /**
     * 기존에 DB에 존재하는 해시태그는 조회하고,
     * 존재하지 않는 태그는 새로 생성하여 저장한 후,
     * 모든 해시태그를 합쳐서 반환합니다.
     *
     * @param tagNames 해시태그 이름 리스트
     * @return 존재하는 해시태그와 새로 생성된 해시태그를 모두 포함한 리스트
     */
    @Transactional
    public List<Hashtag> findOrCreateHashtags(List<String> tagNames) {
        List<Hashtag> existingHashtags = hashtagRepository.findAllByTagNameIn(tagNames);
        Set<String> existingNames = existingHashtags.stream().map(Hashtag::getTagName).collect(Collectors.toSet());

        List<Hashtag> newHashtags = tagNames.stream()
                .filter(tag -> !existingNames.contains(tag))
                .map(Hashtag::new)
                .toList();

        newHashtags = hashtagRepository.saveAll(newHashtags);
        return Stream.concat(existingHashtags.stream(), newHashtags.stream()).toList();
    }
}
