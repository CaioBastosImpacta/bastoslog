package com.bsworks.bastoslog.api.controller;

import com.bsworks.bastoslog.domain.model.Entrega;
import com.bsworks.bastoslog.domain.service.SolicitacaoEntregaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private SolicitacaoEntregaService solicitacaoEntregaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega criar(@RequestBody @Validated Entrega entrega) {
        return solicitacaoEntregaService.solicitar(entrega);
    }
}
