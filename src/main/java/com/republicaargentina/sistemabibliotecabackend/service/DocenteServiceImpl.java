package com.republicaargentina.sistemabibliotecabackend.service;

import com.republicaargentina.sistemabibliotecabackend.entity.Docente;
import com.republicaargentina.sistemabibliotecabackend.exception.DataAccessExceptionImpl;
import com.republicaargentina.sistemabibliotecabackend.repository.DocenteRepository;
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
public class DocenteServiceImpl implements DocenteService {
    private final DocenteRepository docenteRepository;
    private static final String ENTITY_NOT_FOUND_MESSAGE = "No existe un docente con el ID ";

    @Override
    @Transactional(readOnly = true)
    public List<Docente> getAll() {
        try {
            return docenteRepository.getAll();
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Docente getOne(Long id) {
        try {
            return docenteRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE + id));
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional
    public Docente save(Docente docente) {
        try {
            return docenteRepository.save(cambiarLetras(docente));
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Error al guardar los datos. Inténtelo mas tarde.", e);
        }
    }

    @Override
    @Transactional
    public Docente update(Docente docente) {
        if (docente.getId() == null) {
            throw new IllegalArgumentException("El identificador de necesario para la actualización.");
        }
        Docente docenteById = docenteRepository.findById(docente.getId())
                .orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE + docente.getId()));
        try {
            docenteById.setNombre(docente.getNombre());
            docenteById.setApellido(docente.getApellido());
            docenteById.setDni(docente.getDni());
            docenteById.setTelefono(docente.getTelefono());
            return docenteRepository.save(cambiarLetras(docenteById));
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
            if (!docenteRepository.existsById(id)) {
                throw new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE + id);
            }
            docenteRepository.deleteById(id);
            return true;
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Error al eliminar los datos. Inténtelo mas tarde.", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        try {
            return docenteRepository.count();
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Docente getOneByDni(String dni) {
        try {
            return docenteRepository.getOneByDni(dni)
                    .orElseThrow(() -> new EntityNotFoundException("No existe con docente con el dni " + dni));
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Docente> pagination(Pageable pageable) {
        try {
            return docenteRepository.pagination(pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    public Docente cambiarLetras(Docente docente) {
        docente.setNombre(docente.getNombre().toUpperCase());
        docente.setApellido(docente.getApellido().toUpperCase());
        return docente;
    }
}
