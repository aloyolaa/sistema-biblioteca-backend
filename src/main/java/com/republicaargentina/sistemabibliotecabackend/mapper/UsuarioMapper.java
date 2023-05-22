package com.republicaargentina.sistemabibliotecabackend.mapper;

import com.republicaargentina.sistemabibliotecabackend.dto.UsuarioDto;
import com.republicaargentina.sistemabibliotecabackend.entity.Usuario;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper {
    Usuario toEntity(UsuarioDto usuarioDto);

    UsuarioDto toDto(Usuario usuario);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Usuario partialUpdate(UsuarioDto usuarioDto, @MappingTarget Usuario usuario);
}