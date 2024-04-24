package id.ac.ui.cs.youkosu.microserviceorder.model;

class OrderStateCancelled extends OrderStatus {

    OrderStateCancelled() {
    }

    @Override
    public void setStatusToVerified(Order order) {
    }

    @Override
    public void setStatusToCancelled(Order order) {
    }

    @Override
    public void setStatusToShipped(Order order) {
    }

    @Override
    public void setStatusToCompleted(Order order) {
    }

    @Override
    public String toString(){
        return "CANCELLED";
    }
}