package com.gabriel.helpdesk.services;

import com.gabriel.helpdesk.domain.Tecnico;
import com.gabriel.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    @Transactional(readOnly = true)
    public Tecnico buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }
}
