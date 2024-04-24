package id.ac.ui.cs.youkosu.microserviceorder.repository;

import java.util.ArrayList;
import java.util.List;

import id.ac.ui.cs.youkosu.microserviceorder.model.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {
    private List<Order> orderData = new ArrayList<>();

    public Order save(Order order){
        int i = 0;
        for(Order savedOrder: orderData){
            if(savedOrder.getOrderId().equals(order.getOrderId())){
                orderData.remove(i);
                orderData.add(i, order);
                return order;
            }
            i+=1;
        }
        orderData.add(order);
        return order;
    }

    public Order findById(String id){
        for(Order savedOrder: orderData){
            if(savedOrder.getOrderId().equals(id)){
                return savedOrder;
            }
        }
        return null;
    }

}