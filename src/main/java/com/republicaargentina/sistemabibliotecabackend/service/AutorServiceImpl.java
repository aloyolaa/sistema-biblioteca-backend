package com.republicaargentina.sistemabibliotecabackend.service;

import com.republicaargentina.sistemabibliotecabackend.entity.Autor;
import com.republicaargentina.sistemabibliotecabackend.exception.DataAccessExceptionImpl;
import com.republicaargentina.sistemabibliotecabackend.repository.AutorRepository;
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
public class AutorServiceImpl implements AutorService {
    private final AutorRepository autorRepository;
    private static final String ENTITY_NOT_FOUND_MESSAGE = "No existe un autor con el ID ";

    @Override
    @Transactional(readOnly = true)
    public List<Autor> getAll() {
        try {
            return autorRepository.getAll();
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Autor getOne(Long id) {
        try {
            return autorRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE + id));
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional
    public Autor save(Autor autor) {
        try {
            return autorRepository.save(cambiarLetras(autor));
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Error al guardar los datos. Inténtelo mas tarde.", e);
        }
    }

    @Override
    @Transactional
    public Autor update(Autor autor) {
        if (autor.getId() == null) {
            throw new IllegalArgumentException("El identificador de necesario para la actualización.");
        }
        Autor autorById = autorRepository.findById(autor.getId())
                .orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE + autor.getId()));
        try {
            autorById.setNombre(autor.getNombre());
            autorById.setApellido(autor.getApellido());
            return autorRepository.save(cambiarLetras(autorById));
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
            if (!autorRepository.existsById(id)) {
                throw new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE + id);
            }
            autorRepository.deleteById(id);
            return true;
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Error al eliminar los datos. Inténtelo mas tarde.", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        try {
            return autorRepository.count();
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Autor> getAllByNombreAndApellido(String nombre, String apellido) {
        try {
            return autorRepository.getAllByNombreAndApellido(nombre, apellido);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Autor> pagination(Pageable pageable) {
        try {
            return autorRepository.pagination(pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    public Autor cambiarLetras(Autor autor) {
        autor.setNombre(autor.getNombre().toUpperCase());
        autor.setApellido(autor.getApellido().toUpperCase());
        return autor;
    }
}
