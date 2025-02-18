package com.comsense.csvupload.service;

import com.comsense.csvupload.model.EmployeeReview;
import com.comsense.csvupload.repo.EmployeeReviewRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CSVServiceTest {

    @Mock
    private EmployeeReviewRepository repository;

    @InjectMocks
    private CSVService csvService;

    @Test
    void testProcessCSVFile_Success() throws Exception {
        // Create a mock CSV file content
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        CSVPrinter csvPrinter = new CSVPrinter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8),
                CSVFormat.DEFAULT.withHeader("EmployeeID", "ReviewDate", "Goal", "Achievement", "Rating", "Feedback"));

        csvPrinter.printRecord("E123", "2024-02-10T12:30:00", "Improve Code Quality", "Refactored old code", "5", "Excellent work");
        csvPrinter.flush();

        // Convert to input stream
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        MultipartFile mockFile = mock(MultipartFile.class);

        when(mockFile.getInputStream()).thenReturn(inputStream);
        when(repository.saveAll(anyList())).thenReturn(Collections.emptyList());

        // Test method execution
        assertDoesNotThrow(() -> csvService.processCSVFile(mockFile));

        // Verify interactions
        List<EmployeeReview> saveAll = verify(repository, times(1)).saveAll(anyList());
        
    }

    @Test
    void testProcessCSVFile_InvalidData() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        CSVPrinter csvPrinter = new CSVPrinter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8),
                CSVFormat.DEFAULT.withHeader("EmployeeID", "ReviewDate", "Goal", "Achievement", "Rating", "Feedback"));

        // Adding an invalid record (missing fields)
        csvPrinter.printRecord("E123", "", "Improve Code Quality", "", "5", "");
        csvPrinter.flush();

        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        MultipartFile mockFile = mock(MultipartFile.class);

        when(mockFile.getInputStream()).thenReturn(inputStream);

        // Ensure exception is thrown for invalid data
        assertDoesNotThrow(() -> csvService.processCSVFile(mockFile));

        verify(repository, never()).saveAll(anyList());
    }
}