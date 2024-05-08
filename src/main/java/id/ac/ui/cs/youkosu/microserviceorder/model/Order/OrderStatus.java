package id.ac.ui.cs.youkosu.microserviceorder.model.Order;

import id.ac.ui.cs.youkosu.microserviceorder.model.Delivery.Delivery;
import id.ac.ui.cs.youkosu.microserviceorder.model.Delivery.DeliveryMethod;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Data
public abstract class OrderStatus{
    abstract public void setStatusToVerified(Order order);
    abstract public void setStatusToCancelled(Order order);
    abstract public void setStatusToShipped(Order order, Delivery delivery);
    abstract public void setStatusToCompleted(Order order);
}