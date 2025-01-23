package com.cs336.filtermanager.controller;

import com.cs336.filtermanager.dto.MortgageRequest;
import com.cs336.filtermanager.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cs336.filtermanager.dto.IncomeToDebtRatioRequest;


import java.util.Map; // Import statement for Map

@RestController
@RequestMapping("/api/filters")
public class FilterController {

    @Autowired
    private FilterService filterService;

    @PostMapping("/add")
    public ResponseEntity<?> addFilter(@RequestBody MortgageRequest request) {
        filterService.addFilter(request);
        return ResponseEntity.ok().body(Map.of("message", "Filter added successfully!"));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFilter(@RequestParam String name) {
        filterService.deleteFilter(name);
        return ResponseEntity.ok().body(Map.of("message", "Filter deleted: " + name));
    }

    @DeleteMapping("/clear")
    public ResponseEntity<?> clearFilters() {
        filterService.clearFilters();
        return ResponseEntity.ok().body(Map.of("message", "All filters cleared."));
    }

    @GetMapping("/calculateRate")
    public ResponseEntity<?> calculateRate() {
        String rateResult = filterService.calculateRate();
        return ResponseEntity.ok().body(Map.of("result", rateResult));
    }

    @PutMapping("/updatePurchaserType")
    public ResponseEntity<?> updatePurchaserType() {
        filterService.updatePurchaserType();
        return ResponseEntity.ok().body(Map.of("message", "Purchaser type updated to 'private securitization'."));
    }

    @PostMapping("/addIncomeToDebtRatio")
    public ResponseEntity<String> addIncomeToDebtRatio(@RequestBody IncomeToDebtRatioRequest request) {
        filterService.addIncomeToDebtRatioFilter(request.getMinRatio(), request.getMaxRatio());
        return ResponseEntity.ok("Income-to-Debt Ratio filter added successfully!");
        }
}
