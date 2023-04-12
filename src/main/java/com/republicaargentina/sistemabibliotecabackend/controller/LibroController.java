package com.republicaargentina.sistemabibliotecabackend.controller;

import com.republicaargentina.sistemabibliotecabackend.entity.Libro;
import com.republicaargentina.sistemabibliotecabackend.service.LibroService;
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

    @GetMapping("/{id}")
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

    @GetMapping("/findByTitulo/{titulo}")
    public ResponseEntity<List<Libro>> getAllByTitulo(@PathVariable String titulo) {
        return new ResponseEntity<>(libroService.findByTituloContainsIgnoreCase(titulo), HttpStatus.OK);
    }

    @GetMapping("/findByCodigo/{codigo}")
    public ResponseEntity<List<Libro>> getAllByCodigo(@PathVariable String codigo) {
        return new ResponseEntity<>(libroService.findByCodigoContainsIgnoreCaseOrderByCodigoAsc(codigo), HttpStatus.OK);
    }

    @GetMapping("/findByAutor/{nombre}")
    public ResponseEntity<Page<Libro>> getAllByAutor(@PathVariable String nombre, Pageable pageable) {
        return new ResponseEntity<>(libroService.findByAutorNombreIgnoreCaseOrderByCodigoAsc(nombre, pageable), HttpStatus.OK);
    }

    @GetMapping("/findByCategoria/{nombre}")
    public ResponseEntity<Page<Libro>> getAllByCategoria(@PathVariable String nombre, Pageable pageable) {
        return new ResponseEntity<>(libroService.findByCategoriaNombreIgnoreCaseOrderByCodigoAsc(nombre, pageable), HttpStatus.OK);
    }

    @GetMapping("/findByEditorial/{nombre}")
    public ResponseEntity<Page<Libro>> getAllByEditorial(@PathVariable String nombre, Pageable pageable) {
        return new ResponseEntity<>(libroService.findByEditorialNombreIgnoreCaseOrderByCodigoAsc(nombre, pageable), HttpStatus.OK);
    }

    @GetMapping("/findByArea/{nombre}")
    public ResponseEntity<Page<Libro>> getAllByArea(@PathVariable String nombre, Pageable pageable) {
        return new ResponseEntity<>(libroService.findByAreaNombreIgnoreCaseOrderByCodigoAsc(nombre, pageable), HttpStatus.OK);
    }
}
