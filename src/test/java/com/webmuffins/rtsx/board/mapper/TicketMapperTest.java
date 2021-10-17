package com.webmuffins.rtsx.board.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.webmuffins.rtsx.board.constants.Complexity;
import com.webmuffins.rtsx.board.constants.Priority;
import com.webmuffins.rtsx.board.constants.TicketType;
import com.webmuffins.rtsx.board.dto.tag.TagRequestDto;
import com.webmuffins.rtsx.board.dto.tag.TagResponseDto;
import com.webmuffins.rtsx.board.dto.ticket.TicketRequestDto;
import com.webmuffins.rtsx.board.dto.ticket.TicketResponseDto;
import com.webmuffins.rtsx.board.entity.Board;
import com.webmuffins.rtsx.board.entity.BoardRow;
import com.webmuffins.rtsx.board.entity.Tag;
import com.webmuffins.rtsx.board.entity.Ticket;
import com.webmuffins.rtsx.board.repository.BoardRowRepository;
import com.webmuffins.rtsx.board.repository.TagRepository;

@ExtendWith(MockitoExtension.class)
class TicketMapperTest {

    private static final UUID DEFAULT_ID = UUID.randomUUID();
    private static final String DEFAULT_TITLE = "title";
    private static final TicketType DEFAULT_TYPE = TicketType.TASK;
    private static final Priority DEFAULT_PRIORITY = Priority.HIGH;
    private static final int DEFAULT_POSITION = 1;
    private static final Complexity DEFAULT_COMPLEXITY = Complexity.MEDIUM;

    private static final UUID DEFAULT_TAG_ID = UUID.randomUUID();
    private static final String DEFAULT_TAG_NAME = "tag name";

    private Tag tag;
    private TagResponseDto tagResponseDto;
    private TagRequestDto tagRequestDto;
    private List<Tag> tagList;
    private List<TagResponseDto> tagResponseDtoList;
    private List<TagRequestDto> tagRequestDtoList;

    private Ticket ticket;
    private TicketResponseDto ticketResponseDto;
    private TicketRequestDto ticketRequestDto;
    private List<TicketRequestDto> ticketRequestDtoList;
    private List<Ticket> ticketList;
    private List<TicketResponseDto> ticketResponseDtoList;

    @Mock
    private BoardRowRepository boardRowRepository;

    @Mock
    private TagRepository tagRepository;

    @Mock
    private Mapper<Tag, TagRequestDto, TagResponseDto> tagMapper;

    @InjectMocks
    private TicketMapper testInstance;

    @BeforeEach
    void setUp() {
        tag = new Tag();
        tag.setName(DEFAULT_TAG_NAME);
        tagRequestDto = new TagRequestDto();
        tagResponseDto = new TagResponseDto();
        tagResponseDto.setId(DEFAULT_TAG_ID);
        tagRequestDto.setName(DEFAULT_TAG_NAME);
        tagResponseDto.setName(DEFAULT_TAG_NAME);
        tagList  = Collections.singletonList(tag);
        tagRequestDtoList = Collections.singletonList(tagRequestDto);
        tagResponseDtoList = Collections.singletonList(tagResponseDto);

        ticket = new Ticket();
        ticketRequestDto = new TicketRequestDto();
        ticketResponseDto = new TicketResponseDto();
        ticketRequestDtoList = Collections.singletonList(ticketRequestDto);
        ticketList = Collections.singletonList(ticket);
        ticketResponseDtoList = Collections.singletonList(ticketResponseDto);

        ticket.setPriority(DEFAULT_PRIORITY);
        ticket.setComplexity(DEFAULT_COMPLEXITY);
        ticket.setType(DEFAULT_TYPE);
        ticket.setTitle(DEFAULT_TITLE);
        ticket.setPosition(DEFAULT_POSITION);
        ticket.setTags(tagList);
        ticket.setId(DEFAULT_ID);

        ticketResponseDto.setPriority(DEFAULT_PRIORITY);
        ticketResponseDto.setComplexity(DEFAULT_COMPLEXITY);
        ticketResponseDto.setType(DEFAULT_TYPE);
        ticketResponseDto.setTitle(DEFAULT_TITLE);
        ticketResponseDto.setPosition(DEFAULT_POSITION);
        ticketResponseDto.setTags(tagResponseDtoList);
        ticketResponseDto.setId(DEFAULT_ID);

        ticketRequestDto.setPriority(DEFAULT_PRIORITY);
        ticketRequestDto.setComplexity(DEFAULT_COMPLEXITY);
        ticketRequestDto.setType(DEFAULT_TYPE);
        ticketRequestDto.setTitle(DEFAULT_TITLE);
        ticketRequestDto.setPosition(DEFAULT_POSITION);
        ticketRequestDto.setTags(tagRequestDtoList);
    }

    @Test
    void shouldMapDtoToEntity() {
        ticket.setId(null);

        when(tagRepository.findAllByNameIn(Collections.singletonList(DEFAULT_TAG_NAME))).thenReturn(tagList);
        Ticket actual = testInstance.mapDtoToEntity(ticketRequestDto);

        assertThat(actual).isNotNull()
                .isEqualTo(ticket);
    }

    @Test
    void shouldMapEntityToDto() {
        when(tagMapper.mapEntityListToDtoList(tagList)).thenReturn(tagResponseDtoList);

        TicketResponseDto actual = testInstance.mapEntityToDto(ticket);

        assertThat(actual).isNotNull()
                .isEqualTo(ticketResponseDto);
    }

    @Test
    void shouldMapEntityListToDtoList() {
        when(tagMapper.mapEntityListToDtoList(tagList)).thenReturn(tagResponseDtoList);

        List<TicketResponseDto> actual = testInstance.mapEntityListToDtoList(ticketList);

        assertThat(actual).isNotNull()
                .hasSize(1)
                .isEqualTo(ticketResponseDtoList);
    }

    @Test
    void shouldMapDtoListToEntityList() {
        ticket.setId(null);

        when(tagRepository.findAllByNameIn(Collections.singletonList(DEFAULT_TAG_NAME))).thenReturn(tagList);
        List<Ticket> actual = testInstance.mapDtoListToEntityList(ticketRequestDtoList);

        assertThat(actual).isNotNull()
                .hasSize(1)
                .isEqualTo(ticketList);
    }
}
