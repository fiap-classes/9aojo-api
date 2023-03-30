package br.com.fiap.abctechapi.service;

import br.com.fiap.abctechapi.model.Assistance;
import br.com.fiap.abctechapi.repository.AssistanceRepository;
import br.com.fiap.abctechapi.service.impl.AssistanceServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.Mockito.*;


@SpringBootTest
public class AssistanceServiceTest {

    @Mock
    private AssistanceRepository assistanceRepository;
    private AssistanceService assistanceService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        assistanceService = new AssistanceServiceImpl(assistanceRepository);
    }

    @DisplayName("Listando assistencias disponiveis :: Sucesso")
    @Test
    public void list_success(){
        Assistance assistance1 = new Assistance(1L, "Mock Assistance 1", "Description 1");
        Assistance assistance3 = new Assistance(3L, "Mock Assistance 3", "Description 3");
        Assistance assistance2 = new Assistance(2L, "Mock Assistance 2", "Description 2");

        when(assistanceRepository.findAll()).thenReturn(List.of(assistance1, assistance2, assistance3));

        List<Assistance> values = assistanceService.getAssistanceList();

        Assertions.assertEquals(3, values.size());
        Assertions.assertSame(values.get(0), assistance1);
        Assertions.assertSame(values.get(1), assistance2);
    }

    @DisplayName("Listando assistencias indisponiveis :: Erro")
    @Test
    public void list_error(){
        when(assistanceRepository.findAll()).thenReturn(List.of());

        List<Assistance> values = assistanceService.getAssistanceList();
        Assertions.assertEquals(0, values.size());
    }

}

/**
 *
 *
 * Order (id:Long, operator_id:Long, assistances: List<Assistance>, start:OrderLocation, end:OrderLocation) -> Orders
 * OrderLocation(id:Long, latitude: Double, longitude: Double, date: Date) -> OrdersLocations
 * {
 * "operator_id" : 0,
 * "services" : [1 , 2, 3],
 * "start" : {
 * 	"latitude" : 0.0,
 * 	"longitude" : 0.0,
 * 	"datetime" : "2022-02-19 00:00:00"
 * },
 * "end" : {
 * 	"latitude" : 0.0,
 * 	"longitude" : 0.0,
 * 	"datetime" : "2022-02-19 00:00:00"
 * }
 * }
 */
