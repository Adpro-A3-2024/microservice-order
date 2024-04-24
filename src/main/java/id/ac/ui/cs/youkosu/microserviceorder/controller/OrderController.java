package id.ac.ui.cs.youkosu.microserviceorder.controller;


import id.ac.ui.cs.youkosu.microserviceorder.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import id.ac.ui.cs.youkosu.microserviceorder.tempModel.Product;
import id.ac.ui.cs.youkosu.microserviceorder.model.Order;

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

    @PutMapping("/edit-status/{orderId}/{status}")
    public Order updateStatus(@PathVariable String orderId, @PathVariable String status) {
        return orderService.updateStatus(orderId, status);
    }
}
