package com.republicaargentina.sistemabibliotecabackend.controller;

import com.republicaargentina.sistemabibliotecabackend.entity.PrestamoMaterial;
import com.republicaargentina.sistemabibliotecabackend.service.PrestamoMaterialService;
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
@RequestMapping("/api/v1/prestamos-materiales")
@CrossOrigin(origins = "http://localhost:4200")
public class PrestamoMaterialController {
    private final PrestamoMaterialService prestamoMaterialService;

    @GetMapping("/")
    public ResponseEntity<List<PrestamoMaterial>> getAll() {
        return new ResponseEntity<>(prestamoMaterialService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<PrestamoMaterial>> pagination(Pageable pageable) {
        return new ResponseEntity<>(prestamoMaterialService.pagination(pageable), HttpStatus.OK);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<PrestamoMaterial> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(prestamoMaterialService.getOne(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<PrestamoMaterial> save(@Valid @RequestBody PrestamoMaterial prestamoMaterial) {
        return new ResponseEntity<>(prestamoMaterialService.save(prestamoMaterial), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return new ResponseEntity<>(prestamoMaterialService.delete(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<>(prestamoMaterialService.count(), HttpStatus.OK);
    }

    @PutMapping("/close")
    public ResponseEntity<PrestamoMaterial> close(@RequestBody PrestamoMaterial prestamoMaterial) {
        return new ResponseEntity<>(prestamoMaterialService.close(prestamoMaterial), HttpStatus.OK);
    }

    @GetMapping("/paginationByDocente/{dni}/{page}")
    public ResponseEntity<Page<PrestamoMaterial>> paginationByDocente(@PathVariable String dni, @PathVariable Integer page) {
        return new ResponseEntity<>(prestamoMaterialService.paginationByDocente(dni, PageRequest.of(page, 4)), HttpStatus.OK);
    }

    @GetMapping("/paginationByGradoAndSeccion/{grado}/{seccion}/{page}")
    public ResponseEntity<Page<PrestamoMaterial>> paginationByGradoAndSeccion(@PathVariable Integer grado, @PathVariable String seccion, @PathVariable Integer page) {
        return new ResponseEntity<>(prestamoMaterialService.paginationByGradoAndSeccion(grado, seccion, PageRequest.of(page, 4)), HttpStatus.OK);
    }

    @GetMapping("/paginationByFechaPrestamo/{fechaPrestamoStartStr}/{fechaPrestamoEndStr}/{page}")
    public ResponseEntity<Page<PrestamoMaterial>> paginationByFechaPrestamo(@PathVariable String fechaPrestamoStartStr, @PathVariable String fechaPrestamoEndStr, @PathVariable Integer page) {
        return new ResponseEntity<>(prestamoMaterialService.paginationByFechaPrestamo(fechaPrestamoStartStr, fechaPrestamoEndStr, PageRequest.of(page, 4)), HttpStatus.OK);
    }

    @GetMapping("/paginationByFechaPrestamoAndDocente/{fechaPrestamoStartStr}/{fechaPrestamoEndStr}/{docenteId}/{page}")
    public ResponseEntity<Page<PrestamoMaterial>> paginationByFechaPrestamoAndDocente(@PathVariable String fechaPrestamoStartStr, @PathVariable String fechaPrestamoEndStr, @PathVariable Long docenteId, @PathVariable Integer page) {
        return new ResponseEntity<>(prestamoMaterialService.paginationByFechaPrestamoAndDocente(fechaPrestamoStartStr, fechaPrestamoEndStr, docenteId, PageRequest.of(page, 4)), HttpStatus.OK);
    }

    @GetMapping("/paginationByFechaPrestamoAndGradoAndSeccion/{fechaPrestamoStartStr}/{fechaPrestamoEndStr}/{grado}/{seccion}/{page}")
    public ResponseEntity<Page<PrestamoMaterial>> paginationByFechaPrestamoAndGradoAndSeccion(@PathVariable String fechaPrestamoStartStr, @PathVariable String fechaPrestamoEndStr, @PathVariable Integer grado, @PathVariable String seccion, @PathVariable Integer page) {
        return new ResponseEntity<>(prestamoMaterialService.paginationByFechaPrestamoAndGradoAndSeccion(fechaPrestamoStartStr, fechaPrestamoEndStr, grado, seccion, PageRequest.of(page, 4)), HttpStatus.OK);
    }
}
