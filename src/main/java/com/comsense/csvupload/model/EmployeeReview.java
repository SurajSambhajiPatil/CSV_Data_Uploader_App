package com.comsense.csvupload.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


@Data
@Table(name = "EMPLOYEE_REVIEWS_DETAILS", uniqueConstraints = @UniqueConstraint(columnNames = {"EMP_ID", "REVIEW_DATE"}))
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeReview {

 
	@Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name="EMP_ID")
    private String employeeId;

    @Column(nullable = false, name="REVIEW_DATE")
    private LocalDateTime reviewDate;

    private String goal;
    private String achievement;
    private int rating;
    private String feedback;
    
    
    public EmployeeReview(int i, String string, String string2, String string3, int j, String string4) {
		
 	}
}
