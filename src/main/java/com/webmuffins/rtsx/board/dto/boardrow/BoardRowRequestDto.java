package com.webmuffins.rtsx.board.dto.boardrow;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BoardRowRequestDto {

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    private String color;

    @NotNull
    private int position;

    @NotNull
    private UUID boardId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public UUID getBoardId() {
        return boardId;
    }

    public void setBoardId(UUID boardId) {
        this.boardId = boardId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BoardRowRequestDto)) {
            return false;
        }
        BoardRowRequestDto that = (BoardRowRequestDto) o;
        return getPosition() == that.getPosition() && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getColor(), that.getColor())
                && Objects.equals(getBoardId(), that.getBoardId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getColor(), getPosition(), getBoardId());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BoardRowRequestDto.class.getSimpleName() + "[", "]").add("title='" + title + "'").add("color='" + color + "'")
                .add("position=" + position).add("boardId=" + boardId).toString();
    }

}
