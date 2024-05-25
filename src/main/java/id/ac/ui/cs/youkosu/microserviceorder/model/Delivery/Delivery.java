package id.ac.ui.cs.youkosu.microserviceorder.model.Delivery;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Data
public class Delivery{
    DeliveryMethod deliveryMethod;

    void setTrackingNumber(){
        this.deliveryMethod.setTrackingNumber();
    }

    @JsonValue
    @Override
    public String toString(){
        return deliveryMethod.toString();
    }
}