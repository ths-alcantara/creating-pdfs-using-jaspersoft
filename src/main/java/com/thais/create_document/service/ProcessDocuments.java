package com.thais.create_document.service;

import com.thais.create_document.dto.DocumentInfoDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class ProcessDocuments {

    private final ProcessCSV processCSV;

    public ProcessDocuments(ProcessCSV processCSV) {
        this.processCSV = processCSV;
    }

    public byte[] createDoc(MultipartFile csvFile) throws JRException {

        List<DocumentInfoDTO> documentInfos =
                processCSV.getCsvInfo(csvFile);

        ByteArrayOutputStream zipOutputStream =
                new ByteArrayOutputStream();

        try (ZipOutputStream zip =
                     new ZipOutputStream(zipOutputStream)) {

            LocalDateTime certificateDate = LocalDateTime.now();

            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(
                    getClass().getResourceAsStream(
                            "/certi_design.jasper")
            );

            for (DocumentInfoDTO document : documentInfos) {

                document.setCertificateDate(certificateDate);

                JRBeanCollectionDataSource dataSource =
                        new JRBeanCollectionDataSource(
                                List.of(document));

                Map<String, Object> params = new HashMap<>();

                JasperPrint jasperPrint =
                        JasperFillManager.fillReport(
                                jasperReport,
                                params,
                                dataSource
                        );

                byte[] pdfBytes =
                        JasperExportManager.exportReportToPdf(
                                jasperPrint);
                System.out.println(document.getName());
                ZipEntry zipEntry =
                        new ZipEntry(document.getName() + ".pdf");

                zip.putNextEntry(zipEntry);
                zip.write(pdfBytes);
                zip.closeEntry();
            }
        } catch (IOException e) {
            throw new RuntimeException(
                    "Erro ao montar os documentos.",
                    e
            );
        }
        return zipOutputStream.toByteArray();
    }
}
