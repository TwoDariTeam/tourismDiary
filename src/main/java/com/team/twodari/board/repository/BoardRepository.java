package com.team.twodari.board.repository;

import com.team.twodari.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity,Long>,BoardSearchRepository, BoardMyPageRepository {
    @Query("SELECT b FROM BoardEntity b LEFT JOIN FETCH b.images WHERE b.boardSeq = :boardSeq")
    Optional<BoardEntity> findByBoardSeqWithImages(@Param("boardSeq") Long boardSeq);
}
