package com.bsworks.bastoslog.api.controller;

import com.bsworks.bastoslog.domain.model.Cliente;
import com.bsworks.bastoslog.domain.repository.ClienteRepository;
import com.bsworks.bastoslog.domain.service.CatalogoClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;
    private final CatalogoClienteService catalogoClienteService;

    @GetMapping()
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscar(@PathVariable("id") Long clientId) {
        return clienteRepository.findById(clientId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@RequestBody @Validated Cliente cliente) {
        return catalogoClienteService.salvar(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable("id") Long clientId, @RequestBody Cliente cliente) {

        if (!clienteRepository.existsById(clientId)) {
            return ResponseEntity.notFound().build();
        }

        cliente.setId(clientId);
        cliente = catalogoClienteService.salvar(cliente);

        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable("id") Long clientId) {

        if (!clienteRepository.existsById(clientId)) {
            return ResponseEntity.notFound().build();
        }

        catalogoClienteService.excluir(clientId);

        return ResponseEntity.noContent().build();
    }
}
