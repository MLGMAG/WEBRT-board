package com.webmuffins.rtsx.board.mapper;

import com.webmuffins.rtsx.board.dto.TagDto;
import com.webmuffins.rtsx.board.entity.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class TagMapperTest {

    private static final UUID TAG_ID = UUID.randomUUID();
    private static final String TAG_NAME = "tagName";

    private Tag tag;
    private TagDto tagDto;

    private final TagMapper testInstance = new TagMapper();

    @BeforeEach
    void setUp() {
        tag = new Tag();
        tag.setId(TAG_ID);
        tag.setName(TAG_NAME);

        tagDto = new TagDto();
        tagDto.setId(TAG_ID);
        tagDto.setName(TAG_NAME);
    }

    @Test
    void shouldMapEntityToDto() {
        TagDto actual = testInstance.mapEntityToDto(tag);

        UUID actualId = actual.getId();
        assertThat(actualId).isEqualTo(TAG_ID);
        String actualName = actual.getName();
        assertThat(actualName).isEqualTo(TAG_NAME);
    }

    @Test
    void shouldMapDtoToEntity() {
        Tag actual = testInstance.mapDtoToEntity(tagDto);

        UUID actualId = actual.getId();
        assertThat(actualId).isEqualTo(TAG_ID);
        String actualName = actual.getName();
        assertThat(actualName).isEqualTo(TAG_NAME);
    }
}
