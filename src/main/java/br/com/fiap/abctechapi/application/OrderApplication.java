package br.com.fiap.abctechapi.application;

import br.com.fiap.abctechapi.application.dto.OrderDto;
import br.com.fiap.abctechapi.application.dto.OrderResponseDto;

import java.util.List;

public interface OrderApplication {
    void createOrder(OrderDto orderDto) throws Exception;

    List<OrderResponseDto> listOrderByOperatorId(Long operatorId);
}
