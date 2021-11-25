package com.webmuffins.rtsx.board.dto.board;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

import com.webmuffins.rtsx.board.dto.boardrow.BoardRowResponseDto;

public class BoardResponseDto {

    private UUID id;
    private String name;
    private List<BoardRowResponseDto> rows;

    public String getName() {
        return name;
    }

    public void setTitle(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BoardRowResponseDto> getRows() {
        return rows;
    }

    public void setRows(List<BoardRowResponseDto> rows) {
        this.rows = rows;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BoardResponseDto)) {
            return false;
        }
        BoardResponseDto that = (BoardResponseDto) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getRows(), that.getRows());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getRows());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BoardResponseDto.class.getSimpleName() + "[", "]").add("id=" + id).add("name='" + name + "'")
                .add("rows=" + rows).toString();
    }
}
