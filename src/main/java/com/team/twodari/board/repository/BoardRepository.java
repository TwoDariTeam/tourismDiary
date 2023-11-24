package com.team.twodari.board.repository;

import com.team.twodari.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity,Long>,BoardSearchRepository, BoardMyPageRepository {
}
