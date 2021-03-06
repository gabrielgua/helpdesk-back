package com.gabriel.helpdesk.services;

import com.gabriel.helpdesk.domain.Pessoa;
import com.gabriel.helpdesk.domain.Cliente;
import com.gabriel.helpdesk.dto.ClienteDTO;
import com.gabriel.helpdesk.exception.DataIntegrityViolationException;
import com.gabriel.helpdesk.exception.ObjectNotFoundException;
import com.gabriel.helpdesk.repositories.PessoaRepository;
import com.gabriel.helpdesk.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional(readOnly = true)
    public Cliente buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Cliente> buscarTodos() {
        return repository.findAll();
    }

    @Transactional(readOnly = false)
    public Cliente salvar(ClienteDTO ClienteDTO) {
        ClienteDTO.setId(null);
        ClienteDTO.setSenha(encoder.encode(ClienteDTO.getSenha()));
        validaPorCpfEEmail(ClienteDTO);
        Cliente cliente = new Cliente(ClienteDTO);
        return repository.save(cliente);
    }

    @Transactional(readOnly = false)
    private void validaPorCpfEEmail(ClienteDTO clienteDTO) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(clienteDTO.getCpf());
        if (pessoa.isPresent() && pessoa.get().getId() != clienteDTO.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado!");
        }

        pessoa = pessoaRepository.findByEmail(clienteDTO.getEmail());
        if (pessoa.isPresent() && pessoa.get().getId() != clienteDTO.getId()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado!");
        }
    }

    @Transactional(readOnly = false)
    public Cliente editar(Long id, ClienteDTO clienteDTO) {
        clienteDTO.setId(id);
        Cliente cliente = buscarPorId(id);

        if (!clienteDTO.getSenha().equals(cliente.getSenha())) {
            clienteDTO.setSenha(encoder.encode(clienteDTO.getSenha()));
        }

        validaPorCpfEEmail(clienteDTO);
        cliente = new Cliente(clienteDTO);
        return repository.save(cliente);
    }

    @Transactional(readOnly = false)
    public void deletar(Long id) {
        Cliente cliente = buscarPorId(id);
        if (cliente.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Cliente possui chamados atrelados!");
        }
        repository.deleteById(id);
    }
}
