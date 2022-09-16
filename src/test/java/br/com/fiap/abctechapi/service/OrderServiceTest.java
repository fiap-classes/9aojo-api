package br.com.fiap.abctechapi.service;


import br.com.fiap.abctechapi.handler.expcetion.MaxAssistsException;
import br.com.fiap.abctechapi.handler.expcetion.MinimumAssistRequiredException;
import br.com.fiap.abctechapi.model.Assistance;
import br.com.fiap.abctechapi.model.Order;
import br.com.fiap.abctechapi.repository.AssistanceRepository;
import br.com.fiap.abctechapi.repository.OrderRepository;
import br.com.fiap.abctechapi.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private AssistanceRepository assistanceRepository;

    private OrderService orderService;

    @BeforeEach
    public void init() {
        orderService = new OrderServiceImpl(orderRepository, assistanceRepository);
        when(assistanceRepository.findById(any())).
                thenReturn(
                        Optional.of(new Assistance(1L, "Teste", "Teste description:")));
    }

    @Test
    public void order_service_not_null (){
        Assertions.assertNotNull(orderService);
    }

    @Test
    public void create_order_error_minimum() {
        Order newOrder = new Order();
        newOrder.setOperatorId(1234L);

        Assertions.assertThrows(MinimumAssistRequiredException.class, () -> orderService.saveOrder(newOrder, List.of()));
        verify(orderRepository, times(0)).save(newOrder);
    }

    @Test
    public void create_order_error_maximum() {
        Order newOrder = new Order();
        newOrder.setOperatorId(1234L);

        Assertions.assertThrows(MaxAssistsException.class, () -> orderService.saveOrder(newOrder, generate_mocks_ids(20)));
        verify(orderRepository, times(0)).save(newOrder);
    }

    @Test
    public void create_order_success() throws Exception {
        Order newOrder = new Order();
        newOrder.setOperatorId(1234L);

        orderService.saveOrder(newOrder, generate_mocks_ids(5));
        verify(orderRepository, times(1)).save(newOrder);
    }


    private List<Long> generate_mocks_ids(int number){
        ArrayList<Long> arrayList = new ArrayList<>();
        for (int x= 0; x< number; x++){
            arrayList.add(Integer.toUnsignedLong(x));
        }
        return arrayList;
    }

}
