package com.republicaargentina.sistemabibliotecabackend.controller;

import com.republicaargentina.sistemabibliotecabackend.entity.PrestamoMaterial;
import com.republicaargentina.sistemabibliotecabackend.service.PrestamoMaterialService;
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
@RequestMapping("/api/v1/prestamos-materiales")
@PreAuthorize("hasAnyRole({'USER', 'ADMIN'})")
@CrossOrigin(origins = {"{ips.permitidas}", "*"})
public class PrestamoMaterialController {
    private final PrestamoMaterialService prestamoMaterialService;
    private static final String XLS_CONTENT_TYPE = "Content-Type";
    private static final String XLS_CONTENT_TYPE_VALUE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8";
    private static final String XLS_ATTACHMENT = "attachment";

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
    public ResponseEntity<Page<PrestamoMaterial>> paginationByGradoAndSeccion(@PathVariable Integer grado,
            @PathVariable String seccion, Pageable pageable) {
        return new ResponseEntity<>(prestamoMaterialService.paginationByGradoAndSeccion(grado, seccion, pageable),
                HttpStatus.OK);
    }

    @GetMapping("/paginationByDescripcion/{descripcion}")
    public ResponseEntity<Page<PrestamoMaterial>> paginationByDescripcion(@PathVariable String descripcion,
            Pageable pageable) {
        return new ResponseEntity<>(prestamoMaterialService.paginationByDescripcion(descripcion, pageable),
                HttpStatus.OK);
    }

    @GetMapping("/paginationByFechaPrestamo/{fechaPrestamoStartStr}/{fechaPrestamoEndStr}")
    public ResponseEntity<Page<PrestamoMaterial>> paginationByFechaPrestamo(@PathVariable String fechaPrestamoStartStr,
            @PathVariable String fechaPrestamoEndStr, Pageable pageable) {
        return new ResponseEntity<>(
                prestamoMaterialService.paginationByFechaPrestamo(fechaPrestamoStartStr, fechaPrestamoEndStr, pageable),
                HttpStatus.OK);
    }

    @GetMapping("/paginationByFechaPrestamoAndDocente/{fechaPrestamoStartStr}/{fechaPrestamoEndStr}/{docenteId}")
    public ResponseEntity<Page<PrestamoMaterial>> paginationByFechaPrestamoAndDocente(
            @PathVariable String fechaPrestamoStartStr, @PathVariable String fechaPrestamoEndStr,
            @PathVariable Long docenteId, Pageable pageable) {
        return new ResponseEntity<>(prestamoMaterialService.paginationByFechaPrestamoAndDocente(fechaPrestamoStartStr,
                fechaPrestamoEndStr, docenteId, pageable), HttpStatus.OK);
    }

    @GetMapping("/paginationByFechaPrestamoAndGradoAndSeccion/{fechaPrestamoStartStr}/{fechaPrestamoEndStr}/{grado}/{seccion}")
    public ResponseEntity<Page<PrestamoMaterial>> paginationByFechaPrestamoAndGradoAndSeccion(
            @PathVariable String fechaPrestamoStartStr, @PathVariable String fechaPrestamoEndStr,
            @PathVariable Integer grado, @PathVariable String seccion, Pageable pageable) {
        return new ResponseEntity<>(prestamoMaterialService.paginationByFechaPrestamoAndGradoAndSeccion(
                fechaPrestamoStartStr, fechaPrestamoEndStr, grado, seccion, pageable), HttpStatus.OK);
    }

    @GetMapping("/paginationByFechaPrestamoAndDescripcion/{fechaPrestamoStartStr}/{fechaPrestamoEndStr}/{descripcion}")
    public ResponseEntity<Page<PrestamoMaterial>> paginationByFechaPrestamoAndDescripcion(
            @PathVariable String fechaPrestamoStartStr, @PathVariable String fechaPrestamoEndStr,
            @PathVariable String descripcion, Pageable pageable) {
        return new ResponseEntity<>(prestamoMaterialService.paginationByFechaPrestamoAndDescripcion(
                fechaPrestamoStartStr, fechaPrestamoEndStr, descripcion, pageable), HttpStatus.OK);
    }

