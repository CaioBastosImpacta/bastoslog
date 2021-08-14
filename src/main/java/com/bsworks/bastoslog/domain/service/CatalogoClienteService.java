package com.bsworks.bastoslog.domain.service;

import com.bsworks.bastoslog.domain.exception.NegocioException;
import com.bsworks.bastoslog.domain.model.Cliente;
import com.bsworks.bastoslog.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CatalogoClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente buscar(Long clientId) {
        return clienteRepository.findById(clientId)
                .orElseThrow(() -> new NegocioException("Cliente não encontrado."));
    }

    @Transactional
    public Cliente salvar(Cliente cliente) {
        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clienteExistente -> verificarClienteDiferente(cliente, clienteExistente));

        if (emailEmUso) {
            throw new NegocioException("Já existe um cliente cadastrado com esse e-mail.");
        }

        return clienteRepository.save(cliente);
    }

    @Transactional
    public void excluir(Long clientId) {
        clienteRepository.deleteById(clientId);
    }

    private boolean verificarClienteDiferente(Cliente cliente, Cliente clienteExistente) {
        return !clienteExistente.equals(cliente);
    }
}
