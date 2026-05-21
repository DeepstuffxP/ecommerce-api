package com.example.ecommerce.service;

import com.example.ecommerce.model.*;
import com.example.ecommerce.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public Order createOrder(String email, Map<Long, Integer> productQuantities) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found!"));

        Order order = new Order();
        order.setUser(user);
        order.setStatus(Order.Status.PENDING);

        double total = 0;

        List<OrderItem> items = new java.util.ArrayList<>();
        for (Map.Entry<Long, Integer> entry : productQuantities.entrySet()) {
            Products product = productRepository.findById(entry.getKey())
                    .orElseThrow(() -> new RuntimeException("Product not found!"));

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(entry.getValue());
            item.setPrice(product.getPrice());

            total += product.getPrice() * entry.getValue();
            items.add(item);
        }

        order.setItems(items);
        order.setTotalAmount(total);

        return orderRepository.save(order);
    }

    public List<Order> getMyOrders(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        return orderRepository.findByUser(user);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order updateStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found!"));
        order.setStatus(Order.Status.valueOf(status));
        return orderRepository.save(order);
    }
}