package com.republicaargentina.sistemabibliotecabackend.service;

import com.republicaargentina.sistemabibliotecabackend.entity.Area;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AreaService {
    List<Area> getAll();

    Area getOne(Long id);

    Area save(Area area);

    Area update(Area area);

    Boolean delete(Long id);

    long count();

    Page<Area> pagination(Pageable pageable);

    Area cambiarLetras(Area area);
}
