package com.webmuffins.rtsx.board.mapper;

import com.webmuffins.rtsx.board.dto.tag.TagRequestDto;
import com.webmuffins.rtsx.board.dto.tag.TagResponseDto;
import com.webmuffins.rtsx.board.dto.ticket.TicketRequestDto;
import com.webmuffins.rtsx.board.dto.ticket.TicketResponseDto;
import com.webmuffins.rtsx.board.entity.Tag;
import com.webmuffins.rtsx.board.entity.Ticket;
import com.webmuffins.rtsx.board.repository.BoardRowRepository;
import com.webmuffins.rtsx.board.repository.TagRepository;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

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
        mapTags(entity, dto);
        dto.setTitle(entity.getTitle());
        dto.setType(entity.getType());
        dto.setPriority(entity.getPriority());
        dto.setComplexity(entity.getComplexity());
        dto.setDescription(entity.getDescription());

        return dto;
    }

    private void mapTags(Ticket entity, TicketResponseDto dto) {
        List<Tag> entityTags = entity.getTags();

        if (isNull(entityTags)) {
            dto.setTags(Collections.emptyList());
            return;
        }

        List<TagResponseDto> responseTags = tagMapper.mapEntityListToDtoList(entityTags);
        dto.setTags(responseTags);
    }

    @Override
    public Ticket mapDtoToEntity(TicketRequestDto dto) {
        Ticket entity = new Ticket();
        mapTags(dto, entity);
        entity.setPosition(dto.getPosition());
        entity.setTitle(dto.getTitle());
        entity.setType(dto.getType());
        entity.setComplexity(dto.getComplexity());
        entity.setPriority(dto.getPriority());
        entity.setBoardRow(boardRowRepository.getById(dto.getRowId()));
        entity.setDescription(dto.getDescription());

        return entity;
    }

    private void mapTags(TicketRequestDto dto, Ticket entity) {
        List<TagRequestDto> dtoTags = dto.getTags();

        if (isNull(dtoTags)) {
            entity.setTags(Collections.emptyList());
            return;
        }

        List<String> dtoTagNames = dtoTags.stream()
                .map(TagRequestDto::getName)
                .collect(Collectors.toList());
        List<Tag> entityTags = tagRepository.findAllByNameIn(dtoTagNames);
        entity.setTags(entityTags);
    }

}
