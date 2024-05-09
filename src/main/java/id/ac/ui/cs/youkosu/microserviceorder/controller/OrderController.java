package id.ac.ui.cs.youkosu.microserviceorder.controller;


import id.ac.ui.cs.youkosu.microserviceorder.model.Delivery.Delivery;
import id.ac.ui.cs.youkosu.microserviceorder.model.Delivery.DeliveryException;
import id.ac.ui.cs.youkosu.microserviceorder.model.Delivery.GobekDelivery;
import id.ac.ui.cs.youkosu.microserviceorder.model.Delivery.JTEDelivery;
import id.ac.ui.cs.youkosu.microserviceorder.service.OrderServiceImpl;
import id.ac.ui.cs.youkosu.microserviceorder.service.OrderStatusUpdateException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import id.ac.ui.cs.youkosu.microserviceorder.model.Order.Order;

import java.util.List;

@RestController
@RequestMapping("api")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping("/get-all")
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/get/{orderId}")
    public Order findById(@PathVariable String orderId) {
        return orderService.findById(orderId);
    }

    @PostMapping("/create-order")
    public Order createProductPost(@RequestBody @Validated Order order) {
        return orderService.createOrder(order);
    }

    @PutMapping("/edit-status")
    public ResponseEntity<String> updateStatus(@RequestBody @Validated UpdateStatusRequest request) {
        try {
            Order updatedOrder = orderService.updateStatus(request.getOrderId(), request.getStatus(), request.getDelivery());
            return ResponseEntity.ok("Order status updated successfully");
        } catch (OrderStatusUpdateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (DeliveryException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
    @Data
    static class UpdateStatusRequest {
        private String orderId;
        private String status;
        private String delivery;

        // Getters and setters
        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDelivery() {
            return delivery;
        }
        public Delivery getDeliveryObj() {
            Delivery delivery = new Delivery();
;            if(this.delivery.equals("JTE")){
                delivery.setDeliveryMethod(new JTEDelivery());
                return delivery;
            }else if(this.delivery.equals("Gobek")){
                delivery.setDeliveryMethod(new JTEDelivery());
                return delivery;
            }else if(this.delivery.equals("SiWuzz")){
                delivery.setDeliveryMethod(new JTEDelivery());
                return delivery;
            }else {return null;}

        }

    }

}


