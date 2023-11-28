package com.team.twodari.point.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team.twodari.point.entity.PointEntity;

public interface PointRepository extends JpaRepository<PointEntity, Long> {
}
