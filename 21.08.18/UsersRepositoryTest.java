package com.koreait.day02.repository;

import com.koreait.day02.Day02ApplicationTests;
import com.koreait.day02.model.entity.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class UsersRepositoryTest extends Day02ApplicationTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){
        Users users = Users.builder()
                .userid("apple")
                .userpw("1111")
                .hp("010-1111-1111")
                .email("apple@apple.com")
                .regDate(LocalDateTime.now())
                .build();
        Users newuser = userRepository.save(users);

    }

}
