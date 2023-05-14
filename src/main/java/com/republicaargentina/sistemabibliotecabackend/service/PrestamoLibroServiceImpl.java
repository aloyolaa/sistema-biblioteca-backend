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
    private final EjemplarLibroService ejemplarLibroService;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final String DATETIME_MESSAGE = "Ambas fechas son requeridas para el filtro";
    private static final String ENTITY_NOT_FOUND_MESSAGE = "No existe un préstamo de libros con el ID ";

    @Override
    @Transactional(readOnly = true)
    public List<PrestamoLibro> getAll() {
        try {
            return prestamoLibroRepository.getAll();
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PrestamoLibro getOne(Long id) {
        try {
            return prestamoLibroRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE + id));
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional
    public PrestamoLibro save(PrestamoLibro prestamoLibro) {
        try {
            prestamoLibro.getDetalle()
                    .forEach(d -> ejemplarLibroService.cambiarPrestado(d.getEjemplarLibro().getId(), true));
            return prestamoLibroRepository.save(cambiarLetras(prestamoLibro));
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
                throw new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE + id);
            }
            PrestamoLibro prestamoLibro = prestamoLibroRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE + id));
            prestamoLibro.getDetalle()
                    .forEach(d -> ejemplarLibroService.cambiarPrestado(d.getEjemplarLibro().getId(), false));
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
    public PrestamoLibro close(PrestamoLibro prestamoLibro) {
        if (prestamoLibro.getId() == null) {
            throw new IllegalArgumentException("El identificador de necesario para el cierre.");
        }
        PrestamoLibro prestamoLibroById = prestamoLibroRepository.findById(prestamoLibro.getId()).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE + prestamoLibro.getId()));
        try {
            prestamoLibroById.setFechaDevolucion(LocalDateTime.now());
            prestamoLibroById.setActivo(false);
            prestamoLibroById.setObservaciones(prestamoLibro.getObservaciones());
            prestamoLibroById.getDetalle()
                    .forEach(d -> ejemplarLibroService.cambiarPrestado(d.getEjemplarLibro().getId(), false));
            return prestamoLibroRepository.save(cambiarLetras(prestamoLibroById));
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Error al actualizar los datos. Inténtelo mas tarde.", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PrestamoLibro> pagination(Pageable pageable) {
        try {
            return prestamoLibroRepository.pagination(pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PrestamoLibro> paginationByDocente(String dni, Pageable pageable) {
        try {
            return prestamoLibroRepository.paginationByDocente(dni, pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PrestamoLibro> paginationByGradoAndSeccion(Integer grado, String seccion, Pageable pageable) {
        try {
            return prestamoLibroRepository.paginationByGradoAndSeccion(grado, seccion, pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PrestamoLibro> paginationByFechaPrestamo(String fechaPrestamoStartStr, String fechaPrestamoEndStr, Pageable pageable) {
        if (fechaPrestamoStartStr == null || fechaPrestamoEndStr == null) {
            throw new IllegalArgumentException(DATETIME_MESSAGE);
        }
        LocalDateTime fechaPrestamoStart = LocalDateTime.parse(fechaPrestamoStartStr, dateTimeFormatter);
        LocalDateTime fechaPrestamoEnd = LocalDateTime.parse(fechaPrestamoEndStr, dateTimeFormatter);
        fechaPrestamoEnd = fechaPrestamoEnd.plusHours(23).plusMinutes(59).plusSeconds(59);
        try {
            return prestamoLibroRepository.paginationByFechaPrestamo(fechaPrestamoStart, fechaPrestamoEnd, pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PrestamoLibro> paginationByFechaPrestamoAndDocente(String fechaPrestamoStartStr, String fechaPrestamoEndStr, Long id, Pageable pageable) {
        if (fechaPrestamoStartStr == null || fechaPrestamoEndStr == null) {
            throw new IllegalArgumentException(DATETIME_MESSAGE);
        }
        LocalDateTime fechaPrestamoStart = LocalDateTime.parse(fechaPrestamoStartStr, dateTimeFormatter);
        LocalDateTime fechaPrestamoEnd = LocalDateTime.parse(fechaPrestamoEndStr, dateTimeFormatter);
        fechaPrestamoEnd = fechaPrestamoEnd.plusHours(23).plusMinutes(59).plusSeconds(59);
        try {
            return prestamoLibroRepository.paginationByFechaPrestamoAndDocente(fechaPrestamoStart, fechaPrestamoEnd, id, pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PrestamoLibro> paginationByFechaPrestamoAndGradoAndSeccion(String fechaPrestamoStartStr, String fechaPrestamoEndStr, Integer grado, String seccion, Pageable pageable) {
        if (fechaPrestamoStartStr == null || fechaPrestamoEndStr == null) {
            throw new IllegalArgumentException(DATETIME_MESSAGE);
        }
        LocalDateTime fechaPrestamoStart = LocalDateTime.parse(fechaPrestamoStartStr, dateTimeFormatter);
        LocalDateTime fechaPrestamoEnd = LocalDateTime.parse(fechaPrestamoEndStr, dateTimeFormatter);
        fechaPrestamoEnd = fechaPrestamoEnd.plusHours(23).plusMinutes(59).plusSeconds(59);
        try {
            return prestamoLibroRepository.paginationByFechaPrestamoAndGradoAndSeccion(fechaPrestamoStart, fechaPrestamoEnd, grado, seccion, pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    public PrestamoLibro cambiarLetras(PrestamoLibro prestamoLibro) {
        prestamoLibro.setDescripcion(prestamoLibro.getDescripcion().toUpperCase());
        prestamoLibro.setObservaciones(prestamoLibro.getObservaciones().toUpperCase());
        return prestamoLibro;
    }
}
