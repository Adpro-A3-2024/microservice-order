package id.ac.ui.cs.youkosu.microserviceorder.model.Order;

import com.fasterxml.jackson.annotation.JsonValue;
import id.ac.ui.cs.youkosu.microserviceorder.service.OrderStatusUpdateException;

abstract class OrderStatus {

    protected void throwUpdateException(Order order, String newStatus) {
        throw new OrderStatusUpdateException("Cannot update order status to " + newStatus + " for order " + order.getOrderId());
    }

    public abstract void setStatusToVerified(Order order);
    public abstract void setStatusToCancelled(Order order);
    public abstract void setStatusToShipped(Order order, String delivery);
    public abstract void setStatusToCompleted(Order order);

    @JsonValue
    public abstract String toString();
}
