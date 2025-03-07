package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {
    private PaymentRepository paymentRepository;
    private List<Payment> payments;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
        payments = new ArrayList<>();

        Map<String, String> voucherData = Map.of("voucherCode", "ESHOP1234ABC5678");
        Payment successVoucherPayment = new Payment("Voucher", voucherData);
        payments.add(successVoucherPayment);

        Map<String, String> bankData = Map.of("bankName", "", "referenceCode", "INV123456");
        Payment rejectedBankPayment = new Payment("Bank Transfer", bankData);
        payments.add(rejectedBankPayment);

        for (Payment payment : payments) {
            paymentRepository.save(payment);
        }
    }

    @Test
    void testSaveNewPayment() {
        Map<String, String> newPaymentData = Map.of("voucherCode", "ESHOP9999XYZ0000");
        Payment newPayment = new Payment("Voucher", newPaymentData);

        Payment savedPayment = paymentRepository.save(newPayment);
        assertNotNull(savedPayment);
        assertEquals(newPayment.getId(), savedPayment.getId());
        assertEquals("Voucher", savedPayment.getMethod());
        assertEquals(PaymentStatus.SUCCESS, savedPayment.getStatus());
    }

    @Test
    void testSaveUpdatePayment() {
        Payment existingPayment = payments.get(0);
        existingPayment.setStatus(PaymentStatus.REJECTED);

        Payment updatedPayment = paymentRepository.save(existingPayment);
        assertEquals(PaymentStatus.REJECTED, updatedPayment.getStatus());
        assertEquals(existingPayment.getId(), updatedPayment.getId());
    }

    @Test
    void testGetPaymentById() {
        Payment findResult = paymentRepository.getPayment(payments.get(1).getId());

        assertEquals(payments.get(1).getId(), findResult.getId());
        assertEquals(payments.get(1).getMethod(), findResult.getMethod());
        assertEquals(payments.get(1).getStatus(), findResult.getStatus());
    }

    @Test
    void testGetPaymentNotFound() {
        Payment findResult = paymentRepository.getPayment("NON_EXISTENT_ID");
        assertNull(findResult);
    }

    @Test
    void testGetAllPayments() {
        List<Payment> allPayments = paymentRepository.getAllPayments();

        assertEquals(2, allPayments.size());
        assertEquals(payments.get(0).getId(), allPayments.get(0).getId());
        assertEquals(payments.get(1).getId(), allPayments.get(1).getId());
    }
}
