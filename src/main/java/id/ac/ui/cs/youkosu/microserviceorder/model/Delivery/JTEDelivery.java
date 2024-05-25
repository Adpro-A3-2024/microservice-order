package id.ac.ui.cs.youkosu.microserviceorder.model.Delivery;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Random;

@Getter @Setter @Data
public class JTEDelivery implements DeliveryMethod{
    private String trackingNumber;

    public JTEDelivery(){
        setTrackingNumber();
    }
    @Override
    public void setTrackingNumber() {
        this.trackingNumber= "JTE-" + RandomTrackingGenerator.generateRandomNumber(12);
    }

    @Override
    public boolean validateTrackingNumber() {
        if (trackingNumber == null || trackingNumber.length() != 16) {
            return false;
        }
        if (!trackingNumber.startsWith("JTE-")) {
            return false;
        }
        for (int i = 4; i < trackingNumber.length(); i++) {
            if (!Character.isDigit(trackingNumber.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    @JsonValue
    @Override
    public String toString(){
        return this.trackingNumber;
    }
}