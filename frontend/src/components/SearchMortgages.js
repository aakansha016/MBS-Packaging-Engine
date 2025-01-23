import React, { useState } from "react";
import "./SearchMortgages.css";

function SearchMortgages() {
  const [filters, setFilters] = useState({});
  const [results, setResults] = useState([]);
  const [error, setError] = useState(null);

  const API_BASE_URL = process.env.REACT_APP_API_BASE_URL || "http://localhost:8080";

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFilters({ ...filters, [name]: value });
  };

  const handleSearch = async () => {
    try {
      const response = await fetch(`${API_BASE_URL}/api/search`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(filters),
      });

      if (!response.ok) {
        throw new Error("Failed to fetch mortgages");
      }

      const data = await response.json();
      setResults(data);
      setError(null);
    } catch (err) {
      setError("Error fetching mortgages. Please try again.");
      console.error("Error fetching mortgages:", err);
    }
  };

  return (
    <div className="search-container">
      <h2>Search Mortgages</h2>
      <div className="filters">
        <input
          type="text"
          name="loanType"
          placeholder="Loan Type"
          onChange={handleInputChange}
        />
        <input
          type="text"
          name="msamd"
          placeholder="MSAMD"
          onChange={handleInputChange}
        />
        {/* Add more filter inputs as needed */}
      </div>
      <button className="search-btn" onClick={handleSearch}>
        Search
      </button>

      {error && <p className="error-message">{error}</p>}

      <ul className="results-list">
        {results.map((mortgage, index) => (
          <li key={index}>{mortgage.name}</li>
        ))}
      </ul>
    </div>
  );
}

export default SearchMortgages;
