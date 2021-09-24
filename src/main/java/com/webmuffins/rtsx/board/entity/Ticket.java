package com.webmuffins.rtsx.board.entity;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

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
    private String type;

    @Column(name = "uid")
    private String uid;

    @Column(name = "position")
    private int position;

    @Column(name = "story_points")
    private int storyPoints;

    @ManyToMany
    @JoinTable(name = "ticket_tag",
            joinColumns = @JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "column_id")
    private BoardColumn boardColumn;

    public Ticket() {
    }

    public Ticket(UUID id, String title, String type, String uid, int position, int storyPoints, List<Tag> tags, BoardColumn boardColumn) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.uid = uid;
        this.position = position;
        this.storyPoints = storyPoints;
        this.tags = tags;
        this.boardColumn = boardColumn;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getStoryPoints() {
        return storyPoints;
    }

    public void setStoryPoints(int storyPoints) {
        this.storyPoints = storyPoints;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public BoardColumn getBoardColumn() {
        return boardColumn;
    }

    public void setBoardColumn(BoardColumn boardColumn) {
        this.boardColumn = boardColumn;
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
        return getPosition() == ticket.getPosition() &&
                getStoryPoints() == ticket.getStoryPoints() &&
                Objects.equals(getId(), ticket.getId())
                && Objects.equals(getTitle(), ticket.getTitle()) &&
                Objects.equals(getType(), ticket.getType()) &&
                Objects.equals(getUid(), ticket.getUid()) &&
                Objects.equals(getTags(), ticket.getTags()) &&
                Objects.equals(getBoardColumn(), ticket.getBoardColumn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getType(), getUid(), getPosition(), getStoryPoints(), getTags(), getBoardColumn());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Ticket.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("title='" + title + "'")
                .add("type='" + type + "'")
                .add("uid='" + uid + "'")
                .add("position=" + position)
                .add("storyPoints=" + storyPoints)
                .add("tags=" + tags)
                .add("boardColumn=" + boardColumn)
                .toString();
    }
}
