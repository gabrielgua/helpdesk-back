package com.gabriel.helpdesk.controller;

import com.gabriel.helpdesk.domain.Tecnico;
import com.gabriel.helpdesk.dto.TecnicoDTO;
import com.gabriel.helpdesk.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tecnicos")
public class TecnicoController {

    @Autowired
    private TecnicoService service;

    @GetMapping("/{id}")
    public ResponseEntity<TecnicoDTO> buscarTecnicoPorId(@PathVariable("id") Long id) {
        Tecnico tecnico = service.buscarPorId(id);
        return ResponseEntity.ok().body(new TecnicoDTO(tecnico));
    }
}

