package com.comsense.csvupload.repo;


import com.comsense.csvupload.model.EmployeeReview;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmployeeRepositoryTest {

    @Mock
    private EmployeeReviewRepository employeeRepository;

    @Test
    void testFindByEmployeeId() {
    	EmployeeReview emp = new EmployeeReview(101, "2024-02-13", "Increase Sales", "Achieved", 5, "Good Work");

        when(employeeRepository.findById(101L)).thenReturn(Optional.of(emp));
    	//employeeRepository.findById(101)
        Optional<EmployeeReview> retrievedEmp = employeeRepository.findById(101L);

        assertTrue(retrievedEmp.isPresent());
        assertEquals("Increase Sales", retrievedEmp.get().getGoal());
        System.out.println("Find Employee Test Passed!");
    }
}

