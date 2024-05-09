package id.ac.ui.cs.youkosu.microserviceorder.model.Order;

import id.ac.ui.cs.youkosu.microserviceorder.model.Delivery.Delivery;
import id.ac.ui.cs.youkosu.microserviceorder.model.Delivery.DeliveryMethod;
import id.ac.ui.cs.youkosu.microserviceorder.tempModel.Product;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @Data
public class Order {
    private String orderId;
    private List<Product> products;
    private OrderStatus status;
    private Delivery delivery;

    public Order(String orderId, List<Product> products){
        this.orderId = orderId;
        this.products = products;
        this.status = new OrderStateUnverified();

        if (products.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            this.products = products;
        }
    }

    public void setStatusToVerified(){
        this.status.setStatusToVerified(this);
    }

    public void setStatusToCancelled(){
        this.status.setStatusToCancelled(this);
    }

    public void setStatusToShipped(String delivery){
        this.status.setStatusToShipped(this, delivery);
    }

    public void setStatusToCompleted(){
        this.status.setStatusToCompleted(this);
    }
}