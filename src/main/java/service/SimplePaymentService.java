package service;

import config.Log;

public class SimplePaymentService implements PaymentService {
    @Override
    public boolean processPayment(String customerName, double amount) {
        Log.logger.info("Оплата оформляется: " + amount);
        System.out.printf("Обработка оплаты %.2f₽ от клиента %s... Успешно%n", amount, customerName);
        return true;
    }
}
