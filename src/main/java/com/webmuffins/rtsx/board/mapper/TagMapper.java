package com.webmuffins.rtsx.board.mapper;

import org.springframework.stereotype.Component;

import com.webmuffins.rtsx.board.dto.tag.TagRequestDto;
import com.webmuffins.rtsx.board.dto.tag.TagResponseDto;
import com.webmuffins.rtsx.board.entity.Tag;

@Component("tagMapper")
public class TagMapper implements Mapper<Tag, TagRequestDto, TagResponseDto> {

    @Override
    public TagResponseDto mapEntityToDto(Tag entity) {
        TagResponseDto dto = new TagResponseDto();
        dto.setName(entity.getName());
        dto.setId(entity.getId());
        return dto;
    }

    @Override
    public Tag mapDtoToEntity(TagRequestDto dto) {
        Tag tag = new Tag();
        tag.setName(dto.getName());
        return tag;
    }

}
