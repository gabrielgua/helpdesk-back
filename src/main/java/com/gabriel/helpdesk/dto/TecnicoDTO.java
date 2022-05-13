package com.gabriel.helpdesk.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gabriel.helpdesk.domain.Tecnico;
import com.gabriel.helpdesk.enums.Perfil;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class TecnicoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Long id;
    protected String nome;
    protected String cpf;
    protected String email;
    protected String senha;
    protected Set<Integer> perfis = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = LocalDate.now();

    public TecnicoDTO() {
        super();
    }

    public TecnicoDTO(Tecnico t) {
        super();
        this.id = t.getId();
        this.nome = t.getNome();
        this.cpf = t.getCpf();
        this.email = t.getEmail();
        this.senha = t.getSenha();
        this.perfis = t.getPerfis().stream().map(p -> p.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = t.getDataCriacao();
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(p -> Perfil.toEnum(p)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
    }
}
