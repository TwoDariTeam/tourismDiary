package com.team.twodari.image.repository;

import java.util.List;

import com.team.twodari.image.entity.BoardImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardImageRepository extends JpaRepository<BoardImageEntity, Long> {


	//TODO querydsl로 변경
	@Query("SELECT i FROM BoardImageEntity i WHERE i.boardImageSeq IN (SELECT MAX(img.boardImageSeq) FROM BoardImageEntity img WHERE img.board.boardSeq IN :boardSeqs AND img.deleted != 'Y' GROUP BY img.board.boardSeq)")
	List<BoardImageEntity> findFirstByBoardSeqs(@Param("boardSeqs") List<Long> boardSeqs);
}
