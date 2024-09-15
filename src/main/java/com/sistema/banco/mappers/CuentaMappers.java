package com.sistema.banco.mappers;

import org.mapstruct.Mapper;

import com.sistema.banco.dto.CuentaDto;
import com.sistema.banco.models.Cuenta;

// Esta interfaz usa la depenencia de mappstruct, la cual convierte entidades a objectos de acuerdo con el patron de diseño Dto. 
@Mapper(componentModel = "spring")
public interface CuentaMappers {

    // Conversion de Cuenta a Dto
    CuentaDto toCuentaDto(Cuenta cuenta);

    // Conversion de Dto a Cuenta.
    Cuenta toCuenta(CuentaDto cuentaDto);

}
