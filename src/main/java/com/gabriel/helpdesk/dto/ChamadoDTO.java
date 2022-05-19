package com.gabriel.helpdesk.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gabriel.helpdesk.domain.Chamado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class ChamadoDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura = LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataConclusao;
    private Integer prioridade;
    private Integer status;
    private String observacoes;
    private String titulo;
    private Long tecnico;
    private Long cliente;
    private String nomeTecnico;
    private String nomeCliente;

    public ChamadoDTO(Chamado c) {
        super();
        this.id = c.getId();
        this.dataAbertura = c.getDataAbertura();
        this.dataConclusao = c.getDataConclusao();
        this.prioridade = c.getPrioridade().getCodigo();
        this.status = c.getStatus().getCodigo();
        this.titulo = c.getTitulo();
        this.observacoes = c.getObservacoes();
        this.tecnico = c.getTecnico().getId();
        this.cliente = c.getCliente().getId();
        this.nomeTecnico = c.getTecnico().getNome();
        this.nomeCliente = c.getCliente().getNome();
    }
}
