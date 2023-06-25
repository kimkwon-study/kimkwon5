package com.hanhae.hanhae99.certification.service;

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

    @Transactional
    public String register(RegisterRequest request) {

        Optional<User> user = repository.findByUsername(request.name());

        if(!(user.isEmpty())){
            throw new CustomException(ErrorCode.NAME_SAME);
        }

        repository.save(
                User.builder()
                        .userId(request.id())
                        .userPw(request.pw())
                        .username(request.name())
                        .role(UserRoleEnum.USER)
                        .build()
        );
        return "성공!";
    }

}
