package com.alurafood.pagamentos.service;

import com.alurafood.pagamentos.dto.PagamentoDto;
import com.alurafood.pagamentos.model.Pagamento;
import com.alurafood.pagamentos.model.Status;
import com.alurafood.pagamentos.repository.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<PagamentoDto> obterTodos(Pageable pageable) {
        return pagamentoRepository.findAll(pageable).map(p -> modelMapper.map(p, PagamentoDto.class));
    }

    public PagamentoDto obterPorId(Long id) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public PagamentoDto criarPagamento(PagamentoDto dto) {
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
        pagamento.setStatus(Status.CRIADO);
        pagamentoRepository.save(pagamento);

        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public PagamentoDto atualizarPagamento(Long id, PagamentoDto dto) {
        Pagamento pagamento = modelMapper.map(dto, Pagamento.class);
        pagamento.setId(id);
        pagamento = pagamentoRepository.save(pagamento);

        return modelMapper.map(pagamento, PagamentoDto.class);
    }

    public void excluirPagamento(Long id) {
        pagamentoRepository.deleteById(id);
    }

}
