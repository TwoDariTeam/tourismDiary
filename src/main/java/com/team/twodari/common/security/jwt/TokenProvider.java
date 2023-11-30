package com.team.twodari.common.security.jwt;

import com.team.twodari.common.dto.TokenDTO;
import com.team.twodari.user.entity.RoleEntity;
import com.team.twodari.user.entity.UserEntity;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class TokenProvider implements InitializingBean {


    private static final String AUTHORITIES_KEY = "auth";
    //로그확인하기
    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
    private final String secret;
    private final long tokenValidityInMilliseconds;
    private final String secret2;
    private final long tokenValidityInMilliseconds2;
    private Key key;

    //
    public TokenProvider(

            @Value("${jwt.secret}") String secret,
            @Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds,
            @Value("${jwt.secret2}") String secret2,
            @Value("${jwt.token-validity-in-seconds2}") long tokenValidityInSeconds2) {
        this.secret = secret;
        this.tokenValidityInMilliseconds = tokenValidityInSeconds * 1000;
        this.secret2 = secret2;
        this.tokenValidityInMilliseconds2 = tokenValidityInSeconds2 * 2000;
    }

    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    //Token 생성 ->알고르즘대로 만든다.
    public TokenDTO createToken(Authentication authentication) {
        String authorities = ExtractAuthorities(authentication);
        Date validity = validityPeriod(this.tokenValidityInMilliseconds);
        Date validity2 = validityPeriod(this.tokenValidityInMilliseconds2);
        String accessToken = createAccessToken(authentication, authorities,validity);
        String refreshToken = createRefreshToken(authentication, authorities,validity2);
        //access_token은 사용자에게 보내고
        //refresh_token은 디비에 저장하고 나중에 써야 한다
        //토큰 만들기 전에 트랩 설치해야할듯.
        return new TokenDTO(accessToken, refreshToken);
    }

    //시간 지정 함수
    private Date validityPeriod(Long tokenValidityInMilliseconds) {
        long now = (new Date()).getTime();
        Date validity = new Date(now + tokenValidityInMilliseconds);
        return validity;
    }

    private String createAccessToken(Authentication authentication, String authorities, Date validity) {
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
        return accessToken;
    }

    private String createRefreshToken(Authentication authentication, String authorities, Date validity2){
        String refreshToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity2)
                .compact();
        return refreshToken;
    }


    //권한 추출 부분 분리
    public String ExtractAuthorities(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        return authorities;
    }

    //숫자 loginEntity ->Authentication 변환
    public Authentication convertAuthentication(RoleEntity roleEntity, UserEntity userEntity) {
        Long userAuthority = roleEntity.getRoleSeq();
        GrantedAuthority authority = new LongRoleAuthority(userAuthority);
        Authentication authenticationToken =
                new UsernamePasswordAuthenticationToken(userEntity.getEmail(), null, Collections.singletonList(authority));
        return authenticationToken;
    }

    //토큰으로부터 값(사용자 데이터)을 가져오는 메서드.
    //토큰 복호화
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        System.out.println("********************************");
        System.out.println(claims.getSubject());
        System.out.println("********************************");

        User principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);

    }

    //토큰이 맞는지 확인한다
    //기본적으로는 true false만 보내지만
    //자세한 정보를 보내지 커스텀이 필요한 곳
    public Map<String, String> validateToken(String token) {
        Map<String, String> map = new HashMap<String, String>();

        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            map.put("result", "SUCCESS");
            map.put("msg", "인증성공");
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            logger.info("잘못된 JWT 서명입니다.");
            map.put("result", "FAIL");
            //map.put("errcode",ErrorCode.MALOFME_TOKEN_EXCEPTION);
            map.put("msg", "잘못된 JWT 서명입니다");

        } catch (ExpiredJwtException e) {
            logger.info("만료된 JWT 토큰입니다.");
            map.put("result", "FAIL");
            map.put("msg", "만료된 JWT 토큰입니다.");

        } catch (UnsupportedJwtException e) {
            map.put("result", "FAIL");
            map.put("msg", "지원되지 않는 JWT 토큰입니다");
            logger.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            logger.info("JWT 토큰이 잘못되었습니다.");
            map.put("result", "FAIL");
            map.put("msg", "JWT 토큰이 잘못되었습니다.");
        }
        return map;
    }


}
