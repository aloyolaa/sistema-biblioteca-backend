package com.republicaargentina.sistemabibliotecabackend.service;

import com.republicaargentina.sistemabibliotecabackend.entity.Ejemplar;
import com.republicaargentina.sistemabibliotecabackend.exception.DataAccessExceptionImpl;
import com.republicaargentina.sistemabibliotecabackend.repository.EjemplarRepository;
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
public class EjemplarServiceImpl implements EjemplarService {
    private final EjemplarRepository ejemplarRepository;
    private final LibroService libroService;
    private static final String MESSAGE = "No existe un ejemplar con el ID ";

    @Override
    @Transactional(readOnly = true)
    public List<Ejemplar> getAll() {
        try {
            return ejemplarRepository.getAll();
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Ejemplar> pagination(Pageable pageable) {
        try {
            return ejemplarRepository.pagination(pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Ejemplar getOne(Long id) {
        try {
            return ejemplarRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(MESSAGE + id));
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional
    public Boolean save(Long libroId, Integer cantidad) {
        try {
            Optional<Ejemplar> byLibroIdOrderByIdDesc = ejemplarRepository.getOneByLibro(libroId);
            List<Ejemplar> ejemplares = new ArrayList<>();
            Integer numero = 0;
            if (byLibroIdOrderByIdDesc.isPresent()) {
                numero = byLibroIdOrderByIdDesc.orElseThrow().getNumero();
            }
            for (int i = 0; i < cantidad; i++) {
                numero += 1;
                Ejemplar ejemplar = new Ejemplar();
                ejemplar.setNumero(numero);
                ejemplar.setLibro(libroService.getOne(libroId));
                ejemplares.add(ejemplar);
            }
            ejemplarRepository.saveAll(ejemplares);
            return true;
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Error al guardar los datos. Inténtelo mas tarde.", e);
        }
    }

    @Override
    public Ejemplar update(Ejemplar ejemplar) {
        if (ejemplar.getId() == null) {
            throw new IllegalArgumentException("El identificador de necesario para la actualización.");
        }
        Ejemplar ejemplarById = ejemplarRepository.findById(ejemplar.getId()).orElseThrow(() -> new EntityNotFoundException(MESSAGE + ejemplar.getId()));
        try {
            ejemplarById.setEstado(ejemplar.getEstado());
            ejemplarById.setObservaciones(ejemplar.getObservaciones());
            return ejemplarRepository.save(ejemplarById);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Error al actualizar los datos. Inténtelo mas tarde.", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        try {
            return ejemplarRepository.count();
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional
    public void cambiarEstadoAPrestado(Long id) {
        Ejemplar ejemplar = ejemplarRepository.findById(id).orElseThrow();
        ejemplar.setEstado("PRESTADO");
        ejemplarRepository.save(ejemplar);
    }

    @Override
    @Transactional
    public void cambiarEstadoADisponible(Long id) {
        Ejemplar ejemplar = ejemplarRepository.findById(id).orElseThrow();
        ejemplar.setEstado("DISPONIBLE");
        ejemplarRepository.save(ejemplar);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Ejemplar> paginationByLibro(String codigo, Pageable pageable) {
        try {
            return ejemplarRepository.paginationByLibro(codigo, pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Ejemplar> paginationByLibroAndEstado(String codigo, Pageable pageable) {
        try {
            return ejemplarRepository.paginationByLibroAndEstado(codigo, pageable);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public long countByLibro(String codigo) {
        try {
            return ejemplarRepository.countByLibro(codigo);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }
}
