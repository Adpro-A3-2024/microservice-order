package id.ac.ui.cs.youkosu.microserviceorder.model;

import id.ac.ui.cs.youkosu.microserviceorder.tempModel.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Order {
    private String orderId;
    private List<Product> products;
    private OrderStatus status;

    public Order(String orderId, List<Product> products){
        this.orderId = orderId;
        this.products = products;
        this.status = new OrderStateUnverified(this);

        if (products.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            this.products = products;
        }
    }

    public void setStatusToVerified(){
        this.status.setStatusToVerified();
    }

    public void setStatusToCancelled(){
        this.status.setStatusToCancelled();
    }

    public void setStatusToShipped(){
        this.status.setStatusToShipped();
    }

    public void setStatusToCompleted(){
        this.status.setStatusToCompleted();
    }

    public String getStatusByString(){
        return status.toString();
    }

    public void setStatusByString(String orderStatusString){

    }

    public OrderStatus getStatusByObj(){
        return status;
    }

    public void setStatus(OrderStatus orderStatus){
        this.status = orderStatus;
        orderStatus.setOrder(this);
    }
}