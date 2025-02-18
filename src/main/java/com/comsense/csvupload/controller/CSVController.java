package com.comsense.csvupload.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;

import com.comsense.csvupload.model.EmployeeReview;
import com.comsense.csvupload.service.CSVService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/csv")
@RequiredArgsConstructor
@Tag(name = "CSV Upload API", description = "Endpoints for uploading and processing CSV files")
public class CSVController {

	private final CSVService csvService;
	
	  @Operation(summary = "Upload CSV File", description = "Uploads a CSV file, processes it, and stores data in MySQL.")
	  @ApiResponses(value = {
	            @ApiResponse(responseCode = "200", description = "File uploaded successfully",
	                    content = @Content(schema = @Schema(implementation = String.class))),
	            @ApiResponse(responseCode = "400", description = "Invalid file format"),
	            @ApiResponse(responseCode = "500", description = "Error processing file")
	    })
	@PostMapping("/upload")
    @PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<String> uploadCSV(@RequestParam("file") MultipartFile file){
		   if (file.isEmpty() || !file.getContentType().equals("text/csv")) {
	            return ResponseEntity.badRequest().body("Invalid file format. Please upload a CSV file.");
	        }
	        csvService.processCSVFile(file);
	        return ResponseEntity.ok("CSV File processed successfully!");
	}
	  
	  @GetMapping("/fetchData")
	    public List<EmployeeReview> fetchData() {
	         List<EmployeeReview> users = csvService.getAllEntities();
	        // users.forEach(user -> System.out.println(user.getId() +" "+user.getEmployeeId()));
             return users;
	    }
	  
	
 }
