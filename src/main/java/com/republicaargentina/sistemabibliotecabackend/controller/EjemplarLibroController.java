package com.republicaargentina.sistemabibliotecabackend.controller;

import com.republicaargentina.sistemabibliotecabackend.entity.EjemplarLibro;
import com.republicaargentina.sistemabibliotecabackend.service.EjemplarLibroService;
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
@RequestMapping("/api/v1/ejemplares-libros")
@CrossOrigin(origins = "http://localhost:4200")
public class EjemplarLibroController {
    private final EjemplarLibroService ejemplarLibroService;

    @GetMapping("/")
    public ResponseEntity<List<EjemplarLibro>> getAll() {
        return new ResponseEntity<>(ejemplarLibroService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<EjemplarLibro> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(ejemplarLibroService.getOne(id), HttpStatus.OK);
    }

    @GetMapping("/save/{libroId}/{cantidad}")
    public ResponseEntity<Boolean> save(@PathVariable Long libroId, @PathVariable  Integer cantidad) {
        return new ResponseEntity<>(ejemplarLibroService.save(libroId, cantidad), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<EjemplarLibro> update(@Valid @RequestBody EjemplarLibro ejemplarLibro) {
        return new ResponseEntity<>(ejemplarLibroService.update(ejemplarLibro), HttpStatus.CREATED);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<>(ejemplarLibroService.count(), HttpStatus.OK);
    }

    @GetMapping("/getAllByLibroAndEstado/{codigo}/{cantidad}")
    public ResponseEntity<List<EjemplarLibro>> getAllByLibroAndEstado(@PathVariable String codigo, @PathVariable Integer cantidad) {
        return new ResponseEntity<>(ejemplarLibroService.getAllByLibroAndEstado(codigo, cantidad), HttpStatus.OK);
    }

    @GetMapping("/countByLibro/{codigo}")
    public ResponseEntity<Long> countByLibro(@PathVariable String codigo) {
        return new ResponseEntity<>(ejemplarLibroService.countByLibro(codigo), HttpStatus.OK);
    }

    @GetMapping("/countByLibroAndEstado/{codigo}")
    public ResponseEntity<Long> countByLibroAndEstado(@PathVariable String codigo) {
        return new ResponseEntity<>(ejemplarLibroService.countByLibroAndEstado(codigo), HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<EjemplarLibro>> pagination(Pageable pageable) {
        return new ResponseEntity<>(ejemplarLibroService.pagination(pageable), HttpStatus.OK);
    }

    @GetMapping("/paginationByLibro/{codigo}")
    public ResponseEntity<Page<EjemplarLibro>> paginationByLibro(@PathVariable String codigo, Pageable pageable) {
        return new ResponseEntity<>(ejemplarLibroService.paginationByLibro(codigo, pageable), HttpStatus.OK);
    }
}
