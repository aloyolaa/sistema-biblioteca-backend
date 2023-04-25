package com.republicaargentina.sistemabibliotecabackend.controller;


import com.republicaargentina.sistemabibliotecabackend.entity.PrestamoMaterial;
import com.republicaargentina.sistemabibliotecabackend.service.PrestamoMaterialService;
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

    @GetMapping("/close")
    public ResponseEntity<PrestamoMaterial> close(@RequestBody PrestamoMaterial prestamoMaterial) {
        return new ResponseEntity<>(prestamoMaterialService.close(prestamoMaterial), HttpStatus.OK);
    }

    @GetMapping("/paginationByFechaPrestamo/{fechaPrestamoStartStr}/{fechaPrestamoEndStr}")
    public ResponseEntity<Page<PrestamoMaterial>> paginationByFechaPrestamo(@PathVariable String fechaPrestamoStartStr, @PathVariable String fechaPrestamoEndStr, Pageable pageable) {
        return new ResponseEntity<>(prestamoMaterialService.paginationByFechaPrestamo(fechaPrestamoStartStr, fechaPrestamoEndStr, pageable), HttpStatus.OK);
    }
}
