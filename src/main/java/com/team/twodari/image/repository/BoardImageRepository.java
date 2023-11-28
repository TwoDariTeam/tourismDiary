package com.team.twodari.image.repository;

import com.team.twodari.image.entity.BoardImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardImageRepository extends JpaRepository<BoardImageEntity, Long> {
    @Query("SELECT bi.path FROM BoardImageEntity bi " +
            "WHERE bi.board.boardSeq = :boardSeq")
    List<String> findByBoardSeq(@Param("boardSeq") Long boardSeq);
}
