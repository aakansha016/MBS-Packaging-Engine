import React from "react";
import { useNavigate } from "react-router-dom";

function HeroSection() {
  const navigate = useNavigate();

  const handleFindLoan = () => {
    navigate("/search");
  };

  return (
    <div className="hero-section">
      <img
        src="/middle-image.png" // Path to the image
        alt="Explore loan options" // Better alt text
        className="hero-image"
      />
      <h1>You're invited to explore loan options</h1>
      <p>Take the first step of preapproval.</p>
      <button className="find-loan-btn" onClick={handleFindLoan}>
        Find your loan
      </button>
    </div>
  );
}

export default HeroSection;
