package id.ac.ui.cs.youkosu.microserviceorder.model;

class OrderStatusShipped extends OrderStatus {

    OrderStatusShipped(Order order) {
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
        this.order.setStatus(new OrderStatusCompleted(this.order));
    }

    @Override
    public String toString(){
        return "SHIPPED";
    }
}