package id.ac.ui.cs.youkosu.microserviceorder.model;

import com.fasterxml.jackson.annotation.JsonValue;

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
    public void setStatusToShipped(Order order) {
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