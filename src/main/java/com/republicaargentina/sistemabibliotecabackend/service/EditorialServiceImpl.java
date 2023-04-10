package com.republicaargentina.sistemabibliotecabackend.service;

import com.republicaargentina.sistemabibliotecabackend.entity.Editorial;
import com.republicaargentina.sistemabibliotecabackend.exception.DataAccessExceptionImpl;
import com.republicaargentina.sistemabibliotecabackend.repository.EditorialRepository;
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
public class EditorialServiceImpl implements EditorialService {
    private final EditorialRepository editorialRepository;
    private static final String MESSAGE = "No existe una editorial con el ID ";

    @Override
    @Transactional(readOnly = true)
    public List<Editorial> getAll() {
        try {
            return editorialRepository.findAll();
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Editorial> pagination(Pageable pageable) {
        try {
            return editorialRepository.findAll(pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Editorial getOne(Long id) {
        try {
            return editorialRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(MESSAGE + id));
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional
    public Editorial save(Editorial editorial) {
        try {
            return editorialRepository.save(editorial);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Error al guardar los datos. Inténtelo mas tarde.", e);
        }
    }

    @Override
    @Transactional
    public Editorial update(Editorial editorial) {
        if (editorial.getId() == null) {
            throw new IllegalArgumentException("El identificador de necesario para la actualización.");
        }
        Editorial editorialById = editorialRepository.findById(editorial.getId()).orElseThrow(() -> new EntityNotFoundException(MESSAGE + editorial.getId()));
        try {
            editorialById.setNombre(editorial.getNombre());
            return editorialRepository.save(editorialById);
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
            if (!editorialRepository.existsById(id)) {
                throw new EntityNotFoundException(MESSAGE + id);
            }
            editorialRepository.deleteById(id);
            return true;
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Error al eliminar los datos. Inténtelo mas tarde.", e);
        }
    }
}
