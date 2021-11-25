package com.webmuffins.rtsx.board.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webmuffins.rtsx.board.entity.BoardRow;

@Repository
public interface BoardRowRepository extends JpaRepository<BoardRow, UUID> {

    List<BoardRow> findBoardRowByBoard_Id(UUID boardId);

}
