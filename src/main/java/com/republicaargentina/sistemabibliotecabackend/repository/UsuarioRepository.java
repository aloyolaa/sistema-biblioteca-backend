package com.republicaargentina.sistemabibliotecabackend.repository;

import com.republicaargentina.sistemabibliotecabackend.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("select u from Usuario u where upper(u.username) = upper(?1)")
    Optional<Usuario> getOneByUsername(String username);

    @Query("select u from Usuario u order by u.username")
    Page<Usuario> pagination(Pageable pageable);

}