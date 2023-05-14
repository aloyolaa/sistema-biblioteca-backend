package com.republicaargentina.sistemabibliotecabackend.controller;

import com.republicaargentina.sistemabibliotecabackend.entity.Docente;
import com.republicaargentina.sistemabibliotecabackend.service.DocenteService;
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
@RequestMapping("/api/v1/docentes")
@CrossOrigin(origins = "http://localhost:4200")
public class DocenteController {
    private final DocenteService docenteService;

    @GetMapping("/")
    public ResponseEntity<List<Docente>> getAll() {
        return new ResponseEntity<>(docenteService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<Docente> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(docenteService.getOne(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Docente> save(@Valid @RequestBody Docente docente) {
        return new ResponseEntity<>(docenteService.save(docente), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Docente> update(@Valid @RequestBody Docente docente) {
        return new ResponseEntity<>(docenteService.update(docente), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return new ResponseEntity<>(docenteService.delete(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<>(docenteService.count(), HttpStatus.OK);
    }

    @GetMapping("/getOneByDni/{dni}")
    public ResponseEntity<Docente> getOneByDni(@PathVariable String dni) {
        return new ResponseEntity<>(docenteService.getOneByDni(dni), HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<Docente>> pagination(Pageable pageable) {
        return new ResponseEntity<>(docenteService.pagination(pageable), HttpStatus.OK);
    }
}
