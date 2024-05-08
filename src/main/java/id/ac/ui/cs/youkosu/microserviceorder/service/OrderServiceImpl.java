package id.ac.ui.cs.youkosu.microserviceorder.service;

import id.ac.ui.cs.youkosu.microserviceorder.model.Delivery.Delivery;
import id.ac.ui.cs.youkosu.microserviceorder.model.Order.Order;
import id.ac.ui.cs.youkosu.microserviceorder.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Order updateStatus(String orderId, String status, Delivery delivery) {
        Order order = orderRepository.findById(orderId);
        if (order != null) {
            Order newOrder = new Order(order.getOrderId(), order.getProducts());
            newOrder.setStatus(order.getStatus());
            if(status.equals("VERIFIED")){
                newOrder.setStatusToVerified();
            }else if(status.equals("CANCELLED")){
                newOrder.setStatusToCancelled();
            }else if(status.equals("SHIPPED")){
                newOrder.setStatusToShipped(delivery);
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

    @Override
    public List<Order> findAll(){
        return orderRepository.findAll();
    }

}