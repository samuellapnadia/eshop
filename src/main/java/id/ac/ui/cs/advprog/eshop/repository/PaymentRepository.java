package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PaymentRepository {
    private final List<Payment> paymentList;

    public PaymentRepository() {
        this.paymentList = new ArrayList<>();
    }

    public Payment save(Payment payment) {
        Optional<Payment> existingPayment = paymentList.stream()
                .filter(p -> p.getId().equals(payment.getId()))
                .findFirst();

        if (existingPayment.isPresent()) {
            existingPayment.get().setStatus(payment.getStatus());
            return existingPayment.get();
        } else {
            paymentList.add(payment);
            return payment;
        }
    }

    public Payment getPayment(String paymentId) {
        return paymentList.stream()
                .filter(payment -> payment.getId().equals(paymentId))
                .findFirst()
                .orElse(null);
    }

    public List<Payment> getAllPayments() {
        return new ArrayList<>(paymentList);
    }
}
