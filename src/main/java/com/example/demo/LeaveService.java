package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.time.LocalDate;

@Service
public class LeaveService {

    @Autowired
    private LeaveRepository repo;

    public Leave applyLeave(Leave leave) {
        leave.setStatus("PENDING");
        return repo.save(leave);
    }

    public List<Leave> getLeavesByEmail(String email) {
        return repo.findByEmail(email);
    }

    public List<Leave> getAllLeaves() {
        return repo.findAll();
    }

    public Leave updateStatus(Long id, String status) {
        Leave leave = repo.findById(id).get();
        leave.setStatus(status);
        return repo.save(leave);
    }

    public void deleteLeave(Long id) {
        repo.deleteById(id);
    }

    // DASHBOARD STATS
    public Map<String, Long> getLeaveStats() {
        Map<String, Long> stats = new HashMap<>();

        String today = LocalDate.now().toString();

        stats.put("total", repo.count());
        stats.put("pending", repo.countByStatus("PENDING"));
        stats.put("approved", repo.countByStatus("APPROVED"));
        stats.put("rejected", repo.countByStatus("REJECTED"));
        stats.put("today", repo.countByStartDate(today));

        return stats;
    }

    // LEAVE BALANCE
    public int getLeaveBalance(String email) {
        List<Leave> leaves = repo.findByEmail(email);
        int approved = 0;

        for (Leave l : leaves) {
            if ("APPROVED".equals(l.getStatus())) {
                approved++;
            }
        }

        return 20 - approved;
    }
}