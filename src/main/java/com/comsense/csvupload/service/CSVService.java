package com.comsense.csvupload.service;


import com.comsense.csvupload.repo.EmployeeReviewRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.comsense.csvupload.model.EmployeeReview;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CSVService {

    private final EmployeeReviewRepository repository;

    public void processCSVFile(MultipartFile file) {
        try (BufferedReader reader = 
        			 new BufferedReader(
        					 new InputStreamReader(
        							 file.getInputStream(), StandardCharsets.UTF_8));
             CSVParser csvParser = 
            		 new CSVParser(
            				 reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<EmployeeReview> employeeReviews = new ArrayList<>();
            
            for (CSVRecord record : csvParser) {
            	EmployeeReview review = EmployeeReview.builder()
                        .employeeId(record.get("EmployeeID"))
                        .reviewDate(LocalDateTime.parse(record.get("ReviewDate")).atOffset(ZoneOffset.UTC).toLocalDateTime())
                        .goal(record.get("Goal"))
                        .achievement(record.get("Achievement"))
                        .rating(Integer.parseInt(record.get("Rating")))
                        .feedback(record.get("Feedback"))
                        .build();

                employeeReviews.add(review);
            }
            repository.saveAll(employeeReviews);
        } catch (Exception e) {
            throw new RuntimeException("Error processing CSV file: " + e.getMessage());
        }
    }
    
    
    public List<EmployeeReview> getAllEntities() {
         List<EmployeeReview> users = repository.findAll();
         return users;
         
    }
    

}