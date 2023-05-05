package com.republicaargentina.sistemabibliotecabackend.controller;

import com.republicaargentina.sistemabibliotecabackend.entity.Autor;
import com.republicaargentina.sistemabibliotecabackend.service.AutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/autores")
@CrossOrigin(origins = "http://localhost:4200")
public class AutorController {
    private final AutorService autorService;

    @GetMapping("/")
    public ResponseEntity<List<Autor>> getAll() {
        return new ResponseEntity<>(autorService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<Autor> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(autorService.getOne(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Autor> save(@Valid @RequestBody Autor autor) {
        return new ResponseEntity<>(autorService.save(autor), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Autor> update(@Valid @RequestBody Autor autor) {
        return new ResponseEntity<>(autorService.update(autor), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return new ResponseEntity<>(autorService.delete(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<>(autorService.count(), HttpStatus.OK);
    }

    @GetMapping("/getAllByNombreAndApellido/{value}")
    public ResponseEntity<List<Autor>> getAllByNombreAndApellido(@PathVariable String value) {
        return new ResponseEntity<>(autorService.getAllByNombreAndApellido(value, value), HttpStatus.OK);
    }

    @GetMapping("/pagination/{page}")
    public ResponseEntity<Page<Autor>> pagination(@PathVariable Integer page) {
        return new ResponseEntity<>(autorService.pagination(PageRequest.of(page, 4)), HttpStatus.OK);
    }
}
