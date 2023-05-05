package com.republicaargentina.sistemabibliotecabackend.controller;

import com.republicaargentina.sistemabibliotecabackend.entity.Libro;
import com.republicaargentina.sistemabibliotecabackend.service.LibroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/libros")
@CrossOrigin(origins = "http://localhost:4200")
public class LibroController {
    private final LibroService libroService;

    @GetMapping("/")
    public ResponseEntity<List<Libro>> getAll() {
        return new ResponseEntity<>(libroService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<Libro>> pagination(Pageable pageable) {
        return new ResponseEntity<>(libroService.pagination(pageable), HttpStatus.OK);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<Libro> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(libroService.getOne(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Libro> save(@Valid @RequestBody Libro libro) {
        return new ResponseEntity<>(libroService.save(libro), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Libro> update(@Valid @RequestBody Libro libro) {
        return new ResponseEntity<>(libroService.update(libro), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return new ResponseEntity<>(libroService.delete(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<>(libroService.count(), HttpStatus.OK);
    }

    @GetMapping("/getOneByTitulo/{titulo}")
    public ResponseEntity<Libro> getOneByTitulo(@PathVariable String titulo) {
        return new ResponseEntity<>(libroService.getOneByTitulo(titulo), HttpStatus.OK);
    }

    @GetMapping("/getOneByCodigo/{codigo}")
    public ResponseEntity<Libro> getOneByCodigo(@PathVariable String codigo) {
        return new ResponseEntity<>(libroService.getOneByCodigo(codigo), HttpStatus.OK);
    }

    @GetMapping("/getAllByTitulo/{titulo}")
    public ResponseEntity<List<Libro>> getAllByTitulo(@PathVariable String titulo) {
        return new ResponseEntity<>(libroService.getAllByTitulo(titulo), HttpStatus.OK);
    }

    @GetMapping("/getAllByCodigo/{codigo}")
    public ResponseEntity<List<Libro>> getAllByCodigo(@PathVariable String codigo) {
        return new ResponseEntity<>(libroService.getAllByCodigo(codigo), HttpStatus.OK);
    }

    @GetMapping("/paginationByArea/{id}/{page}")
    public ResponseEntity<Page<Libro>> paginationByArea(@PathVariable Long id, @PathVariable Integer page) {
        return new ResponseEntity<>(libroService.paginationByArea(id, PageRequest.of(page, 4)), HttpStatus.OK);
    }

    @GetMapping("/paginationByCategoria/{id}/{page}")
    public ResponseEntity<Page<Libro>> paginationByCategoria(@PathVariable Long id, @PathVariable Integer page) {
        return new ResponseEntity<>(libroService.paginationByCategoria(id, PageRequest.of(page, 4)), HttpStatus.OK);
    }

    @GetMapping("/paginationByEditorial/{id}/{page}")
    public ResponseEntity<Page<Libro>> paginationByEditorial(@PathVariable Long id, @PathVariable Integer page) {
        return new ResponseEntity<>(libroService.paginationByEditorial(id, PageRequest.of(page, 4)), HttpStatus.OK);
    }

    @GetMapping("/paginationByAutor/{id}/{page}")
    public ResponseEntity<Page<Libro>> paginationByAutor(@PathVariable Long id, @PathVariable Integer page) {
        return new ResponseEntity<>(libroService.paginationByAutor(id, PageRequest.of(page, 4)), HttpStatus.OK);
    }
}
