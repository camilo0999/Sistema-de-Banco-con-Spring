package com.sistema.banco.mappers;

import org.mapstruct.Mapper;

import com.sistema.banco.dto.ClienteDto;
import com.sistema.banco.models.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMappers {

    // Coversion de Cliente a Dto.
    ClienteDto toClienteDto(Cliente cliente);

    // Conversion de Dto a Cliente.
    Cliente toCliente(ClienteDto clienteDto);

}
