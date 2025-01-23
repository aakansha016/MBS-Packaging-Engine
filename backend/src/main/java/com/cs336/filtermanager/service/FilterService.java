package com.cs336.filtermanager.service;

import com.cs336.filtermanager.FilterManager;
import com.cs336.filtermanager.dto.MortgageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilterService {

    @Autowired
    private FilterManager filterManager;

    public void addFilter(MortgageRequest request) {
        filterManager.addFilter("income", String.valueOf(request.getIncome()));
        filterManager.addFilter("loanAmount", String.valueOf(request.getLoanAmount()));
        if (request.getMsamd() != null) {
            filterManager.addFilter("msamd", request.getMsamd());
        }
        if (request.getApplicantSex() != null) {
            filterManager.addFilter("applicantSex", request.getApplicantSex());
        }
        if (request.getLoanType() != null) {
            filterManager.addFilter("loanType", request.getLoanType());
        }
        if (request.getEthnicity() != null) {
            filterManager.addFilter("ethnicity", request.getEthnicity());
        }
        if (request.getPropertyType() != null) {
            filterManager.addFilter("propertyType", request.getPropertyType());
        }
        if (request.getLoanPurpose() != null) {
            filterManager.addFilter("loanPurpose", request.getLoanPurpose());
        }
        if (request.getOwnerOccupancy() != null) {
            filterManager.addFilter("ownerOccupancy", request.getOwnerOccupancy());
        }
        filterManager.addFilter("rateSpread", String.valueOf(request.getRateSpread()));
        filterManager.addFilter("lienStatus", String.valueOf(request.getLienStatus()));
        filterManager.addFilter("hudMedianFamilyIncome", String.valueOf(request.getHudMedianFamilyIncome()));
    }

    public void deleteFilter(String name) {
        filterManager.deleteFilter(name);
    }

    public void clearFilters() {
        filterManager.clearFilters();
    }

    public void addIncomeToDebtRatioFilter(double minRatio, double maxRatio) {
        filterManager.addIncomeToDebtRatioFilter(minRatio, maxRatio);
    }

    public String calculateRate() {
        return filterManager.calculateRate();
    }

    public void updatePurchaserType() {
        filterManager.updatePurchaserType();
    }
}
