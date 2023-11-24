package com.team.twodari.point.repository;

import com.team.twodari.point.entity.PointEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<PointEntity,Long> {
}
