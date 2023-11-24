package com.team.twodari.subBoard.repository;

import com.team.twodari.subBoard.entity.SubBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubBoardRepository extends JpaRepository<SubBoardEntity, Long> {
    List<SubBoardEntity> findByBoardBoardSeq(Long boardSeq);
}
