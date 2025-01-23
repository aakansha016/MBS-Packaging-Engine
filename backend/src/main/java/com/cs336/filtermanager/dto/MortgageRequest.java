package com.cs336.filtermanager.dto;

public class MortgageRequest {
    private double income;
    private double loanAmount;
    private String msamd;
    private String applicantSex;
    private String loanType;
    private String ethnicity;
    private String propertyType;
    private String loanPurpose;
    private String ownerOccupancy;
    private double rateSpread;
    private int lienStatus;
    private int hudMedianFamilyIncome;

    // Getters and Setters
    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getMsamd() {
        return msamd;
    }

    public void setMsamd(String msamd) {
        this.msamd = msamd;
    }

    public String getApplicantSex() {
        return applicantSex;
    }

    public void setApplicantSex(String applicantSex) {
        this.applicantSex = applicantSex;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getLoanPurpose() {
        return loanPurpose;
    }

    public void setLoanPurpose(String loanPurpose) {
        this.loanPurpose = loanPurpose;
    }

    public String getOwnerOccupancy() {
        return ownerOccupancy;
    }

    public void setOwnerOccupancy(String ownerOccupancy) {
        this.ownerOccupancy = ownerOccupancy;
    }

    public double getRateSpread() {
        return rateSpread;
    }

    public void setRateSpread(double rateSpread) {
        this.rateSpread = rateSpread;
    }

    public int getLienStatus() {
        return lienStatus;
    }

    public void setLienStatus(int lienStatus) {
        this.lienStatus = lienStatus;
    }

    public int getHudMedianFamilyIncome() {
        return hudMedianFamilyIncome;
    }

    public void setHudMedianFamilyIncome(int hudMedianFamilyIncome) {
        this.hudMedianFamilyIncome = hudMedianFamilyIncome;
    }

    @Override
    public String toString() {
        return "MortgageRequest{" +
                "income=" + income +
                ", loanAmount=" + loanAmount +
                ", msamd='" + msamd + '\'' +
                ", applicantSex='" + applicantSex + '\'' +
                ", loanType='" + loanType + '\'' +
                ", ethnicity='" + ethnicity + '\'' +
                ", propertyType='" + propertyType + '\'' +
                ", loanPurpose='" + loanPurpose + '\'' +
                ", ownerOccupancy='" + ownerOccupancy + '\'' +
                ", rateSpread=" + rateSpread +
                ", lienStatus=" + lienStatus +
                ", hudMedianFamilyIncome=" + hudMedianFamilyIncome +
                '}';
    }
}
