package id.ac.ui.cs.youkosu.microserviceorder.model.Order;

import com.fasterxml.jackson.annotation.JsonValue;
import id.ac.ui.cs.youkosu.microserviceorder.model.Delivery.Delivery;
import id.ac.ui.cs.youkosu.microserviceorder.service.OrderStatusUpdateException;

import java.sql.SQLOutput;

class OrderStatusShipped extends OrderStatus {

    OrderStatusShipped() {
    }

    @Override
    public void setStatusToVerified(Order order) {
        throw new OrderStatusUpdateException("Cannot update order status to VERIFIED" + " for order " + order.getOrderId());
    }

    @Override
    public void setStatusToCancelled(Order order) {
        throw new OrderStatusUpdateException("Cannot update order status to CANCELLED" + " for order " + order.getOrderId());
    }

    @Override
    public void setStatusToShipped(Order order, String delivery) {
        throw new OrderStatusUpdateException("Cannot update order status to SHIPPED" + " for order " + order.getOrderId());
    }

    @Override
    public void setStatusToCompleted(Order order) {
        order.setStatus(new OrderStatusCompleted().toString());
        System.out.println("Line 32 OrderStatusShipped"+order.getTrackingNumber());
        order.setTrackingNumber(order.getTrackingNumber());
    }

    @JsonValue
    @Override
    public String toString(){
        return "SHIPPED";
    }
}