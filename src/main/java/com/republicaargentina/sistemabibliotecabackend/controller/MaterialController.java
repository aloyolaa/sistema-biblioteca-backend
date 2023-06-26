package com.republicaargentina.sistemabibliotecabackend.controller;

import com.republicaargentina.sistemabibliotecabackend.entity.Material;
import com.republicaargentina.sistemabibliotecabackend.service.MaterialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/materiales")
@PreAuthorize("hasAnyRole({'USER', 'ADMIN'})")
@CrossOrigin(origins = {"http://192.168.1.9:80", "*"})
public class MaterialController {
    private final MaterialService materialService;

    @GetMapping("/")
    public ResponseEntity<List<Material>> getAll() {
        return new ResponseEntity<>(materialService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getOne/{id}")
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

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<>(materialService.count(), HttpStatus.OK);
    }

    @GetMapping("/getOneByNombre/{nombre}")
    public ResponseEntity<Material> getOneByNombre(@PathVariable String nombre) {
        return new ResponseEntity<>(materialService.getOneByNombre(nombre), HttpStatus.OK);
    }

    @GetMapping("/getOneByCodigo/{codigo}")
    public ResponseEntity<Material> getOneByCodigo(@PathVariable String codigo) {
        return new ResponseEntity<>(materialService.getOneByCodigo(codigo), HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<Material>> pagination(Pageable pageable) {
        return new ResponseEntity<>(materialService.pagination(pageable), HttpStatus.OK);
    }

    @GetMapping("/paginationByNombre/{nombre}")
    public ResponseEntity<Page<Material>> paginationByNombre(@PathVariable String nombre, Pageable pageable) {
        return new ResponseEntity<>(materialService.paginationByNombre(nombre, pageable), HttpStatus.OK);
    }

    @GetMapping("/paginationByCodigo/{codigo}")
    public ResponseEntity<Page<Material>> paginationByCodigo(@PathVariable String codigo, Pageable pageable) {
        return new ResponseEntity<>(materialService.paginationByCodigo(codigo, pageable), HttpStatus.OK);
    }

    @GetMapping("/paginationByArea/{id}")
    public ResponseEntity<Page<Material>> paginationByArea(@PathVariable Long id, Pageable pageable) {
        return new ResponseEntity<>(materialService.paginationByArea(id, pageable), HttpStatus.OK);
    }

    @GetMapping("/export-pdf-1")
    public ResponseEntity<Resource> exportPDF() {
        return materialService.exportPDF();
    }

    @GetMapping("/export-all-pdf")
    public ResponseEntity<byte[]> exportAllToPdf() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inventario_materiales",
                "inventario_materiales_" + LocalDate.now() + ".pdf");
        return ResponseEntity.ok().headers(headers).body(materialService.exportAllToPdf());
    }

    @GetMapping("/export-all-xls")
    public ResponseEntity<byte[]> exportAllToXls() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8");
        var contentDisposition = ContentDisposition.builder("attachment")
                .filename("inventario_materiales_" + LocalDate.now() + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok()
                .headers(headers)
                .body(materialService.exportAllToXls());
    }

    @GetMapping("/export-by-area-pdf/{id}")
    public ResponseEntity<byte[]> exportByAreaToPdf(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inventario_materiales_por_area",
                "inventario_materiales_por_area_" + LocalDate.now() + ".pdf");
        return ResponseEntity.ok().headers(headers).body(materialService.exportByAreaToPdf(id));
    }

    @GetMapping("/export-by-area-xls/{id}")
    public ResponseEntity<byte[]> exportByAreaToXls(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8");
        var contentDisposition = ContentDisposition.builder("attachment")
                .filename("inventario_materiales_por_area_" + LocalDate.now() + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok()
                .headers(headers)
                .body(materialService.exportByAreaToXls(id));
    }
}
