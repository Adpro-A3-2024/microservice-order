package id.ac.ui.cs.youkosu.microserviceorder.service;

import id.ac.ui.cs.youkosu.microserviceorder.model.Delivery.Delivery;
import id.ac.ui.cs.youkosu.microserviceorder.model.Order.Order;

import java.util.List;

public interface OrderService {
    public Order createOrder(Order order);
    public Order updateStatus(String orderId, String status, String delivery);
    public Order findById(String orderId);
    public List<Order> findAll();
}