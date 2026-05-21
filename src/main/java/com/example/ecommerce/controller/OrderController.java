package com.example.ecommerce.controller;

import com.example.ecommerce.model.Order;
import com.example.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(Authentication authentication,
                                             @RequestBody Map<Long, Integer> productQuantities) {
        String email = authentication.getName();
        return ResponseEntity.ok(orderService.createOrder(email, productQuantities));
    }

    @GetMapping("/my")
    public ResponseEntity<List<Order>> getMyOrders(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(orderService.getMyOrders(email));
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Order> updateStatus(@PathVariable Long id,
                                              @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(orderService.updateStatus(id, body.get("status")));
    }
}