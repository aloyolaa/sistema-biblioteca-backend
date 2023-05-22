package com.republicaargentina.sistemabibliotecabackend.controller;

import com.republicaargentina.sistemabibliotecabackend.entity.EjemplarMaterial;
import com.republicaargentina.sistemabibliotecabackend.service.EjemplarMaterialService;
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
@RequestMapping("/api/v1/ejemplares-materiales")
@PreAuthorize("hasAnyRole({'USER', 'ADMIN'})")
@CrossOrigin(origins = "http://localhost:4200")
public class EjemplarMaterialController {
    private final EjemplarMaterialService ejemplarMaterialService;

    @GetMapping("/")
    public ResponseEntity<List<EjemplarMaterial>> getAll() {
        return new ResponseEntity<>(ejemplarMaterialService.getAll(), HttpStatus.OK);
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

    @GetMapping("/getAllByMaterialAndEstado/{codigo}/{cantidad}")
    public ResponseEntity<List<EjemplarMaterial>> getAllByMaterialAndEstado(@PathVariable String codigo, @PathVariable Integer cantidad) {
        return new ResponseEntity<>(ejemplarMaterialService.getAllByMaterialAndEstado(codigo, cantidad), HttpStatus.OK);
    }

    @GetMapping("/countByMaterial/{codigo}")
    public ResponseEntity<Long> countByMaterial(@PathVariable String codigo) {
        return new ResponseEntity<>(ejemplarMaterialService.countByMaterial(codigo), HttpStatus.OK);
    }

    @GetMapping("/countByMaterialAndEstado/{codigo}")
    public ResponseEntity<Long> countByMaterialAndEstado(@PathVariable String codigo) {
        return new ResponseEntity<>(ejemplarMaterialService.countByMaterialAndEstado(codigo), HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<EjemplarMaterial>> pagination(Pageable pageable) {
        return new ResponseEntity<>(ejemplarMaterialService.pagination(pageable), HttpStatus.OK);
    }

    @GetMapping("/paginationByMaterial/{codigo}")
    public ResponseEntity<Page<EjemplarMaterial>> paginationByMaterial(@PathVariable String codigo, Pageable pageable) {
        return new ResponseEntity<>(ejemplarMaterialService.paginationByMaterial(codigo, pageable), HttpStatus.OK);
    }

    @GetMapping("/export-all-pdf")
    public ResponseEntity<byte[]> exportAllToPdf() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("ejemplares_materiales", "ejemplares_materiales_" + LocalDate.now() + ".pdf");
        return ResponseEntity.ok().headers(headers).body(ejemplarMaterialService.exportAllToPdf());
    }

    @GetMapping("/export-all-xls")
    public ResponseEntity<byte[]> exportAllToXls() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8");
        var contentDisposition = ContentDisposition.builder("attachment")
                .filename("ejemplares_materiales_" + LocalDate.now() + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok()
                .headers(headers)
                .body(ejemplarMaterialService.exportAllToXls());
    }

    @GetMapping("/export-by-material-pdf/{id}")
    public ResponseEntity<byte[]> exportByMaterialToPdf(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("ejemplares_materiales_por_material", "ejemplares_materiales_por_material_" + LocalDate.now() + ".pdf");
        return ResponseEntity.ok().headers(headers).body(ejemplarMaterialService.exportByMaterialToPdf(id));
    }

    @GetMapping("/export-by-material-xls/{id}")
    public ResponseEntity<byte[]> exportByMaterialToXls(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8");
        var contentDisposition = ContentDisposition.builder("attachment")
                .filename("ejemplares_materiales_por_material_" + LocalDate.now() + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok()
                .headers(headers)
                .body(ejemplarMaterialService.exportByMaterialToXls(id));
    }
}
