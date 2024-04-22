package id.ac.ui.cs.youkosu.microserviceorder.model;

class OrderStateVerified extends OrderStatus {

    OrderStateVerified(Order order) {
        super(order);
    }

    @Override
    void setStatusToVerified() {
    }

    @Override
    void setStatusToCancelled() {
        this.order.setStatus(new OrderStateCancelled(this.order));
    }

    @Override
    void setStatusToShipped() {
        this.order.setStatus(new OrderStatusShipped(this.order));
    }

    @Override
    void setStatusToCompleted() {
    }

    @Override
    public String toString(){
        return "VERIFIED";
    }
}