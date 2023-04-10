package com.republicaargentina.sistemabibliotecabackend.service;

import com.republicaargentina.sistemabibliotecabackend.entity.Libro;
import com.republicaargentina.sistemabibliotecabackend.exception.DataAccessExceptionImpl;
import com.republicaargentina.sistemabibliotecabackend.repository.LibroRepository;
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
public class LibroServiceImpl implements LibroService {
    private final LibroRepository libroRepository;
    private static final String MESSAGE = "No existe un libro con el ID ";

    @Override
    @Transactional(readOnly = true)
    public List<Libro> getAll() {
        try {
            return libroRepository.findAll();
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Libro> pagination(Pageable pageable) {
        try {
            return libroRepository.findAll(pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Libro getOne(Long id) {
        try {
            return libroRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(MESSAGE + id));
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional
    public Libro save(Libro libro) {
        try {
            return libroRepository.save(libro);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Error al guardar los datos. Inténtelo mas tarde.", e);
        }
    }

    @Override
    @Transactional
    public Libro update(Libro libro) {
        if (libro.getId() == null) {
            throw new IllegalArgumentException("El identificador de necesario para la actualización.");
        }
        Libro libroById = libroRepository.findById(libro.getId()).orElseThrow(() -> new EntityNotFoundException(MESSAGE + libro.getId()));
        try {
            libroById.setTitulo(libro.getTitulo());
            libroById.setAnio(libro.getAnio());
            libroById.setGrado(libro.getGrado());
            libroById.setCantidad(libro.getCantidad());
            libroById.setObservaciones(libro.getObservaciones());
            libroById.setAutor(libro.getAutor());
            libroById.setCategoria(libro.getCategoria());
            libroById.setEditorial(libro.getEditorial());
            libroById.setArea(libro.getArea());
            return libroRepository.save(libroById);
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
            if (!libroRepository.existsById(id)) {
                throw new EntityNotFoundException(MESSAGE + id);
            }
            libroRepository.deleteById(id);
            return true;
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Error al eliminar los datos. Inténtelo mas tarde.", e);
        }
    }
}
