package com.gabriel.helpdesk.repositories;

import com.gabriel.helpdesk.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {



}
