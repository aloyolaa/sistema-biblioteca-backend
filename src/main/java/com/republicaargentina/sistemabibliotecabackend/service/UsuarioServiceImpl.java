package com.republicaargentina.sistemabibliotecabackend.service;

import com.republicaargentina.sistemabibliotecabackend.dto.UsuarioDto;
import com.republicaargentina.sistemabibliotecabackend.entity.Usuario;
import com.republicaargentina.sistemabibliotecabackend.exception.DataAccessExceptionImpl;
import com.republicaargentina.sistemabibliotecabackend.mapper.UsuarioMapper;
import com.republicaargentina.sistemabibliotecabackend.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private static final String ENTITY_NOT_FOUND_MESSAGE = "No existe un usuario con el ID ";
    private final UsuarioMapper usuarioMapper;

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDto> getAll() {
        try {
            return usuarioRepository.findAll().stream().map(usuarioMapper::toDto).toList();
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioDto getOne(Long id) {
        try {
            return usuarioMapper.toDto(usuarioRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE + id)));
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional
    public UsuarioDto save(Usuario usuario) {
        try {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            return usuarioMapper.toDto(usuarioRepository.save(usuario));
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Error al guardar los datos. Inténtelo mas tarde.", e);
        }
    }

    @Override
    @Transactional
    public UsuarioDto update(UsuarioDto usuarioDto) {
        if (usuarioDto.id() == null) {
            throw new IllegalArgumentException("El identificador de necesario para la actualización.");
        }
        Usuario usuarioById = usuarioRepository.findById(usuarioDto.id())
                .orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE + usuarioDto.id()));
        Usuario usuario = usuarioMapper.partialUpdate(usuarioDto, usuarioById);
        try {
            return usuarioMapper.toDto(usuarioRepository.save(usuario));
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
            if (!usuarioRepository.existsById(id)) {
                throw new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE + id);
            }
            usuarioRepository.deleteById(id);
            return true;
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Error al eliminar los datos. Inténtelo mas tarde.", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioDto getOneByUsername(String username) {
        try {
            return usuarioMapper.toDto(usuarioRepository.getOneByUsername(username).orElseThrow(
                    () -> new EntityNotFoundException("No existe un usuario con el Username " + username)));
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UsuarioDto> pagination(Pageable pageable) {
        try {
            return usuarioRepository.pagination(pageable).map(usuarioMapper::toDto);
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }
}
