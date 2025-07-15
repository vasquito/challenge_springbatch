package com.accenture.challenge.app.rest;

import com.accenture.challenge.sales.service.generator.ISalesFileGeneratorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;

@Tag(name = "Sales")
@RestController
@RequestMapping("/sales")
@Slf4j
public class SalesController {

    @Autowired
    @Qualifier("salesCsvGeneratorService")
    private ISalesFileGeneratorService salesCsvGeneratorService;

    @Value("${batch.sales.source.file-name}")
    private String sourceFileName;

    @Operation(summary = "Generate and download the CSV file for sales tests")
    @GetMapping("csv/download")
    public ResponseEntity<Resource> downloadCsv(
            @RequestParam(defaultValue = "100000")
            @Parameter(name = "rows",
                       description = "Number of records")
                    long rows) throws Exception {
        File file = salesCsvGeneratorService.generateFile(sourceFileName, rows);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
