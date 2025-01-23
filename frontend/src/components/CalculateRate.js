import React, { useState } from "react";
import "./CalculateRate.css"; // Custom styles

function CalculateRate() {
  const [inputs, setInputs] = useState({});
  const [calculatedRate, setCalculatedRate] = useState(null);
  const [error, setError] = useState(null);

  // Use environment variable for API base URL
  const API_BASE_URL = process.env.REACT_APP_API_BASE_URL || "http://localhost:8080";

  const handleInputChange = (e) => {
    setInputs({ ...inputs, [e.target.name]: e.target.value });
  };

  const handleCalculate = async () => {
    // Input validation
    if (!inputs.loanAmount || !inputs.loanTerm || !inputs.interestRate) {
      setError("Please fill in all fields.");
      return;
    }

    if (
      inputs.loanAmount <= 0 ||
      inputs.loanTerm <= 0 ||
      inputs.interestRate <= 0
    ) {
      setError("All values must be greater than zero.");
      return;
    }

    try {
      // Fetch API call
      const response = await fetch(`http://localhost:8080/api/filters/calculate`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(inputs), // Send inputs as JSON
      });

      if (!response.ok) {
        throw new Error(`Error: ${response.status} ${response.statusText}`);
      }

      const data = await response.json();
      setCalculatedRate(data.rate); // Update rate
      setError(null); // Clear any errors
    } catch (err) {
      setError("Error calculating rate. Please try again.");
      console.error("API call error:", err);
    }
  };

  return (
    <div className="calculate-container">
      <h2>Calculate Rate</h2>
      <div className="inputs">
        <input
          type="number"
          name="loanAmount"
          placeholder="Loan Amount"
          onChange={handleInputChange}
        />
        <input
          type="number"
          name="loanTerm"
          placeholder="Loan Term (Years)"
          onChange={handleInputChange}
        />
        <input
          type="number"
          name="interestRate"
          placeholder="Interest Rate (%)"
          onChange={handleInputChange}
        />
      </div>
      <button className="calculate-btn" onClick={handleCalculate}>
        Calculate Rate
      </button>

      {error && <p className="error-message">{error}</p>}

      {calculatedRate !== null && (
        <p className="rate-result">
          Calculated Rate: <strong>{calculatedRate}</strong>
        </p>
      )}
    </div>
  );
}

export default CalculateRate;
