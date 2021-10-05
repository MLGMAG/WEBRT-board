package com.webmuffins.rtsx.board.service;

import java.util.List;
import java.util.UUID;

import com.webmuffins.rtsx.board.dto.tag.TagRequestDto;
import com.webmuffins.rtsx.board.dto.tag.TagResponseDto;
import com.webmuffins.rtsx.board.entity.Tag;

public interface TagService {

    List<TagResponseDto> getAllTags();

    TagResponseDto getTagById(UUID id);

    TagResponseDto createTag(TagRequestDto dto);

    TagResponseDto updateTagById(UUID id, TagRequestDto dto);

    void deleteTagById(UUID id);

    Tag getEntityById(UUID id);

}
