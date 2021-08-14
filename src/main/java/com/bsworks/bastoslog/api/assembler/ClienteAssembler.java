package com.bsworks.bastoslog.api.assembler;

import com.bsworks.bastoslog.api.model.ClienteModel;
import com.bsworks.bastoslog.api.model.EntregaModel;
import com.bsworks.bastoslog.api.model.input.ClienteInput;
import com.bsworks.bastoslog.api.model.input.EntregaInput;
import com.bsworks.bastoslog.domain.model.Cliente;
import com.bsworks.bastoslog.domain.model.Entrega;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ClienteAssembler {

    private ModelMapper modelMapper;

    public ClienteModel toModel(Cliente cliente) {
        return modelMapper.map(cliente, ClienteModel.class);
    }

    public List<ClienteModel> toCollectionModel(List<Cliente> clientes) {
        return clientes.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Cliente toEntity(ClienteInput clienteInput) {
        return modelMapper.map(clienteInput, Cliente.class);
    }
}
