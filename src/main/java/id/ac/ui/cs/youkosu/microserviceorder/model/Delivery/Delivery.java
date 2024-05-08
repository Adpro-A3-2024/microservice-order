package id.ac.ui.cs.youkosu.microserviceorder.model.Delivery;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Data
public class Delivery{
    DeliveryMethod deliveryMethod;

    void setTrackingNumber(){
        this.deliveryMethod.setTrackingNumber();
    }
}