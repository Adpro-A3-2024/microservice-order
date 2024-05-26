package id.ac.ui.cs.youkosu.microserviceorder.model.Order;

import com.fasterxml.jackson.annotation.JsonValue;

class OrderStatusUnverified extends OrderStatus {
    public OrderStatusUnverified() {
        super();
    }
    @Override
    public void setStatusToVerified(Order order) {
        order.setStatus(new OrderStatusVerified().toString());
    }

    @Override
    public void setStatusToCancelled(Order order) {
        order.setStatus(new OrderStatusCancelled().toString());
    }

    @Override
    public void setStatusToShipped(Order order, String delivery) {
        throwUpdateException(order, "SHIPPED");
    }

    @Override
    public void setStatusToCompleted(Order order) {
        throwUpdateException(order, "COMPLETED");
    }
    @JsonValue
    @Override
    public String toString(){
        return "UNVERIFIED";
    }
}
