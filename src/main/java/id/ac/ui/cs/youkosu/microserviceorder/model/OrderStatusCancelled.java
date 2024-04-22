package id.ac.ui.cs.youkosu.microserviceorder.model;

class OrderStateCancelled extends OrderStatus {

    OrderStateCancelled(Order order) {
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
    }

    @Override
    public String toString(){
        return "CANCELLED";
    }
}