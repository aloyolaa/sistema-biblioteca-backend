package com.republicaargentina.sistemabibliotecabackend.repository;

import com.republicaargentina.sistemabibliotecabackend.entity.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    @Query("select m from Material m order by m.nombre")
    List<Material> findByOrderByNombreAsc();

    @Query("select m from Material m order by m.nombre")
    Page<Material> paginationByOrderByNombreAsc(Pageable pageable);

    @Query("select m from Material m where upper(m.nombre) like upper(concat('%', ?1, '%')) order by m.nombre")
    List<Material> findByNombreContainsIgnoreCaseOrderByNombreAsc(String nombre);

    @Query("select m from Material m where upper(m.area.nombre) = upper(?1) order by m.nombre")
    Page<Material> findByAreaNombreIgnoreCaseOrderByNombreAsc(String nombre, Pageable pageable);
}
