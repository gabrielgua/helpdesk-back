package com.gabriel.helpdesk.services;

import com.gabriel.helpdesk.domain.Pessoa;
import com.gabriel.helpdesk.domain.Tecnico;
import com.gabriel.helpdesk.dto.TecnicoDTO;
import com.gabriel.helpdesk.exception.DataIntegrityViolationException;
import com.gabriel.helpdesk.exception.ObjectNotFoundException;
import com.gabriel.helpdesk.repositories.PessoaRepository;
import com.gabriel.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional(readOnly = true)
    public Tecnico buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Tecnico> buscarTodos() {
        return repository.findAll();
    }

    @Transactional(readOnly = false)
    public Tecnico salvar(TecnicoDTO tecnicoDTO) {
        tecnicoDTO.setId(null);
        tecnicoDTO.setSenha(encoder.encode(tecnicoDTO.getSenha()));
        validaPorCpfEEmail(tecnicoDTO);
        Tecnico tecnico = new Tecnico(tecnicoDTO);
        return repository.save(tecnico);
    }

    @Transactional(readOnly = true)
    private void validaPorCpfEEmail(TecnicoDTO tecnicoDTO) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(tecnicoDTO.getCpf());

        if (pessoa.isPresent() && pessoa.get().getId() != tecnicoDTO.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado!");
        }

        pessoa = pessoaRepository.findByEmail(tecnicoDTO.getEmail());

        if (pessoa.isPresent() && pessoa.get().getId() != tecnicoDTO.getId()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado!");
        }


    }

    @Transactional(readOnly = false)
    public Tecnico editar(Long id, TecnicoDTO tecnicoDTO) {
        tecnicoDTO.setId(id);
        Tecnico tecnico = buscarPorId(id);

        if (!tecnicoDTO.getSenha().equals(tecnico.getSenha())) {
            tecnicoDTO.setSenha(encoder.encode(tecnicoDTO.getSenha()));
        }

        validaPorCpfEEmail(tecnicoDTO);
        tecnico = new Tecnico(tecnicoDTO);
        return repository.save(tecnico);
    }

    @Transactional(readOnly = false)
    public void deletar(Long id) {
        Tecnico tecnico = buscarPorId(id);
        if (tecnico.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Técnico possui chamados atrelados!");
        }
        repository.deleteById(id);
    }
}
