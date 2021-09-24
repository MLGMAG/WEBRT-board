package com.webmuffins.rtsx.board.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.webmuffins.rtsx.board.dto.TagDto;
import com.webmuffins.rtsx.board.entity.Tag;

@Component
public class TagMapper {

    public TagDto mapEntityToDto(Tag entity) {
        if (entity == null) {
            return null;
        }

        TagDto dto = new TagDto();
        dto.setName(entity.getName());
        dto.setId(entity.getId());
        return dto;
    }

    public Tag mapDtoToEntity(TagDto dto) {
        if (dto == null) {
            return null;
        }

        Tag tag = new Tag();
        tag.setName(dto.getName());

        return tag;
    }

    public List<TagDto> mapEntityListToDtoList(List<Tag> entities) {
        List<TagDto> dtoList = new ArrayList<>();

        entities.forEach(tag -> dtoList.add(mapEntityToDto(tag)));
        return dtoList;
    }

}
