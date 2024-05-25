package id.ac.ui.cs.youkosu.microserviceorder.model.Order;

public class OrderStatusFactory {
    public static OrderStatus getOrderStatus(String status) {
        switch (status) {
            case "UNVERIFIED":
                return new OrderStatusUnverified();
            case "VERIFIED":
                return new OrderStatusVerified();
            case "SHIPPED":
                return new OrderStatusShipped();
            case "CANCELLED":
                return new OrderStatusCancelled();
            case "COMPLETED":
                return new OrderStatusCompleted();
            default:
                throw new IllegalArgumentException("Invalid order status: " + status);
        }
    }
}
