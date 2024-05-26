package id.ac.ui.cs.youkosu.microserviceorder.model.Order;

import com.fasterxml.jackson.annotation.JsonValue;
import id.ac.ui.cs.youkosu.microserviceorder.model.Delivery.*;

class OrderStatusVerified extends OrderStatus {
    public OrderStatusVerified() {
    }
    @Override
    public void setStatusToVerified(Order order) {
        throwUpdateException(order, "VERIFIED");
    }

    @Override
    public void setStatusToCancelled(Order order) {
        throwUpdateException(order, "CANCELLED");
    }

    @Override
    public void setStatusToShipped(Order order, String delivery) {
        order.setStatus(new OrderStatusShipped().toString());
        Delivery deliveryObj = new Delivery();

        switch (delivery) {
            case "JTE":
                deliveryObj.setDeliveryMethod(new JTEDelivery());
                break;
            case "SiWuzz":
                deliveryObj.setDeliveryMethod(new SiWuzzDelivery());
                break;
            case "Gobek":
                deliveryObj.setDeliveryMethod(new GobekDelivery());
                break;
            default:
                throw new DeliveryException("Wrong delivery for order " + order.getOrderId() + ". There is no " + delivery + " delivery method");
        }
        order.setTrackingNumber(deliveryObj.toString());
    }

    @Override
    public void setStatusToCompleted(Order order) {
        throwUpdateException(order, "COMPLETED");
    }

    @JsonValue
    @Override
    public String toString(){
        return "VERIFIED";
    }
}
