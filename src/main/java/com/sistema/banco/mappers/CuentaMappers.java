package com.sistema.banco.mappers;

import org.mapstruct.Mapper;

import com.sistema.banco.dto.CuentaDto;
import com.sistema.banco.models.Cuenta;

@Mapper(componentModel = "spring")
public interface CuentaMappers {

    CuentaDto toCuentaDto(Cuenta cuenta);

    Cuenta toCuenta(CuentaDto cuentaDto);

}
