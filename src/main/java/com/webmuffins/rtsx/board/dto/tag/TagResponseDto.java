package com.webmuffins.rtsx.board.dto.tag;

import java.util.Objects;
import java.util.StringJoiner;

public class TagResponseDto {

    private Long id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TagResponseDto)) {
            return false;
        }
        TagResponseDto dto = (TagResponseDto) o;
        return Objects.equals(getId(), dto.getId()) &&
                Objects.equals(getName(), dto.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TagResponseDto.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }
}
