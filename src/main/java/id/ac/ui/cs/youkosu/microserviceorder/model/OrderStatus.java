package id.ac.ui.cs.youkosu.microserviceorder.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class OrderStatus{
    protected Order order;
    OrderStatus(Order order){
        this.order = order;
    }
    abstract public void setStatusToVerified();
    abstract public void setStatusToCancelled();
    abstract public void setStatusToShipped();
    abstract public void setStatusToCompleted();
}