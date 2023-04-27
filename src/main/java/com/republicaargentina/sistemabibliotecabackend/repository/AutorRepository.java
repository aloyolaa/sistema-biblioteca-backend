package com.republicaargentina.sistemabibliotecabackend.repository;

import com.republicaargentina.sistemabibliotecabackend.entity.Autor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("select a from Autor a order by a.apellido, a.nombre")
    List<Autor> getAll();

    @Query("select a from Autor a order by a.apellido, a.nombre")
    Page<Autor> pagination(Pageable pageable);

    @Query("""
            select a from Autor a
            where upper(a.nombre) like upper(concat('%', ?1, '%')) or upper(a.apellido) like upper(concat('%', ?2, '%'))""")
    List<Autor> getAllByNombreAndApellido(String nombre, String apellido);
}