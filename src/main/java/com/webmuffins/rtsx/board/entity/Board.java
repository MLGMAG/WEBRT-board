package com.webmuffins.rtsx.board.entity;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "Board")
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "title")
    private String title;

    @OneToMany
    private List<BoardRow> rows;

    @Column(name = "code")
    private String code;

    public Board() {
    }

    public Board(UUID id, String title, List<BoardRow> rows, String code) {
        this.id = id;
        this.title = title;
        this.rows = rows;
        this.code = code;
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

    public List<BoardRow> getRows() {
        return rows;
    }

    public void setRows(List<BoardRow> rows) {
        this.rows = rows;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Board)) {
            return false;
        }
        Board board = (Board) o;
        return Objects.equals(getId(), board.getId()) && Objects.equals(getTitle(), board.getTitle()) && Objects.equals(getRows(), board.getRows())
                && Objects.equals(getCode(), board.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getRows(), getCode());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Board.class.getSimpleName() + "[", "]").add("id=" + id).add("title='" + title + "'").add("columns=" + rows)
                .add("code='" + code + "'").toString();
    }
}
