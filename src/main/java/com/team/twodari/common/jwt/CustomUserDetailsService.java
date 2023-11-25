//package com.team.twodari.common.jwt;
//
//import com.team.twodari.common.constant.Constant;
//import com.team.twodari.user.entity.LoginEntityImpl;
//import com.team.twodari.user.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
////@RequiredArgsConstructor
////@Service
//public class CustomUserDetailsService implements UserDetailsService {
//    private final UserRepository userRepository;
//    //JWT 성공하면 여기를 지나가지를 않고 넘어감.
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        return userRepository.findUserByEmail(email)
//                .map(user-> new LoginEntityImpl(user.getEmail(), user.getNickname(), user.getPassword(), user.getUserSeq()))
//                .orElseThrow(()->new UsernameNotFoundException(email + Constant.EMAIL_COMPARE_FALSE_MESG));
//    }
//}
