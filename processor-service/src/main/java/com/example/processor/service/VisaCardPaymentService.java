package com.example.processor.service;

import org.springframework.stereotype.Service;

@Service
public class VisaCardPaymentService implements PaymentService {

    /**
     *
     * It checks if the card number starts with "4" (a common prefix for Visa)
     * and simulates a payment approval for amounts less than 10000.
     *
     * @param cardNumber the number of the card to be charged
     * @param expiry the expiry date on the card
     * @param cvv the CVV on the card
     * @param amount the amount to charge on the card
     * @return true if the payment is successful, false otherwise
     * @throws Exception if the card number is invalid for Visa processing
     */
    @Override
    public Boolean processPayment(String cardNumber, String expiry, String cvv, long amount) throws Exception {
        // Validate that the card number starts with "4"
        if (cardNumber == null || !cardNumber.startsWith("4")) {
            throw new Exception("Invalid card number for Visa payment.");
        }

        // Log the payment process (in a real application, consider using a proper logger)
        System.out.println("Processing Visa payment:");
        System.out.println("Card Number: " + cardNumber);
        System.out.println("Expiry: " + expiry);
        System.out.println("CVV: " + cvv);
        System.out.println("Amount: " + amount);

        // Dummy logic: Approve payment if amount is less than 10000, otherwise decline
        if (amount < 10000) {
            System.out.println("Payment approved.");
            return true;
        } else {
            System.out.println("Payment declined.");
            return false;
        }
    }
}
