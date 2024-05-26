package id.ac.ui.cs.youkosu.microserviceorder.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import id.ac.ui.cs.youkosu.microserviceorder.model.Order.Order;
import id.ac.ui.cs.youkosu.microserviceorder.tempModel.CartItem;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    Optional<Order> findByTrackingNumber(String trackingNumber);
}