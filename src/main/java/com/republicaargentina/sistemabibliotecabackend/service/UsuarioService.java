package com.republicaargentina.sistemabibliotecabackend.service;

import com.republicaargentina.sistemabibliotecabackend.dto.UsuarioDto;
import com.republicaargentina.sistemabibliotecabackend.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UsuarioService {
    List<UsuarioDto> getAll();

    UsuarioDto getOne(Long id);

    UsuarioDto save(Usuario usuario);

    UsuarioDto update(UsuarioDto usuarioDto);

    Boolean delete(Long id);

    UsuarioDto getOneByUsername(String username);

    Page<UsuarioDto> pagination(Pageable pageable);
}
