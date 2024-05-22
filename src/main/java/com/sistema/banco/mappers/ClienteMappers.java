package com.sistema.banco.mappers;

import org.mapstruct.Mapper;

import com.sistema.banco.dto.ClienteDto;
import com.sistema.banco.models.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMappers {

    ClienteDto toClienteDto(Cliente cliente);

    Cliente toCliente(ClienteDto clienteDto);

}
