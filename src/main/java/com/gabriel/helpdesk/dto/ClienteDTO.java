package com.gabriel.helpdesk.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gabriel.helpdesk.domain.Cliente;
import com.gabriel.helpdesk.enums.Perfil;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class ClienteDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Long id;
    @NotNull(message = "Nome é requerido!")
    protected String nome;

    @NotNull(message = "CPF é requerido!")
    protected String cpf;

    @NotNull(message = "Email é requerido!")
    protected String email;

    @NotNull(message = "Senha é requerida!")
    protected String senha;
    protected Set<Integer> perfis = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDateTime dataCriacao = LocalDateTime.now();

    public ClienteDTO() {
        super();
        addPerfil(Perfil.CLIENTE);
    }

    public ClienteDTO(Cliente t) {
        super();
        this.id = t.getId();
        this.nome = t.getNome();
        this.cpf = t.getCpf();
        this.email = t.getEmail();
        this.senha = t.getSenha();
        this.perfis = t.getPerfis().stream().map(p -> p.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = t.getDataCriacao();
        addPerfil(Perfil.CLIENTE);
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(p -> Perfil.toEnum(p)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
    }
}
