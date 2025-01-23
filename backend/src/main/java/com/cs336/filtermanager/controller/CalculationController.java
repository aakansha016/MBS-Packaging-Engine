package com.cs336.filtermanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/filters")
public class CalculationController {

    @PostMapping("/calculate")
    public ResponseEntity<Map<String, Object>> calculateRate(@RequestBody Map<String, Object> inputs) {
        try {
            double loanAmount = Double.parseDouble(inputs.get("loanAmount").toString());
            double loanTerm = Double.parseDouble(inputs.get("loanTerm").toString());
            double interestRate = Double.parseDouble(inputs.get("interestRate").toString());

            double rate = (loanAmount * interestRate * loanTerm) / 100;

            return ResponseEntity.ok(Map.of("rate", rate));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid input data"));
        }
    }
}
