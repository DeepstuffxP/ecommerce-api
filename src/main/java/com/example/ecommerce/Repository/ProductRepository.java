package com.example.ecommerce.Repository;
import com.example.ecommerce.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Products, Long> {
}
