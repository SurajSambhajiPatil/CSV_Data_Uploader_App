package com.comsense.csvupload.controller;

import com.comsense.csvupload.service.CSVService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class CsvControllerTest {

    @InjectMocks
    private CSVController csvController;

    @Mock
    private CSVService csvService;

    @Test
    void testUploadCsvFile_Success() throws Exception {
        // Mock MultipartFile
        MockMultipartFile file = new MockMultipartFile(
                "file", "test.csv", MediaType.TEXT_PLAIN_VALUE, "EmployeeID,ReviewDate,Goal,Achievement,Rating,Feedback".getBytes());

        // Mock service method
       // when(csvService.processCSVFile(file)).thenReturn("File uploaded successfully");
        doReturn("File uploaded successfully").when(csvService).processCSVFile(file);
        // Call controller method
        ResponseEntity<String> response = csvController.uploadCSV(file);

        // Assertions
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("File uploaded successfully", response.getBody());
    }

	
}
