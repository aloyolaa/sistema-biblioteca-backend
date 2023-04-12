package com.republicaargentina.sistemabibliotecabackend.repository;

import com.republicaargentina.sistemabibliotecabackend.entity.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    @Query("select l from Libro l order by l.codigo")
    List<Libro> findByOrderByCodigoAsc();

    @Query("select l from Libro l order by l.codigo")
    Page<Libro> paginationByOrderByCodigoAsc(Pageable pageable);

    @Query("select l from Libro l where upper(l.titulo) like upper(concat('%', ?1, '%')) order by l.codigo")
    List<Libro> findByTituloContainsIgnoreCaseOrderByCodigoAsc(String titulo);

    @Query("select l from Libro l where upper(l.codigo) like upper(concat('%', ?1, '%')) order by l.codigo")
    List<Libro> findByCodigoContainsIgnoreCaseOrderByCodigoAsc(String codigo);

    @Query("select l from Libro l where upper(l.autor.nombre) = upper(?1) order by l.codigo")
    Page<Libro> findByAutorNombreIgnoreCaseOrderByCodigoAsc(String nombre, Pageable pageable);

    @Query("select l from Libro l where upper(l.categoria.nombre) = upper(?1) order by l.codigo")
    Page<Libro> findByCategoriaNombreIgnoreCaseOrderByCodigoAsc(String nombre, Pageable pageable);

    @Query("select l from Libro l where upper(l.editorial.nombre) = upper(?1) order by l.codigo")
    Page<Libro> findByEditorialNombreIgnoreCaseOrderByCodigoAsc(String nombre, Pageable pageable);

    @Query("select l from Libro l where upper(l.area.nombre) = upper(?1) order by l.codigo")
    Page<Libro> findByAreaNombreIgnoreCaseOrderByCodigoAsc(String nombre, Pageable pageable);
}
