package id.ac.ui.cs.youkosu.microserviceorder.model.Delivery;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @Data
public class GobekDelivery implements DeliveryMethod{
    String trackingNumber;

    public GobekDelivery(){
        setTrackingNumber();
    }
    @Override
    public void setTrackingNumber() {
        this.trackingNumber = "GBK-" + RandomTrackingGenerator.generateRandomAlphanumeric(12);
    }

    @Override
    public boolean validateTrackingNumber(){
        if (trackingNumber == null || trackingNumber.length() != 16) {
            return false;
        }
        if (!trackingNumber.startsWith("GBK-")) {
            return false;
        }
        for (int i = 4; i < trackingNumber.length(); i++) {
            char c = trackingNumber.charAt(i);
            if (!Character.isUpperCase(c) && !Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}