package com.bsworks.bastoslog.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class EntregaInput {

    @NotNull
    @Valid
    private ClienteIdInput cliente;

    @NotNull
    @Valid
    private DestinatarioInput destinatario;

    @NotNull
    private BigDecimal taxa;
}
