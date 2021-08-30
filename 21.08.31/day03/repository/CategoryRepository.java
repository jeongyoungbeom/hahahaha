package com.koreait.day03.repository;

import com.koreait.day03.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findFirstByTypeOrderByIdDesc(String type);

    Optional<Category> findByTitle(String title);

    Optional<Category> findByType(String type);

    Optional<Category> findById(Long Id);
}
