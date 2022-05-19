package com.gabriel.helpdesk.controller;

import com.gabriel.helpdesk.domain.Chamado;
import com.gabriel.helpdesk.dto.ChamadoDTO;
import com.gabriel.helpdesk.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
