package com.webmuffins.rtsx.board.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface Mapper<T, R> {
    R mapEntityToDto(T t);

    T mapDtoToEntity(R r);

    default List<R> mapEntityListToDtoList(List<T> entries) {
        return entries.stream().map(this::mapEntityToDto).collect(Collectors.toList());
    }

    default List<T> mapDtoListToEntityList(List<R> entries) {
        return entries.stream().map(this::mapDtoToEntity).collect(Collectors.toList());
    }
}
