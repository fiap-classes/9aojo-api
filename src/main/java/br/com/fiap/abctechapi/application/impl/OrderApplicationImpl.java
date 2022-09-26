package br.com.fiap.abctechapi.application.impl;

import br.com.fiap.abctechapi.application.OrderApplication;
import br.com.fiap.abctechapi.application.dto.AssistDto;
import br.com.fiap.abctechapi.application.dto.OrderDto;
import br.com.fiap.abctechapi.application.dto.OrderLocationDto;
import br.com.fiap.abctechapi.application.dto.OrderResponseDto;
import br.com.fiap.abctechapi.model.Assistance;
import br.com.fiap.abctechapi.model.Order;
import br.com.fiap.abctechapi.model.OrderLocation;
import br.com.fiap.abctechapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderApplicationImpl implements OrderApplication {

    private OrderService orderService;
    public OrderApplicationImpl(@Autowired OrderService orderService){
        this.orderService = orderService;
    }
    @Override
    public void createOrder(OrderDto orderDto) throws Exception {
        Order order = new Order();
        order.setOperatorId(orderDto.getOperatorId());
        order.setStartOrderLocation(getOrderLocationFromOrderLocationDto(orderDto.getStart()));
        order.setEndOrderLocation(getOrderLocationFromOrderLocationDto(orderDto.getEnd()));
        this.orderService.saveOrder(order, orderDto.getAssists());
    }

    @Override
    public List<OrderResponseDto> listOrderByOperatorId(Long operatorId){
        return orderService.listOrderByOperator(operatorId).stream().map(
                (order) -> new OrderResponseDto(order.getId(), order.getOperatorId(), order.getAssists().stream().map(this::mapAssistToDto).collect(Collectors.toList()), mapOrderLocationToDto(order.getStartOrderLocation()), mapOrderLocationToDto(order.getEndOrderLocation()))
        ).collect(Collectors.toList());
    }
    private AssistDto mapAssistToDto(Assistance assist){
        return new AssistDto(assist.getId(), assist.getName(), assist.getDescription());
    }
    private OrderLocation getOrderLocationFromOrderLocationDto(OrderLocationDto orderLocationDto){
        OrderLocation orderLocation = new OrderLocation();
        orderLocation.setLatitude(orderLocationDto.getLatitude());
        orderLocation.setLongitude(orderLocationDto.getLongitude());
        orderLocation.setDate(orderLocationDto.getDateTime());
        return orderLocation;
    }

    private OrderLocationDto mapOrderLocationToDto(OrderLocation orderLocation){
        OrderLocationDto orderLocationDto = new OrderLocationDto();
        orderLocationDto.setLatitude(orderLocation.getLatitude());
        orderLocationDto.setLongitude(orderLocation.getLongitude());
        orderLocationDto.setDateTime(orderLocation.getDate());
        return orderLocationDto;
    }
}
