package br.com.fiap.abctechapi.application.dto;


import lombok.Data;

import java.util.List;

/**
 * ```json
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
 * ```
 */
@Data
public class OrderDto {

    private Long operatorId;
    private List<Long> assists;
    private OrderLocationDto start;
    private OrderLocationDto end;
}
