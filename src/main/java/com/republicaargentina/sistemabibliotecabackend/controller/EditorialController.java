package com.republicaargentina.sistemabibliotecabackend.controller;

import com.republicaargentina.sistemabibliotecabackend.entity.Editorial;
import com.republicaargentina.sistemabibliotecabackend.service.EditorialService;
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
@RequestMapping("/api/v1/editoriales")
@PreAuthorize("hasAnyRole({'USER', 'ADMIN'})")
@CrossOrigin(origins = {"{ips.permitidas}", "*"})
public class EditorialController {
    private final EditorialService editorialService;

    @GetMapping("/")
    public ResponseEntity<List<Editorial>> getAll() {
        return new ResponseEntity<>(editorialService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<Editorial> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(editorialService.getOne(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Editorial> save(@Valid @RequestBody Editorial editorial) {
        return new ResponseEntity<>(editorialService.save(editorial), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Editorial> update(@Valid @RequestBody Editorial editorial) {
        return new ResponseEntity<>(editorialService.update(editorial), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return new ResponseEntity<>(editorialService.delete(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<>(editorialService.count(), HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<Editorial>> pagination(Pageable pageable) {
        return new ResponseEntity<>(editorialService.pagination(pageable), HttpStatus.OK);
    }
}
