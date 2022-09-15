package br.com.fiap.abctechapi.application;

import br.com.fiap.abctechapi.application.dto.AssistDto;

import java.util.List;

public interface AssistanceApplication {
    List<AssistDto> getAssists();
}
