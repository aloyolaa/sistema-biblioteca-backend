package com.republicaargentina.sistemabibliotecabackend.controller;

import com.republicaargentina.sistemabibliotecabackend.entity.Libro;
import com.republicaargentina.sistemabibliotecabackend.service.LibroService;
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
@RequestMapping("/api/v1/libros")
@PreAuthorize("hasAnyRole({'USER', 'ADMIN'})")
@CrossOrigin(origins = "http://localhost:4200")
public class LibroController {
    private final LibroService libroService;
    private static final String XLS_CONTENT_TYPE = "Content-Type";
    private static final String XLS_CONTENT_TYPE_VALUE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8";
    private static final String XLS_ATTACHMENT = "attachment";

    @GetMapping("/")
    public ResponseEntity<List<Libro>> getAll() {
        return new ResponseEntity<>(libroService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<Libro> getOne(@PathVariable Long id) {
        return new ResponseEntity<>(libroService.getOne(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Libro> save(@Valid @RequestBody Libro libro) {
        return new ResponseEntity<>(libroService.save(libro), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Libro> update(@Valid @RequestBody Libro libro) {
        return new ResponseEntity<>(libroService.update(libro), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return new ResponseEntity<>(libroService.delete(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<>(libroService.count(), HttpStatus.OK);
    }

    @GetMapping("/getOneByTitulo/{titulo}")
    public ResponseEntity<Libro> getOneByTitulo(@PathVariable String titulo) {
        return new ResponseEntity<>(libroService.getOneByTitulo(titulo), HttpStatus.OK);
    }

    @GetMapping("/getOneByCodigo/{codigo}")
    public ResponseEntity<Libro> getOneByCodigo(@PathVariable String codigo) {
        return new ResponseEntity<>(libroService.getOneByCodigo(codigo), HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<Libro>> pagination(Pageable pageable) {
        return new ResponseEntity<>(libroService.pagination(pageable), HttpStatus.OK);
    }

    @GetMapping("/paginationByTitulo/{titulo}")
    public ResponseEntity<Page<Libro>> paginationByTitulo(@PathVariable String titulo, Pageable pageable) {
        return new ResponseEntity<>(libroService.paginationByTitulo(titulo, pageable), HttpStatus.OK);
    }

    @GetMapping("/paginationByCodigo/{codigo}")
    public ResponseEntity<Page<Libro>> paginationByCodigo(@PathVariable String codigo, Pageable pageable) {
        return new ResponseEntity<>(libroService.paginationByCodigo(codigo, pageable), HttpStatus.OK);
    }

    @GetMapping("/paginationByArea/{id}")
    public ResponseEntity<Page<Libro>> paginationByArea(@PathVariable Long id, Pageable pageable) {
        return new ResponseEntity<>(libroService.paginationByArea(id, pageable), HttpStatus.OK);
    }

    @GetMapping("/paginationByCategoria/{id}")
    public ResponseEntity<Page<Libro>> paginationByCategoria(@PathVariable Long id, Pageable pageable) {
        return new ResponseEntity<>(libroService.paginationByCategoria(id, pageable), HttpStatus.OK);
    }

    @GetMapping("/paginationByEditorial/{id}")
    public ResponseEntity<Page<Libro>> paginationByEditorial(@PathVariable Long id, Pageable pageable) {
        return new ResponseEntity<>(libroService.paginationByEditorial(id, pageable), HttpStatus.OK);
    }

    @GetMapping("/paginationByAutor/{id}")
    public ResponseEntity<Page<Libro>> paginationByAutor(@PathVariable Long id, Pageable pageable) {
        return new ResponseEntity<>(libroService.paginationByAutor(id, pageable), HttpStatus.OK);
    }

    @GetMapping("/export-all-pdf")
    public ResponseEntity<byte[]> exportAllToPdf() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inventario_libros", "inventario_libros_" + LocalDate.now() + ".pdf");
        return ResponseEntity.ok().headers(headers).body(libroService.exportAllToPdf());
    }

    @GetMapping("/export-all-xls")
    public ResponseEntity<byte[]> exportAllToXls() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(XLS_CONTENT_TYPE, XLS_CONTENT_TYPE_VALUE);
        var contentDisposition = ContentDisposition.builder(XLS_ATTACHMENT)
                .filename("inventario_libros_" + LocalDate.now() + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok()
                .headers(headers)
                .body(libroService.exportAllToXls());
    }

    @GetMapping("/export-by-area-pdf/{id}")
    public ResponseEntity<byte[]> exportByAreaToPdf(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inventario_libros_por_area",
                "inventario_libros_por_area_" + LocalDate.now() + ".pdf");
        return ResponseEntity.ok().headers(headers).body(libroService.exportByAreaToPdf(id));
    }

    @GetMapping("/export-by-area-xls/{id}")
    public ResponseEntity<byte[]> exportByAreaToXls(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(XLS_CONTENT_TYPE, XLS_CONTENT_TYPE_VALUE);
        var contentDisposition = ContentDisposition.builder(XLS_ATTACHMENT)
                .filename("inventario_libros_por_area_" + LocalDate.now() + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok()
                .headers(headers)
                .body(libroService.exportByAreaToXls(id));
    }

    @GetMapping("/export-by-categoria-pdf/{id}")
    public ResponseEntity<byte[]> exportByCategoriaToPdf(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inventario_libros_por_cateegoria",
                "inventario_libros_por_categoria_" + LocalDate.now() + ".pdf");
        return ResponseEntity.ok().headers(headers).body(libroService.exportByCategoriaToPdf(id));
    }

    @GetMapping("/export-by-categoria-xls/{id}")
    public ResponseEntity<byte[]> exportByCategoriaToXls(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(XLS_CONTENT_TYPE, XLS_CONTENT_TYPE_VALUE);
        var contentDisposition = ContentDisposition.builder(XLS_ATTACHMENT)
                .filename("inventario_libros_por_categoria_" + LocalDate.now() + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok()
                .headers(headers)
                .body(libroService.exportByCategoriaToXls(id));
    }

    @GetMapping("/export-by-editorial-pdf/{id}")
    public ResponseEntity<byte[]> exportByEditorialToPdf(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inventario_libros_por_editorial",
                "inventario_libros_por_editorial_" + LocalDate.now() + ".pdf");
        return ResponseEntity.ok().headers(headers).body(libroService.exportByEditorialToPdf(id));
    }

    @GetMapping("/export-by-editorial-xls/{id}")
    public ResponseEntity<byte[]> exportByEditorialToXls(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(XLS_CONTENT_TYPE, XLS_CONTENT_TYPE_VALUE);
        var contentDisposition = ContentDisposition.builder(XLS_ATTACHMENT)
                .filename("inventario_libros_por_editorial_" + LocalDate.now() + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok()
                .headers(headers)
                .body(libroService.exportByEditorialToXls(id));
    }

    @GetMapping("/export-by-autor-pdf/{id}")
    public ResponseEntity<byte[]> exportByAutorToPdf(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inventario_libros_por_autor",
                "inventario_libros_por_autor_" + LocalDate.now() + ".pdf");
        return ResponseEntity.ok().headers(headers).body(libroService.exportByAutorToPdf(id));
    }

    @GetMapping("/export-by-autor-xls/{id}")
    public ResponseEntity<byte[]> exportByAutorToXls(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(XLS_CONTENT_TYPE, XLS_CONTENT_TYPE_VALUE);
        var contentDisposition = ContentDisposition.builder(XLS_ATTACHMENT)
                .filename("inventario_libros_por_autor_" + LocalDate.now() + ".xls").build();
        headers.setContentDisposition(contentDisposition);
        return ResponseEntity.ok()
                .headers(headers)
                .body(libroService.exportByAutorToXls(id));
    }
}
