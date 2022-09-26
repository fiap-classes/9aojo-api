package br.com.fiap.abctechapi.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderResponseDto {

    public Long id;
    private Long operatorId;
    private List<AssistDto> assists;
    private OrderLocationDto start;
    private OrderLocationDto end;

}
