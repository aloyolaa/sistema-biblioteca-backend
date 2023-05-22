package com.republicaargentina.sistemabibliotecabackend.controller;

import com.republicaargentina.sistemabibliotecabackend.entity.Usuario;
import com.republicaargentina.sistemabibliotecabackend.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/usuarios")
@PreAuthorize("hasRole('ADMIN')")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @GetMapping("/")
    public ResponseEntity<List<Usuario>> getAll() {
        return new ResponseEntity<>(usuarioService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(usuarioService.getOne(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Usuario> save(@Valid @RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioService.save(usuario), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Usuario> update(@Valid @RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioService.update(usuario), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return new ResponseEntity<>(usuarioService.delete(id), HttpStatus.OK);
    }
}