    @GetMapping("/export-all-pdf")
    public ResponseEntity<byte[]> exportAllToPdf() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("prestamos_materiales",
                "prestamos_materiales_" + LocalDate.now() + ".pdf");
        return ResponseEntity.ok().headers(headers).body(prestamoMaterialService.exportAllToPdf());
    }

    @GetMapping("/export-all-xls")
    public ResponseEntity<byte[]> exportAllToXls() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(XLS_CONTENT_TYPE, XLS_CONTENT_TYPE_VALUE);
        var contentDisposition = ContentDisposition.builder(XLS_ATTACHMENT)
                .filename("prestamos_materiales_" + LocalDate.now() + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok()
                .headers(headers)
                .body(prestamoMaterialService.exportAllToXls());
    }

    @GetMapping("/export-by-prestamo-pdf/{id}")
    public ResponseEntity<byte[]> exportByPrestamoLibroToPdf(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("prestamo_materiales", "prestamo_materiales_" + LocalDate.now() + ".pdf");
        return ResponseEntity.ok().headers(headers).body(prestamoMaterialService.exportByPrestamoMaterialToPdf(id));
    }

    @GetMapping("/export-by-prestamo-xls/{id}")
    public ResponseEntity<byte[]> exportByPrestamoLibroToXls(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(XLS_CONTENT_TYPE, XLS_CONTENT_TYPE_VALUE);
        var contentDisposition = ContentDisposition.builder(XLS_ATTACHMENT)
                .filename("prestamo_materiales_" + LocalDate.now() + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok()
                .headers(headers)
                .body(prestamoMaterialService.exportByPrestamoMaterialToXls(id));
    }

    @GetMapping("/export-by-docente-pdf/{id}")
    public ResponseEntity<byte[]> exportByDocenteToPdf(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("prestamos_materiales_por_docente",
                "prestamo_materiales_por_docente_" + LocalDate.now() + ".pdf");
        return ResponseEntity.ok().headers(headers).body(prestamoMaterialService.exportByDocenteToPdf(id));
    }

    @GetMapping("/export-by-docente-xls/{id}")
    public ResponseEntity<byte[]> exportByDocenteToXls(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(XLS_CONTENT_TYPE, XLS_CONTENT_TYPE_VALUE);
        var contentDisposition = ContentDisposition.builder(XLS_ATTACHMENT)
                .filename("prestamos_materiales_por_docente_" + LocalDate.now() + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok()
                .headers(headers)
                .body(prestamoMaterialService.exportByDocenteToXls(id));
    }

    @GetMapping("/export-by-grado-seccion-pdf/{grado}/{seccion}")
    public ResponseEntity<byte[]> exportByGradoAndSeccionToPdf(@PathVariable Integer grado,
            @PathVariable String seccion) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("prestamos_materiales_por_grado_seccion",
                "prestamo_materiales_por_grado_seccion_" + LocalDate.now() + ".pdf");
        return ResponseEntity.ok().headers(headers)
                .body(prestamoMaterialService.exportByGradoAndSeccionToPdf(grado, seccion));
    }

    @GetMapping("/export-by-grado-seccion-xls/{grado}/{seccion}")
    public ResponseEntity<byte[]> exportByGradoAndSeccionToXls(@PathVariable Integer grado,
            @PathVariable String seccion) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(XLS_CONTENT_TYPE, XLS_CONTENT_TYPE_VALUE);
        var contentDisposition = ContentDisposition.builder(XLS_ATTACHMENT)
                .filename("prestamos_materiales_por_grado_seccion_" + LocalDate.now() + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok()
                .headers(headers)
                .body(prestamoMaterialService.exportByGradoAndSeccionToXls(grado, seccion));
    }
}
