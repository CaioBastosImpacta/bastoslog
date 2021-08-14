package com.bsworks.bastoslog.domain.service;

import com.bsworks.bastoslog.domain.exception.ClienteNaoEncontradoException;
import com.bsworks.bastoslog.domain.model.Cliente;
import com.bsworks.bastoslog.domain.model.Entrega;
import com.bsworks.bastoslog.domain.model.StatusEntrega;
import com.bsworks.bastoslog.domain.repository.ClienteRepository;
import com.bsworks.bastoslog.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

    private CatalogoClienteService catalogoClienteService;
    private EntregaRepository entregaRepository;

    @Transactional
    public Entrega solicitar(Entrega entrega) {
        Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());

        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());

        return entregaRepository.save(entrega);
    }
}
