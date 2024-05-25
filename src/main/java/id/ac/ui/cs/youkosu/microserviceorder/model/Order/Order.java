package id.ac.ui.cs.youkosu.microserviceorder.model.Order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;
import id.ac.ui.cs.youkosu.microserviceorder.model.Delivery.Delivery;
import id.ac.ui.cs.youkosu.microserviceorder.model.Delivery.DeliveryFactory;
import id.ac.ui.cs.youkosu.microserviceorder.model.Delivery.DeliveryMethod;
import id.ac.ui.cs.youkosu.microserviceorder.tempModel.CartItem;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import jakarta.persistence.*;

import java.util.List;

import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "orders")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Data
public class Order {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @NotNull
    private UUID orderId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems;

    @NotNull(message = "Status order tidak boleh bernilai null.")
    @NotBlank(message = "Status order tidak boleh kosong.")
    private String status;

    private String trackingNumber;
    private Double totalPrice;

    public Order(UUID orderId, List<CartItem> cartItems){

        this.orderId = orderId;
        this.cartItems = cartItems;
        this.status = new OrderStatusUnverified().toString();
        this.totalPrice = calculateTotalPrice(cartItems);

        if (cartItems.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            this.cartItems = cartItems;
        }

        for(CartItem cartItem : this.cartItems){
            cartItem.setOrder(this);
        }
    }

    public Order(List<CartItem> cartItems){
        this.cartItems = cartItems;
        this.status = new OrderStatusUnverified().toString();
        this.totalPrice = calculateTotalPrice(cartItems);

        if (cartItems.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            this.cartItems = cartItems;
        }

        for(CartItem cartItem : this.cartItems){
            cartItem.setOrder(this);
        }
    }

    private Double calculateTotalPrice(List<CartItem> cartItems) {
        double total = 0.0;
        for (CartItem cartItem : cartItems) {
            total += cartItem.getProductPrice();
        }
        return total;
    }

    public void setStatusToVerified(){
        OrderStatus currentStatus = OrderStatusFactory.getOrderStatus(this.status);
        currentStatus.setStatusToVerified(this);
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

//    @JsonValue
//    @Override
//    public String toString(){
//        return this.orderId.toString();
//    }
}