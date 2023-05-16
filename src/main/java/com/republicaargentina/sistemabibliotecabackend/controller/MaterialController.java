package com.republicaargentina.sistemabibliotecabackend.controller;

import com.republicaargentina.sistemabibliotecabackend.entity.Material;
import com.republicaargentina.sistemabibliotecabackend.service.MaterialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/materiales")
@CrossOrigin(origins = "http://localhost:4200")
public class MaterialController {
    private final MaterialService materialService;

    @GetMapping("/")
    public ResponseEntity<List<Material>> getAll() {
        return new ResponseEntity<>(materialService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<Material> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(materialService.getOne(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Material> save(@Valid @RequestBody Material material) {
        return new ResponseEntity<>(materialService.save(material), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Material> update(@Valid @RequestBody Material material) {
        return new ResponseEntity<>(materialService.update(material), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return new ResponseEntity<>(materialService.delete(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<>(materialService.count(), HttpStatus.OK);
    }

    @GetMapping("/getOneByNombre/{nombre}")
    public ResponseEntity<Material> getOneByNombre(@PathVariable String nombre) {
        return new ResponseEntity<>(materialService.getOneByNombre(nombre), HttpStatus.OK);
    }

    @GetMapping("/getOneByCodigo/{codigo}")
    public ResponseEntity<Material> getOneByCodigo(@PathVariable String codigo) {
        return new ResponseEntity<>(materialService.getOneByCodigo(codigo), HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<Material>> pagination(Pageable pageable) {
        return new ResponseEntity<>(materialService.pagination(pageable), HttpStatus.OK);
    }

    @GetMapping("/paginationByNombre/{nombre}")
    public ResponseEntity<Page<Material>> paginationByNombre(@PathVariable String nombre, Pageable pageable) {
        return new ResponseEntity<>(materialService.paginationByNombre(nombre, pageable), HttpStatus.OK);
    }

    @GetMapping("/paginationByCodigo/{codigo}")
    public ResponseEntity<Page<Material>> paginationByCodigo(@PathVariable String codigo, Pageable pageable) {
        return new ResponseEntity<>(materialService.paginationByCodigo(codigo, pageable), HttpStatus.OK);
    }

    @GetMapping("/paginationByArea/{id}")
    public ResponseEntity<Page<Material>> paginationByArea(@PathVariable Long id, Pageable pageable) {
        return new ResponseEntity<>(materialService.paginationByArea(id, pageable), HttpStatus.OK);
    }

    @GetMapping("/export-pdf")
    public ResponseEntity<Resource> exportPDF() {
        return materialService.exportPDF();
    }
}
