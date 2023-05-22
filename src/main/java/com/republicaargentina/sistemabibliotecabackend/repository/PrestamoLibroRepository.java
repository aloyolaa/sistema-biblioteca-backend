package com.republicaargentina.sistemabibliotecabackend.repository;

import com.republicaargentina.sistemabibliotecabackend.entity.PrestamoLibro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PrestamoLibroRepository extends JpaRepository<PrestamoLibro, Long> {
    @Query("select p from PrestamoLibro p order by p.fechaPrestamo DESC")
    List<PrestamoLibro> getAll();

    @Query("select p from PrestamoLibro p where p.docente.id = ?1 order by p.fechaPrestamo DESC")
    List<PrestamoLibro> getAllByDocente(Long id);

    @Query("""
            select p from PrestamoLibro p
            where p.grado = ?1 and upper(p.seccion) = upper(?2)
            order by p.fechaPrestamo DESC""")
    List<PrestamoLibro> getAllByGradoAndSeccion(Integer grado, String seccion);

    @Query("select p from PrestamoLibro p order by p.fechaPrestamo DESC")
    Page<PrestamoLibro> pagination(Pageable pageable);

    @Query("select p from PrestamoLibro p where p.docente.dni = ?1 order by p.fechaPrestamo DESC")
    Page<PrestamoLibro> paginationByDocente(String dni, Pageable pageable);

    @Query("select p from PrestamoLibro p where p.grado = ?1 and upper(p.seccion) = upper(?2) order by p.fechaPrestamo DESC")
    Page<PrestamoLibro> paginationByGradoAndSeccion(Integer grado, String seccion, Pageable pageable);

    @Query("""
            select p from PrestamoLibro p
            where upper(p.descripcion) like upper(concat('%', ?1, '%'))
            order by p.fechaPrestamo DESC""")
    Page<PrestamoLibro> paginationByDescripcion(String descripcion, Pageable pageable);

    @Query("select p from PrestamoLibro p where p.fechaPrestamo between ?1 and ?2 order by p.fechaPrestamo DESC")
    Page<PrestamoLibro> paginationByFechaPrestamo(LocalDateTime fechaPrestamoStart, LocalDateTime fechaPrestamoEnd, Pageable pageable);

    @Query("""
            select p from PrestamoLibro p
            where p.fechaPrestamo between ?1 and ?2 and p.docente.id = ?3
            order by p.fechaPrestamo DESC""")
    Page<PrestamoLibro> paginationByFechaPrestamoAndDocente(LocalDateTime fechaPrestamoStart, LocalDateTime fechaPrestamoEnd, Long id, Pageable pageable);

    @Query("""
            select p from PrestamoLibro p
            where p.fechaPrestamo between ?1 and ?2 and p.grado = ?3 and upper(p.seccion) = upper(?4)
            order by p.fechaPrestamo DESC""")
    Page<PrestamoLibro> paginationByFechaPrestamoAndGradoAndSeccion(LocalDateTime fechaPrestamoStart, LocalDateTime fechaPrestamoEnd, Integer grado, String seccion, Pageable pageable);

    @Query("""
            select p from PrestamoLibro p
            where p.fechaPrestamo between ?1 and ?2 and upper(p.descripcion) like upper(concat('%', ?3, '%'))
            order by p.fechaPrestamo DESC""")
    Page<PrestamoLibro> paginationByFechaPrestamoAndDescripcion(LocalDateTime fechaPrestamoStart, LocalDateTime fechaPrestamoEnd, String descripcion, Pageable pageable);
}
