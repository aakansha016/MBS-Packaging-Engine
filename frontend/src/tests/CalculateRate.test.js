import React from "react";
import { render, screen, fireEvent } from "@testing-library/react";
import CalculateRate from "../components/CalculateRate";

// Mock fetch API globally
global.fetch = jest.fn(() =>
  Promise.resolve({
    ok: true,
    json: () => Promise.resolve({ rate: 150000.0 }),
  })
);

afterEach(() => {
  jest.clearAllMocks();
  jest.clearAllTimers();
});

describe("CalculateRate Component", () => {
  test("renders input fields and button", () => {
    render(<CalculateRate />);
    expect(screen.getByPlaceholderText(/Loan Amount/i)).toBeInTheDocument();
    expect(screen.getByPlaceholderText(/Loan Term/i)).toBeInTheDocument();
    expect(screen.getByPlaceholderText(/Interest Rate/i)).toBeInTheDocument();
    expect(screen.getByRole("button", { name: /Calculate Rate/i })).toBeInTheDocument();
  });

  test("displays error when fields are empty", () => {
    render(<CalculateRate />);
    fireEvent.click(screen.getByRole("button", { name: /Calculate Rate/i }));
    expect(screen.getByText(/Please fill in all fields./i)).toBeInTheDocument();
  });

  test("makes API call and displays calculated rate", async () => {
    render(<CalculateRate />);
    fireEvent.change(screen.getByPlaceholderText(/Loan Amount/i), {
      target: { value: "100000", name: "loanAmount" },
    });
    fireEvent.change(screen.getByPlaceholderText(/Loan Term/i), {
      target: { value: "30", name: "loanTerm" },
    });
    fireEvent.change(screen.getByPlaceholderText(/Interest Rate/i), {
      target: { value: "5", name: "interestRate" },
    });

    fireEvent.click(screen.getByRole("button", { name: /Calculate Rate/i }));

    const result = await screen.findByText((content) =>
      content.includes("Calculated Rate: 150000")
    );
    expect(result).toBeInTheDocument();
    expect(fetch).toHaveBeenCalledWith(
      "http://localhost:8080/api/filters/calculate",
      expect.objectContaining({
        method: "POST",
      })
    );
  });

  test("displays error if API call fails", async () => {
    fetch.mockImplementationOnce(() => Promise.reject("API is down"));

    render(<CalculateRate />);
    fireEvent.change(screen.getByPlaceholderText(/Loan Amount/i), {
      target: { value: "100000", name: "loanAmount" },
    });
    fireEvent.change(screen.getByPlaceholderText(/Loan Term/i), {
      target: { value: "30", name: "loanTerm" },
    });
    fireEvent.change(screen.getByPlaceholderText(/Interest Rate/i), {
      target: { value: "5", name: "interestRate" },
    });

    fireEvent.click(screen.getByRole("button", { name: /Calculate Rate/i }));

    const error = await screen.findByText(/Error calculating rate. Please try again./i);
    expect(error).toBeInTheDocument();
    expect(fetch).toHaveBeenCalledTimes(1);
  });
});
