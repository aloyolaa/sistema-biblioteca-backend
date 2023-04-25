package com.republicaargentina.sistemabibliotecabackend.service;

import com.republicaargentina.sistemabibliotecabackend.entity.PrestamoMaterial;
import com.republicaargentina.sistemabibliotecabackend.exception.DataAccessExceptionImpl;
import com.republicaargentina.sistemabibliotecabackend.repository.PrestamoMaterialRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrestamoMaterialServiceImpl implements PrestamoMaterialService {
    private final PrestamoMaterialRepository prestamoMaterialRepository;
    private final EjemplarMaterialService ejemplarMaterialService;
    private static final String ENTITY_NOT_FOUND_MESSAGE = "No existe un préstamo de materiales con el ID ";

    @Override
    @Transactional
    public List<PrestamoMaterial> getAll() {
        try {
            return prestamoMaterialRepository.getAll();
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional
    public Page<PrestamoMaterial> pagination(Pageable pageable) {
        try {
            return prestamoMaterialRepository.pagination(pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    public PrestamoMaterial getOne(Long id) {
        try {
            return prestamoMaterialRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE + id));
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional
    public PrestamoMaterial save(PrestamoMaterial prestamoMaterial) {
        try {
            prestamoMaterial.getDetalle()
                    .forEach(d -> ejemplarMaterialService.cambiarEstadoAPrestado(d.getEjemplarMaterial().getId()));
            return prestamoMaterialRepository.save(cambiarLetras(prestamoMaterial));
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Error al guardar los datos. Inténtelo mas tarde.", e);
        }
    }

    @Override
    @Transactional
    public Boolean delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El identificador de necesario para la eliminación.");
        }
        try {
            if (!prestamoMaterialRepository.existsById(id)) {
                throw new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE + id);
            }
            PrestamoMaterial prestamoMaterial = prestamoMaterialRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE + id));
            prestamoMaterial.getDetalle()
                    .forEach(d -> ejemplarMaterialService.cambiarEstadoADisponible(d.getEjemplarMaterial().getId()));
            prestamoMaterialRepository.deleteById(id);
            return true;
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Error al eliminar los datos. Inténtelo mas tarde.", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        try {
            return prestamoMaterialRepository.count();
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional
    public PrestamoMaterial close(PrestamoMaterial prestamoMaterial) {
        if (prestamoMaterial.getId() == null) {
            throw new IllegalArgumentException("El identificador de necesario para la eliminación.");
        }
        PrestamoMaterial prestamoMaterialById = prestamoMaterialRepository.findById(prestamoMaterial.getId()).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE + prestamoMaterial.getId()));
        try {
            prestamoMaterialById.setFechaDevolucion(LocalDateTime.now());
            prestamoMaterialById.setEstado("DEVUELTO");
            prestamoMaterialById.setObservaciones(prestamoMaterial.getObservaciones());
            prestamoMaterialById.getDetalle()
                    .forEach(d -> ejemplarMaterialService.cambiarEstadoADisponible(d.getEjemplarMaterial().getId()));
            return prestamoMaterialRepository.save(cambiarLetras(prestamoMaterialById));
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Error al actualizar los datos. Inténtelo mas tarde.", e);
        }
    }

    @Override
    public Page<PrestamoMaterial> paginationByFechaPrestamo(String fechaPrestamoStartStr, String fechaPrestamoEndStr, Pageable pageable) {
        if (fechaPrestamoStartStr == null || fechaPrestamoEndStr == null) {
            throw new IllegalArgumentException("Ambas fechas son requeridas para el filtro");
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime fechaPrestamoStart = LocalDateTime.parse(fechaPrestamoStartStr, dateTimeFormatter);
        LocalDateTime fechaPrestamoEnd = LocalDateTime.parse(fechaPrestamoEndStr, dateTimeFormatter);
        try {
            return prestamoMaterialRepository.paginationByFechaPrestamo(fechaPrestamoStart, fechaPrestamoEnd, pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    public PrestamoMaterial cambiarLetras(PrestamoMaterial prestamoMaterial) {
        prestamoMaterial.setDescripcion(prestamoMaterial.getDescripcion().toUpperCase());
        prestamoMaterial.setObservaciones(prestamoMaterial.getObservaciones().toUpperCase());
        return prestamoMaterial;
    }
}