package id.ac.ui.cs.youkosu.microserviceorder.model.Order;

import com.fasterxml.jackson.annotation.JsonValue;

class OrderStatusCancelled extends OrderStatus {

    OrderStatusCancelled() {
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
        throwUpdateException(order, "COMPLETED");
    }

    @JsonValue
    @Override
    public String toString(){
        return "CANCELLED";
    }
}
