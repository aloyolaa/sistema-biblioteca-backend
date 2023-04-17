package com.republicaargentina.sistemabibliotecabackend.controller;

import com.republicaargentina.sistemabibliotecabackend.entity.Ejemplar;
import com.republicaargentina.sistemabibliotecabackend.service.EjemplarService;
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
@RequestMapping("/api/v1/ejemplares")
@CrossOrigin(origins = "http://localhost:4200")
public class EjemplarController {
    private final EjemplarService ejemplarService;

    @GetMapping("/")
    public ResponseEntity<List<Ejemplar>> getAll() {
        return new ResponseEntity<>(ejemplarService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<Ejemplar>> pagination(Pageable pageable) {
        return new ResponseEntity<>(ejemplarService.pagination(pageable), HttpStatus.OK);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<Ejemplar> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(ejemplarService.getOne(id), HttpStatus.OK);
    }

    @PostMapping("/save/{libroId}/{cantidad}")
    public ResponseEntity<Boolean> save(@PathVariable Long libroId, @PathVariable  Integer cantidad) {
        return new ResponseEntity<>(ejemplarService.save(libroId, cantidad), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Ejemplar> update(@Valid @RequestBody Ejemplar ejemplar) {
        return new ResponseEntity<>(ejemplarService.update(ejemplar), HttpStatus.CREATED);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<>(ejemplarService.count(), HttpStatus.OK);
    }

    @GetMapping("/paginationByLibro/{codigo}")
    public ResponseEntity<Page<Ejemplar>> paginationByLibro(@PathVariable String codigo, Pageable pageable) {
        return new ResponseEntity<>(ejemplarService.paginationByLibro(codigo, pageable), HttpStatus.OK);
    }

    @GetMapping("/paginationByLibroAndEstado/{codigo}")
    public ResponseEntity<Page<Ejemplar>> paginationByLibroAndEstado(@PathVariable String codigo, Pageable pageable) {
        return new ResponseEntity<>(ejemplarService.paginationByLibroAndEstado(codigo, pageable), HttpStatus.OK);
    }

    @GetMapping("/countByLibro/{codigo}")
    public ResponseEntity<Long> countByLibro(@PathVariable String codigo) {
        return new ResponseEntity<>(ejemplarService.countByLibro(codigo), HttpStatus.OK);
    }
}
