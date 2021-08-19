package com.koreait.day02.repository;

import com.koreait.day02.Day02ApplicationTests;
import com.koreait.day02.model.entity.OrderGroup;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class OrderGroupRepositoryTest extends Day02ApplicationTests {
    @Autowired
    OrderGroupRepository orderGroupRepository;

    @Test
    public void create(){
        OrderGroup orderGroup = OrderGroup.builder()
                .orderType("ALL")
                .status("결제완료")
                .revAddress("서울시 서초구 호롱동")
                .revName("김메론")
                .paymentType("카드대기")
                .totalPrice(BigDecimal.valueOf(1500000))
                .totalQuantity(1)
                .regDate(LocalDateTime.now())
                .orderAt(LocalDateTime.now())
                .userid(24L)
                .build();
        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);
    }

    @Test
    public void read(){
        Optional<OrderGroup> orderGroup = orderGroupRepository.findFirstByRevNameOrderByIdDesc("김사과");
        orderGroup.ifPresent(selectOG ->{
            System.out.println(selectOG.getOrderType());
            System.out.println(selectOG.getStatus());
            System.out.println(selectOG.getRevAddress());
            System.out.println(selectOG.getRevName());
            System.out.println(selectOG.getPaymentType());
            System.out.println(selectOG.getTotalPrice());
        });
    }

    @Test
    public void update(){
        Optional<OrderGroup> orderGroup = orderGroupRepository.findById(21L);
        orderGroup.ifPresent(selectOG ->{
            selectOG.setStatus("결제중");
            selectOG.setRevAddress("인천시");
            selectOG.setRevName("오렌쥐");
            selectOG.setPaymentType("카드결제");
            orderGroupRepository.save(selectOG);
        });
    }

    @Test
    public void delete(){
        Optional<OrderGroup> orderGroup = orderGroupRepository.findByRevAddress("인천시");
        orderGroup.ifPresent(selectOG ->{
            orderGroupRepository.delete(selectOG);
        });

        Optional<OrderGroup> neworderGroup = orderGroupRepository.findByRevAddress("인천시");
        if (neworderGroup.isPresent()){
            System.out.println("삭제실패");
        }else{
            System.out.println("삭제실패");
        }
    }
}
