package com.team.twodari.image.repository;

import com.team.twodari.image.entity.BoardImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardImageRepository extends JpaRepository<BoardImageEntity, Long> {
}
