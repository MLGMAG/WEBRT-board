package com.webmuffins.rtsx.board.dto.boardrow;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

import com.webmuffins.rtsx.board.dto.ticket.TicketResponseDto;

public class BoardRowResponseDto {

    private UUID id;
    private String title;
    private String color;
    private int positon;
    private UUID boardId;
    private List<TicketResponseDto> tickets;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public int getPositon() {
        return positon;
    }

    public void setPositon(int positon) {
        this.positon = positon;
    }

    public UUID getBoardId() {
        return boardId;
    }

    public void setBoardId(UUID boardId) {
        this.boardId = boardId;
    }

    public List<TicketResponseDto> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketResponseDto> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BoardRowResponseDto)) {
            return false;
        }
        BoardRowResponseDto that = (BoardRowResponseDto) o;
        return getPositon() == that.getPositon() && Objects.equals(getId(), that.getId()) && Objects.equals(getTitle(), that.getTitle()) && Objects
                .equals(getColor(), that.getColor()) && Objects.equals(getBoardId(), that.getBoardId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getColor(), getPositon(), getBoardId());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BoardRowResponseDto.class.getSimpleName() + "[", "]").add("id=" + id).add("title='" + title + "'")
                .add("color='" + color + "'").add("positon=" + positon).add("boardId=" + boardId).toString();
    }
}
