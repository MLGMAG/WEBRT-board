package com.webmuffins.rtsx.board.mapper;

import com.webmuffins.rtsx.board.dto.TagDto;
import com.webmuffins.rtsx.board.entity.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagMapper implements Mapper<Tag, TagDto> {

    @Override
    public TagDto mapEntityToDto(Tag entity) {
        if (entity == null) {
            return null;
        }

        TagDto dto = new TagDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    @Override
    public Tag mapDtoToEntity(TagDto dto) {
        if (dto == null) {
            return null;
        }

        Tag tag = new Tag();
        tag.setId(dto.getId());
        tag.setName(dto.getName());

        return tag;
    }
}
