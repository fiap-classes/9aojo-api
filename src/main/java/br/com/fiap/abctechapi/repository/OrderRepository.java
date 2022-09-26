package br.com.fiap.abctechapi.repository;

import br.com.fiap.abctechapi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> getOrdersByOperatorId(Long operatorId);
}

