package id.ac.ui.cs.youkosu.microserviceorder.model;

class OrderStateCancelled extends OrderStatus {

    OrderStateCancelled(Order order) {
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
        return "CANCELLED";
    }
}