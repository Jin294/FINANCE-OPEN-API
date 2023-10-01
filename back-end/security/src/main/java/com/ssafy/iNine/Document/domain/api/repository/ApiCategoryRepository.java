package com.ssafy.iNine.Document.domain.api.repository;

import com.ssafy.iNine.Document.common.entity.api.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApiCategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAll();
}
