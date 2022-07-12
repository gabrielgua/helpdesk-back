package com.gabriel.helpdesk.controller;

import com.gabriel.helpdesk.domain.Chamado;
import com.gabriel.helpdesk.dto.ChamadoDTO;
import com.gabriel.helpdesk.services.ChamadoService;
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
@RequestMapping("chamados")
@Tag(name = "Chamado")
public class ChamadoController {

    @Autowired
    private ChamadoService service;

    @GetMapping("/{id}")
    @Operation(description = "Busca chamado por {id}")
    public ResponseEntity<ChamadoDTO> buscarChamadoPorId(@PathVariable("id") Long id) {
        Chamado chamado = service.buscarPorId(id);
        return ResponseEntity.ok().body(new ChamadoDTO(chamado));
    }

    @GetMapping
    @Operation(description = "Busca todos os Chamados")
    public ResponseEntity<List<ChamadoDTO>> buscarTodosChamados() {
        List<Chamado> chamados = service.buscarTodos();
        List<ChamadoDTO> dtos = chamados.stream().map(c -> new ChamadoDTO(c)).collect(Collectors.toList());
        return ResponseEntity.ok().body(dtos);
    }

    @PostMapping
    @Operation(description = "Persiste o chamado no banco de dados")
    public ResponseEntity<ChamadoDTO> salvarChamado(@Valid @RequestBody ChamadoDTO dto) {
        Chamado chamado = service.salvar(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(chamado.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Operation(description = "Atualiza o chamado especificado pelo {id}")
    public ResponseEntity<ChamadoDTO> editarChamado(@PathVariable("id") Long id, @Valid @RequestBody ChamadoDTO dto) {
        Chamado chamado = service.editar(id, dto);
        return ResponseEntity.ok().body(new ChamadoDTO(chamado));
    }
}
