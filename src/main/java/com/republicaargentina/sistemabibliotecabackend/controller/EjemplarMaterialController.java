package com.republicaargentina.sistemabibliotecabackend.controller;

import com.republicaargentina.sistemabibliotecabackend.entity.EjemplarMaterial;
import com.republicaargentina.sistemabibliotecabackend.service.EjemplarMaterialService;
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
@RequestMapping("/api/v1/ejemplares-materiales")
@CrossOrigin(origins = "http://localhost:4200")
public class EjemplarMaterialController {
    private final EjemplarMaterialService ejemplarMaterialService;

    @GetMapping("/")
    public ResponseEntity<List<EjemplarMaterial>> getAll() {
        return new ResponseEntity<>(ejemplarMaterialService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<EjemplarMaterial>> pagination(Pageable pageable) {
        return new ResponseEntity<>(ejemplarMaterialService.pagination(pageable), HttpStatus.OK);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<EjemplarMaterial> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(ejemplarMaterialService.getOne(id), HttpStatus.OK);
    }

    @GetMapping("/save/{materialId}/{cantidad}")
    public ResponseEntity<Boolean> save(@PathVariable Long materialId, @PathVariable  Integer cantidad) {
        return new ResponseEntity<>(ejemplarMaterialService.save(materialId, cantidad), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<EjemplarMaterial> update(@Valid @RequestBody EjemplarMaterial ejemplarMaterial) {
        return new ResponseEntity<>(ejemplarMaterialService.update(ejemplarMaterial), HttpStatus.CREATED);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<>(ejemplarMaterialService.count(), HttpStatus.OK);
    }

    @GetMapping("/paginationByMaterial/{codigo}")
    public ResponseEntity<Page<EjemplarMaterial>> paginationByMaterial(@PathVariable String codigo, Pageable pageable) {
        return new ResponseEntity<>(ejemplarMaterialService.paginationByMaterial(codigo, pageable), HttpStatus.OK);
    }

    @GetMapping("/paginationByMaterialAndEstado/{codigo}")
    public ResponseEntity<Page<EjemplarMaterial>> paginationByMaterialAndEstado(@PathVariable String codigo, Pageable pageable) {
        return new ResponseEntity<>(ejemplarMaterialService.paginationByMaterialAndEstado(codigo, pageable), HttpStatus.OK);
    }

    @GetMapping("/countByMaterial/{codigo}")
    public ResponseEntity<Long> countByMaterial(@PathVariable String codigo) {
        return new ResponseEntity<>(ejemplarMaterialService.countByMaterial(codigo), HttpStatus.OK);
    }

    @GetMapping("/countByMaterialAndEstado/{codigo}")
    public ResponseEntity<Long> countByMaterialAndEstado(@PathVariable String codigo) {
        return new ResponseEntity<>(ejemplarMaterialService.countByMaterialAndEstado(codigo), HttpStatus.OK);
    }
}
