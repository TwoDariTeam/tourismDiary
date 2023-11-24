package com.team.twodari.user.repository;

import com.team.twodari.user.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<RoleEntity,Long> {
}
