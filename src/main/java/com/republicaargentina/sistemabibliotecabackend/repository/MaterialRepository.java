package com.republicaargentina.sistemabibliotecabackend.repository;

import com.republicaargentina.sistemabibliotecabackend.entity.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    @Query("select m from Material m order by m.codigo")
    List<Material> getAll();

    @Query("select m from Material m order by m.codigo")
    Page<Material> pagination(Pageable pageable);

    @Query("select m from Material m where upper(m.nombre) = upper(?1)")
    Optional<Material> getOneByNombre(String nombre);

    @Query("select m from Material m where upper(m.codigo) = upper(?1)")
    Optional<Material> getOneByCodigo(String codigo);

    @Query("select m from Material m where upper(m.nombre) like upper(concat('%', ?1, '%')) order by m.nombre")
    List<Material> getAllByNombre(String nombre);

    @Query("select m from Material m where upper(m.codigo) like upper(concat('%', ?1, '%')) order by m.codigo")
    List<Material> getAllByCodigo(String codigo);

    @Query("select m from Material m where upper(m.area.nombre) = upper(?1) order by m.nombre")
    Page<Material> paginationByArea(String nombre, Pageable pageable);
}
