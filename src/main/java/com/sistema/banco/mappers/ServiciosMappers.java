package com.sistema.banco.mappers;

import org.mapstruct.Mapper;

import com.sistema.banco.dto.ServiciosDto;
import com.sistema.banco.models.Servicio;

@Mapper(componentModel = "spring")
public interface ServiciosMappers {

    Servicio toServicio(ServiciosDto serviciosDto);

    ServiciosDto toServiciosDto(Servicio servicio);

}
