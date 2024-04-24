package id.ac.ui.cs.youkosu.microserviceorder.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Data
public abstract class OrderStatus{
    abstract public void setStatusToVerified(Order order);
    abstract public void setStatusToCancelled(Order order);
    abstract public void setStatusToShipped(Order order);
    abstract public void setStatusToCompleted(Order order);
}