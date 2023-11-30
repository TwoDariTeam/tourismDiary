package com.team.twodari.user.repository;

import com.team.twodari.user.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<RoleEntity,Long> {
    @Query(value = "SELECT u FROM RoleEntity u Where u.userSeq=:userSeq")
    Optional<RoleEntity> findByUserSeq(@Param("userSeq") Long userSeq);
}
