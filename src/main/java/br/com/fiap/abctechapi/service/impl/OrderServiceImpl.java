package br.com.fiap.abctechapi.service.impl;

import br.com.fiap.abctechapi.handler.expcetion.MaxAssistsException;
import br.com.fiap.abctechapi.handler.expcetion.MinimumAssistRequiredException;
import br.com.fiap.abctechapi.model.Assistance;
import br.com.fiap.abctechapi.model.Order;
import br.com.fiap.abctechapi.repository.AssistanceRepository;
import br.com.fiap.abctechapi.repository.OrderRepository;
import br.com.fiap.abctechapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private AssistanceRepository assistanceRepository;


    public OrderServiceImpl(
            @Autowired OrderRepository orderRepository,
            @Autowired AssistanceRepository assistanceRepository) {
        this.orderRepository = orderRepository;
        this.assistanceRepository = assistanceRepository;
    }

    @Override
    public void saveOrder(Order order, List<Long> arrayAssists) throws Exception {
        ArrayList<Assistance> assistances = new ArrayList<>();
        arrayAssists.forEach( i -> {
            Optional<Assistance> assistance = assistanceRepository.findById(i);
            if(!assistance.isPresent()){
//                throw new Not();
            }
            assistances.add(assistance.get());

        });

        order.setAssists(assistances);

        if(!order.hasMinAssists()){
            throw new MinimumAssistRequiredException("Invalid Assists", "Necessario no minimo 1 assistencia");
        }else if (order.exceedsMaxAssists()){
            throw new MaxAssistsException("Invalid Assists", "Número máximo de assistências é 15");
        }
        orderRepository.save(order);
    }

    @Override
    public List<Order> listOrderByOperator(Long operatorId) {
        return orderRepository.getOrdersByOperatorId(operatorId);
    }
}
