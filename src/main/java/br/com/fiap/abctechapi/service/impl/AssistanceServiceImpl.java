package br.com.fiap.abctechapi.service.impl;

import br.com.fiap.abctechapi.model.Assistance;
import br.com.fiap.abctechapi.repository.AssistanceRepository;
import br.com.fiap.abctechapi.service.AssistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssistanceServiceImpl implements AssistanceService {

    private final AssistanceRepository repository;

    @Autowired
    public AssistanceServiceImpl(AssistanceRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Assistance> getAssistanceList() {
        return repository.findAll();
    }
}
