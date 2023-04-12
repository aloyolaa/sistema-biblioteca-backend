package com.republicaargentina.sistemabibliotecabackend.controller;

import com.republicaargentina.sistemabibliotecabackend.entity.Material;
import com.republicaargentina.sistemabibliotecabackend.service.MaterialService;
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
@RequestMapping("/api/v1/materiales")
@CrossOrigin(origins = "http://localhost:4200")
public class MaterialController {
    private final MaterialService materialService;

    @GetMapping("/")
    public ResponseEntity<List<Material>> getAll() {
        return new ResponseEntity<>(materialService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<Material>> pagination(Pageable pageable) {
        return new ResponseEntity<>(materialService.pagination(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
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

    @GetMapping("/findByNombre/{titulo}")
    public ResponseEntity<List<Material>> getAllByNombre(@PathVariable String nombre) {
        return new ResponseEntity<>(materialService.findByNombreContainsIgnoreCaseOrderByNombreAsc(nombre), HttpStatus.OK);
    }

    @GetMapping("/findByArea/{nombre}")
    public ResponseEntity<Page<Material>> getAllByArea(@PathVariable String nombre, Pageable pageable) {
        return new ResponseEntity<>(materialService.findByAreaNombreIgnoreCaseOrderByNombreAsc(nombre, pageable), HttpStatus.OK);
    }
}
