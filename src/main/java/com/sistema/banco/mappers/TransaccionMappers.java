package com.sistema.banco.mappers;

import org.mapstruct.Mapper;

import com.sistema.banco.dto.TransaccionDto;
import com.sistema.banco.models.Transaccion;

// Esta interfaz usa la depenencia de mappstruct, la cual convierte entidades a objectos de acuerdo con el patron de diseño Dto. 
@Mapper(componentModel = "spring")
public interface TransaccionMappers {

    // Convertidor Transaccion a Dto
    TransaccionDto toTransaccionDto(Transaccion transaccion);

    // Conversion de Dto a Transaccion.
    Transaccion toTransaccion(TransaccionDto transaccionDto);

}
