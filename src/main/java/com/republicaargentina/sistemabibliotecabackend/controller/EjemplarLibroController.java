package com.republicaargentina.sistemabibliotecabackend.controller;

import com.republicaargentina.sistemabibliotecabackend.entity.EjemplarLibro;
import com.republicaargentina.sistemabibliotecabackend.service.EjemplarLibroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ejemplares-libros")
@PreAuthorize("hasAnyRole({'USER', 'ADMIN'})")
@CrossOrigin(origins = "http://localhost:4200")
public class EjemplarLibroController {
    private final EjemplarLibroService ejemplarLibroService;

    @GetMapping("/")
    public ResponseEntity<List<EjemplarLibro>> getAll() {
        return new ResponseEntity<>(ejemplarLibroService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<EjemplarLibro> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(ejemplarLibroService.getOne(id), HttpStatus.OK);
    }

    @GetMapping("/save/{libroId}/{cantidad}")
    public ResponseEntity<Boolean> save(@PathVariable Long libroId, @PathVariable  Integer cantidad) {
        return new ResponseEntity<>(ejemplarLibroService.save(libroId, cantidad), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<EjemplarLibro> update(@Valid @RequestBody EjemplarLibro ejemplarLibro) {
        return new ResponseEntity<>(ejemplarLibroService.update(ejemplarLibro), HttpStatus.CREATED);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<>(ejemplarLibroService.count(), HttpStatus.OK);
    }

    @GetMapping("/getAllByLibroAndEstado/{codigo}/{cantidad}")
    public ResponseEntity<List<EjemplarLibro>> getAllByLibroAndEstado(@PathVariable String codigo, @PathVariable Integer cantidad) {
        return new ResponseEntity<>(ejemplarLibroService.getAllByLibroAndEstado(codigo, cantidad), HttpStatus.OK);
    }

    @GetMapping("/countByLibro/{codigo}")
    public ResponseEntity<Long> countByLibro(@PathVariable String codigo) {
        return new ResponseEntity<>(ejemplarLibroService.countByLibro(codigo), HttpStatus.OK);
    }

    @GetMapping("/countByLibroAndEstado/{codigo}")
    public ResponseEntity<Long> countByLibroAndEstado(@PathVariable String codigo) {
        return new ResponseEntity<>(ejemplarLibroService.countByLibroAndEstado(codigo), HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<EjemplarLibro>> pagination(Pageable pageable) {
        return new ResponseEntity<>(ejemplarLibroService.pagination(pageable), HttpStatus.OK);
    }

    @GetMapping("/paginationByLibro/{codigo}")
    public ResponseEntity<Page<EjemplarLibro>> paginationByLibro(@PathVariable String codigo, Pageable pageable) {
        return new ResponseEntity<>(ejemplarLibroService.paginationByLibro(codigo, pageable), HttpStatus.OK);
    }

    @GetMapping("/export-all-pdf")
    public ResponseEntity<byte[]> exportAllToPdf() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("ejemplares_libros", "ejemplares_libros_" + LocalDate.now() + ".pdf");
        return ResponseEntity.ok().headers(headers).body(ejemplarLibroService.exportAllToPdf());
    }

    @GetMapping("/export-all-xls")
    public ResponseEntity<byte[]> exportAllToXls() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8");
        var contentDisposition = ContentDisposition.builder("attachment")
                .filename("ejemplares_libros_" + LocalDate.now() + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok()
                .headers(headers)
                .body(ejemplarLibroService.exportAllToXls());
    }

    @GetMapping("/export-by-libro-pdf/{id}")
    public ResponseEntity<byte[]> exportByLibroToPdf(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("ejemplares_libros_por_libro", "ejemplares_libros_por_libro_" + LocalDate.now() + ".pdf");
        return ResponseEntity.ok().headers(headers).body(ejemplarLibroService.exportByLibroToPdf(id));
    }

    @GetMapping("/export-by-libro-xls/{id}")
    public ResponseEntity<byte[]> exportByLibroToXls(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8");
        var contentDisposition = ContentDisposition.builder("attachment")
                .filename("ejemplares_libros_por_libro_" + LocalDate.now() + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok()
                .headers(headers)
                .body(ejemplarLibroService.exportByLibroToXls(id));
    }
}
