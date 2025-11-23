package service;

import config.Log;

public class SimplePaymentService implements PaymentService {
    @Override
    public boolean processPayment(String customerName, double amount) {
        Log.logger.info("Processing payment: " + amount);
        System.out.printf("Processing payment %.2f$ from customer %s... Success%n", amount, customerName);
        return true;
    }
}