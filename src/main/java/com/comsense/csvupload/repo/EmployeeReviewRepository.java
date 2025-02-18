package com.comsense.csvupload.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comsense.csvupload.model.EmployeeReview;

public interface EmployeeReviewRepository extends JpaRepository<EmployeeReview, Long> {

}
