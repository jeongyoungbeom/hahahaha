package com.koreait.day02.repository;

import com.koreait.day02.Day02ApplicationTests;
import com.koreait.day02.model.entity.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class ItemRepositoryTest extends Day02ApplicationTests {
    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create(){
        Item item = Item.builder()
                .name("엘지 냉장고")
                .status("판매중")
                .title("양문형 냉장고")
                .content("아주시원해요")
                .price(BigDecimal.valueOf(2000000))
                .regDate(LocalDateTime.now())
                .partnerId(4L)
                .build();
        Item newitem = itemRepository.save(item);
    }

    @Test
    public void read(){
        Optional<Item> item = itemRepository.findFirstByNameOrderByIdDesc("아반떼");
        if (item.isPresent()){
            System.out.println("데이터있음");
        }else{
            System.out.println("데이터없음");
        }
    }

    @Test
    public void update(){
        Optional<Item> item = itemRepository.findByName("맥북 프로");
        item.ifPresent(selectitem ->{
           selectitem.setStatus("판매중지");
           selectitem.setTitle("맥북 프로 13인치");
           selectitem.setContent("가볍고 작아요");
           selectitem.setPrice(BigDecimal.valueOf(1800000));
           itemRepository.save(selectitem);
        });
    }

    @Test
    public void delete(){
        Optional<Item> item = itemRepository.findByName("엘지 냉장고");
        item.ifPresent(selectitem ->{
            itemRepository.delete(selectitem);
        });
        Optional<Item> newitem = itemRepository.findByName("엘지 냉장고");
        if(newitem.isPresent()){
            System.out.println("삭제실패");
        }else{
            System.out.println("삭제성공");
        }
    }
}
