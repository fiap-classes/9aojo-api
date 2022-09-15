package br.com.fiap.abctechapi.application;

import br.com.fiap.abctechapi.application.dto.OrderDto;

public interface OrderApplication {
    void createOrder(OrderDto orderDto);
}
