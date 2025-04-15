package com.guld.sciq.security.auth;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import com.guld.sciq.common.error.ErrorMessage;
import com.guld.sciq.exception.auth.InvalidTokenException;
import com.guld.sciq.exception.auth.UserAlreadyExistsException;
import com.guld.sciq.security.UserPrincipal;
import com.guld.sciq.security.dto.AuthDto;
import com.guld.sciq.security.dto.TokenDto;
import com.guld.sciq.security.jwt.TokenProvider;
import com.guld.sciq.user.entity.RefreshToken;
import com.guld.sciq.user.entity.User;
import com.guld.sciq.user.repository.RefreshTokenRepository;
import com.guld.sciq.user.repository.UserRepository;
import com.guld.sciq.user.service.UserService;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public User signup(AuthDto.SignUpRequest signUpRequest) {
        if (userService.existsByEmail(signUpRequest.getEmail())) {
            throw new UserAlreadyExistsException(ErrorMessage.USER_ALREADY_EXIST);
        }

        User user = signUpRequest.toEntity(passwordEncoder);

        UserPrincipal.create(user);

        return userRepository.save(user);
    }

    @Transactional
    public TokenDto.Response login(AuthDto.SignUpRequest signUpRequest) {
        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = signUpRequest.toAuthentication();

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. UserPrincipal 생성 및 Authentication에 설정
        User user = userRepository.findByEmail(signUpRequest.getEmail())
                .orElseThrow(() -> new NoSuchElementException(ErrorMessage.USER_NOT_FOUND));
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        authentication = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());

        // 4. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto.Response tokenResDto = tokenProvider.generateToken(authentication);

        // 5. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenResDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        // 6. 토큰 발급
        return tokenResDto;
    }

    @Transactional
    public void logout(TokenDto.Request tokenRequest, HttpServletRequest request) {
        Authentication authentication = tokenProvider.getAuthentication(tokenRequest.getAccessToken(),request);
        refreshTokenRepository.deleteByKey(authentication.getName());
    }

    @Transactional
    public TokenDto.Response reissue(TokenDto.Request tokenRequest, HttpServletRequest request) {
        validateRefreshToken(tokenRequest.getRefreshToken());

        Authentication authentication = tokenProvider.getAuthentication(tokenRequest.getAccessToken(), request);
        RefreshToken refreshToken = getRefreshToken(authentication.getName());
        validateTokenMatch(refreshToken, tokenRequest.getRefreshToken());

        TokenDto.Response tokenResponse = tokenProvider.generateToken(authentication);
        updateRefreshToken(refreshToken, tokenResponse.getRefreshToken());

        return tokenResponse;
    }

    private void validateRefreshToken(String refreshToken) {
        if (!tokenProvider.validateToken(refreshToken)) {
            throw new InvalidTokenException();
        }
    }

    private RefreshToken getRefreshToken(String memberId) {
        return refreshTokenRepository.findByKey(memberId)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessage.USER_ALREADY_LOGOUT));
    }

    private void validateTokenMatch(RefreshToken storedToken, String providedToken) {
        if (!storedToken.getValue().equals(providedToken)) {
            throw new InvalidTokenException();
        }
    }

    private void updateRefreshToken(RefreshToken refreshToken, String newTokenValue) {
        refreshToken.updateValue(newTokenValue);
        refreshTokenRepository.save(refreshToken);
    }


}
