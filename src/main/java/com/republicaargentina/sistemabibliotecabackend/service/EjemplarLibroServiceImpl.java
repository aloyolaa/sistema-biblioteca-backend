package com.republicaargentina.sistemabibliotecabackend.service;

import com.republicaargentina.sistemabibliotecabackend.entity.EjemplarLibro;
import com.republicaargentina.sistemabibliotecabackend.exception.DataAccessExceptionImpl;
import com.republicaargentina.sistemabibliotecabackend.repository.EjemplarLibroRepository;
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
public class EjemplarLibroServiceImpl implements EjemplarLibroService {
    private final EjemplarLibroRepository ejemplarLibroRepository;
    private final LibroService libroService;
    private static final String ENTITY_NOT_FOUND_MESSAGE = "No existe un ejemplar del libro con el ID ";

    @Override
    @Transactional(readOnly = true)
    public List<EjemplarLibro> getAll() {
        try {
            return ejemplarLibroRepository.getAll();
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EjemplarLibro> pagination(Pageable pageable) {
        try {
            return ejemplarLibroRepository.pagination(pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public EjemplarLibro getOne(Long id) {
        try {
            return ejemplarLibroRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE + id));
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional
    public Boolean save(Long libroId, Integer cantidad) {
        try {
            Optional<EjemplarLibro> byLibroIdOrderByIdDesc = ejemplarLibroRepository.getOneByLibro(libroId);
            List<EjemplarLibro> ejemplares = new ArrayList<>();
            Integer numero = 0;
            if (byLibroIdOrderByIdDesc.isPresent()) {
                numero = byLibroIdOrderByIdDesc.orElseThrow().getNumero();
            }
            for (int i = 0; i < cantidad; i++) {
                numero += 1;
                EjemplarLibro ejemplarLibro = new EjemplarLibro();
                ejemplarLibro.setNumero(numero);
                ejemplarLibro.setLibro(libroService.getOne(libroId));
                ejemplares.add(ejemplarLibro);
            }
            ejemplarLibroRepository.saveAll(ejemplares);
            return true;
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Error al guardar los datos. Inténtelo mas tarde.", e);
        }
    }

    @Override
    @Transactional
    public EjemplarLibro update(EjemplarLibro ejemplarLibro) {
        if (ejemplarLibro.getId() == null) {
            throw new IllegalArgumentException("El identificador de necesario para la actualización.");
        }
        EjemplarLibro ejemplarLibroById = ejemplarLibroRepository.findById(ejemplarLibro.getId()).orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE + ejemplarLibro.getId()));
        if (Boolean.TRUE.equals(ejemplarLibroById.getPrestado())) {
            throw new IllegalArgumentException("No se puede actualizar un ejemplar que esta prestado.");
        }
        try {
            ejemplarLibroById.setEstado(ejemplarLibro.getEstado());
            ejemplarLibroById.setObservaciones(ejemplarLibro.getObservaciones());
            return ejemplarLibroRepository.save(cambiarLetras(ejemplarLibroById));
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Error al actualizar los datos. Inténtelo mas tarde.", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        try {
            return ejemplarLibroRepository.count();
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional
    public void cambiarPrestado(Long id, Boolean prestado) {
        EjemplarLibro ejemplarLibro = ejemplarLibroRepository.findById(id).orElseThrow();
        ejemplarLibro.setPrestado(prestado);
        ejemplarLibroRepository.save(ejemplarLibro);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EjemplarLibro> paginationByLibro(String codigo, Pageable pageable) {
        try {
            return ejemplarLibroRepository.paginationByLibro(codigo, pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<EjemplarLibro> getAllByLibroAndEstado(String codigo, Integer cantidad) {
        try {
            return ejemplarLibroRepository.getAllByLibroAndEstado(codigo, cantidad);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public long countByLibro(String codigo) {
        try {
            return ejemplarLibroRepository.countByLibro(codigo);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public long countByLibroAndEstado(String codigo) {
        try {
            return ejemplarLibroRepository.countByLibroAndEstado(codigo);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    public EjemplarLibro cambiarLetras(EjemplarLibro ejemplarLibro) {
        ejemplarLibro.setEstado(ejemplarLibro.getEstado());
        ejemplarLibro.setObservaciones(ejemplarLibro.getObservaciones().toUpperCase());
        return ejemplarLibro;
    }
}
