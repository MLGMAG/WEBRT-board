package com.webmuffins.rtsx.board.entity;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.webmuffins.rtsx.board.constants.Complexity;
import com.webmuffins.rtsx.board.constants.Priority;
import com.webmuffins.rtsx.board.constants.TicketType;

@Entity(name = "Ticket")
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(generator = "ticket_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ticket_id_seq", sequenceName = "ticket_id_seq", allocationSize = 1)
    private Long id;

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
    private Priority priority;

    @ManyToMany
    @JoinTable(name = "ticket_tag", joinColumns = @JoinColumn(name = "ticket_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "row_id")
    private BoardRow boardRow;

    public Ticket() {
    }

    public Ticket(Long id, String title, TicketType type, int position, Complexity complexity, Priority priority, List<Tag> tags, BoardRow boardRow) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.position = position;
        this.complexity = complexity;
        this.priority = priority;
        this.tags = tags;
        this.boardRow = boardRow;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
                && getType() == ticket.getType() && getComplexity() == ticket.getComplexity() && getPriority() == ticket.getPriority() && Objects
                .equals(getTags(), ticket.getTags()) && Objects.equals(getBoardRow(), ticket.getBoardRow());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getType(), getPosition(), getComplexity(), getPriority(), getTags(), getBoardRow());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Ticket.class.getSimpleName() + "[", "]").add("id=" + id).add("title='" + title + "'").add("type=" + type)
                .add("position=" + position).add("complexity=" + complexity).add("priority=" + priority).add("tags=" + tags)
                .add("boardRow=" + boardRow).toString();
    }

}


