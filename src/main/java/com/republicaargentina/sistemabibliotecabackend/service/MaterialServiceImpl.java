package com.republicaargentina.sistemabibliotecabackend.service;

import com.republicaargentina.sistemabibliotecabackend.entity.Material;
import com.republicaargentina.sistemabibliotecabackend.exception.DataAccessExceptionImpl;
import com.republicaargentina.sistemabibliotecabackend.repository.MaterialRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {
    private final MaterialRepository materialRepository;
    private static final String ENTITY_NOT_FOUND_MESSAGE = "No existe un material con el ID ";

    @Override
    @Transactional(readOnly = true)
    public List<Material> getAll() {
        try {
            return materialRepository.getAll();
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Material getOne(Long id) {
        try {
            return materialRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE + id));
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional
    public Material save(Material material) {
        try {
            return materialRepository.save(cambiarLetras(material));
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Error al guardar los datos. Inténtelo mas tarde.", e);
        }
    }

    @Override
    @Transactional
    public Material update(Material material) {
        if (material.getId() == null) {
            throw new IllegalArgumentException("El identificador de necesario para la actualización.");
        }
        Material materialById = materialRepository.findById(material.getId()).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE + material.getId()));
        try {
            materialById.setCodigo(material.getCodigo());
            materialById.setNombre(material.getNombre());
            materialById.setMedidas(material.getMedidas());
            materialById.setArea(material.getArea());
            return materialRepository.save(cambiarLetras(materialById));
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
            if (!materialRepository.existsById(id)) {
                throw new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE + id);
            }
            materialRepository.deleteById(id);
            return true;
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Error al eliminar los datos. Inténtelo mas tarde.", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        try {
            return materialRepository.count();
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Material getOneByNombre(String nombre) {
        try {
            return materialRepository.getOneByNombre(nombre).orElseThrow(() -> new EntityNotFoundException("No existe un material con el nombre " + nombre));
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Material getOneByCodigo(String codigo) {
        try {
            return materialRepository.getOneByCodigo(codigo).orElseThrow(() -> new EntityNotFoundException("No existe un material con el código " + codigo));
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Material> pagination(Pageable pageable) {
        try {
            return materialRepository.pagination(pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Material> paginationByNombre(String nombre, Pageable pageable) {
        try {
            return materialRepository.paginationByNombre(nombre, pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Material> paginationByCodigo(String codigo, Pageable pageable) {
        try {
            return materialRepository.paginationByCodigo(codigo, pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Material> paginationByArea(Long id, Pageable pageable) {
        try {
            return materialRepository.paginationByArea(id, pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    public Material cambiarLetras(Material material) {
        material.setCodigo(material.getCodigo().toUpperCase());
        material.setNombre(material.getNombre().toUpperCase());
        if (material.getMedidas() != null) {
            material.setMedidas(material.getMedidas().toUpperCase());
        }
        return material;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Resource> exportPDF() {
        try {
            File file = ResourceUtils.getFile("classpath:reports/materiales/reporte_materiales.jasper");
            File logo = ResourceUtils.getFile("classpath:img/logoColegio.png");
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("logoColegio", new FileInputStream((logo)));
            parameters.put("ds", new JRBeanCollectionDataSource(materialRepository.getAll()));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
            StringBuilder stringBuilder = new StringBuilder().append("inventario_materiales");
            byte[] report = JasperExportManager.exportReportToPdf(jasperPrint);
            ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                    .filename(
                            stringBuilder
                                    .append("_")
                                    .append(LocalDate.now())
                                    .append(".pdf")
                                    .toString()
                    ).build();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentDisposition(contentDisposition);
            return ResponseEntity.ok().contentLength(report.length)
                    .contentType(MediaType.APPLICATION_PDF)
                    .headers(httpHeaders).body(new ByteArrayResource(report));
        } catch (FileNotFoundException | JRException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
