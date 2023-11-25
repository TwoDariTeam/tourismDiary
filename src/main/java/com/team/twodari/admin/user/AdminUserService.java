package com.team.twodari.admin.user;

import com.team.twodari.admin.exception.BusinessLogicException;
import com.team.twodari.admin.exception.ExceptionCode;
import com.team.twodari.common.constant.UserRoleConfig;
import com.team.twodari.user.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

@Service
public class AdminUserService {

    private final AdminUserRepository adminUserRepository;
    private final AdminUserRoleRepository adminUserRoleRepository;

    public AdminUserService(AdminUserRepository adminUserRepository, AdminUserRoleRepository adminUserRoleRepository) {
        this.adminUserRepository = adminUserRepository;
        this.adminUserRoleRepository = adminUserRoleRepository;
    }

    public Page<UserEntity> getAllUsers(Pageable pageable) {
       return adminUserRepository.findAll(pageable);
    }

    public UserRoleEntity updateUser(Long userSeq) {
        validateExistsUser(userSeq);

        UserRoleEntity userRoleEntity = adminUserRoleRepository.findById(userSeq)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_ROLE_NOT_FOUND));
        userRoleEntity.setRoleSeq(UserRoleConfig.UserRole.BLOCK.getLevel()); // 차단

        return adminUserRoleRepository.save(userRoleEntity);
    }

    public UserEntity deleteUser(Long userSeq) {
        UserEntity userEntity = validateExistsUser(userSeq);
        userEntity.setDeleted("Y"); // 탈퇴

        return adminUserRepository.save(userEntity);
    }

    private UserEntity validateExistsUser(Long userSeq) {
        return adminUserRepository.findById(userSeq)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
    }
}