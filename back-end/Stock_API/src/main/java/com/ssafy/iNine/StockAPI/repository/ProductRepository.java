package com.ssafy.iNine.StockAPI.repository;

import com.ssafy.iNine.StockAPI.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    Product findByProdCode(String prodCode);

    // 모든 Product 엔티티를 가져온다.
    List<Product> findAll();
}
