import React, { useState } from "react";
import "./PackageMortgages.css"; // Add specific styles

function PackageMortgages() {
  const [packagedResults, setPackagedResults] = useState([]);
  const [error, setError] = useState(null);

  // Use the environment variable for the API base URL
  const API_BASE_URL = process.env.REACT_APP_API_BASE_URL || "http://localhost:8080";

  const handlePackage = async () => {
    try {
      const response = await fetch(`${API_BASE_URL}/api/package`, {
        method: "POST",
      });

      if (!response.ok) {
        throw new Error("Failed to package mortgages");
      }

      const data = await response.json();
      setPackagedResults(data); // Save the packaged results
      setError(null); // Clear previous errors
    } catch (err) {
      setError("Error packaging mortgages. Please try again.");
      console.error("Error packaging mortgages:", err); // Log error details for debugging
    }
  };

  return (
    <div className="package-container">
      <h2>Package Mortgages</h2>
      <p>
        Combine eligible mortgages into mortgage-backed securities and view the packaged results.
      </p>
      <button className="package-btn" onClick={handlePackage}>
        Package Mortgages
      </button>

      {error && <p className="error-message">{error}</p>}

      <ul className="results-list">
        {packagedResults.map((result, index) => (
          <li key={index}>{result.name}</li>
        ))}
      </ul>
    </div>
  );
}

export default PackageMortgages;
