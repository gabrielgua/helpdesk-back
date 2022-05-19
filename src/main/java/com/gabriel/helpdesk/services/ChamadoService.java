package com.gabriel.helpdesk.services;

import com.gabriel.helpdesk.domain.Chamado;
import com.gabriel.helpdesk.dto.ChamadoDTO;
import com.gabriel.helpdesk.exception.ObjectNotFoundException;
import com.gabriel.helpdesk.repositories.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;

    @Transactional(readOnly = true)
    public Chamado buscarPorId(Long id) {
        Optional<Chamado> chamado = repository.findById(id);
        return chamado.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: "+id));
    }

    @Transactional(readOnly = true)
    public List<Chamado> buscarTodos() {
        return repository.findAll();
    }
}
