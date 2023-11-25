package com.team.twodari.common.jwt;

import com.team.twodari.common.constant.Constant;
import com.team.twodari.user.entity.LoginEntity;
import com.team.twodari.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email)
                .map(user-> new LoginEntity(user.getEmail(), user.getNickname(), user.getPassword(), user.getUserSeq()))
                .orElseThrow(()->new UsernameNotFoundException(email + Constant.EMAIL_COMPARE_FALSE_MESG));
    }
}
