package com.cs336.filtermanager.dto;

public class IncomeToDebtRatioRequest {
    private double minRatio;
    private double maxRatio;

    // Getters and Setters
    public double getMinRatio() {
        return minRatio;
    }

    public void setMinRatio(double minRatio) {
        this.minRatio = minRatio;
    }

    public double getMaxRatio() {
        return maxRatio;
    }

    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }
}
