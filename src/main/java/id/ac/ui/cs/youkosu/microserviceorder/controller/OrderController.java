package id.ac.ui.cs.youkosu.microserviceorder.controller;


import id.ac.ui.cs.youkosu.microserviceorder.model.DTO.OrderUpdateStatusDTO;
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
import java.util.UUID;

@RestController
@RequestMapping("api")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping("/get-all")
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/get")
    public Order findById(@RequestParam UUID orderId) {
        return orderService.findById(orderId);
    }

    @PostMapping("/create-order")
    public Order createProductPost(@RequestBody @Validated Order order) {
        Order newOrder = new Order(order.getCartItems());
        newOrder.setOrderId(order.getOrderId());
        return orderService.createOrder(newOrder);
    }

    @PutMapping("/edit-status")
    public ResponseEntity<String> updateStatus(@RequestBody OrderUpdateStatusDTO orderUpdateStatusDTO) {
        try {
            Order updatedOrder = orderService.updateStatus(orderUpdateStatusDTO);
            return ResponseEntity.ok("Order status updated successfully");
        } catch (OrderStatusUpdateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (DeliveryException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
}


