package com.hanhae.hanhae99.certification.repository;

import com.hanhae.hanhae99.certification.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(String userId);

    Optional<User> findByUsername(String userName);

    Optional<User> findByUserIdAndUserPw(String userId, String userPw);

}