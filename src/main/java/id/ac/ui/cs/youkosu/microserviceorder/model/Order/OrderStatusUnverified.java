package id.ac.ui.cs.youkosu.microserviceorder.model.Order;

import com.fasterxml.jackson.annotation.JsonValue;
import id.ac.ui.cs.youkosu.microserviceorder.model.Delivery.Delivery;
import id.ac.ui.cs.youkosu.microserviceorder.service.OrderStatusUpdateException;

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
        order.setStatus(new OrderStatusCancelled());
    }

    @Override
    public void setStatusToShipped(Order order, String delivery) {
        throw new OrderStatusUpdateException("Cannot update order status to SHIPPED" + " for order " + order.getOrderId());
    }

    @Override
    public void setStatusToCompleted(Order order) {
        throw new OrderStatusUpdateException("Cannot update order status to COMPLETED" + " for order " + order.getOrderId());
    }
    @JsonValue
    @Override
    public String toString(){
        return "UNVERIFIED";
    }
}