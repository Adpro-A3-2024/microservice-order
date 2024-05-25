package id.ac.ui.cs.youkosu.microserviceorder.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import id.ac.ui.cs.youkosu.microserviceorder.tempModel.CartItem;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
import java.util.List;

@Data @Getter @Setter
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class OrderUpdateStatusDTO {
    private UUID orderId;
    private String status;
    private String delivery;
}
