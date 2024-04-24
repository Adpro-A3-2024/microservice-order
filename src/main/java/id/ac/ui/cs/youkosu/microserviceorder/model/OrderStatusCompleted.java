package id.ac.ui.cs.youkosu.microserviceorder.model;

class OrderStatusCompleted extends OrderStatus {

    OrderStatusCompleted() {
    }

    @Override
    public void setStatusToVerified(Order order) {
    }

    @Override
    public void setStatusToCancelled(Order order) {
    }

    @Override
    public void setStatusToShipped(Order order) {
    }

    @Override
    public void setStatusToCompleted(Order order) {
    }

    @Override
    public String toString(){
        return "COMPLETED";
    }
}