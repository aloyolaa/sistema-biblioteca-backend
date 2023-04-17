package com.republicaargentina.sistemabibliotecabackend.controller;


import com.republicaargentina.sistemabibliotecabackend.entity.PrestamoLibro;
import com.republicaargentina.sistemabibliotecabackend.service.PrestamoLibroService;
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
@RequestMapping("/api/v1/prestamos-libros")
@CrossOrigin(origins = "http://localhost:4200")
public class PrestamoLibroController {
    private final PrestamoLibroService prestamoLibroService;

    @GetMapping("/")
    public ResponseEntity<List<PrestamoLibro>> getAll() {
        return new ResponseEntity<>(prestamoLibroService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<PrestamoLibro>> pagination(Pageable pageable) {
        return new ResponseEntity<>(prestamoLibroService.pagination(pageable), HttpStatus.OK);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<PrestamoLibro> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(prestamoLibroService.getOne(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<PrestamoLibro> save(@Valid @RequestBody PrestamoLibro prestamoLibro) {
        return new ResponseEntity<>(prestamoLibroService.save(prestamoLibro), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return new ResponseEntity<>(prestamoLibroService.delete(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<>(prestamoLibroService.count(), HttpStatus.OK);
    }

    @GetMapping("/close/{id}")
    public ResponseEntity<PrestamoLibro> close(@PathVariable Long id) {
        return new ResponseEntity<>(prestamoLibroService.close(id), HttpStatus.OK);
    }

    @GetMapping("/paginationByFechaPrestamo/{fechaPrestamoStartStr}/{fechaPrestamoEndStr}")
    public ResponseEntity<Page<PrestamoLibro>> paginationByFechaPrestamo(@PathVariable String fechaPrestamoStartStr, @PathVariable String fechaPrestamoEndStr, Pageable pageable) {
        return new ResponseEntity<>(prestamoLibroService.paginationByFechaPrestamo(fechaPrestamoStartStr, fechaPrestamoEndStr, pageable), HttpStatus.OK);
    }
}
