package com.ssafy.iNine.StockAPI.repository;

import com.ssafy.iNine.StockAPI.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
    Product findByProdCode(String prodCode);
}
