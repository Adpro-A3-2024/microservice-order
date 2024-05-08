package id.ac.ui.cs.youkosu.microserviceorder.model.Delivery;

import id.ac.ui.cs.youkosu.microserviceorder.tempModel.Product;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


public interface DeliveryMethod {
    void setTrackingNumber();
    boolean validateTrackingNumber();
}
