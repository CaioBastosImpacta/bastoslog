package com.bsworks.bastoslog.api.model;

import com.bsworks.bastoslog.domain.model.StatusEntrega;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class EntregaModel {

    private Long id;
    private ClienteResumoModel cliente;
    private DestinatarioModel destinatario;
    private StatusEntrega status;
    private BigDecimal taxa;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalizacao;
}
