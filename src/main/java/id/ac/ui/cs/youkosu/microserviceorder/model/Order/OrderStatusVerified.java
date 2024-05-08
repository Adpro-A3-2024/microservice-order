package id.ac.ui.cs.youkosu.microserviceorder.model.Order;

import com.fasterxml.jackson.annotation.JsonValue;
import id.ac.ui.cs.youkosu.microserviceorder.model.Delivery.Delivery;

class OrderStateVerified extends OrderStatus {
    public OrderStateVerified() {
    }
    @Override
    public void setStatusToVerified(Order order) {
    }

    @Override
    public void setStatusToCancelled(Order order) {
    }

    @Override
    public void setStatusToShipped(Order order, Delivery delivery) {
        order.setStatus(new OrderStatusShipped());
        order.setDelivery(delivery);
    }

    @Override
    public void setStatusToCompleted(Order order) {
    }

    @JsonValue
    @Override
    public String toString(){
        return "VERIFIED";
    }
}