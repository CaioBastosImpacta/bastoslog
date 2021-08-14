package com.bsworks.bastoslog.api.controller;

import com.bsworks.bastoslog.api.assembler.OcorrenciaAssembler;
import com.bsworks.bastoslog.api.model.OcorrenciaModel;
import com.bsworks.bastoslog.api.model.input.OcorrenciaInput;
import com.bsworks.bastoslog.domain.model.Entrega;
import com.bsworks.bastoslog.domain.model.Ocorrencia;
import com.bsworks.bastoslog.domain.service.BuscaEntregaService;
import com.bsworks.bastoslog.domain.service.RegistroOcorrenciaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{id}/ocorrencias")
public class OcorrenciaController {

    private final BuscaEntregaService buscaEntregaService;
    private final OcorrenciaAssembler ocorrenciaAssembler;
    private final RegistroOcorrenciaService registroOcorrenciaService;

    @GetMapping
    public List<OcorrenciaModel> buscar(@PathVariable("id") Long entregaId) {
        Entrega entrega = buscaEntregaService.buscar(entregaId);

        return ocorrenciaAssembler.toCollectionModel(entrega.getOcorrencias());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaModel registrar(@PathVariable("id") Long entregaId,
                                     @Validated @RequestBody OcorrenciaInput ocorrenciaInput) {

        Ocorrencia ocorrenciaRegistrada = registroOcorrenciaService
                .registrar(entregaId, ocorrenciaInput.getDescricao());

        return ocorrenciaAssembler.toModel(ocorrenciaRegistrada);
    }

}
