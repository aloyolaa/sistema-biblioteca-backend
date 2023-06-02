package com.republicaargentina.sistemabibliotecabackend.service;

import com.republicaargentina.sistemabibliotecabackend.entity.PrestamoMaterial;
import com.republicaargentina.sistemabibliotecabackend.exception.DataAccessExceptionImpl;
import com.republicaargentina.sistemabibliotecabackend.repository.PrestamoMaterialRepository;
import com.republicaargentina.sistemabibliotecabackend.util.PrestamoMaterialReportGenerator;
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
    private final PrestamoMaterialReportGenerator prestamoMaterialReportGenerator;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final String ENTITY_NOT_FOUND_MESSAGE = "No existe un préstamo de materiales con el ID ";
    private static final String DATETIME_MESSAGE = "Ambas fechas son requeridas para el filtro";

    @Override
    @Transactional(readOnly = true)
    public List<PrestamoMaterial> getAll() {
        try {
            return prestamoMaterialRepository.getAll();
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public PrestamoMaterial getOne(Long id) {
        try {
            return prestamoMaterialRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE + id));
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional
    public PrestamoMaterial save(PrestamoMaterial prestamoMaterial) {
        try {
            prestamoMaterial.getEjemplares()
                    .forEach(e -> ejemplarMaterialService.cambiarPrestado(e.getId(), true));
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
            PrestamoMaterial prestamoMaterial = prestamoMaterialRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE + id));
            prestamoMaterial.getEjemplares()
                    .forEach(e -> ejemplarMaterialService.cambiarPrestado(e.getId(), false));
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
            throw new IllegalArgumentException("El identificador de necesario para el cierre.");
        }
        PrestamoMaterial prestamoMaterialById = prestamoMaterialRepository.findById(prestamoMaterial.getId())
                .orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE + prestamoMaterial.getId()));
        try {
            prestamoMaterialById.setFechaDevolucion(LocalDateTime.now());
            prestamoMaterialById.setActivo(false);
            prestamoMaterialById.setObservaciones(prestamoMaterial.getObservaciones());
            prestamoMaterialById.getEjemplares()
                    .forEach(e -> ejemplarMaterialService.cambiarPrestado(e.getId(), false));
            return prestamoMaterialRepository.save(cambiarLetras(prestamoMaterialById));
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Error al actualizar los datos. Inténtelo mas tarde.", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PrestamoMaterial> pagination(Pageable pageable) {
        try {
            return prestamoMaterialRepository.pagination(pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PrestamoMaterial> paginationByDocente(String dni, Pageable pageable) {
        try {
            return prestamoMaterialRepository.paginationByDocente(dni, pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PrestamoMaterial> paginationByGradoAndSeccion(Integer grado, String seccion, Pageable pageable) {
        try {
            return prestamoMaterialRepository.paginationByGradoAndSeccion(grado, seccion, pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PrestamoMaterial> paginationByDescripcion(String descripcion, Pageable pageable) {
        try {
            return prestamoMaterialRepository.paginationByDescripcion(descripcion, pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PrestamoMaterial> paginationByFechaPrestamo(String fechaPrestamoStartStr, String fechaPrestamoEndStr,
            Pageable pageable) {
        if (fechaPrestamoStartStr == null || fechaPrestamoEndStr == null) {
            throw new IllegalArgumentException(DATETIME_MESSAGE);
        }
        LocalDateTime fechaPrestamoStart = LocalDateTime.parse(fechaPrestamoStartStr, dateTimeFormatter);
        LocalDateTime fechaPrestamoEnd = LocalDateTime.parse(fechaPrestamoEndStr, dateTimeFormatter);
        fechaPrestamoEnd = fechaPrestamoEnd.plusHours(23).plusMinutes(59).plusSeconds(59);
        try {
            return prestamoMaterialRepository.paginationByFechaPrestamo(fechaPrestamoStart, fechaPrestamoEnd, pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PrestamoMaterial> paginationByFechaPrestamoAndDocente(String fechaPrestamoStartStr,
            String fechaPrestamoEndStr, Long id, Pageable pageable) {
        if (fechaPrestamoStartStr == null || fechaPrestamoEndStr == null) {
            throw new IllegalArgumentException(DATETIME_MESSAGE);
        }
        LocalDateTime fechaPrestamoStart = LocalDateTime.parse(fechaPrestamoStartStr, dateTimeFormatter);
        LocalDateTime fechaPrestamoEnd = LocalDateTime.parse(fechaPrestamoEndStr, dateTimeFormatter);
        fechaPrestamoEnd = fechaPrestamoEnd.plusHours(23).plusMinutes(59).plusSeconds(59);
        try {
            return prestamoMaterialRepository.paginationByFechaPrestamoAndDocente(fechaPrestamoStart, fechaPrestamoEnd,
                    id, pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PrestamoMaterial> paginationByFechaPrestamoAndGradoAndSeccion(String fechaPrestamoStartStr,
            String fechaPrestamoEndStr, Integer grado, String seccion, Pageable pageable) {
        if (fechaPrestamoStartStr == null || fechaPrestamoEndStr == null) {
            throw new IllegalArgumentException(DATETIME_MESSAGE);
        }
        LocalDateTime fechaPrestamoStart = LocalDateTime.parse(fechaPrestamoStartStr, dateTimeFormatter);
        LocalDateTime fechaPrestamoEnd = LocalDateTime.parse(fechaPrestamoEndStr, dateTimeFormatter);
        fechaPrestamoEnd = fechaPrestamoEnd.plusHours(23).plusMinutes(59).plusSeconds(59);
        try {
            return prestamoMaterialRepository.paginationByFechaPrestamoAndGradoAndSeccion(fechaPrestamoStart,
                    fechaPrestamoEnd, grado, seccion, pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PrestamoMaterial> paginationByFechaPrestamoAndDescripcion(String fechaPrestamoStartStr,
            String fechaPrestamoEndStr, String descripcion, Pageable pageable) {
        if (fechaPrestamoStartStr == null || fechaPrestamoEndStr == null) {
            throw new IllegalArgumentException(DATETIME_MESSAGE);
        }
        LocalDateTime fechaPrestamoStart = LocalDateTime.parse(fechaPrestamoStartStr, dateTimeFormatter);
        LocalDateTime fechaPrestamoEnd = LocalDateTime.parse(fechaPrestamoEndStr, dateTimeFormatter);
        fechaPrestamoEnd = fechaPrestamoEnd.plusHours(23).plusMinutes(59).plusSeconds(59);
        try {
            return prestamoMaterialRepository.paginationByFechaPrestamoAndDescripcion(fechaPrestamoStart,
                    fechaPrestamoEnd, descripcion, pageable);
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

    @Override
    @Transactional(readOnly = true)
    public byte[] exportAllToPdf() {
        return prestamoMaterialReportGenerator.exportToPdf(prestamoMaterialRepository.getAll());
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportAllToXls() {
        return prestamoMaterialReportGenerator.exportToXls(prestamoMaterialRepository.getAll());
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportByPrestamoMaterialToPdf(Long id) {
        return prestamoMaterialReportGenerator.exportByPrestamoMaterialToPdf(prestamoMaterialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE + id)));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportByPrestamoMaterialToXls(Long id) {
        return prestamoMaterialReportGenerator.exportByPrestamoMaterialToXls(prestamoMaterialRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE + id)));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportByDocenteToPdf(Long id) {
        return prestamoMaterialReportGenerator.exportToPdf(prestamoMaterialRepository.getAllByDocente(id));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportByDocenteToXls(Long id) {
        return prestamoMaterialReportGenerator.exportToXls(prestamoMaterialRepository.getAllByDocente(id));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportByGradoAndSeccionToPdf(Integer grado, String seccion) {
        return prestamoMaterialReportGenerator
                .exportToPdf(prestamoMaterialRepository.getAllByGradoAndSeccion(grado, seccion));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exportByGradoAndSeccionToXls(Integer grado, String seccion) {
        return prestamoMaterialReportGenerator
                .exportToXls(prestamoMaterialRepository.getAllByGradoAndSeccion(grado, seccion));
    }
}
