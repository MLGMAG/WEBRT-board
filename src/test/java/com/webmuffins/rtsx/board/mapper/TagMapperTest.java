package com.webmuffins.rtsx.board.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.webmuffins.rtsx.board.dto.tag.TagRequestDto;
import com.webmuffins.rtsx.board.dto.tag.TagResponseDto;
import com.webmuffins.rtsx.board.entity.Tag;

class TagMapperTest {

    private static final Long DEFAULT_ID = 1L;
    private static final String DEFAULT_NAME = "name";

    private Tag tag;
    private TagResponseDto tagResponseDto;
    private TagRequestDto tagRequestDto;
    private List<Tag> tagList;
    private List<TagResponseDto> tagResponseDtoList;
    private List<TagRequestDto> tagRequestDtoList;

    private TagMapper testInstance = new TagMapper();

    @BeforeEach
    void setUp() {
        tag = new Tag();
        tagRequestDto = new TagRequestDto();
        tagResponseDto = new TagResponseDto();

        tag.setId(DEFAULT_ID);
        tag.setName(DEFAULT_NAME);

        tagResponseDto.setId(DEFAULT_ID);
        tagResponseDto.setName(DEFAULT_NAME);

        tagRequestDto.setName(DEFAULT_NAME);

        tagList  = Collections.singletonList(tag);
        tagRequestDtoList = Collections.singletonList(tagRequestDto);
        tagResponseDtoList = Collections.singletonList(tagResponseDto);
    }

    @Test
    void shouldMapDtoToEntity() {
        tag.setId(null);
        Tag actual = testInstance.mapDtoToEntity(tagRequestDto);

        assertThat(actual)
                .isNotNull()
                .isEqualTo(tag);
    }

    @Test
    void shouldMapEntityToDto() {
        TagResponseDto actual = testInstance.mapEntityToDto(tag);

        assertThat(actual)
                .isNotNull()
                .isEqualTo(tagResponseDto);
    }

    @Test
    void shouldMapDtoListToEntityList() {
        tag.setId(null);
        List<Tag> actual = testInstance.mapDtoListToEntityList(tagRequestDtoList);

        assertThat(actual).isNotNull()
                .hasSize(1)
                .isEqualTo(tagList);
    }

    @Test
    void shouldMapEntityListToDtoList() {
        List<TagResponseDto> actual = testInstance.mapEntityListToDtoList(tagList);

        assertThat(actual).isNotNull()
                .hasSize(1)
                .isEqualTo(tagResponseDtoList);
    }
}
