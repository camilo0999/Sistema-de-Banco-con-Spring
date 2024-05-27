package com.sistema.banco.mappers;

import org.mapstruct.Mapper;

import com.sistema.banco.dto.ClienteDto;
import com.sistema.banco.models.Cliente;

// Esta interfaz usa la depenencia de mappstruct, la cual convierte entidades a objectos de acuerdo con el patron de diseño Dto. 
@Mapper(componentModel = "spring")
public interface ClienteMappers {

    // Coversion de Cliente a Dto.
    ClienteDto toClienteDto(Cliente cliente);

    // Conversion de Dto a Cliente.
    Cliente toCliente(ClienteDto clienteDto);

}
