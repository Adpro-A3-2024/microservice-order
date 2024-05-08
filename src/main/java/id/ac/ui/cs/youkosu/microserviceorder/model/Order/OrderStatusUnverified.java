package id.ac.ui.cs.youkosu.microserviceorder.model.Order;

import com.fasterxml.jackson.annotation.JsonValue;
import id.ac.ui.cs.youkosu.microserviceorder.model.Delivery.Delivery;

class OrderStateUnverified extends OrderStatus {
    public OrderStateUnverified() {
        super();
    }
    @Override
    public void setStatusToVerified(Order order) {
        order.setStatus(new OrderStateVerified());
    }

    @Override
    public void setStatusToCancelled(Order order) {
        order.setStatus(new OrderStateCancelled());
    }

    @Override
    public void setStatusToShipped(Order order, Delivery delivery) {
    }

    @Override
    public void setStatusToCompleted(Order order) {
    }
    @JsonValue
    @Override
    public String toString(){
        return "UNVERIFIED";
    }
}