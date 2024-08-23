package com.fullcycle.admin.catalogo.infrastructure;

import com.fullcycle.admin.catalogo.infrastructure.notificador.NotificadorEmail;
import com.fullcycle.admin.catalogo.infrastructure.notificador.NotificadorSMS;
import com.fullcycle.admin.catalogo.infrastructure.service.AtivacaoClienteService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        Cliente joao = new Cliente("João", "joao@gmail.com", "12345");
        Cliente maria = new Cliente("maria", "maria@gmail.com", "5345");

//        AtivacaoClienteService ativacaoClienteService = new AtivacaoClienteService(new NotificadorEmail());
//        ativacaoClienteService.ativar(joao);
//        ativacaoClienteService.ativar(maria);
    }
}