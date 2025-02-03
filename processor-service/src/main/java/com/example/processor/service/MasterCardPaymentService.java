package com.example.processor.service;

import org.springframework.stereotype.Service;

@Service
public class MasterCardPaymentService implements PaymentService {

    /**
     *
     * It checks if the card number starts with "5" (a common prefix for MasterCard)
     * and simulates a payment approval for amounts less than 5000.
     *
     * @param cardNumber the number of the card to be charged
     * @param expiry the expiry date on the card
     * @param cvv the CVV on the card
     * @param amount the amount to charge on the card
     * @return true if the payment is successful, false otherwise
     * @throws Exception if the card number is invalid for MasterCard processing
     */
    @Override
    public Boolean processPayment(String cardNumber, String expiry, String cvv, long amount) throws Exception {
        // Validate that the card number starts with "5"
        if (cardNumber == null || !cardNumber.startsWith("5")) {
            throw new Exception("Invalid card number for MasterCard payment.");
        }

        // Log the payment process (or use a logger in a real application)
        System.out.println("Processing MasterCard payment:");
        System.out.println("Card Number: " + cardNumber);
        System.out.println("Expiry: " + expiry);
        System.out.println("CVV: " + cvv);
        System.out.println("Amount: " + amount);

        // Dummy logic: Approve payment if amount is less than 5000, otherwise decline
        if (amount < 5000) {
            System.out.println("Payment approved.");
            return true;
        } else {
            System.out.println("Payment declined.");
            return false;
        }
    }
}
