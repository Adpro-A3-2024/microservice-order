package id.ac.ui.cs.youkosu.microserviceorder.model.Delivery;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @Data
public class SiWuzzDelivery implements DeliveryMethod{
    String trackingNumber;

    public SiWuzzDelivery(){
        setTrackingNumber();
    }
    @Override
    public void setTrackingNumber() {
        this.trackingNumber = "SWZ-" + RandomTrackingGenerator.generateRandomUppercase(12);
    }

    @Override
    public boolean validateTrackingNumber() {
        if (trackingNumber == null || trackingNumber.length() != 16) {
            return false;
        }
        if (!trackingNumber.startsWith("SWZ-")) {
            return false;
        }
        for (int i = 4; i < trackingNumber.length(); i++) {
            if (!Character.isUpperCase(trackingNumber.charAt(i))) {
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