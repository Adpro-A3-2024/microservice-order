package id.ac.ui.cs.youkosu.microserviceorder.model;

abstract class OrderStatus{
    protected Order order;
    OrderStatus(Order order){
        this.order = order;
    }
    abstract void setStatusToVerified();
    abstract void setStatusToCancelled();
    abstract void setStatusToShipped();
    abstract void setStatusToCompleted();
}