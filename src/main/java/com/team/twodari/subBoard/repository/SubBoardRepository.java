package com.team.twodari.subBoard.repository;

import com.team.twodari.subBoard.entity.SubBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SubBoardRepository extends JpaRepository<SubBoardEntity, Long> {
    @Query("SELECT s FROM SubBoardEntity s LEFT JOIN FETCH s.images WHERE s.subBoardSeq = :subBoardSeq")
    Optional<SubBoardEntity> findBySubBoardSeqWithImages(@Param("subBoardSeq") Long subBoardSeq);
}
