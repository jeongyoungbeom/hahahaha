package com.koreait.day02.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(
    name = "user_seq",
    sequenceName = "user_seq",
    initialValue = 1,
    allocationSize = 1
)

@Builder
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="user_seq" )
    private Long id;
    private String userid;
    private String userpw;
    private String hp;
    private String email;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
}
