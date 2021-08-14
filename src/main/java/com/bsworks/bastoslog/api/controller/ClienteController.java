package com.bsworks.bastoslog.api.controller;

import com.bsworks.bastoslog.api.assembler.ClienteAssembler;
import com.bsworks.bastoslog.api.model.ClienteModel;
import com.bsworks.bastoslog.api.model.input.ClienteInput;
import com.bsworks.bastoslog.domain.model.Cliente;
import com.bsworks.bastoslog.domain.repository.ClienteRepository;
import com.bsworks.bastoslog.domain.service.CatalogoClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;
    private final CatalogoClienteService catalogoClienteService;
    private final ClienteAssembler clienteAssembler;

    @GetMapping()
    public List<ClienteModel> listar() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clienteAssembler.toCollectionModel(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteModel> buscar(@PathVariable("id") Long clientId) {
        return clienteRepository.findById(clientId)
                .map(cliente -> ResponseEntity.ok(clienteAssembler.toModel(cliente)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteModel adicionar(@RequestBody @Validated ClienteInput clienteInput) {
        Cliente clienteNovo = clienteAssembler.toEntity(clienteInput);
        Cliente clienteCadastrado = catalogoClienteService.salvar(clienteNovo);
        return clienteAssembler.toModel(clienteCadastrado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteModel> atualizar(@PathVariable("id") Long clientId, @RequestBody ClienteInput clienteInput) {
        Cliente cliente = clienteAssembler.toEntity(clienteInput);

        return clienteRepository.findById(clientId)
                .map(client -> {
                    cliente.setId(client.getId());
                    Cliente clienteAtualizado = clienteRepository.save(cliente);
                    return clienteAssembler.toModel(clienteAtualizado);
                })
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
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
