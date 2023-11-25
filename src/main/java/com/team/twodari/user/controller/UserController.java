package com.team.twodari.user.controller;

import com.team.twodari.common.ComResponseEntity;
import com.team.twodari.common.dto.ComResponseDTO;
import com.team.twodari.common.dto.TokenDTO;
import com.team.twodari.user.dto.*;
import com.team.twodari.user.service.UserService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //로그인 post 완성
    //로그 아웃 post
    //회원 가입 post 완성
    //아이디 중복 검사 완성
    //회원 정보 수정 post 완성 - 닉네임 중복검사 완성
    //비밀 번호 수정 post 완성
    //아이디 찾기 기능 ?? 어떻게 구현??
    //비밀 번호 찾기 기능 완성
    //회원 탈퇴 post 완성

    @PostMapping("/login")
    public ComResponseEntity<Optional<TokenDTO>> login(@RequestBody Login login){
        System.out.println("login");
        Optional<TokenDTO> loginToken = userService.login(login);
        return new ComResponseEntity<>(new ComResponseDTO<>("로그인 성공했습니다.",loginToken));
    }

    @PostMapping("/create")
    public ComResponseEntity<String> createUser(@RequestBody CreateUser createUser){
        System.out.println("createUser");
        String resultMesg = userService.createUser(createUser);
        return  new ComResponseEntity<>(new ComResponseDTO<>(resultMesg));
    }

    @GetMapping("/compareId")
    public ComResponseEntity<String> compareId(@NotBlank String email){
        System.out.println("compareId");
        String resultMesg = userService.compareId(email);
        return  new ComResponseEntity<>(new ComResponseDTO<>(resultMesg));
    }

    @PostMapping("/update")
    public ComResponseEntity<String> updateUserInfo(@RequestBody UpdateUserInfo updateUserInfo){
        System.out.println("update");
        String resultMesg = userService.updateUserInfo(updateUserInfo);
        return new ComResponseEntity<>(new ComResponseDTO<>(resultMesg));
    }

    @PostMapping("/updatePw")
    public ComResponseEntity<String> updatePassword(@RequestBody UpdatePassword updatePassword){
        System.out.println("updatePw");
        String resultMesg = userService.updatePassword(updatePassword);
        return new ComResponseEntity<>(new ComResponseDTO<>(resultMesg));
    }

    //비밀번호 찾기 서비스는 2차 때 확장하여 이메일로 전송
    @PostMapping("/searchPw")
    public ComResponseEntity<String> searchPassword(@RequestBody SearchPassword searchPassword){
        System.out.println("searchPw");
        String resultMesg = userService.searchPassword(searchPassword);
        return new ComResponseEntity<>(new ComResponseDTO<>(resultMesg));
    }

    @PostMapping("/delete")
    public ComResponseEntity<Void> deleteUser(@RequestBody DeleteUser deleteUser){
        System.out.println("delete");
        userService.deleteUser(deleteUser);
        return  new ComResponseEntity<>(new ComResponseDTO<>());
    }

}
