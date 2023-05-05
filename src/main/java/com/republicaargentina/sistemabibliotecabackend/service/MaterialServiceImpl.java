package com.republicaargentina.sistemabibliotecabackend.service;

import com.republicaargentina.sistemabibliotecabackend.entity.Material;
import com.republicaargentina.sistemabibliotecabackend.exception.DataAccessExceptionImpl;
import com.republicaargentina.sistemabibliotecabackend.repository.MaterialRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
