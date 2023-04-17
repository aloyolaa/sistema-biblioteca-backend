package com.republicaargentina.sistemabibliotecabackend.repository;

import com.republicaargentina.sistemabibliotecabackend.entity.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    @Query("select l from Libro l order by l.codigo")
    List<Libro> getAll();

    @Query("select l from Libro l order by l.codigo")
    Page<Libro> pagination(Pageable pageable);

    @Query("select l from Libro l where upper(l.titulo) = upper(?1)")
    Optional<Libro> getOneByTitulo(String titulo);

    @Query("select l from Libro l where upper(l.codigo) = upper(?1)")
    Optional<Libro> getOneByCodigo(String codigo);

    @Query("select l from Libro l where upper(l.titulo) like upper(concat('%', ?1, '%')) order by l.codigo")
    List<Libro> getAllByTitulo(String titulo);

    @Query("select l from Libro l where upper(l.codigo) like upper(concat('%', ?1, '%')) order by l.codigo")
    List<Libro> getAllByCodigo(String codigo);

    @Query("select l from Libro l where upper(l.autor.nombre) = upper(?1) order by l.codigo")
    Page<Libro> paginationByAutor(String nombre, Pageable pageable);

    @Query("select l from Libro l where upper(l.categoria.nombre) = upper(?1) order by l.codigo")
    Page<Libro> paginationByCategoria(String nombre, Pageable pageable);

    @Query("select l from Libro l where upper(l.editorial.nombre) = upper(?1) order by l.codigo")
    Page<Libro> paginationByEditorial(String nombre, Pageable pageable);

    @Query("select l from Libro l where upper(l.area.nombre) = upper(?1) order by l.codigo")
    Page<Libro> paginationByArea(String nombre, Pageable pageable);
}
