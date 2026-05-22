package com.thais.create_document.service;

import com.thais.create_document.dto.DocumentInfoDTO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class ProcessDocuments {

    private final ProcessCSV processCSV;

    public ProcessDocuments(ProcessCSV processCSV) {
        this.processCSV = processCSV;
    }

    public byte[] createDoc(MultipartFile csvFile) throws JRException {
        List<DocumentInfoDTO> documentInfos = processCSV.getCsvInfo(csvFile);

        ByteArrayOutputStream zipOutputStream = new ByteArrayOutputStream();

        InputStream reportStream = getClass()
                .getResourceAsStream("/templates/documento.jasper");

        try (ZipOutputStream zip = new ZipOutputStream(zipOutputStream)) {

            for (DocumentInfoDTO document : documentInfos) {

                JRBeanCollectionDataSource dataSource =
                        new JRBeanCollectionDataSource(List.of(document));

                JasperPrint jasperPrint = JasperFillManager.fillReport(
                        reportStream,
                        new HashMap<>(),
                        dataSource
                );

                byte[] pdfBytes =
                        JasperExportManager.exportReportToPdf(jasperPrint);

                ZipEntry zipEntry =
                        new ZipEntry(document.getNome() + ".pdf");

                zip.putNextEntry(zipEntry);
                zip.write(pdfBytes);
                zip.closeEntry();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return zipOutputStream.toByteArray();
    }
}
