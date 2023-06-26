package com.republicaargentina.sistemabibliotecabackend.controller;

import com.republicaargentina.sistemabibliotecabackend.entity.PrestamoLibro;
import com.republicaargentina.sistemabibliotecabackend.service.PrestamoLibroService;
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
@RequestMapping("/api/v1/prestamos-libros")
@PreAuthorize("hasAnyRole({'USER', 'ADMIN'})")
@CrossOrigin(origins = {"http://192.168.1.9:80", "*"})
public class PrestamoLibroController {
    private final PrestamoLibroService prestamoLibroService;
    private static final String XLS_CONTENT_TYPE = "Content-Type";
    private static final String XLS_CONTENT_TYPE_VALUE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8";
    private static final String XLS_ATTACHMENT = "attachment";

    @GetMapping("/")
    public ResponseEntity<List<PrestamoLibro>> getAll() {
        return new ResponseEntity<>(prestamoLibroService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<PrestamoLibro> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(prestamoLibroService.getOne(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<PrestamoLibro> save(@Valid @RequestBody PrestamoLibro prestamoLibro) {
        return new ResponseEntity<>(prestamoLibroService.save(prestamoLibro), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return new ResponseEntity<>(prestamoLibroService.delete(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<>(prestamoLibroService.count(), HttpStatus.OK);
    }

    @PutMapping("/close")
    public ResponseEntity<PrestamoLibro> close(@RequestBody PrestamoLibro prestamoLibro) {
        return new ResponseEntity<>(prestamoLibroService.close(prestamoLibro), HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<PrestamoLibro>> pagination(Pageable pageable) {
        return new ResponseEntity<>(prestamoLibroService.pagination(pageable), HttpStatus.OK);
    }

    @GetMapping("/paginationByDocente/{dni}")
    public ResponseEntity<Page<PrestamoLibro>> paginationByDocente(@PathVariable String dni, Pageable pageable) {
        return new ResponseEntity<>(prestamoLibroService.paginationByDocente(dni, pageable), HttpStatus.OK);
    }

    @GetMapping("/paginationByGradoAndSeccion/{grado}/{seccion}")
    public ResponseEntity<Page<PrestamoLibro>> paginationByGradoAndSeccion(@PathVariable Integer grado,
            @PathVariable String seccion, Pageable pageable) {
        return new ResponseEntity<>(prestamoLibroService.paginationByGradoAndSeccion(grado, seccion, pageable),
                HttpStatus.OK);
    }

    @GetMapping("/paginationByDescripcion/{descripcion}")
    public ResponseEntity<Page<PrestamoLibro>> paginationByDescripcion(@PathVariable String descripcion,
            Pageable pageable) {
        return new ResponseEntity<>(prestamoLibroService.paginationByDescripcion(descripcion, pageable), HttpStatus.OK);
    }

    @GetMapping("/paginationByFechaPrestamo/{fechaPrestamoStartStr}/{fechaPrestamoEndStr}")
    public ResponseEntity<Page<PrestamoLibro>> paginationByFechaPrestamo(@PathVariable String fechaPrestamoStartStr,
            @PathVariable String fechaPrestamoEndStr, Pageable pageable) {
        return new ResponseEntity<>(
                prestamoLibroService.paginationByFechaPrestamo(fechaPrestamoStartStr, fechaPrestamoEndStr, pageable),
                HttpStatus.OK);
    }

    @GetMapping("/paginationByFechaPrestamoAndDocente/{fechaPrestamoStartStr}/{fechaPrestamoEndStr}/{docenteId}")
    public ResponseEntity<Page<PrestamoLibro>> paginationByFechaPrestamoAndDocente(
            @PathVariable String fechaPrestamoStartStr, @PathVariable String fechaPrestamoEndStr,
            @PathVariable Long docenteId, Pageable pageable) {
        return new ResponseEntity<>(prestamoLibroService.paginationByFechaPrestamoAndDocente(fechaPrestamoStartStr,
                fechaPrestamoEndStr, docenteId, pageable), HttpStatus.OK);
    }

    @GetMapping("/paginationByFechaPrestamoAndGradoAndSeccion/{fechaPrestamoStartStr}/{fechaPrestamoEndStr}/{grado}/{seccion}")
    public ResponseEntity<Page<PrestamoLibro>> paginationByFechaPrestamoAndGradoAndSeccion(
            @PathVariable String fechaPrestamoStartStr, @PathVariable String fechaPrestamoEndStr,
            @PathVariable Integer grado, @PathVariable String seccion, Pageable pageable) {
        return new ResponseEntity<>(prestamoLibroService.paginationByFechaPrestamoAndGradoAndSeccion(
                fechaPrestamoStartStr, fechaPrestamoEndStr, grado, seccion, pageable), HttpStatus.OK);
    }

    @GetMapping("/paginationByFechaPrestamoAndDescripcion/{fechaPrestamoStartStr}/{fechaPrestamoEndStr}/{descripcion}")
    public ResponseEntity<Page<PrestamoLibro>> paginationByFechaPrestamoAndDescripcion(
            @PathVariable String fechaPrestamoStartStr, @PathVariable String fechaPrestamoEndStr,
            @PathVariable String descripcion, Pageable pageable) {
        return new ResponseEntity<>(prestamoLibroService.paginationByFechaPrestamoAndDescripcion(fechaPrestamoStartStr,
                fechaPrestamoEndStr, descripcion, pageable), HttpStatus.OK);
    }

    @GetMapping("/export-all-pdf")
    public ResponseEntity<byte[]> exportAllToPdf() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("prestamos_libros", "prestamos_libros_" + LocalDate.now() + ".pdf");
        return ResponseEntity.ok().headers(headers).body(prestamoLibroService.exportAllToPdf());
    }

    @GetMapping("/export-all-xls")
    public ResponseEntity<byte[]> exportAllToXls() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(XLS_CONTENT_TYPE, XLS_CONTENT_TYPE_VALUE);
        var contentDisposition = ContentDisposition.builder(XLS_ATTACHMENT)
                .filename("prestamos_libros_" + LocalDate.now() + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok()
                .headers(headers)
                .body(prestamoLibroService.exportAllToXls());
    }

    @GetMapping("/export-by-prestamo-pdf/{id}")
    public ResponseEntity<byte[]> exportByPrestamoLibroToPdf(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("prestamo_libros", "prestamo_libros_" + LocalDate.now() + ".pdf");
        return ResponseEntity.ok().headers(headers).body(prestamoLibroService.exportByPrestamoLibroToPdf(id));
    }

    @GetMapping("/export-by-prestamo-xls/{id}")
    public ResponseEntity<byte[]> exportByPrestamoLibroToXls(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(XLS_CONTENT_TYPE, XLS_CONTENT_TYPE_VALUE);
        var contentDisposition = ContentDisposition.builder(XLS_ATTACHMENT)
                .filename("prestamo_libros_" + LocalDate.now() + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok()
                .headers(headers)
                .body(prestamoLibroService.exportByPrestamoLibroToXls(id));
    }

    @GetMapping("/export-by-docente-pdf/{id}")
    public ResponseEntity<byte[]> exportByDocenteToPdf(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("prestamos_libros_por_docente",
                "prestamo_libros_por_docente_" + LocalDate.now() + ".pdf");
        return ResponseEntity.ok().headers(headers).body(prestamoLibroService.exportByDocenteToPdf(id));
    }

    @GetMapping("/export-by-docente-xls/{id}")
    public ResponseEntity<byte[]> exportByDocenteToXls(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(XLS_CONTENT_TYPE, XLS_CONTENT_TYPE_VALUE);
        var contentDisposition = ContentDisposition.builder(XLS_ATTACHMENT)
                .filename("prestamos_libros_por_docente_" + LocalDate.now() + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok()
                .headers(headers)
                .body(prestamoLibroService.exportByDocenteToXls(id));
    }

    @GetMapping("/export-by-grado-seccion-pdf/{grado}/{seccion}")
    public ResponseEntity<byte[]> exportByGradoAndSeccionToPdf(@PathVariable Integer grado,
            @PathVariable String seccion) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("prestamos_libros_por_grado_seccion",
                "prestamo_libros_por_grado_seccion_" + LocalDate.now() + ".pdf");
        return ResponseEntity.ok().headers(headers)
                .body(prestamoLibroService.exportByGradoAndSeccionToPdf(grado, seccion));
    }

    @GetMapping("/export-by-grado-seccion-xls/{grado}/{seccion}")
    public ResponseEntity<byte[]> exportByGradoAndSeccionToXls(@PathVariable Integer grado,
            @PathVariable String seccion) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(XLS_CONTENT_TYPE, XLS_CONTENT_TYPE_VALUE);
        var contentDisposition = ContentDisposition.builder(XLS_ATTACHMENT)
                .filename("prestamos_libros_por_grado_seccion_" + LocalDate.now() + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok()
                .headers(headers)
                .body(prestamoLibroService.exportByGradoAndSeccionToXls(grado, seccion));
    }
}
