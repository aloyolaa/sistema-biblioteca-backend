package com.republicaargentina.sistemabibliotecabackend.service;

import com.republicaargentina.sistemabibliotecabackend.entity.EjemplarMaterial;
import com.republicaargentina.sistemabibliotecabackend.exception.DataAccessExceptionImpl;
import com.republicaargentina.sistemabibliotecabackend.repository.EjemplarMaterialRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EjemplarMaterialServiceImpl implements EjemplarMaterialService {
    private final EjemplarMaterialRepository ejemplarMaterialRepository;
    private final MaterialService materialService;
    private static final String ENTITY_NOT_FOUND_MESSAGE = "No existe un ejemplar del material con el ID ";

    @Override
    @Transactional(readOnly = true)
    public List<EjemplarMaterial> getAll() {
        try {
            return ejemplarMaterialRepository.getAll();
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public EjemplarMaterial getOne(Long id) {
        try {
            return ejemplarMaterialRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE + id));
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional
    public Boolean save(Long materialId, Integer cantidad) {
        try {
            Optional<EjemplarMaterial> byMaterialIdOrderByIdDesc = ejemplarMaterialRepository.getOneByMaterial(materialId);
            List<EjemplarMaterial> ejemplares = new ArrayList<>();
            Integer numero = 0;
            if (byMaterialIdOrderByIdDesc.isPresent()) {
                numero = byMaterialIdOrderByIdDesc.orElseThrow().getNumero();
            }
            for (int i = 0; i < cantidad; i++) {
                numero += 1;
                EjemplarMaterial ejemplarMaterial = new EjemplarMaterial();
                ejemplarMaterial.setNumero(numero);
                ejemplarMaterial.setMaterial(materialService.getOne(materialId));
                ejemplares.add(ejemplarMaterial);
            }
            ejemplarMaterialRepository.saveAll(ejemplares);
            return true;
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Error al guardar los datos. Inténtelo mas tarde.", e);
        }
    }

    @Override
    @Transactional
    public EjemplarMaterial update(EjemplarMaterial ejemplarMaterial) {
        if (ejemplarMaterial.getId() == null) {
            throw new IllegalArgumentException("El identificador de necesario para la actualización.");
        }
        EjemplarMaterial ejemplarMaterialById = ejemplarMaterialRepository.findById(ejemplarMaterial.getId()).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE + ejemplarMaterial.getId()));
        if (Boolean.TRUE.equals(ejemplarMaterialById.getPrestado())) {
            throw new IllegalArgumentException("No se puede actualizar un ejemplar que esta prestado.");
        }
        try {
            ejemplarMaterialById.setEstado(ejemplarMaterial.getEstado());
            ejemplarMaterialById.setObservaciones(ejemplarMaterial.getObservaciones());
            return ejemplarMaterialRepository.save(cambiarLetras(ejemplarMaterialById));
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Error al actualizar los datos. Inténtelo mas tarde.", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        try {
            return ejemplarMaterialRepository.count();
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<EjemplarMaterial> getAllByMaterialAndEstado(String codigo, Integer cantidad) {
        try {
            return ejemplarMaterialRepository.getAllByMaterialAndEstado(codigo, cantidad);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public long countByMaterial(String codigo) {
        try {
            return ejemplarMaterialRepository.countByMaterial(codigo);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public long countByMaterialAndEstado(String codigo) {
        try {
            return ejemplarMaterialRepository.countByMaterialAndEstado(codigo);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EjemplarMaterial> pagination(Pageable pageable) {
        try {
            return ejemplarMaterialRepository.pagination(pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EjemplarMaterial> paginationByMaterial(String codigo, Pageable pageable) {
        try {
            return ejemplarMaterialRepository.paginationByMaterial(codigo, pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional
    public void cambiarPrestado(Long id, Boolean prestado) {
        EjemplarMaterial ejemplarMaterial = ejemplarMaterialRepository.findById(id).orElseThrow();
        ejemplarMaterial.setPrestado(prestado);
        ejemplarMaterialRepository.save(ejemplarMaterial);
    }

    @Override
    public EjemplarMaterial cambiarLetras(EjemplarMaterial ejemplarMaterial) {
        ejemplarMaterial.setEstado(ejemplarMaterial.getEstado());
        ejemplarMaterial.setObservaciones(ejemplarMaterial.getObservaciones().toUpperCase());
        return ejemplarMaterial;
    }
}
