package com.sistema.banco.mappers;

import org.mapstruct.Mapper;

import com.sistema.banco.dto.TransaccionDto;
import com.sistema.banco.models.Transaccion;

@Mapper(componentModel = "spring")
public interface TransaccionMappers {

    TransaccionDto toTransaccionDto(Transaccion transaccion);

    Transaccion toTransaccion(TransaccionDto transaccionDto);

}
