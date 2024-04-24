package id.ac.ui.cs.youkosu.microserviceorder.model;

class OrderStatusShipped extends OrderStatus {

    OrderStatusShipped(Order order) {
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
        this.order.setStatus(new OrderStatusCompleted(this.order));
    }

    @Override
    public String toString(){
        return "SHIPPED";
    }
}