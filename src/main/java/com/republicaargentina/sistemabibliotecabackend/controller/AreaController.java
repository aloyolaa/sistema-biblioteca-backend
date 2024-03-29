package com.republicaargentina.sistemabibliotecabackend.controller;

import com.republicaargentina.sistemabibliotecabackend.entity.Area;
import com.republicaargentina.sistemabibliotecabackend.service.AreaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/areas")
@PreAuthorize("hasAnyRole({'USER', 'ADMIN'})")
@CrossOrigin(origins = {"{ips.permitidas}", "*"})
public class AreaController {
    private final AreaService areaService;

    @GetMapping("/")
    public ResponseEntity<List<Area>> getAll() {
        return new ResponseEntity<>(areaService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<Area> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(areaService.getOne(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Area> save(@Valid @RequestBody Area area) {
        return new ResponseEntity<>(areaService.save(area), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Area> update(@Valid @RequestBody Area area) {
        return new ResponseEntity<>(areaService.update(area), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return new ResponseEntity<>(areaService.delete(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<>(areaService.count(), HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<Area>> pagination(Pageable pageable) {
        return new ResponseEntity<>(areaService.pagination(pageable), HttpStatus.OK);
    }
}
