package com.republicaargentina.sistemabibliotecabackend.controller;

import com.republicaargentina.sistemabibliotecabackend.entity.Categoria;
import com.republicaargentina.sistemabibliotecabackend.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categorias")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaController {
    private final CategoriaService categoriaService;

    @GetMapping("/")
    public ResponseEntity<List<Categoria>> getAll() {
        return new ResponseEntity<>(categoriaService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<Categoria>> pagination(Pageable pageable) {
        return new ResponseEntity<>(categoriaService.pagination(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(categoriaService.getOne(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Categoria> save(@Valid @RequestBody Categoria categoria) {
        return new ResponseEntity<>(categoriaService.save(categoria), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Categoria> update(@Valid @RequestBody Categoria categoria) {
        return new ResponseEntity<>(categoriaService.update(categoria), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return new ResponseEntity<>(categoriaService.delete(id), HttpStatus.OK);
    }
}
