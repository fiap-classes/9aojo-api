package br.com.fiap.abctechapi.application.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AssistDto {
     private Long id;
     private String name;
     private String description;
}

