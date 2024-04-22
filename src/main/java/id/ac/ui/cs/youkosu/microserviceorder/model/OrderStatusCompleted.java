package id.ac.ui.cs.youkosu.microserviceorder.model;

class OrderStatusCompleted extends OrderStatus {

    OrderStatusCompleted(Order order) {
        super(order);
    }

    @Override
    void setStatusToVerified() {
    }

    @Override
    void setStatusToCancelled() {
    }

    @Override
    void setStatusToShipped() {
    }

    @Override
    void setStatusToCompleted() {
    }

    @Override
    public String toString(){
        return "COMPLETED";
    }
}