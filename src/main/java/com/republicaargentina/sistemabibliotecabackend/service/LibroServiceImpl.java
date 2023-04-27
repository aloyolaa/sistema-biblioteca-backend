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
    private static final String ENTITY_NOT_FOUND_MESSAGE = "No existe un libro con el ID ";

    @Override
    @Transactional(readOnly = true)
    public List<Libro> getAll() {
        try {
            return libroRepository.getAll();
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Libro> pagination(Pageable pageable) {
        try {
            return libroRepository.pagination(pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Libro getOne(Long id) {
        try {
            return libroRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE + id));
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional
    public Libro save(Libro libro) {
        try {
            return libroRepository.save(cambiarLetras(libro));
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
        Libro libroById = libroRepository.findById(libro.getId()).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE + libro.getId()));
        try {
            libroById.setCodigo(libro.getCodigo());
            libroById.setTitulo(libro.getTitulo());
            libroById.setAnio(libro.getAnio());
            libroById.setGrado(libro.getGrado());
            libroById.setArea(libro.getArea());
            libroById.setCategoria(libro.getCategoria());
            libroById.setEditorial(libro.getEditorial());
            libroById.setAutores(libro.getAutores());
            return libroRepository.save(cambiarLetras(libroById));
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
                throw new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE + id);
            }
            libroRepository.deleteById(id);
            return true;
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Error al eliminar los datos. Inténtelo mas tarde.", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        try {
            return libroRepository.count();
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Libro getOneByTitulo(String titulo) {
        try {
            return libroRepository.getOneByTitulo(titulo).orElseThrow(() -> new EntityNotFoundException("No existe un libro con el título " + titulo));
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Libro getOneByCodigo(String codigo) {
        try {
            return libroRepository.getOneByCodigo(codigo).orElseThrow(() -> new EntityNotFoundException("No existe un libro con el código " + codigo));
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Libro> getAllByTitulo(String titulo) {
        try {
            return libroRepository.getAllByTitulo(titulo);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Libro> getAllByCodigo(String codigo) {
        try {
            return libroRepository.getAllByCodigo(codigo);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Libro> paginationByArea(Long id, Pageable pageable) {
        try {
            return libroRepository.paginationByArea(id, pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Libro> paginationByCategoria(Long id, Pageable pageable) {
        try {
            return libroRepository.paginationByCategoria(id, pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Libro> paginationByEditorial(Long id, Pageable pageable) {
        try {
            return libroRepository.paginationByEditorial(id, pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Libro> paginationByAutor(Long id, Pageable pageable) {
        try {
            return libroRepository.paginationByAutor(id, pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    public Libro cambiarLetras(Libro libro) {
        libro.setCodigo(libro.getCodigo().toUpperCase());
        libro.setTitulo(libro.getTitulo().toUpperCase());
        return libro;
    }
}
