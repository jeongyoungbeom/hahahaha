package com.koreait.day02.repository;

import com.koreait.day02.model.entity.Item;
import com.koreait.day02.model.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    Optional<OrderDetail> findFirstByIdOrderByIdDesc(Long id);

    Optional<OrderDetail> findById(Long id);
}
