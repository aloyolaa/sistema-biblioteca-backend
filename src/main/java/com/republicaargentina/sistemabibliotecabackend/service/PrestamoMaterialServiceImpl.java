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
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrestamoMaterialServiceImpl implements PrestamoMaterialService {
    private final PrestamoMaterialRepository prestamoMaterialRepository;
    private static final String MESSAGE = "No existe un préstamo con el ID ";

    @Override
    @Transactional
    public List<PrestamoMaterial> getAll() {
        try {
            cambiarEstados();
            return prestamoMaterialRepository.getAll();
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional
    public Page<PrestamoMaterial> pagination(Pageable pageable) {
        try {
            cambiarEstados();
            return prestamoMaterialRepository.pagination(pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    public PrestamoMaterial getOne(Long id) {
        try {
            return prestamoMaterialRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(MESSAGE + id));
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional
    public PrestamoMaterial save(PrestamoMaterial prestamoMaterial) {
        try {
            return prestamoMaterialRepository.save(prestamoMaterial);
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
                throw new EntityNotFoundException(MESSAGE + id);
            }
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
    public PrestamoMaterial close(Long id) {
        PrestamoMaterial prestamoMaterial = prestamoMaterialRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(MESSAGE + id));
        try {
            prestamoMaterial.setFechaDevolucion(LocalDateTime.now());
            if (prestamoMaterial.getFechaDevolucion().isAfter(prestamoMaterial.getFechaLimite())) {
                prestamoMaterial.setEstado("DEVUELTO CON TARDANZA");
            } else {
                prestamoMaterial.setEstado("DEVUELTO");
            }
            prestamoMaterial.setObservaciones(prestamoMaterial.getObservaciones());
            return prestamoMaterialRepository.save(prestamoMaterial);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Error al actualizar los datos. Inténtelo mas tarde.", e);
        }
    }

    @Override
    @Transactional
    public void cambiarEstados() {
        prestamoMaterialRepository.findAll()
                .forEach(p -> {
                    if (p.getFechaDevolucion() == null && LocalDateTime.now().isAfter(p.getFechaLimite())) {
                        p.setEstado("ATRASADO");
                        prestamoMaterialRepository.save(p);
                    }
                });
    }
}
