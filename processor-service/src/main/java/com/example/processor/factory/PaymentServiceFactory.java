package com.example.processor.factory;

import com.example.processor.service.PaymentService;
import com.example.processor.service.MasterCardPaymentService;
import com.example.processor.service.VisaCardPaymentService;

public class PaymentServiceFactory {

    /**
     * Returns an instance of PaymentService based on the card number.
     *
     * @param serviceProvider to determine the card type
     * @return PaymentService implementation for Visa or MasterCard
     * @throws Exception if the card number is null, empty, or unsupported
     */
    public static PaymentService getPaymentService(String serviceProvider) throws Exception {
        if (serviceProvider == null || serviceProvider.isEmpty()) {
            throw new Exception("Card number cannot be null or empty");
        }

        if (serviceProvider.equals("master")) {
            return new VisaCardPaymentService();
        } else if (serviceProvider.equals("visa")) {
            return new MasterCardPaymentService();
        } else {
            throw new Exception("Unsupported card type");
        }
    }
}
