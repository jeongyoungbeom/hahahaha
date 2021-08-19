package com.koreait.day02.repository;

import com.koreait.day02.Day02ApplicationTests;
import com.koreait.day02.model.entity.OrderDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class OrderDetailRepositoryTest extends Day02ApplicationTests {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create(){
        OrderDetail orderDetail = OrderDetail.builder()
                .status("결제완료")
                .quantity(1)
                .totalPrice(BigDecimal.valueOf(3000000))
                .regDate(LocalDateTime.now())
                .itemId(2L)
                .orderGroupId(1L)
                .build();
        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
    }
    @Test
    public void read(){
        Optional<OrderDetail> orderDetail = orderDetailRepository.findFirstByIdOrderByIdDesc(1L);
        orderDetail.ifPresent(selectOD ->{
            System.out.println(selectOD.getId());
            System.out.println(selectOD.getStatus());
            System.out.println(selectOD.getTotalPrice());
            System.out.println(selectOD.getRegDate());
        });
    }

    @Test
    public void update(){
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(1L);
        orderDetail.ifPresent(selectOD ->{
            selectOD.setStatus("결제대기");
            orderDetailRepository.save(selectOD);
        });
    }

    @Test
    public void delete(){
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(2L);
        orderDetail.ifPresent(selectOD ->{
            orderDetailRepository.delete(selectOD);
        });

        Optional<OrderDetail> neworderDetail = orderDetailRepository.findById(2L);
        if (neworderDetail.isPresent()){
            System.out.println("삭제실패");
        }else{
            System.out.println("삭제성공");
        }
    }
}
