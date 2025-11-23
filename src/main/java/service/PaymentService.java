package service;

public interface PaymentService {
    boolean processPayment(String customerName, double amount);
}
