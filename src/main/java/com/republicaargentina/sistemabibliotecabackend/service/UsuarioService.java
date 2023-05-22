package com.republicaargentina.sistemabibliotecabackend.service;

import com.republicaargentina.sistemabibliotecabackend.entity.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> getAll();

    Usuario getOne(Long id);

    Usuario save(Usuario usuario);

    Usuario update(Usuario usuario);

    Boolean delete(Long id);
}
