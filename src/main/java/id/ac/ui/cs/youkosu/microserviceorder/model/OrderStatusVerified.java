package id.ac.ui.cs.youkosu.microserviceorder.model;

class OrderStateVerified extends OrderStatus {
    public OrderStateVerified() {
    }
    @Override
    public void setStatusToVerified(Order order) {
    }

    @Override
    public void setStatusToCancelled(Order order) {
        order.setStatus(new OrderStateCancelled());
    }

    @Override
    public void setStatusToShipped(Order order) {
        order.setStatus(new OrderStatusShipped());
    }

    @Override
    public void setStatusToCompleted(Order order) {
    }

    @Override
    public String toString(){
        return "VERIFIED";
    }
}