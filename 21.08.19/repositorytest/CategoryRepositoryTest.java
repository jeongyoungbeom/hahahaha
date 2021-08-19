package com.koreait.day02.repository;

import com.koreait.day02.Day02ApplicationTests;
import com.koreait.day02.model.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class CategoryRepositoryTest extends Day02ApplicationTests {
    @Autowired
    private CategoryRepository categoryRepository;

    // 자동차, 콤퓨타, 가전
    @Test
    public void create(){
        Category category = Category.builder()
                .type("자전거")
                .title("현대 자전거")
                .regDate(LocalDateTime.now())
                .build();
        Category newcategory = categoryRepository.save(category);
    }

    @Test
    public void read(){
        Optional<Category> category = categoryRepository.findFirstByTypeOrderByIdDesc("가전");
        if (category != null){
            System.out.println("데이터가 존재");
        }else{
            System.out.println("데이터 없음");
        }
    }

    @Test
    public void update(){
        Optional<Category> category = categoryRepository.findById(1L);
        category.ifPresent(selectCate -> {
            selectCate.setType("자전거");
            selectCate.setTitle("현대 자전거");
            selectCate.setUpdateDate(LocalDateTime.now());
            categoryRepository.save(selectCate);
        });
    }

    @Test
    public void delete(){
        Optional<Category> category = categoryRepository.findById(1L);
        category.ifPresent(selectCate ->{
            categoryRepository.delete(selectCate);
        });

        Optional<Category> newcategory = categoryRepository.findById(1L);
        if (newcategory.isPresent()){
            System.out.println("삭제실패");
        }else{
            System.out.println("삭제성공");
        }
    }
}
