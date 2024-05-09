package id.ac.ui.cs.youkosu.microserviceorder.service;

public class OrderStatusUpdateException extends RuntimeException {
    public OrderStatusUpdateException(String message) {
        super(message);
    }
}