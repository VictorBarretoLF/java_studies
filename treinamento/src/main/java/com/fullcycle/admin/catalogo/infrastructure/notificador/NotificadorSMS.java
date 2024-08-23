package com.fullcycle.admin.catalogo.infrastructure.notificador;

import com.fullcycle.admin.catalogo.infrastructure.Cliente;

public class NotificadorSMS implements Notificador {

    @Override
    public void notificar(Cliente cliente, String msg){
        System.out.printf("Notificando SMS %s %s %s \n",
                cliente.getNome(), cliente.getTelefone(), msg);
    }
}
