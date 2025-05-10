package ru.kovrochist.platform.mono.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kovrochist.platform.mono.entity.OrderItems;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItems, Long> {
}
