package com.koreait.day02.repository;

import com.koreait.day02.model.entity.Category;
import com.koreait.day02.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findFirstByNameOrderByIdDesc(String name);

    Optional<Item> findByName(String name);

    Optional<Item> findById(Long Id);
}
