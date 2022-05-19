package com.gabriel.helpdesk.services;

import com.gabriel.helpdesk.domain.Chamado;
import com.gabriel.helpdesk.exception.ObjectNotFoundException;
import com.gabriel.helpdesk.repositories.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;

    public Chamado buscarPorId(Long id) {
        Optional<Chamado> chamado = repository.findById(id);
        return chamado.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: "+id));
    }

}
