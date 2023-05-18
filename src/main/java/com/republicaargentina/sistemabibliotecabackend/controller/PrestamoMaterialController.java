package com.republicaargentina.sistemabibliotecabackend.controller;

import com.republicaargentina.sistemabibliotecabackend.entity.PrestamoMaterial;
import com.republicaargentina.sistemabibliotecabackend.service.PrestamoMaterialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @PutMapping("/close")
    public ResponseEntity<PrestamoMaterial> close(@RequestBody PrestamoMaterial prestamoMaterial) {
        return new ResponseEntity<>(prestamoMaterialService.close(prestamoMaterial), HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<PrestamoMaterial>> pagination(Pageable pageable) {
        return new ResponseEntity<>(prestamoMaterialService.pagination(pageable), HttpStatus.OK);
    }

    @GetMapping("/paginationByDocente/{dni}")
    public ResponseEntity<Page<PrestamoMaterial>> paginationByDocente(@PathVariable String dni, Pageable pageable) {
        return new ResponseEntity<>(prestamoMaterialService.paginationByDocente(dni, pageable), HttpStatus.OK);
    }

    @GetMapping("/paginationByGradoAndSeccion/{grado}/{seccion}")
    public ResponseEntity<Page<PrestamoMaterial>> paginationByGradoAndSeccion(@PathVariable Integer grado, @PathVariable String seccion, Pageable pageable) {
        return new ResponseEntity<>(prestamoMaterialService.paginationByGradoAndSeccion(grado, seccion, pageable), HttpStatus.OK);
    }

    @GetMapping("/paginationByFechaPrestamo/{fechaPrestamoStartStr}/{fechaPrestamoEndStr}")
    public ResponseEntity<Page<PrestamoMaterial>> paginationByFechaPrestamo(@PathVariable String fechaPrestamoStartStr, @PathVariable String fechaPrestamoEndStr, Pageable pageable) {
        return new ResponseEntity<>(prestamoMaterialService.paginationByFechaPrestamo(fechaPrestamoStartStr, fechaPrestamoEndStr, pageable), HttpStatus.OK);
    }

    @GetMapping("/paginationByFechaPrestamoAndDocente/{fechaPrestamoStartStr}/{fechaPrestamoEndStr}/{docenteId}")
    public ResponseEntity<Page<PrestamoMaterial>> paginationByFechaPrestamoAndDocente(@PathVariable String fechaPrestamoStartStr, @PathVariable String fechaPrestamoEndStr, @PathVariable Long docenteId, Pageable pageable) {
        return new ResponseEntity<>(prestamoMaterialService.paginationByFechaPrestamoAndDocente(fechaPrestamoStartStr, fechaPrestamoEndStr, docenteId, pageable), HttpStatus.OK);
    }

    @GetMapping("/paginationByFechaPrestamoAndGradoAndSeccion/{fechaPrestamoStartStr}/{fechaPrestamoEndStr}/{grado}/{seccion}")
    public ResponseEntity<Page<PrestamoMaterial>> paginationByFechaPrestamoAndGradoAndSeccion(@PathVariable String fechaPrestamoStartStr, @PathVariable String fechaPrestamoEndStr, @PathVariable Integer grado, @PathVariable String seccion, Pageable pageable) {
        return new ResponseEntity<>(prestamoMaterialService.paginationByFechaPrestamoAndGradoAndSeccion(fechaPrestamoStartStr, fechaPrestamoEndStr, grado, seccion, pageable), HttpStatus.OK);
    }

    @GetMapping("/export-pdf/{id}")
    public ResponseEntity<byte[]> exportToPdf(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("prestamo_materiales", "prestamo_materiales_" + LocalDate.now() + ".pdf");
        return ResponseEntity.ok().headers(headers).body(prestamoMaterialService.exportToPdf(id));
    }

    @GetMapping("/export-xls/{id}")
    public ResponseEntity<byte[]> exportToXls(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8");
        var contentDisposition = ContentDisposition.builder("attachment")
                .filename("prestamo_materiales_" + LocalDate.now() + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok()
                .headers(headers)
                .body(prestamoMaterialService.exportToXls(id));
    }
}
