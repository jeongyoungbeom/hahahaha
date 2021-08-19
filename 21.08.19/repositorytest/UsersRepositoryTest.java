package com.koreait.day02.repository;

import com.koreait.day02.Day02ApplicationTests;
import com.koreait.day02.model.entity.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class UsersRepositoryTest extends Day02ApplicationTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){
        Users users = Users.builder()
                .userid("banana")
                .userpw("2222")
                .hp("010-2222-2222")
                .email("banana@apple.com")
                .regDate(LocalDateTime.now())
                .build();
        Users newuser = userRepository.save(users);

    }
    @Test
    public void read(){
//         select * from users where userid=?
//        Optional<Users> user = userRepository.findByUserid("banana");
//        user.ifPresent(selectUser -> {
//            System.out.println("users : "+ selectUser);
//            System.out.println("userid : "+ selectUser.getUserid());
//            System.out.println("userpw : "+ selectUser.getUserpw());
//            System.out.println("hp : "+ selectUser.getHp());
//            System.out.println("email : "+ selectUser.getEmail());
//        });
        Users user = userRepository.findFirstByHpOrderByIdDesc("010-2222-2222");
        if(user != null){
            System.out.println("데이터가 존재합니다");
        } else{
            System.out.println("데이터가 존재하지 않습니다.");
        }

    }

    @Test
    public void update(){
        Optional<Users> user = userRepository.findByUserid("banana");
        user.ifPresent(selectUser -> {
            selectUser.setEmail("banana@banana.com");
            selectUser.setHp("010-9899-9999");
            selectUser.setUpdateDate(LocalDateTime.now());
            userRepository.save(selectUser);
        });
    }

    @Test
    public void delete(){
        Optional<Users> user = userRepository.findByUserid("banana");
        user.ifPresent(selectUser -> {
            userRepository.delete(selectUser);
        });

        Optional<Users> deleteuser = userRepository.findByUserid("banana");
        if(deleteuser.isPresent()){
            System.out.println("삭제 실패");
        }else{
            System.out.println("삭제 성공");
        }
    }
}
