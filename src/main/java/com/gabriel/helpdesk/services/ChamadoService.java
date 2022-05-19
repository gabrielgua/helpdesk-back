package com.gabriel.helpdesk.services;

import com.gabriel.helpdesk.domain.Chamado;
import com.gabriel.helpdesk.domain.Cliente;
import com.gabriel.helpdesk.domain.Tecnico;
import com.gabriel.helpdesk.dto.ChamadoDTO;
import com.gabriel.helpdesk.enums.Prioridade;
import com.gabriel.helpdesk.enums.Status;
import com.gabriel.helpdesk.exception.ObjectNotFoundException;
import com.gabriel.helpdesk.repositories.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    @Transactional(readOnly = true)
    public Chamado buscarPorId(Long id) {
        Optional<Chamado> chamado = repository.findById(id);
        return chamado.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: "+id));
    }

    @Transactional(readOnly = true)
    public List<Chamado> buscarTodos() {
        return repository.findAll();
    }

    @Transactional(readOnly = false)
    public Chamado salvar(@Valid ChamadoDTO dto) {
        return repository.save(novoChamado(dto));
    }

    private Chamado novoChamado(ChamadoDTO dto) {
        Tecnico tecnico = tecnicoService.buscarPorId(dto.getTecnico());
        Cliente cliente = clienteService.buscarPorId(dto.getCliente());

        Chamado chamado = new Chamado();
        if (dto.getId() != null) {
            chamado.setId(dto.getId());
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(dto.getPrioridade()));
        chamado.setStatus(Status.toEnum(dto.getStatus()));
        chamado.setTitulo(dto.getTitulo());
        chamado.setObservacoes(dto.getObservacoes());

        return chamado;
    }




























}
