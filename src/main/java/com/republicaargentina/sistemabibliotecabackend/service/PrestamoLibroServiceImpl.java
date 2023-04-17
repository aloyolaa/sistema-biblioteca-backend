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
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrestamoLibroServiceImpl implements PrestamoLibroService {
    private final PrestamoLibroRepository prestamoLibroRepository;
    private final EjemplarService ejemplarService;
    private static final String MESSAGE = "No existe un préstamo con el ID ";

    @Override
    @Transactional
    public List<PrestamoLibro> getAll() {
        try {
            cambiarEstados();
            return prestamoLibroRepository.getAll();
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional
    public Page<PrestamoLibro> pagination(Pageable pageable) {
        try {
            cambiarEstados();
            return prestamoLibroRepository.pagination(pageable);
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
            prestamoLibro.getDetalle()
                    .forEach(d -> ejemplarService.cambiarEstadoAPrestado(d.getEjemplar().getId()));
            return prestamoLibroRepository.save(prestamoLibro);
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
            if (!prestamoLibroRepository.existsById(id)) {
                throw new EntityNotFoundException(MESSAGE + id);
            }
            PrestamoLibro prestamoLibro = prestamoLibroRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(MESSAGE + id));
            prestamoLibro.getDetalle().forEach(d -> d.getEjemplar().setEstado("DISPONIBLE"));
            prestamoLibroRepository.deleteById(id);
            return true;
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Error al eliminar los datos. Inténtelo mas tarde.", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        try {
            return prestamoLibroRepository.count();
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
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
            prestamoLibro.getDetalle()
                    .forEach(d -> ejemplarService.cambiarEstadoADisponible(d.getEjemplar().getId()));
            return prestamoLibroRepository.save(prestamoLibro);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Error al actualizar los datos. Inténtelo mas tarde.", e);
        }
    }

    @Override
    @Transactional
    public void cambiarEstados() {
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
    public Page<PrestamoLibro> paginationByFechaPrestamo(String fechaPrestamoStartStr, String fechaPrestamoEndStr, Pageable pageable) {
        if (fechaPrestamoStartStr == null || fechaPrestamoEndStr == null) {
            throw new IllegalArgumentException("Ambas fechas son requeridas para el filtro");
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime fechaPrestamoStart = LocalDateTime.parse(fechaPrestamoStartStr, dateTimeFormatter);
        LocalDateTime fechaPrestamoEnd = LocalDateTime.parse(fechaPrestamoEndStr, dateTimeFormatter);
        try {
            return prestamoLibroRepository.paginationByFechaPrestamo(fechaPrestamoStart, fechaPrestamoEnd, pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }
}
