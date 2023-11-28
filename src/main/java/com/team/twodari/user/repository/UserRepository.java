package com.team.twodari.user.repository;

import com.team.twodari.user.entity.LoginEntity;
import com.team.twodari.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

   //로그인 시 사용
   @Query(value = "SELECT u.EMAIL, u.NICKNAME, u.PASSWORD, ur.ROLE_SEQ FROM TB_USER u JOIN MP_USER_ROLE ur ON u.USER_SEQ = ur.USER_SEQ WHERE u.EMAIL = :email", nativeQuery = true)
   Optional<LoginEntity> findByEmail(@Param(value = "email")String email);
   //querydsl

   //중복 아이디 체크
   @Query(value = "SELECT u FROM UserEntity u Where u.email =:email")
   UserEntity existByEmail(@Param(value = "email") String email);

   //회원정보 수정할 때 사용
   @Query(value = "SELECT u FROM UserEntity u Where u.email =:email")
   Optional<UserEntity> findUserByEmail(@Param(value = "email")String email);

   //닉네임 중복 체크
   @Query(value = "SELECT u FROM UserEntity u Where u.nickname=:nickname")
   Optional<UserEntity> findByNickname(@Param(value = "nickname")String nickname); //중복이면 true 없으면 false
   //Query사용한 녀석은 항상 객체를 반환한다.
}
