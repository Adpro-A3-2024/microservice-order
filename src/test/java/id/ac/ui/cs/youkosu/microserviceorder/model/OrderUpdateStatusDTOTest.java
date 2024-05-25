package id.ac.ui.cs.youkosu.microserviceorder.model.DTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderUpdateStatusDTOTest {
    private OrderUpdateStatusDTO dto;
    private UUID orderId;
    private String status;
    private String delivery;

    @BeforeEach
    void setUp() {
        orderId = UUID.randomUUID();
        status = "VERIFIED";
        delivery = "DeliveryMethod";
        dto = new OrderUpdateStatusDTO();
        dto.setOrderId(orderId);
        dto.setStatus(status);
        dto.setDelivery(delivery);
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(orderId, dto.getOrderId());
        assertEquals(status, dto.getStatus());
        assertEquals(delivery, dto.getDelivery());
    }

    @Test
    void testEqualsAndHashCode() {
        OrderUpdateStatusDTO sameDto = new OrderUpdateStatusDTO();
        sameDto.setOrderId(orderId);
        sameDto.setStatus(status);
        sameDto.setDelivery(delivery);

        OrderUpdateStatusDTO differentDto = new OrderUpdateStatusDTO();
        differentDto.setOrderId(UUID.randomUUID());
        differentDto.setStatus("CANCELLED");
        differentDto.setDelivery("DifferentDeliveryMethod");

        assertEquals(dto, sameDto);
        assertNotEquals(dto, differentDto);
        assertEquals(dto.hashCode(), sameDto.hashCode());
        assertNotEquals(dto.hashCode(), differentDto.hashCode());
    }

    @Test
    void testEqualsDifferentTypes() {
        assertNotEquals(dto, new Object());
    }

    @Test
    void testEqualsNull() {
        assertNotEquals(dto, null);
    }

    @Test
    void testToString() {
        String expected = "OrderUpdateStatusDTO(orderId=" + orderId + ", status=" + status + ", delivery=" + delivery + ")";
        assertEquals(expected, dto.toString());
    }

    @Test
    void testCanEqual() {
        OrderUpdateStatusDTO sameDto = new OrderUpdateStatusDTO();
        assertTrue(dto.canEqual(sameDto));
        assertFalse(dto.canEqual(new Object()));
    }
}
