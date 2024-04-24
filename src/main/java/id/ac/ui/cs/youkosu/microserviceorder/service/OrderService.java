package id.ac.ui.cs.youkosu.microserviceorder.service;

import id.ac.ui.cs.youkosu.microserviceorder.model.Order;

import java.util.List;

public interface OrderService {
    public Order createOrder(Order order);
    public Order updateStatus(String orderId, String status);
    public Order findById(String orderId);
}