package com.webmuffins.rtsx.board.entity;

import com.webmuffins.rtsx.board.constants.Complexity;
import com.webmuffins.rtsx.board.constants.Priority;
import com.webmuffins.rtsx.board.constants.TicketType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

@Entity(name = "Ticket")
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TicketType type;

    @Column(name = "position")
    private int position;

    @Column(name = "complexity")
    @Enumerated(EnumType.STRING)
    private Complexity complexity;

    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(name = "ticket_tag", joinColumns = @JoinColumn(name = "ticket_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "row_id")
    private BoardRow boardRow;

    @Column(name = "user_id")
    private Long userId;

    public Ticket() {
    }

    public Ticket(UUID id, String title, TicketType type, int position, Complexity complexity, Priority priority, String description, List<Tag> tags,
            BoardRow boardRow, Long userId) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.position = position;
        this.complexity = complexity;
        this.priority = priority;
        this.description = description;
        this.tags = tags;
        this.boardRow = boardRow;
        this.userId = userId;
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

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public BoardRow getBoardRow() {
        return boardRow;
    }

    public void setBoardRow(BoardRow boardRow) {
        this.boardRow = boardRow;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long assignee) {
        this.userId = assignee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ticket)) {
            return false;
        }
        Ticket ticket = (Ticket) o;
        return getPosition() == ticket.getPosition() && Objects.equals(getId(), ticket.getId()) && Objects.equals(getTitle(), ticket.getTitle())
                && getType() == ticket.getType() && getComplexity() == ticket.getComplexity() && getPriority() == ticket.getPriority()
                && Objects.equals(getDescription(), ticket.getDescription()) && Objects.equals(getTags(), ticket.getTags()) && Objects.equals(
                getBoardRow(), ticket.getBoardRow()) && Objects.equals(getUserId(), ticket.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getType(), getPosition(), getComplexity(), getPriority(), getDescription(), getTags(), getBoardRow(),
                getUserId()
        );
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Ticket.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("title='" + title + "'")
                .add("type=" + type)
                .add("position=" + position)
                .add("complexity=" + complexity)
                .add("priority=" + priority)
                .add("description='" + description + "'")
                .add("tags=" + tags)
                .add("boardRow=" + boardRow)
                .add("userId=" + userId).toString();
    }
}
