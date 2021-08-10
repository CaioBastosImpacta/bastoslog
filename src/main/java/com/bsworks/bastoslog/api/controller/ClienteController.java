package com.bsworks.bastoslog.api.controller;

import com.bsworks.bastoslog.domain.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClienteController {

    @GetMapping("/clientes")
    public List<Cliente> listar() {
        var cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setNome("João");
        cliente1.setTelefone("11 98351-2009");
        cliente1.setEmail("caio@bswork.com");

        var cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNome("Maria");
        cliente2.setTelefone("11 5138-2010");
        cliente2.setEmail("maria@bswork.com");

        return List.of(cliente1, cliente2);
    }
}
