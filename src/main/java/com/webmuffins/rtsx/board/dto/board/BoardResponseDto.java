package com.webmuffins.rtsx.board.dto.board;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

public class BoardResponseDto {

    private UUID id;
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BoardResponseDto)) {
            return false;
        }
        BoardResponseDto that = (BoardResponseDto) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BoardResponseDto.class.getSimpleName() + "[", "]").add("id=" + id).add("name='" + name + "'").toString();
    }
}
