package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/leaves")
@CrossOrigin("http://localhost:5173")
public class LeaveController {

    @Autowired
    private LeaveService service;

    @PostMapping("/apply")
    public Leave applyLeave(@RequestBody Leave leave) {
        return service.applyLeave(leave);
    }

    @GetMapping("/{email}")
    public List<Leave> getLeaves(@PathVariable String email) {
        return service.getLeavesByEmail(email);
    }

    @GetMapping("/all")
    public List<Leave> getAllLeaves() {
        return service.getAllLeaves();
    }

    @PutMapping("/{id}/{status}")
    public Leave updateStatus(@PathVariable Long id, @PathVariable String status) {
        return service.updateStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public void deleteLeave(@PathVariable Long id) {
        service.deleteLeave(id);
    }

    @GetMapping("/stats")
    public Map<String, Long> getStats() {
        return service.getLeaveStats();
    }

    @GetMapping("/balance/{email}")
    public int getBalance(@PathVariable String email) {
        return service.getLeaveBalance(email);
    }
}