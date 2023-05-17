package com.republicaargentina.sistemabibliotecabackend.util;

import com.republicaargentina.sistemabibliotecabackend.entity.Material;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class MaterialReportGenerator {
    public JasperPrint getReport(List<Material> materiales) {
        try {
            File file = ResourceUtils.getFile("classpath:reports/materiales/reporte_materiales.jasper");
            File logo = ResourceUtils.getFile("classpath:img/logoColegio.png");
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("logoColegio", new FileInputStream((logo)));
            parameters.put("ds", new JRBeanCollectionDataSource(materiales));
            return JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        } catch (FileNotFoundException | JRException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public byte[] exportToPdf(List<Material> materiales) {
        try {
            return JasperExportManager.exportReportToPdf(getReport(materiales));
        } catch (JRException e) {
            log.error(e.getMessage());
        }
        return new byte[0];
    }

    public byte[] exportToXls(List<Material> materiales) {
        try {
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            SimpleOutputStreamExporterOutput output = new SimpleOutputStreamExporterOutput(byteArray);
            JRXlsExporter exporter = new JRXlsExporter();
            exporter.setExporterInput(new SimpleExporterInput(getReport(materiales)));
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
