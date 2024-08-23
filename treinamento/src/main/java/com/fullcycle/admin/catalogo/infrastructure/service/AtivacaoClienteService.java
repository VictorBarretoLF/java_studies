package com.fullcycle.admin.catalogo.infrastructure.service;

import com.fullcycle.admin.catalogo.infrastructure.Cliente;
import com.fullcycle.admin.catalogo.infrastructure.notificador.Notificador;

public class AtivacaoClienteService {

    private Notificador notificador;

    public AtivacaoClienteService(Notificador notificador){
        this.notificador = notificador;
    }

    public void ativar(Cliente cliente){
        cliente.setAtivo(true);

        this.notificador.notificar(cliente, "Cadastro ativado");
    }
}
