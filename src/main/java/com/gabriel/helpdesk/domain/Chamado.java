package com.gabriel.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gabriel.helpdesk.enums.Prioridade;
import com.gabriel.helpdesk.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Chamado implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private ZonedDateTime dataAbertura = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private ZonedDateTime dataConclusao;
    private Prioridade prioridade;
    private Status status;
    private String observacoes;
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Chamado() {
        super();
    }

    public Chamado(Long id, Prioridade prioridade, Status status, String observacoes, String titulo, Tecnico tecnico, Cliente cliente, ZonedDateTime dataConclusao) {
        super();
        this.id = id;
        this.prioridade = prioridade;
        this.status = status;
        this.observacoes = observacoes;
        this.titulo = titulo;
        this.tecnico = tecnico;
        this.cliente = cliente;
        this.dataConclusao = dataConclusao;
    }
}
