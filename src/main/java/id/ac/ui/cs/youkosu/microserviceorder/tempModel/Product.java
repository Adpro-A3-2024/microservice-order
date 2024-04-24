package id.ac.ui.cs.youkosu.microserviceorder.tempModel;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Product {
    private String productReqId;
    private String productReqName;
    private double productReqPrice;
    private String productReqPictureUrl;
    private String productReqSourceUrl;
}