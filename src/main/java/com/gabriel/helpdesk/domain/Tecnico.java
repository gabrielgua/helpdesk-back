package com.gabriel.helpdesk.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gabriel.helpdesk.dto.TecnicoDTO;
import com.gabriel.helpdesk.enums.Perfil;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Tecnico extends Pessoa{
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "tecnico")
    private List<Chamado> chamados = new ArrayList<>();

    public Tecnico() {
        super();
        addPerfil(Perfil.TECNICO);
    }

    public Tecnico(Long id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.CLIENTE);
    }

    public Tecnico(TecnicoDTO t) {
        super();
        this.id = t.getId();
        this.nome = t.getNome();
        this.cpf = t.getCpf();
        this.email = t.getEmail();
        this.senha = t.getSenha();
        this.perfis = t.getPerfis().stream().map(p -> p.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = t.getDataCriacao();
    }
}
