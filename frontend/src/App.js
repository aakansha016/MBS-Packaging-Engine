import React from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom"; // Added Link
import HeroSection from "./components/HeroSection";
import SearchMortgages from "./components/SearchMortgages";
import CalculateRate from "./components/CalculateRate";
import PackageMortgages from "./components/PackageMortgages";


function App() {
  return (
    <Router>
      <header className="header">
        <div className="header-left">
          <Link to="/" className="logo-link">
            <img src="/main.png" alt="GAS Mortgage Logo" className="logo" />
            <h1 className="title">GAS Mortgage</h1>
          </Link>
        </div>
        <nav>
          <Link to="/search">Search Mortgages</Link>
          <Link to="/calculate">Calculate Rate</Link>
          <Link to="/package">Package Mortgages</Link>
        </nav>
      </header>
      <Routes>
        <Route path="/" element={<HeroSection />} />
        <Route path="/search" element={<SearchMortgages />} />
        <Route path="/calculate" element={<CalculateRate />} />
        <Route path="/package" element={<PackageMortgages />} />
      </Routes>
    </Router>
  );
}

export default App;
