package com.webmuffins.rtsx.board.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webmuffins.rtsx.board.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, UUID> {}
