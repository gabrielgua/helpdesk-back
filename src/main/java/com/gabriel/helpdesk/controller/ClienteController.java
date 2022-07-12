package com.gabriel.helpdesk.controller;

import com.gabriel.helpdesk.domain.Cliente;
import com.gabriel.helpdesk.dto.ClienteDTO;
import com.gabriel.helpdesk.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("clientes")
@Tag(name = "Clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping("/{id}")
    @Operation(description = "Busca cliente por {id}")
    public ResponseEntity<ClienteDTO> buscarClientePorId(@PathVariable("id") Long id) {
        Cliente cliente = service.buscarPorId(id);
        return ResponseEntity.ok().body(new ClienteDTO(cliente));
    }

    @GetMapping
    @Operation(description = "Busca todos os clientes")
    public ResponseEntity<List<ClienteDTO>> buscarTodosClientes() {
        List<Cliente> cliente = service.buscarTodos();
        List<ClienteDTO> dtos = cliente.stream().map(t -> new ClienteDTO(t)).collect(Collectors.toList());
        return ResponseEntity.ok().body(dtos);
    }

    @PostMapping
    @Operation(description = "Persiste o cliente no banco de dados")
    public ResponseEntity<ClienteDTO> salvarCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = service.salvar(clienteDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Operation(description = "Atualiza o cliente especificado pelo {id}")
    public ResponseEntity<ClienteDTO> editarCliente(@PathVariable("id") Long id,@Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = service.editar(id, clienteDTO);
        return ResponseEntity.ok().body(new ClienteDTO(cliente));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Remove o cliente especificado pelo {id}")
    public ResponseEntity<ClienteDTO> deletarCliente(@PathVariable("id") Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

