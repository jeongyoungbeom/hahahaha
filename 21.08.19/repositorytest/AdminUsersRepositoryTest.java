package com.koreait.day02.repository;

import com.koreait.day02.Day02ApplicationTests;
import com.koreait.day02.model.entity.AdminUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class AdminUsersRepositoryTest extends Day02ApplicationTests {
    @Autowired
    private AdminUserRepository adminUserRepository;

    @Test
    public void creat(){
        AdminUser adminUser = AdminUser.builder()
                .userid("admin")
                .userpw("1234")
                .name("관리자")
                .status("use")
                .regDate((LocalDateTime.now()))
                .build();
        AdminUser newAdminUser = adminUserRepository.save(adminUser);
        // Assertions.assertNotNull(); 데이터가 있는지 확인
    }
}
