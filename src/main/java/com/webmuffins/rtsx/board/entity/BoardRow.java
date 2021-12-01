package com.webmuffins.rtsx.board.entity;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "BoardRow")
@Table(name = "board_row")
public class BoardRow {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "color")
    private String color;

    @Column(name = "position")
    private int position;

    @OneToMany(mappedBy = "boardRow")
    private List<Ticket> tickets;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    public BoardRow() {
    }

    public BoardRow(UUID id, String title, String color, int position, List<Ticket> tickets, Board board) {
        this.id = id;
        this.title = title;
        this.color = color;
        this.position = position;
        this.tickets = tickets;
        this.board = board;
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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BoardRow)) {
            return false;
        }
        BoardRow that = (BoardRow) o;
        return getPosition() == that.getPosition() &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getTitle(), that.getTitle()) &&
                Objects.equals(getColor(), that.getColor()) &&
                Objects.equals(getTickets(), that.getTickets()) &&
                Objects.equals(getBoard(), that.getBoard());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getColor(), getPosition(), getTickets(), getBoard());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BoardRow.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("title='" + title + "'")
                .add("color='" + color + "'")
                .add("position=" + position)
                .add("tickets=" + tickets)
                .add("board=" + board).toString();
    }

}
