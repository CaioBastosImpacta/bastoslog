package com.bsworks.bastoslog.domain.model;

import com.bsworks.bastoslog.domain.ValidationGroups;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.groups.ConvertGroup;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ConvertGroup(to = ValidationGroups.ClientId.class)
    @ManyToOne
    private Cliente cliente;

    @Embedded
    private Destinatario destinatario;

    @Enumerated(EnumType.STRING)
    private StatusEntrega status;
    private BigDecimal taxa;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalizacao;
}
