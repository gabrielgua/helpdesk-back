package com.gabriel.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gabriel.helpdesk.dto.ClienteDTO;
import com.gabriel.helpdesk.dto.TecnicoDTO;
import com.gabriel.helpdesk.enums.Perfil;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Cliente extends Pessoa{
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Chamado> chamados = new ArrayList<>();

    public Cliente() {
        super();
        addPerfil(Perfil.CLIENTE);
    }

    public Cliente(Long id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.CLIENTE);
    }

    public Cliente(ClienteDTO c) {
        super();
        this.id = c.getId();
        this.nome = c.getNome();
        this.cpf = c.getCpf();
        this.email = c.getEmail();
        this.senha = c.getSenha();
        this.perfis = c.getPerfis().stream().map(p -> p.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = c.getDataCriacao();
    }
}
