package com.fullcycle.admin.catalogo.infrastructure.notificador;

import com.fullcycle.admin.catalogo.infrastructure.Cliente;
import org.springframework.stereotype.Component;

@Component
public class NotificadorEmail {

    public NotificadorEmail(){
        System.out.println("Construtor chamado");
    }

    public void notificar(Cliente cliente, String msg){
        System.out.printf("Notificando email %s %s %s \n",
                cliente.getNome(), cliente.getEmail(), msg);
    }
}
