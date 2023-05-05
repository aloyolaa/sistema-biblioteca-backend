package com.republicaargentina.sistemabibliotecabackend.controller;


import com.republicaargentina.sistemabibliotecabackend.entity.PrestamoLibro;
import com.republicaargentina.sistemabibliotecabackend.service.PrestamoLibroService;
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

    @PutMapping("/close")
    public ResponseEntity<PrestamoLibro> close(@RequestBody PrestamoLibro prestamoLibro) {
        return new ResponseEntity<>(prestamoLibroService.close(prestamoLibro), HttpStatus.OK);
    }

    @GetMapping("/paginationByDocente/{dni}/{page}")
    public ResponseEntity<Page<PrestamoLibro>> paginationByDocente(@PathVariable String dni, @PathVariable Integer page) {
        return new ResponseEntity<>(prestamoLibroService.paginationByDocente(dni, PageRequest.of(page, 4)), HttpStatus.OK);
    }

    @GetMapping("/paginationByGradoAndSeccion/{grado}/{seccion}/{page}")
    public ResponseEntity<Page<PrestamoLibro>> paginationByGradoAndSeccion(@PathVariable Integer grado, @PathVariable String seccion, @PathVariable Integer page) {
        return new ResponseEntity<>(prestamoLibroService.paginationByGradoAndSeccion(grado, seccion, PageRequest.of(page, 4)), HttpStatus.OK);
    }

    @GetMapping("/paginationByFechaPrestamo/{fechaPrestamoStartStr}/{fechaPrestamoEndStr}/{page}")
    public ResponseEntity<Page<PrestamoLibro>> paginationByFechaPrestamo(@PathVariable String fechaPrestamoStartStr, @PathVariable String fechaPrestamoEndStr, @PathVariable Integer page) {
        return new ResponseEntity<>(prestamoLibroService.paginationByFechaPrestamo(fechaPrestamoStartStr, fechaPrestamoEndStr, PageRequest.of(page, 4)), HttpStatus.OK);
    }

    @GetMapping("/paginationByFechaPrestamoAndDocente/{fechaPrestamoStartStr}/{fechaPrestamoEndStr}/{docenteId}/{page}")
    public ResponseEntity<Page<PrestamoLibro>> paginationByFechaPrestamoAndDocente(@PathVariable String fechaPrestamoStartStr, @PathVariable String fechaPrestamoEndStr, @PathVariable Long docenteId, @PathVariable Integer page) {
        return new ResponseEntity<>(prestamoLibroService.paginationByFechaPrestamoAndDocente(fechaPrestamoStartStr, fechaPrestamoEndStr, docenteId, PageRequest.of(page, 4)), HttpStatus.OK);
    }

    @GetMapping("/paginationByFechaPrestamoAndGradoAndSeccion/{fechaPrestamoStartStr}/{fechaPrestamoEndStr}/{grado}/{seccion}/{page}")
    public ResponseEntity<Page<PrestamoLibro>> paginationByFechaPrestamoAndGradoAndSeccion(@PathVariable String fechaPrestamoStartStr, @PathVariable String fechaPrestamoEndStr, @PathVariable Integer grado, @PathVariable String seccion, @PathVariable Integer page) {
        return new ResponseEntity<>(prestamoLibroService.paginationByFechaPrestamoAndGradoAndSeccion(fechaPrestamoStartStr, fechaPrestamoEndStr, grado, seccion, PageRequest.of(page, 4)), HttpStatus.OK);
    }
}
