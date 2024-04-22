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

    Order(String orderId, List<Product> products){
        this.orderId = orderId;
        this.products = products;
        this.status = new OrderStateUnverified(this);

        if (products.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            this.products = products;
        }
    }

    void setStatusToVerified(){
        this.status.setStatusToVerified();
    }

    void setStatusToCancelled(){
        this.status.setStatusToCancelled();
    }

    void setStatusToShipped(){
        this.status.setStatusToShipped();
    }

    void setStatusToCompleted(){
        this.status.setStatusToCompleted();
    }

    String getStatus(){
        return status.toString();
    }
}