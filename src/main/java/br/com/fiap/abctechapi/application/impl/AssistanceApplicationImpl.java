package br.com.fiap.abctechapi.application.impl;


import br.com.fiap.abctechapi.application.AssistanceApplication;
import br.com.fiap.abctechapi.application.dto.AssistDto;
import br.com.fiap.abctechapi.model.Assistance;
import br.com.fiap.abctechapi.service.AssistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AssistanceApplicationImpl implements AssistanceApplication {

    private AssistanceService service;

    public  AssistanceApplicationImpl (@Autowired AssistanceService service){
        this.service = service;
    }
    @Override
    public List<AssistDto> getAssists() {
        return this.service.getAssistanceList().stream().map(this::mapAssistToDto).collect(Collectors.toList());
    }

    private AssistDto mapAssistToDto(Assistance assist){
        return new AssistDto(assist.getId(), assist.getName(), assist.getDescription());
    }
}
