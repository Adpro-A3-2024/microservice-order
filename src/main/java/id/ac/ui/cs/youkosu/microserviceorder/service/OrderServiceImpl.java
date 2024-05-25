package id.ac.ui.cs.youkosu.microserviceorder.service;

import id.ac.ui.cs.youkosu.microserviceorder.model.DTO.OrderUpdateStatusDTO;
import id.ac.ui.cs.youkosu.microserviceorder.model.Delivery.Delivery;
import id.ac.ui.cs.youkosu.microserviceorder.model.Order.Order;
import id.ac.ui.cs.youkosu.microserviceorder.repository.OrderRepository;
import id.ac.ui.cs.youkosu.microserviceorder.tempModel.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Override
    public Order createOrder(Order order) {
//        CartItem cartItems = order.getCartItems();
//        order.addCartItem();
        orderRepository.save(order);
        return order;
    }

    @Override
    public Order updateStatus(OrderUpdateStatusDTO orderUpdateStatusDTO) {
        UUID orderId = orderUpdateStatusDTO.getOrderId();
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            String status = orderUpdateStatusDTO.getStatus();
            String delivery  = orderUpdateStatusDTO.getDelivery();
            Order newOrder = new Order(order.getOrderId(), order.getCartItems());


            newOrder.setStatus(order.getStatus());
            newOrder.setTrackingNumber(order.getTrackingNumber());
            if (status.equals("VERIFIED")) {
                newOrder.setStatusToVerified();
            } else if (status.equals("CANCELLED")) {
                newOrder.setStatusToCancelled();
            } else if (status.equals("SHIPPED")) {
                newOrder.setStatusToShipped(delivery);
            } else if (status.equals("COMPLETED")) {
                newOrder.setStatusToCompleted();
            }else{
                throw new OrderStatusUpdateException("Cannot update order status to " + status + " for order " + order.getOrderId()+". There is not "+status+" order status");
            }
            orderRepository.save(newOrder);
            return newOrder;
        } else {
            throw new NegativeArraySizeException();
        }
    }

    @Override
    public Order findById(UUID orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    @Override
    public List<Order> findAll(){
        return orderRepository.findAll();
    }

}