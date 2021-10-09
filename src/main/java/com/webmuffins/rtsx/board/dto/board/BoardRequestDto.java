package com.webmuffins.rtsx.board.dto.board;

import java.util.Objects;
import java.util.StringJoiner;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BoardRequestDto {

    @NotNull
    @NotBlank
    private String name;

    public String getTitle() {
        return name;
    }

    public void setTitle(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BoardRequestDto)) {
            return false;
        }
        BoardRequestDto that = (BoardRequestDto) o;
        return Objects.equals(getTitle(), that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BoardRequestDto.class.getSimpleName() + "[", "]").add("name='" + name + "'").toString();
    }
}
