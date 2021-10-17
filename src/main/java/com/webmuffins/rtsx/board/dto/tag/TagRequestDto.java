package com.webmuffins.rtsx.board.dto.tag;

import java.util.Objects;
import java.util.StringJoiner;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TagRequestDto {

    @NotNull(message = "Ticket name can not be null")
    @NotBlank(message = "Ticket name can not be blank")
    private String name;

    public TagRequestDto() {
    }

    public TagRequestDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TagRequestDto)) {
            return false;
        }
        TagRequestDto that = (TagRequestDto) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TagRequestDto.class.getSimpleName() + "[", "]").add("name='" + name + "'").toString();
    }
}
