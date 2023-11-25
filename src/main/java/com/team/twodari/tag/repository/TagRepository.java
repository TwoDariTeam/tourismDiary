package com.team.twodari.tag.repository;

import com.team.twodari.tag.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends JpaRepository<TagEntity, Long> {

    @Query("SELECT t.tag " +
            "from TagEntity t " +
            "where t.boardSeq = :boardSeq")
    List<String> findTagNamesByBoardSeq(@Param("boardSeq") Long boardSeq);

}
