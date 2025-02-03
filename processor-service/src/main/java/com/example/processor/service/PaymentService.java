package com.example.processor.service;

public interface PaymentService {

    /**
     * Charges the given card for the specified amount.
     *
     * @param cardNumber the number of the card to be charged
     * @param expiry the expiry  on the card
     * @param cvv the cvv on the card
     * @param amount the amount to charge on the card
     * @return a Boolean is payment is success or not.
     */
    Boolean processPayment(String cardNumber, String expiry, String cvv, long amount) throws Exception;

}
