package id.ac.ui.cs.youkosu.microserviceorder.model;

class OrderStateUnverified extends OrderStatus {

    OrderStateUnverified(Order order) {
        super(order);
    }

    @Override
    public void setStatusToVerified() {
        this.order.setStatus(new OrderStateVerified(this.order));
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
        return "UNVERIFIED";
    }
}