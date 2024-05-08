package id.ac.ui.cs.youkosu.microserviceorder.model.Order;

import com.fasterxml.jackson.annotation.JsonValue;
import id.ac.ui.cs.youkosu.microserviceorder.model.Delivery.Delivery;

class OrderStatusShipped extends OrderStatus {

    OrderStatusShipped() {
    }

    @Override
    public void setStatusToVerified(Order order) {
    }

    @Override
    public void setStatusToCancelled(Order order) {
    }

    @Override
    public void setStatusToShipped(Order order, Delivery delivery) {
    }

    @Override
    public void setStatusToCompleted(Order order) {
        order.setStatus(new OrderStatusCompleted());
    }

    @JsonValue
    @Override
    public String toString(){
        return "SHIPPED";
    }
}