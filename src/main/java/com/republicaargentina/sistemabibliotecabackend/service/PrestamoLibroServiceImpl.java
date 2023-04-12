package com.republicaargentina.sistemabibliotecabackend.service;

import com.republicaargentina.sistemabibliotecabackend.entity.PrestamoLibro;
import com.republicaargentina.sistemabibliotecabackend.exception.DataAccessExceptionImpl;
import com.republicaargentina.sistemabibliotecabackend.repository.PrestamoLibroRepository;
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
public class PrestamoLibroServiceImpl implements PrestamoLibroService {
    private final PrestamoLibroRepository prestamoLibroRepository;
    private static final String MESSAGE = "No existe un préstamo con el ID ";

    private void updateEstados() {
        prestamoLibroRepository.findAll()
                .forEach(p -> {
                    if (p.getFechaDevolucion() == null && LocalDateTime.now().isAfter(p.getFechaLimite())) {
                        p.setEstado("ATRASADO");
                        prestamoLibroRepository.save(p);
                    }
                });
    }

    @Override
    @Transactional(readOnly = true)
    public List<PrestamoLibro> findByOrderByFechaPrestamoDesc() {
        try {
            updateEstados();
            return prestamoLibroRepository.findByOrderByFechaPrestamoDesc();
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PrestamoLibro> paginationByOrderByFechaPrestamoDesc(Pageable pageable) {
        try {
            updateEstados();
            return prestamoLibroRepository.paginationByOrderByFechaPrestamoDesc(pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PrestamoLibro getOne(Long id) {
        try {
            return prestamoLibroRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(MESSAGE + id));
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional
    public PrestamoLibro save(PrestamoLibro prestamoLibro) {
        try {
            //TODO Manejar el cambio de estado del libro a "PRESTADO"
            return prestamoLibroRepository.save(prestamoLibro);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Error al guardar los datos. Inténtelo mas tarde.", e);
        }
    }

    @Override
    public PrestamoLibro update(PrestamoLibro prestamoLibro) {
        PrestamoLibro prestamoLibroById = prestamoLibroRepository.findById(prestamoLibro.getId()).orElseThrow(() -> new EntityNotFoundException(MESSAGE + prestamoLibro.getId()));
        try {
            prestamoLibroById.setFechaPrestamo(prestamoLibro.getFechaPrestamo());
            prestamoLibroById.setFechaLimite(prestamoLibro.getFechaLimite());
            prestamoLibroById.setFechaDevolucion(prestamoLibro.getFechaDevolucion());
            prestamoLibroById.setGrado(prestamoLibro.getGrado());
            prestamoLibroById.setSeccion(prestamoLibro.getSeccion());
            prestamoLibroById.setObservaciones(prestamoLibro.getObservaciones());
            prestamoLibroById.setDocente(prestamoLibro.getDocente());
            prestamoLibroById.setDetalle(prestamoLibro.getDetalle());
            return prestamoLibroRepository.save(prestamoLibroById);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Error al actualizar los datos. Inténtelo mas tarde.", e);
        }
    }

    @Override
    @Transactional
    public Boolean delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El identificador de necesario para la eliminación.");
        }
        try {
            if (!prestamoLibroRepository.existsById(id)) {
                throw new EntityNotFoundException(MESSAGE + id);
            }
            prestamoLibroRepository.deleteById(id);
            return true;
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Error al eliminar los datos. Inténtelo mas tarde.", e);
        }
    }

    @Override
    @Transactional
    public PrestamoLibro close(Long id) {
        PrestamoLibro prestamoLibro = prestamoLibroRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(MESSAGE + id));
        try {
            prestamoLibro.setFechaDevolucion(LocalDateTime.now());
            if (prestamoLibro.getFechaDevolucion().isAfter(prestamoLibro.getFechaLimite())) {
                prestamoLibro.setEstado("DEVUELTO CON TARDANZA");
            } else {
                prestamoLibro.setEstado("DEVUELTO");
            }
            prestamoLibro.setObservaciones(prestamoLibro.getObservaciones());
            return prestamoLibroRepository.save(prestamoLibro);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Error al actualizar los datos. Inténtelo mas tarde.", e);
        }
    }
}
