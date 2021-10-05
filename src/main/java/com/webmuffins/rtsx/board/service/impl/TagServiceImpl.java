package com.webmuffins.rtsx.board.service.impl;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.webmuffins.rtsx.board.dto.tag.TagRequestDto;
import com.webmuffins.rtsx.board.dto.tag.TagResponseDto;
import com.webmuffins.rtsx.board.entity.Tag;
import com.webmuffins.rtsx.board.exception.NotFoundException;
import com.webmuffins.rtsx.board.mapper.Mapper;
import com.webmuffins.rtsx.board.mapper.TagMapper;
import com.webmuffins.rtsx.board.repository.TagRepository;
import com.webmuffins.rtsx.board.service.TagService;

@Service
public class TagServiceImpl implements TagService {

    private static final Logger LOG = LoggerFactory.getLogger(TagServiceImpl.class);

    private final TagRepository tagRepository;
    private final Mapper<Tag, TagRequestDto, TagResponseDto> tagMapper;

    public TagServiceImpl(TagRepository tagRepository, TagMapper tagMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }

    @Override
    public List<TagResponseDto> getAllTags() {
        List<Tag> tags = tagRepository.findAll();
        return tagMapper.mapEntityListToDtoList(tags);
    }

    @Override
    public TagResponseDto getTagById(UUID id) {
        Tag tag = getEntityById(id);
        return tagMapper.mapEntityToDto(tag);
    }

    @Override
    public TagResponseDto createTag(TagRequestDto dto) {
        Tag tag = tagMapper.mapDtoToEntity(dto);
        Tag savedTag = tagRepository.save(tag);
        LOG.info("Create tag with name: '{}'", dto.getName());
        return tagMapper.mapEntityToDto(savedTag);
    }

    @Override
    public TagResponseDto updateTagById(UUID id, TagRequestDto dto) {
        Tag tagToUpdate = tagMapper.mapDtoToEntity(dto);
        tagToUpdate.setId(id);
        Tag updatedTag = tagRepository.save(tagToUpdate);
        return tagMapper.mapEntityToDto(updatedTag);
    }

    @Override
    public void deleteTagById(UUID id) {
        boolean tagExists = tagRepository.existsById(id);
        if(!tagExists) {
            throw new NotFoundException("Can not find tag with such id");
        }
        tagRepository.deleteById(id);
        LOG.info("Delete tag with id: '{}'", id);
    }

    @Override
    public Tag getEntityById(UUID id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Can not find tag with such id"));
    }

}
