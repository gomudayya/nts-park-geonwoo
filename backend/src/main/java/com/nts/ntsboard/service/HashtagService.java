package com.nts.ntsboard.service;

import com.nts.ntsboard.domain.Hashtag;
import com.nts.ntsboard.repository.HashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class HashtagService {

    private final HashtagRepository hashtagRepository;

    public List<Hashtag> syncHashtags(List<String> tagNames) {
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
