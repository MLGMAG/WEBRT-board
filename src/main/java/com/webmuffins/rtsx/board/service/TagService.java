package com.webmuffins.rtsx.board.service;

import java.util.List;
import java.util.UUID;

import com.webmuffins.rtsx.board.dto.TagDto;
import com.webmuffins.rtsx.board.entity.Tag;

public interface TagService {

    List<TagDto> getAllTags();

    TagDto getTagById(UUID id);

    TagDto createTag(TagDto dto);

    TagDto updateTagById(UUID id, TagDto dto);

    void deleteTagById(UUID id);

    Tag getEntityById(UUID id);

}
