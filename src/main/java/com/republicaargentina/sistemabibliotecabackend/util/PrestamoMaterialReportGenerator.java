package com.republicaargentina.sistemabibliotecabackend.util;

import com.republicaargentina.sistemabibliotecabackend.entity.PrestamoMaterial;
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
public class PrestamoMaterialReportGenerator {
    public JasperPrint getReport(List<PrestamoMaterial> prestamosMateriales) {
        try {
            File file = ResourceUtils
                    .getFile("src/main/resources/reports/prestamos_materiales/reporte_prestamos_materiales.jasper");
            File logo = ResourceUtils.getFile("src/main/resources/img/logoColegio.png");
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("logoColegio", new FileInputStream((logo)));
            parameters.put("ds", new JRBeanCollectionDataSource(prestamosMateriales));
            return JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        } catch (FileNotFoundException | JRException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public byte[] exportToPdf(List<PrestamoMaterial> prestamosMateriales) {
        try {
            return JasperExportManager.exportReportToPdf(getReport(prestamosMateriales));
        } catch (JRException e) {
            log.error(e.getMessage());
        }
        return new byte[0];
    }

    public byte[] exportToXls(List<PrestamoMaterial> prestamosMateriales) {
        try {
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            SimpleOutputStreamExporterOutput output = new SimpleOutputStreamExporterOutput(byteArray);
            JRXlsExporter exporter = new JRXlsExporter();
            exporter.setExporterInput(new SimpleExporterInput(getReport(prestamosMateriales)));
            exporter.setExporterOutput(output);
            exporter.exportReport();
            output.close();
            return byteArray.toByteArray();
        } catch (JRException e) {
            log.error(e.getMessage());
        }
        return new byte[0];
    }

    public JasperPrint getReportByPrestamoMaterial(PrestamoMaterial prestamoMaterial) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy / MM / dd HH:mm");
        try {
            File file = ResourceUtils
                    .getFile("src/main/resources/reports/prestamos_materiales/reporte_prestamo_materiales.jasper");
            File logo = ResourceUtils.getFile("src/main/resources/img/logoColegio.png");
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("logoColegio", new FileInputStream((logo)));
            parameters.put("ds", new JRBeanCollectionDataSource(prestamoMaterial.getEjemplares()));
            parameters.put("nombreDocente",
                    prestamoMaterial.getDocente().getNombre() + " " + prestamoMaterial.getDocente().getApellido());
            parameters.put("telefono", prestamoMaterial.getDocente().getTelefono());
            parameters.put("fechaPrestamo", dateTimeFormatter.format(prestamoMaterial.getFechaPrestamo()));
            parameters.put("fechaDevolucion", prestamoMaterial.getFechaDevolucion() == null ? null
                    : dateTimeFormatter.format(prestamoMaterial.getFechaDevolucion()));
            parameters.put("aula", prestamoMaterial.getGrado() + "° \"" + prestamoMaterial.getSeccion() + "\"");
            return JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        } catch (FileNotFoundException | JRException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public byte[] exportByPrestamoMaterialToPdf(PrestamoMaterial prestamoMaterial) {
        try {
            return JasperExportManager.exportReportToPdf(getReportByPrestamoMaterial(prestamoMaterial));
        } catch (JRException e) {
            log.error(e.getMessage());
        }
        return new byte[0];
    }

    public byte[] exportByPrestamoMaterialToXls(PrestamoMaterial prestamoMaterial) {
        try {
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            SimpleOutputStreamExporterOutput output = new SimpleOutputStreamExporterOutput(byteArray);
            JRXlsExporter exporter = new JRXlsExporter();
            exporter.setExporterInput(new SimpleExporterInput(getReportByPrestamoMaterial(prestamoMaterial)));
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
