package com.cs336.filtermanager;

import com.cs336.filtermanager.repository.DatabaseConnection;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Component
public class FilterManager {

    private final Map<String, String> filters = new HashMap<>();

    public void addFilter(String name, String value) {
        filters.put(name, value);
    }

    public void deleteFilter(String name) {
        filters.remove(name);
    }

    public void clearFilters() {
        filters.clear();
    }

    public void addIncomeToDebtRatioFilter(double minRatio, double maxRatio) {
        String condition = String.format("(a.income / a.loan_amount_000s) BETWEEN %.2f AND %.2f", minRatio, maxRatio);
        filters.put("incomeToDebtRatio", condition);
    }

    public String calculateRate() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = buildQuery();
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            double totalLoanAmount = 0;
            double weightedRateSum = 0;

            while (rs.next()) {
                double loanAmount = rs.getDouble("loan_amount_000s");
                double rateSpread = rs.getDouble("rate_spread");

                if (rateSpread == 0) {
                    rateSpread = computeDefaultRateSpread(rs.getInt("lien_status_id"));
                }

                double rate = 2.33 + rateSpread; // Base rate is 2.33%
                weightedRateSum += loanAmount * rate;
                totalLoanAmount += loanAmount;
            }

            if (totalLoanAmount == 0) {
                return "No loans match the current filters.";
            }

            double weightedAverageRate = weightedRateSum / totalLoanAmount;
            return String.format("Total Loan Amount: $%.2f\nWeighted Average Rate: %.2f%%", totalLoanAmount, weightedAverageRate);

        } catch (SQLException e) {
            return "Error calculating rate: " + e.getMessage();
        }
    }

    private String buildQuery() {
        StringBuilder query = new StringBuilder("SELECT a.rate_spread, a.lien_status_id, a.loan_amount_000s FROM application_new a " +
                "JOIN action_taken ac on ac.action_taken_id = a.action_taken_id " +
                "WHERE ac.action_taken_name = 'Loan originated' AND a.purchaser_type_id IN (0,1,2,3,4,8)");

        for (String condition : filters.values()) {
            query.append(" AND ").append(condition);
        }

        return query.toString();
    }


    private double computeDefaultRateSpread(int lienStatus) {
        return (lienStatus == 1) ? 1.5 : 3.5;
    }

    public void updatePurchaserType() {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            conn.setAutoCommit(false);

            String updateQuery = "UPDATE application_new " +
                    "SET purchaser_type_id = 9 " +
                    "WHERE action_taken_id = ? AND purchaser_type_id IN (0, 1, 2, 3, 4, 8)";
            PreparedStatement stmt = conn.prepareStatement(updateQuery);

            stmt.setInt(1, 1); // Assuming 1 is the ID for 'Loan originated'
            int rowsUpdated = stmt.executeUpdate();
            conn.commit();

            System.out.printf("Successfully updated %d rows. Purchaser type set to 'private securitization'.%n", rowsUpdated);

        } catch (SQLException e) {
            System.err.println("Error updating purchaser type: " + e.getMessage());
            rollbackTransaction(conn);
        } finally {
            closeConnection(conn);
        }
    }

    private void rollbackTransaction(Connection conn) {
        if (conn != null) {
            try {
                conn.rollback();
                System.err.println("Transaction rolled back successfully.");
            } catch (SQLException rollbackEx) {
                System.err.println("Rollback failed: " + rollbackEx.getMessage());
            }
        }
    }

    private void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException closeEx) {
                System.err.println("Error closing connection: " + closeEx.getMessage());
            }
        }
    }
}
