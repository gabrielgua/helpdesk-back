package com.gabriel.helpdesk.controller;

import com.gabriel.helpdesk.domain.Tecnico;
import com.gabriel.helpdesk.dto.TecnicoDTO;
import com.gabriel.helpdesk.services.TecnicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("tecnicos")
@Tag(name = "Técnico")
public class TecnicoController {

    @Autowired
    private TecnicoService service;

    @GetMapping("/{id}")
    @Operation(description = "Busca o técnico por {id}")
    public ResponseEntity<TecnicoDTO> buscarTecnicoPorId(@PathVariable("id") Long id) {
        Tecnico tecnico = service.buscarPorId(id);
        return ResponseEntity.ok().body(new TecnicoDTO(tecnico));
    }

    @GetMapping
    @Operation(description = "Busca todos os técnicos")
    public ResponseEntity<List<TecnicoDTO>> buscarTodosTecnicos() {
        List<Tecnico> tecnicos = service.buscarTodos();
        List<TecnicoDTO> dtos = tecnicos.stream().map(t -> new TecnicoDTO(t)).collect(Collectors.toList());
        return ResponseEntity.ok().body(dtos);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    @Operation(description = "Persiste o técnico no bando de dados")
    public ResponseEntity<TecnicoDTO> salvarTecnico(@Valid @RequestBody TecnicoDTO tecnicoDTO) {
        Tecnico tecnico = service.salvar(tecnicoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tecnico.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    @Operation(description = "Atualiza o técnico especificado pelo {id}")
    public ResponseEntity<TecnicoDTO> editarTecnico(@PathVariable("id") Long id,@Valid @RequestBody TecnicoDTO tecnicoDTO) {
        Tecnico tecnico = service.editar(id, tecnicoDTO);
        return ResponseEntity.ok().body(new TecnicoDTO(tecnico));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(description = "Remove o técnico especificado pelo {id}")
    public ResponseEntity<TecnicoDTO> deletarTecnico(@PathVariable("id") Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

