package com.fullcycle.admin.catalogo.infrastructure.service;

import com.fullcycle.admin.catalogo.infrastructure.Cliente;
import com.fullcycle.admin.catalogo.infrastructure.Produto;
import com.fullcycle.admin.catalogo.infrastructure.notificador.Notificador;
import com.fullcycle.admin.catalogo.infrastructure.notificador.NotificadorEmail;

public class EmissaoNotaFiscalService {

    private Notificador notificador;

    public EmissaoNotaFiscalService(Notificador notificador){
        this.notificador = notificador;
    }

    public void emitir(Cliente cliente, Produto produto){
        this.notificador.notificar(cliente, "Nota fiscal do produto"
        + produto.getNome() + " foi emitida!");
    }

}
