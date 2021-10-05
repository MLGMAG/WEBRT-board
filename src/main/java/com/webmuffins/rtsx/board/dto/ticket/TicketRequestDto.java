package com.webmuffins.rtsx.board.dto.ticket;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.webmuffins.rtsx.board.constants.Complexity;
import com.webmuffins.rtsx.board.constants.Priority;
import com.webmuffins.rtsx.board.constants.TicketType;
import com.webmuffins.rtsx.board.dto.tag.TagRequestDto;

public class TicketRequestDto {

    @NotNull(message = "Ticket title can not be null")
    @NotBlank(message = "Ticket title can not be blank")
    private String title;

    @NotNull(message = "Ticket type can not be null")
    private TicketType type;

    private int position;

    private Complexity complexity;
    private Priority priority;

    private List<TagRequestDto> tags;

    @NotNull(message = "Ticket row can not be null")
    private UUID rowId;

    public TicketRequestDto() {
    }

    public TicketRequestDto(String title, TicketType type, int position, Complexity complexity, Priority priority, List<TagRequestDto> tags, UUID rowId) {
        this.title = title;
        this.type = type;
        this.position = position;
        this.complexity = complexity;
        this.priority = priority;
        this.tags = tags;
        this.rowId = rowId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Complexity getComplexity() {
        return complexity;
    }

    public void setComplexity(Complexity complexity) {
        this.complexity = complexity;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public List<TagRequestDto> getTags() {
        return tags;
    }

    public void setTags(List<TagRequestDto> tags) {
        this.tags = tags;
    }

    public UUID getRowId() {
        return rowId;
    }

    public void setRowId(UUID rowId) {
        this.rowId = rowId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TicketRequestDto)) {
            return false;
        }
        TicketRequestDto that = (TicketRequestDto) o;
        return getPosition() == that.getPosition() && Objects.equals(getTitle(), that.getTitle()) && getType() == that.getType()
                && getComplexity() == that.getComplexity() && getPriority() == that.getPriority() && Objects.equals(getTags(), that.getTags())
                && Objects.equals(getRowId(), that.getRowId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getType(), getPosition(), getComplexity(), getPriority(), getTags(), getRowId());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TicketRequestDto.class.getSimpleName() + "[", "]").add("title='" + title + "'").add("ticketType=" + type)
                .add("position=" + position).add("complexity=" + complexity).add("priority=" + priority).add("tags=" + tags).add("rowId=" + rowId)
                .toString();
    }

}
