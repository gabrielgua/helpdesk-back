package com.gabriel.helpdesk.controller;

import com.gabriel.helpdesk.domain.Chamado;
import com.gabriel.helpdesk.dto.ChamadoDTO;
import com.gabriel.helpdesk.services.ChamadoService;
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
public class ChamadoController {

    @Autowired
    private ChamadoService service;

    @GetMapping("/{id}")
    public ResponseEntity<ChamadoDTO> buscarChamadoPorId(@PathVariable("id") Long id) {
        Chamado chamado = service.buscarPorId(id);
        return ResponseEntity.ok().body(new ChamadoDTO(chamado));
    }

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> buscarTodosChamados() {
        List<Chamado> chamados = service.buscarTodos();
        List<ChamadoDTO> dtos = chamados.stream().map(c -> new ChamadoDTO(c)).collect(Collectors.toList());
        return ResponseEntity.ok().body(dtos);
    }

    @PostMapping
    public ResponseEntity<ChamadoDTO> salvarChamado(@Valid @RequestBody ChamadoDTO dto) {
        Chamado chamado = service.salvar(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(chamado.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


}
