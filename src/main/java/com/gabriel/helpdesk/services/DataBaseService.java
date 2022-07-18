package com.gabriel.helpdesk.services;

import com.gabriel.helpdesk.domain.Chamado;
import com.gabriel.helpdesk.domain.Cliente;
import com.gabriel.helpdesk.domain.Tecnico;
import com.gabriel.helpdesk.enums.Perfil;
import com.gabriel.helpdesk.enums.Prioridade;
import com.gabriel.helpdesk.enums.Status;
import com.gabriel.helpdesk.repositories.ChamadoRepository;
import com.gabriel.helpdesk.repositories.ClienteRepository;
import com.gabriel.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DataBaseService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public void dbInit() {
        Tecnico tec1 = new Tecnico(null, "Admin", "67094830025", "admin@mail.com", encoder.encode("123123"));
        tec1.addPerfil(Perfil.ADMIN);
        Tecnico tec2 = new Tecnico(null, "Gabriel Guaitanele", "90334707056", "stallman@mail.com", encoder.encode("123123"));
        Tecnico tec3 = new Tecnico(null, "Claude Elwood Shannon", "27106847054", "shannon@mail.com", encoder.encode("123123"));
        Tecnico tec4 = new Tecnico(null, "Tim Berners-Lee", "16272012039", "lee@mail.com", encoder.encode("123123"));
        Tecnico tec5 = new Tecnico(null, "Linus Torvalds", "77855617027", "linus@mail.com", encoder.encode("123123"));

        Cliente cli1 = new Cliente(null, "Albert Einstein", "11166189074", "einstein@mail.com", encoder.encode("123123"));
        Cliente cli2 = new Cliente(null, "Marie Curie", "32242914006", "curie@mail.com", encoder.encode("123123"));
        Cliente cli3 = new Cliente(null, "Charles Darwin", "79204383062", "darwin@mail.com", encoder.encode("123123"));
        Cliente cli4 = new Cliente(null, "Stephen Hawking", "17740968030", "hawking@mail.com", encoder.encode("123123"));
        Cliente cli5 = new Cliente(null, "Max Planck", "08139930083", "planck@mail.com", encoder.encode("123123"));

        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 1", "Teste chamado 1", tec1, cli1);
        Chamado c2 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 2", "Teste chamado 2", tec1, cli2);
        Chamado c3 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado 3", "Teste chamado 3", tec2, cli3);
        Chamado c4 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 4", "Teste chamado 4", tec3, cli3);
        Chamado c5 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 5", "Teste chamado 5", tec2, cli1);
        Chamado c6 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado 7", "Teste chamado 6", tec1, cli5);

        tecnicoRepository.saveAll(Arrays.asList(tec1, tec2, tec3, tec4, tec5));
        clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5));
        chamadoRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6));
    }

}
