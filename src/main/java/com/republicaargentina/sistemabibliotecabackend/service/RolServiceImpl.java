package com.republicaargentina.sistemabibliotecabackend.service;

import com.republicaargentina.sistemabibliotecabackend.entity.Rol;
import com.republicaargentina.sistemabibliotecabackend.exception.DataAccessExceptionImpl;
import com.republicaargentina.sistemabibliotecabackend.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {
    private final RolRepository rolRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Rol> getAll() {
        try {
            return rolRepository.findAll();
        } catch (DataAccessException e) {
            throw new DataAccessExceptionImpl(e);
        }
    }
}
