package id.ac.ui.cs.youkosu.microserviceorder.model;

class OrderStateVerified extends OrderStatus {

    OrderStateVerified(Order order) {
        super(order);
    }

    @Override
    public void setStatusToVerified() {
    }

    @Override
    public void setStatusToCancelled() {
        this.order.setStatus(new OrderStateCancelled(this.order));
    }

    @Override
    public void setStatusToShipped() {
        this.order.setStatus(new OrderStatusShipped(this.order));
    }

    @Override
    public void setStatusToCompleted() {
    }

    @Override
    public String toString(){
        return "VERIFIED";
    }
}