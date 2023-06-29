package com.hanhae.hanhae99.certification.service;

import com.hanhae.hanhae99.certification.config.PasswordConfig;
import com.hanhae.hanhae99.certification.model.entity.User;
import com.hanhae.hanhae99.certification.model.request.RegisterRequest;
import com.hanhae.hanhae99.certification.model.type.UserRoleEnum;
import com.hanhae.hanhae99.certification.repository.UserRepository;
import com.hanhae.hanhae99.global.exception.CustomException;
import com.hanhae.hanhae99.global.model.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository repository;
    private final PasswordConfig passwordEncoder;

    @Transactional
    public String register(RegisterRequest request) {

        Optional<User> user = repository.findByUsername(request.name());

        if(!(user.isEmpty())){
            throw new CustomException(ErrorCode.NAME_SAME);
        }

        //TODO: 임시 테스트
        if(request.id().equals("admin") && request.pw().equals("adminpass")){

            repository.save(
                    User.builder()
                            .userId(request.id())
                            .userPw(passwordEncoder.passwordEncoder().encode(request.pw()))
                            .username(request.name())
                            .role(UserRoleEnum.ADMIN)
                            .build()
            );
            return "성공!";
        }

        repository.save(
                User.builder()
                        .userId(request.id())
                        .userPw(passwordEncoder.passwordEncoder().encode(request.pw()))
                        .username(request.name())
                        .role(UserRoleEnum.USER)
                        .build()
        );
        return "성공!";
    }

}
