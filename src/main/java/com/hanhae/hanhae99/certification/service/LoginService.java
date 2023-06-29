package com.hanhae.hanhae99.certification.service;

import com.hanhae.hanhae99.certification.model.UserDetailsImpl;
import com.hanhae.hanhae99.certification.model.entity.User;
import com.hanhae.hanhae99.certification.model.request.LoginRequest;
import com.hanhae.hanhae99.certification.model.type.UserRoleEnum;
import com.hanhae.hanhae99.certification.repository.UserRepository;
import com.hanhae.hanhae99.global.exception.CustomException;
import com.hanhae.hanhae99.global.model.type.ErrorCode;
import com.hanhae.hanhae99.global.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final UserRepository repository;
    private final JwtUtil jwtUtil;

//    @Transactional(readOnly = true)
//    public String login(HttpServletResponse response, LoginRequest request){
//
//        User user = repository.findByUserIdAndUserPw(request.id(), request.pw())
//                .orElseThrow(() -> {
//                    throw new CustomException(ErrorCode.LOGIN_NO);
//                });
//        String token = jwtUtil.createToken(user.getUsername(), user.getRole()); //토큰 생성
//        jwtUtil.addJwtToCookie(token, response); //쿠키 설정
//
//        return "로그인 성공!!";
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        User user = repository.findByUserId(username)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("유저를 찾을수 없음");
                });
        return new UserDetailsImpl(user);
    }

}
