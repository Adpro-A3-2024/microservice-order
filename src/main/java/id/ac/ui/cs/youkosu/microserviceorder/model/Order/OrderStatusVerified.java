package id.ac.ui.cs.youkosu.microserviceorder.model.Order;

import com.fasterxml.jackson.annotation.JsonValue;
import id.ac.ui.cs.youkosu.microserviceorder.model.Delivery.*;
import id.ac.ui.cs.youkosu.microserviceorder.service.OrderStatusUpdateException;

class OrderStatusVerified extends OrderStatus {
    public OrderStatusVerified() {
    }
    @Override
    public void setStatusToVerified(Order order) {
        throw new OrderStatusUpdateException("Cannot update order status to VERIFIED" + " for order " + order.getOrderId());
    }

    @Override
    public void setStatusToCancelled(Order order) {
        throw new OrderStatusUpdateException("Cannot update order status to CANCELLED" + " for order " + order.getOrderId());
    }

    @Override
    public void setStatusToShipped(Order order, String delivery) {
        order.setStatus(new OrderStatusShipped().toString());
        Delivery deliveryObj = new Delivery();

        if(delivery.equals("JTE")){
            deliveryObj.setDeliveryMethod(new JTEDelivery());
            order.setTrackingNumber(deliveryObj.toString());
        }else if(delivery.equals("SiWuzz")){
            deliveryObj.setDeliveryMethod(new SiWuzzDelivery());
            order.setTrackingNumber(deliveryObj.toString());
        }else if(delivery.equals("Gobek")){
            deliveryObj.setDeliveryMethod(new GobekDelivery());
            order.setTrackingNumber(deliveryObj.toString());
        }else{
            throw new DeliveryException("Wrong delivery for order " + order.getOrderId() + ". There is no " +delivery + " delivery method" );
        }
    }

    @Override
    public void setStatusToCompleted(Order order) {
        throw new OrderStatusUpdateException("Cannot update order status to COMPLETED" + " for order " + order.getOrderId());

    }

    @JsonValue
    @Override
    public String toString(){
        return "VERIFIED";
    }
}