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
    private List<Product> products;
    private String status;
    private String trackingNumber;
    private Double totalPrice;

    public Order(String orderId, List<Product> products){
        this.orderId = orderId;
        this.products = products;
        this.status = new OrderStatusUnverified().toString();
        this.totalPrice = calculateTotalPrice(products);

        if (products.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            this.products = products;
        }
    }

    private Double calculateTotalPrice(List<Product> products) {
        double total = 0.0;
        for (Product product : products) {
            total += product.getProductReqPrice();
        }
        return total;
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
        OrderStatus currentStatus = OrderStatusFactory.getOrderStatus(this.status);
        Delivery delivery = DeliveryFactory.getDelivery(deliveryMethod);
        currentStatus.setStatusToShipped(this, deliveryMethod);
    }

    public void setStatusToCompleted(){
        OrderStatus currentStatus = OrderStatusFactory.getOrderStatus(this.status);
        currentStatus.setStatusToCompleted(this);
    }
}