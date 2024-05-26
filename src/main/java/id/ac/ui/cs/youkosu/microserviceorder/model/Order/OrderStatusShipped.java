package id.ac.ui.cs.youkosu.microserviceorder.model.Order;

import com.fasterxml.jackson.annotation.JsonValue;

class OrderStatusShipped extends OrderStatus {

    OrderStatusShipped() {
    }

    @Override
    public void setStatusToVerified(Order order) {
        throwUpdateException(order, "VERIFIED");
    }

    @Override
    public void setStatusToCancelled(Order order) {
        throwUpdateException(order, "CANCELLED");
    }

    @Override
    public void setStatusToShipped(Order order, String delivery) {
        throwUpdateException(order, "SHIPPED");
    }

    @Override
    public void setStatusToCompleted(Order order) {
        order.setStatus(new OrderStatusCompleted().toString());
        order.setTrackingNumber(order.getTrackingNumber());
    }

    @JsonValue
    @Override
    public String toString(){
        return "SHIPPED";
    }
}
