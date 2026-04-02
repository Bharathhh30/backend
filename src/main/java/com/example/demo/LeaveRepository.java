package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave, Long> {

    List<Leave> findByEmail(String email);

    long countByStatus(String status);

    long countByStartDate(String startDate);

    void deleteById(Long id);
}