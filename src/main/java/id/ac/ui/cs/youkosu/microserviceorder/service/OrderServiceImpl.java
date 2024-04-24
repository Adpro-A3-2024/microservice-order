package id.ac.ui.cs.youkosu.microserviceorder.service;

import id.ac.ui.cs.youkosu.microserviceorder.model.Order;
import id.ac.ui.cs.youkosu.microserviceorder.model.OrderStatus;
import id.ac.ui.cs.youkosu.microserviceorder.repository.OrderRepository;
import id.ac.ui.cs.youkosu.microserviceorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public Order createOrder(Order order) {
        if (orderRepository.findById(order.getOrderId()) == null) {
            orderRepository.save(order);
            return order;
        }
        return null;
    }

    @Override
    public Order updateStatus(String orderId, String status) {
        Order order = orderRepository.findById(orderId);
        if (order != null) {
            Order newOrder = new Order(order.getOrderId(), order.getProducts());
            newOrder.setStatus(order.getStatus());
            if(status.equals("VERIFIED")){
                newOrder.setStatusToVerified();
            }else if(status.equals("CANCELLED")){
                newOrder.setStatusToCancelled();
            }else if(status.equals("SHIPPED")){
                newOrder.setStatusToShipped();
            }else if(status.equals("COMPLETED")){
                newOrder.setStatusToCompleted();
            }
            orderRepository.save(newOrder);
            return newOrder;
        } else {
            throw new NegativeArraySizeException();
        }
    }

    @Override
    public Order findById(String orderId) {
        return orderRepository.findById(orderId);
    }
}