package com.webmuffins.rtsx.board.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webmuffins.rtsx.board.entity.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, UUID> {

    List<Tag> findAllByNameIn(List<String> names);

}
