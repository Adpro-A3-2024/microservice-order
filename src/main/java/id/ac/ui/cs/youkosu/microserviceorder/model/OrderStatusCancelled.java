package id.ac.ui.cs.youkosu.microserviceorder.model;

import com.fasterxml.jackson.annotation.JsonValue;

class OrderStateCancelled extends OrderStatus {

    OrderStateCancelled() {
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
    }

    @JsonValue
    @Override
    public String toString(){
        return "CANCELLED";
    }
}