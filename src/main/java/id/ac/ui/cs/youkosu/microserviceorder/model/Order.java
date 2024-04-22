package id.ac.ui.cs.youkosu.microserviceorder.model;

import id.ac.ui.cs.youkosu.microserviceorder.tempModel.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class Order {
    private String orderId;
    private List<Product> products;
    private String status;

    Order(String orderId, List<Product> products){
        this.orderId = orderId;
        this.products = products;
        this.status = "UNVERIFIED";

        if (products.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            this.products = products;
        }
    }

    void setStatusToVerified(){
        this.status = "VERIFIED";
    }

    void setStatusToCancelled(){
        this.status = "CANCELLED";
    }

    void setStatusToShipped(){
        this.status = "SHIPPED";
    }

    void setStatusToCompleted(){
        this.status = "COMPLETED";
    }
}