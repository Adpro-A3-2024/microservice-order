package id.ac.ui.cs.youkosu.microserviceorder.model;

class OrderStateUnverified extends OrderStatus {

    OrderStateUnverified(Order order) {
        super(order);
    }

    @Override
    void setStatusToVerified() {
        this.order.setStatus(new OrderStateVerified(this.order));
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
        return "UNVERIFIED";
    }
}