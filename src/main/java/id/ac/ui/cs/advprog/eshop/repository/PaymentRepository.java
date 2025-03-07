package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentRepository {
    private final List<Payment> paymentList;

    public PaymentRepository() {
        this.paymentList = new ArrayList<>();
    }

    public Payment save(Payment payment) {

            return null;

    }

    public Payment getPayment(String paymentId) {
        return null;
    }

    public List<Payment> getAllPayments() {
        return List.of();
    }
}
