package id.ac.ui.cs.youkosu.microserviceorder.service;

import id.ac.ui.cs.youkosu.microserviceorder.model.DTO.OrderUpdateStatusDTO;
import id.ac.ui.cs.youkosu.microserviceorder.model.Delivery.Delivery;
import id.ac.ui.cs.youkosu.microserviceorder.model.Order.Order;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    public Order createOrder(Order order);
    public Order updateStatus(OrderUpdateStatusDTO orderUpdateStatusDTO);
    public Order findById(UUID orderId);
    public List<Order> findAll();
}