package com.alurafood.pagamentos.repository;

import com.alurafood.pagamentos.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
