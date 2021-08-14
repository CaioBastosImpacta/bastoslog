package com.bsworks.bastoslog.domain.service;

import com.bsworks.bastoslog.domain.exception.EntidadeNaoEncontradaException;
import com.bsworks.bastoslog.domain.model.Entrega;
import com.bsworks.bastoslog.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BuscaEntregaService {

    private final EntregaRepository entregaRepository;

    public Entrega buscar(Long entregaId) {
        return entregaRepository.findById(entregaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada."));
    }
}
