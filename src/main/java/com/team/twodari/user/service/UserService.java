package com.team.twodari.user.service;

import com.team.twodari.common.config.jwt.TokenProvider;
import com.team.twodari.common.constant.Constant;
import com.team.twodari.common.dto.TokenDTO;
import com.team.twodari.user.dto.*;
import com.team.twodari.user.entity.RoleEntity;
import com.team.twodari.user.entity.UserEntity;
import com.team.twodari.user.repository.UserRepository;
import com.team.twodari.user.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    //로그인 메서드
    // Token 반환
    public Optional<TokenDTO> login(Login login) {
        return userRepository.findByEmail(login.getEmail()) //이메일로 이메일, 닉네임, 비밀번호, 권한레벨 반환
                .map(loginEntity -> {
                    if (passwordEncoder.matches(login.getPassword(), loginEntity.getPassword())) {
                        Authentication authenticationToken = tokenProvider.convertAuthentication(loginEntity);
                        TokenDTO tokenDTO = tokenProvider.createToken(authenticationToken);
                        return Optional.of(tokenDTO);
                    } else
                        throw new BadCredentialsException(Constant.PASSWORD_COMPARE_FALSE_MESG);

                })
                .orElseThrow(() -> new UsernameNotFoundException(Constant.EMAIL_COMPARE_FALSE_MESG));
    }


    public String createUser(CreateUser createUser) {
        String resultMesg = Constant.FALSE_MESG;
        UserEntity resultUserInfo = createUserInfo(createUser);
        boolean resultUserRole = createUserRole(resultUserInfo.getUserSeq());
        if (resultUserInfo!=null && resultUserRole) {
            resultMesg = createUser.getNickname() + " 회원 가입 성공하셨습니다.";
            return resultMesg;
        }
        return Constant.FALSE_MESG;
    }

    private UserEntity createUserInfo(CreateUser createUser) {
        UserEntity savedEntity = userRepository.save(UserEntity.builder()
                .email(createUser.getEmail())
                .nickname(createUser.getNickname())
                .password(passwordEncoder.encode(createUser.getPassword()))
                .build());
        return savedEntity;
    }

    //UserRole ENUM사용시 에러 발생 추후 변경
    //임시방편
    private boolean createUserRole(Long userSeq) {
        RoleEntity saveEntity = userRoleRepository.save(RoleEntity.builder()
                        .userSeq(userSeq)
                        .roleSeq(7L)
                        .build());
        return saveEntity != null;
    }

    //아이디 중복 검사
    public String compareId(String email) {
        String resultMesg = Constant.EMAIL_COMPARE_FALSE_MESG;
        boolean resultEmail = userRepository.existByEmail(email);
        if(resultEmail)
            resultMesg=Constant.CHECK_EMAIL;
        return resultMesg;
    }

    //유저 변경 현재 이메일만 변경 가능 사진은? 어찌 구현?
    public String updateUserInfo(UpdateUserInfo updateUserInfo) {
        String resultMesg = Constant.FALSE_MESG;
        Optional<UserEntity> searchUserInfo = userRepository.findUserByEmail(updateUserInfo.getEmail());
       boolean checkResult = handleUpdateUserInfo(searchUserInfo);
       if (checkResult)
           resultMesg = Constant.SUCCESS_MESG;
       return resultMesg;
    }

    private boolean handleUpdateUserInfo(Optional<UserEntity> searchUserInfo) {
        if (searchUserInfo.isPresent()){
            UserEntity userEntity = searchUserInfo.get();
            userEntity.setNickname(searchUserInfo.get().getNickname());
            userRepository.save(userEntity);
            return true;
        }
        return false;
    }


    //비밀번호 수정
    public String updatePassword(UpdatePassword updatePassword) {
        Optional<UserEntity> searchUserInfo = userRepository.findUserByEmail(updatePassword.getEmail());
        String resultMesg = Constant.FALSE_MESG;
        if (passwordEncoder.matches(updatePassword.getPassword(), searchUserInfo.get().getPassword())){
            searchUserInfo.get().updateUserEntity(handlePassword(updatePassword));
            resultMesg = Constant.SUCCESS_MESG;

        }
        return resultMesg;
    }

    //새로운 비밀번호 암호화.
    private String handlePassword(UpdatePassword updatePassword) {
        String newPassword = passwordEncoder.encode(updatePassword.getNewPassword());
       return newPassword;
    }

    //이메일만 맞다고 비밀번호를 줘?
    //해당 이메일로 보내는 기능이 좋을 듯 시간 구현가능?
    //해당 이메일로 무엇을 줘야할까요? 비밀번호를 수정하고 난 랜덤비밀번호 설정하고 보여주기까지 구현
    //랜덤 비밀번호는 (정규식 패턴) (영어,숫자 ,특수문자 한개 이상) 포함한 8글자 이상
    public String searchPassword(SearchPassword searchPassword) {
        Optional<UserEntity> searchUserInfo = userRepository.findUserByEmail(searchPassword.getEmail());
        String resultMesg = Constant.FALSE_MESG;
        inputPassword();
        return resultMesg;
    }

    private void inputPassword() {
    }


}
