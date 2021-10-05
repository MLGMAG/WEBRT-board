package com.webmuffins.rtsx.board.service;

import java.util.List;

import com.webmuffins.rtsx.board.dto.tag.TagRequestDto;
import com.webmuffins.rtsx.board.dto.tag.TagResponseDto;
import com.webmuffins.rtsx.board.entity.Tag;

public interface TagService {

    List<TagResponseDto> getAllTags();

    TagResponseDto getTagById(Long id);

    TagResponseDto createTag(TagRequestDto dto);

    TagResponseDto updateTagById(Long id, TagRequestDto dto);

    void deleteTagById(Long id);

    Tag getEntityById(Long id);

}
