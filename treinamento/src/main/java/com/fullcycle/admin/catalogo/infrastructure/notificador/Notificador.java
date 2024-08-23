package com.fullcycle.admin.catalogo.infrastructure.notificador;

import com.fullcycle.admin.catalogo.infrastructure.Cliente;

public interface Notificador {
    void notificar(Cliente cliente, String msg);
}
