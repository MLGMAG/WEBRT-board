package com.webmuffins.rtsx.board.dto.ticket;

import com.webmuffins.rtsx.board.constants.Complexity;
import com.webmuffins.rtsx.board.constants.Priority;
import com.webmuffins.rtsx.board.constants.TicketType;
import com.webmuffins.rtsx.board.dto.tag.TagResponseDto;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

public class TicketResponseDto {

    private UUID id;
    private String title;
    private TicketType type;
    private int position;
    private Complexity complexity;
    private Priority priority;
    private List<TagResponseDto> tags;
    private String description;
    private Long assigneeId;

    public TicketResponseDto() {
    }

    public TicketResponseDto(UUID id, String title, TicketType type, int position, Complexity complexity, Priority priority, List<TagResponseDto> tags, String description,
            Long assigneeId) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.position = position;
        this.complexity = complexity;
        this.priority = priority;
        this.tags = tags;
        this.description = description;
        this.assigneeId = assigneeId;
    }

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

    public List<TagResponseDto> getTags() {
        return tags;
    }

    public void setTags(List<TagResponseDto> tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(Long assigneeId) {
        this.assigneeId = assigneeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TicketResponseDto)) {
            return false;
        }
        TicketResponseDto that = (TicketResponseDto) o;
        return getPosition() == that.getPosition() && Objects.equals(getId(), that.getId()) && Objects.equals(getTitle(), that.getTitle())
                && getType() == that.getType() && getComplexity() == that.getComplexity() && getPriority() == that.getPriority() && Objects.equals(
                getTags(), that.getTags()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(
                getAssigneeId(), that.getAssigneeId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getId(), getTitle(), getType(), getPosition(), getComplexity(), getPriority(), getTags(), getDescription(), getAssigneeId());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TicketResponseDto.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("title='" + title + "'")
                .add("type=" + type)
                .add("position=" + position)
                .add("complexity=" + complexity)
                .add("priority=" + priority)
                .add("tags=" + tags)
                .add("description='" + description + "'")
                .add("assigneeId=" + assigneeId).toString();
    }
}
