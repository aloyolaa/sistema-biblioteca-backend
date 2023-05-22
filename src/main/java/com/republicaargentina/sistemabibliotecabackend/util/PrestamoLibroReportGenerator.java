package com.republicaargentina.sistemabibliotecabackend.util;

import com.republicaargentina.sistemabibliotecabackend.entity.PrestamoLibro;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PrestamoLibroReportGenerator {
    public JasperPrint getReport(List<PrestamoLibro> prestamosLibros) {
        try {
            File file = ResourceUtils.getFile("src/main/resources/reports/prestamos_libros/reporte_prestamos_libros.jasper");
            File logo = ResourceUtils.getFile("src/main/resources/img/logoColegio.png");
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("logoColegio", new FileInputStream((logo)));
            parameters.put("ds", new JRBeanCollectionDataSource(prestamosLibros));
            return JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        } catch (FileNotFoundException | JRException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public byte[] exportToPdf(List<PrestamoLibro> prestamosLibros) {
        try {
            return JasperExportManager.exportReportToPdf(getReport(prestamosLibros));
        } catch (JRException e) {
            log.error(e.getMessage());
        }
        return new byte[0];
    }

    public byte[] exportToXls(List<PrestamoLibro> prestamosLibros) {
        try {
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            SimpleOutputStreamExporterOutput output = new SimpleOutputStreamExporterOutput(byteArray);
            JRXlsExporter exporter = new JRXlsExporter();
            exporter.setExporterInput(new SimpleExporterInput(getReport(prestamosLibros)));
            exporter.setExporterOutput(output);
            exporter.exportReport();
            output.close();
            return byteArray.toByteArray();
        } catch (JRException e) {
            log.error(e.getMessage());
        }
        return new byte[0];
    }

    public JasperPrint getReportByPrestamoLibro(PrestamoLibro prestamoLibro) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy / MM / dd HH:mm");
        try {
            File file = ResourceUtils.getFile("src/main/resources/reports/prestamos_libros/reporte_prestamo_libros.jasper");
            File logo = ResourceUtils.getFile("src/main/resources/img/logoColegio.png");
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("logoColegio", new FileInputStream((logo)));
            parameters.put("ds", new JRBeanCollectionDataSource(prestamoLibro.getDetalle()));
            parameters.put("nombreDocente", prestamoLibro.getDocente().getNombre() + " " + prestamoLibro.getDocente().getApellido());
            parameters.put("telefono", prestamoLibro.getDocente().getTelefono());
            parameters.put("fechaPrestamo", dateTimeFormatter.format(prestamoLibro.getFechaPrestamo()));
            parameters.put("fechaDevolucion", prestamoLibro.getFechaDevolucion() == null ? null : dateTimeFormatter.format(prestamoLibro.getFechaDevolucion()));
            parameters.put("aula", prestamoLibro.getGrado() + "° \"" + prestamoLibro.getSeccion() + "\"");
            return JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        } catch (FileNotFoundException | JRException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public byte[] exportByPrestamoLibroToPdf(PrestamoLibro prestamoLibro) {
        try {
            return JasperExportManager.exportReportToPdf(getReportByPrestamoLibro(prestamoLibro));
        } catch (JRException e) {
            log.error(e.getMessage());
        }
        return new byte[0];
    }

    public byte[] exportByPrestamoLibroToXls(PrestamoLibro prestamoLibro) {
        try {
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            SimpleOutputStreamExporterOutput output = new SimpleOutputStreamExporterOutput(byteArray);
            JRXlsExporter exporter = new JRXlsExporter();
            exporter.setExporterInput(new SimpleExporterInput(getReportByPrestamoLibro(prestamoLibro)));
            exporter.setExporterOutput(output);
            exporter.exportReport();
            output.close();
            return byteArray.toByteArray();
        } catch (JRException e) {
            log.error(e.getMessage());
        }
        return new byte[0];
    }
}
