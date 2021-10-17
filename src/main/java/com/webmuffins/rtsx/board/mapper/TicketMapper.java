package com.webmuffins.rtsx.board.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.webmuffins.rtsx.board.dto.tag.TagRequestDto;
import com.webmuffins.rtsx.board.dto.tag.TagResponseDto;
import com.webmuffins.rtsx.board.dto.ticket.TicketRequestDto;
import com.webmuffins.rtsx.board.dto.ticket.TicketResponseDto;
import com.webmuffins.rtsx.board.entity.Tag;
import com.webmuffins.rtsx.board.entity.Ticket;
import com.webmuffins.rtsx.board.repository.BoardRowRepository;
import com.webmuffins.rtsx.board.repository.TagRepository;

@Component("ticketMapper")
public class TicketMapper implements Mapper<Ticket, TicketRequestDto, TicketResponseDto> {

    private final BoardRowRepository boardRowRepository;
    private final TagRepository tagRepository;
    private final Mapper<Tag, TagRequestDto, TagResponseDto> tagMapper;

    public TicketMapper(BoardRowRepository boardRowRepository, TagRepository tagRepository,
            Mapper<Tag, TagRequestDto, TagResponseDto> tagMapper) {
        this.boardRowRepository = boardRowRepository;
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }

    @Override
    public TicketResponseDto mapEntityToDto(Ticket entity) {
        TicketResponseDto dto = new TicketResponseDto();
        dto.setId(entity.getId());
        dto.setPosition(entity.getPosition());
        dto.setTags(tagMapper.mapEntityListToDtoList(entity.getTags()));
        dto.setTitle(entity.getTitle());
        dto.setType(entity.getType());
        dto.setPriority(entity.getPriority());
        dto.setComplexity(entity.getComplexity());

        return dto;
    }

    @Override
    public Ticket mapDtoToEntity(TicketRequestDto dto) {
        Ticket entity = new Ticket();
        List<String> tagNames = dto.getTags().stream()
                .map(TagRequestDto::getName)
                .collect(Collectors.toList());
        entity.setTags(tagRepository.findAllByNameIn(tagNames));
        entity.setPosition(dto.getPosition());
        entity.setTitle(dto.getTitle());
        entity.setType(dto.getType());
        entity.setComplexity(dto.getComplexity());
        entity.setPriority(dto.getPriority());
        entity.setBoardRow(boardRowRepository.getById(dto.getRowId()));
        return entity;
    }

}
