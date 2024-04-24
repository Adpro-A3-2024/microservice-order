package id.ac.ui.cs.youkosu.microserviceorder.model;

class OrderStatusCompleted extends OrderStatus {

    OrderStatusCompleted(Order order) {
        super(order);
    }

    @Override
    public void setStatusToVerified() {
    }

    @Override
    public void setStatusToCancelled() {
    }

    @Override
    public void setStatusToShipped() {
    }

    @Override
    public void setStatusToCompleted() {
    }

    @Override
    public String toString(){
        return "COMPLETED";
    }
}