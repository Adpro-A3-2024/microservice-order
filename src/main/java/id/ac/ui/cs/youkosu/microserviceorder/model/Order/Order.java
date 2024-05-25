package id.ac.ui.cs.youkosu.microserviceorder.model.Order;

import id.ac.ui.cs.youkosu.microserviceorder.model.Delivery.Delivery;
import id.ac.ui.cs.youkosu.microserviceorder.model.Delivery.DeliveryFactory;
import id.ac.ui.cs.youkosu.microserviceorder.model.Delivery.DeliveryMethod;
import id.ac.ui.cs.youkosu.microserviceorder.tempModel.Product;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @Data
public class Order {
    private String orderId;
    private List<String> products;
    private String status;
    private String trackingNumber;

    public Order(String orderId, List<String> products){
        this.orderId = orderId;
        this.products = products;
        this.status = new OrderStatusUnverified().toString();

        if (products.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            this.products = products;
        }
    }

    public void setStatusToVerified(){
        OrderStatus currentStatus = OrderStatusFactory.getOrderStatus(this.status);
        currentStatus.setStatusToVerified(this);
        System.out.println("Line 35 Order.java "+this.status);
    }

    public void setStatusToCancelled(){
        OrderStatus currentStatus = OrderStatusFactory.getOrderStatus(this.status);
        currentStatus.setStatusToCancelled(this);
    }

    public void setStatusToShipped(String deliveryMethod){
        System.out.println("line 44 Order.java " +this.status);
        OrderStatus currentStatus = OrderStatusFactory.getOrderStatus(this.status);
        System.out.println("line 46 Order.java " +this.status +" "+deliveryMethod);
        Delivery delivery = DeliveryFactory.getDelivery(deliveryMethod);
        currentStatus.setStatusToShipped(this, deliveryMethod);
        this.trackingNumber = delivery.getDeliveryMethod().toString();
    }

    public void setStatusToCompleted(){
        OrderStatus currentStatus = OrderStatusFactory.getOrderStatus(this.status);
        currentStatus.setStatusToCompleted(this);
    }
}