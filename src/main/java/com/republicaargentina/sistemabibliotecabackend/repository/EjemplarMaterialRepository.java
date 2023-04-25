package com.republicaargentina.sistemabibliotecabackend.repository;

import com.republicaargentina.sistemabibliotecabackend.entity.EjemplarMaterial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EjemplarMaterialRepository extends JpaRepository<EjemplarMaterial, Long> {
    @Query("select e from EjemplarMaterial e order by e.material.codigo, e.numero")
    List<EjemplarMaterial> getAll();

    @Query("select e from EjemplarMaterial e order by e.material.codigo, e.numero")
    Page<EjemplarMaterial> pagination(Pageable pageable);

    @Query(value = "select e from EjemplarMaterial e where e.material.id = ?1 order by e.id desc limit 1")
    Optional<EjemplarMaterial> getOneByMaterial(Long id);

    @Query("select e from EjemplarMaterial e where upper(e.material.codigo) = upper(?1) order by e.numero")
    Page<EjemplarMaterial> paginationByMaterial(String codigo, Pageable pageable);

    @Query("select e from EjemplarMaterial e where upper(e.material.codigo) = upper(?1) and e.estado = 'DISPONIBLE' order by e.numero")
    Page<EjemplarMaterial> paginationByMaterialAndEstado(String codigo, Pageable pageable);

    @Query("select count(e) from EjemplarMaterial e where upper(e.material.codigo) = upper(?1)")
    long countByMaterial(String codigo);

    @Query("select count(e) from EjemplarMaterial e where upper(e.material.codigo) = upper(?1) and e.estado = 'DISPONIBLE'")
    long countByMaterialAndEstado(String codigo);
}