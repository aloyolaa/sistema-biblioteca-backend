package com.republicaargentina.sistemabibliotecabackend.controller;

import com.republicaargentina.sistemabibliotecabackend.dto.UsuarioDto;
import com.republicaargentina.sistemabibliotecabackend.entity.Usuario;
import com.republicaargentina.sistemabibliotecabackend.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/usuarios")
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin(origins = {"{ips.permitidas}", "*"})
public class UsuarioController {
    private final UsuarioService usuarioService;

    @GetMapping("/")
    public ResponseEntity<List<UsuarioDto>> getAll() {
        return new ResponseEntity<>(usuarioService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<UsuarioDto> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(usuarioService.getOne(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<UsuarioDto> save(@Valid @RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioService.save(usuario), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<UsuarioDto> update(@Valid @RequestBody UsuarioDto usuarioDto) {
        return new ResponseEntity<>(usuarioService.update(usuarioDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return new ResponseEntity<>(usuarioService.delete(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole({'USER', 'ADMIN'})")
    @GetMapping("/getOneByUsername/{username}")
    public ResponseEntity<UsuarioDto> getOneByUsername(@PathVariable String username) {
        return new ResponseEntity<>(usuarioService.getOneByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<UsuarioDto>> pagination(Pageable pageable) {
        return new ResponseEntity<>(usuarioService.pagination(pageable), HttpStatus.OK);
    }
}
