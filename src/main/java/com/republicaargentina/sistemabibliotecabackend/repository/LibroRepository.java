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

    @Query("select l from Libro l where upper(l.titulo) = upper(?1)")
    Optional<Libro> getOneByTitulo(String titulo);

    @Query("select l from Libro l where upper(l.codigo) = upper(?1)")
    Optional<Libro> getOneByCodigo(String codigo);

    @Query("select l from Libro l where l.area.id = ?1 order by l.codigo")
    List<Libro> getAllByArea(Long id);

    @Query("select l from Libro l where l.categoria.id = ?1 order by l.codigo")
    List<Libro> getAllByCategoria(Long id);

    @Query("select l from Libro l where l.editorial.id = ?1 order by l.codigo")
    List<Libro> getAllByEditorial(Long id);

    @Query("select l from Libro l inner join l.autores autores where autores.id = ?1 order by l.codigo")
    List<Libro> getAllByAutor(Long id);

    @Query("select l from Libro l order by l.codigo")
    Page<Libro> pagination(Pageable pageable);

    @Query("select l from Libro l where upper(l.titulo) like upper(concat('%', ?1, '%')) order by l.codigo")
    Page<Libro> paginationByTitulo(String titulo, Pageable pageable);

    @Query("select l from Libro l where upper(l.codigo) like upper(concat('%', ?1, '%')) order by l.codigo")
    Page<Libro> paginationByCodigo(String codigo, Pageable pageable);

    @Query("select l from Libro l where l.area.id = ?1 order by l.codigo")
    Page<Libro> paginationByArea(Long id, Pageable pageable);

    @Query("select l from Libro l where l.categoria.id = ?1 order by l.codigo")
    Page<Libro> paginationByCategoria(Long id, Pageable pageable);

    @Query("select l from Libro l where l.editorial.id = ?1 order by l.codigo")
    Page<Libro> paginationByEditorial(Long id, Pageable pageable);

    @Query("select l from Libro l inner join l.autores autores where autores.id = ?1 order by l.codigo")
    Page<Libro> paginationByAutor(Long id, Pageable pageable);
}
