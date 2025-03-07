package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    private Map<String, String> voucherData;
    private Map<String, String> bankTransferData;

    @BeforeEach
    void setup() {
        voucherData = new HashMap<>();
        voucherData.put("voucherCode", "ESHOP1234ABC5678");

        bankTransferData = new HashMap<>();
        bankTransferData.put("bankName", "BCA");
        bankTransferData.put("referenceCode", "INV123456");
    }

    @Test
    void testCreatePaymentWithVoucherCodeSuccess() {
        Payment payment = new Payment("Voucher", new HashMap<>(voucherData));
        assertEquals(PaymentStatus.SUCCESS, payment.getStatus());
    }

    @Test
    void testCreatePaymentWithBankTransferSuccess() {
        Payment payment = new Payment("Bank Transfer", new HashMap<>(bankTransferData));
        assertEquals(PaymentStatus.SUCCESS, payment.getStatus());
    }

    @Test
    void testCreatePaymentWithInvalidVoucher() {
        Map<String, String> invalidVoucher = new HashMap<>(voucherData);
        invalidVoucher.put("voucherCode", "SHOP1234ABCD5678");
        Payment payment = new Payment("Voucher", invalidVoucher);
        assertEquals(PaymentStatus.REJECTED, payment.getStatus());
    }

    @Test
    void testCreatePaymentWithInvalidBankTransfer() {
        Map<String, String> invalidBankTransfer = new HashMap<>(bankTransferData);
        invalidBankTransfer.remove("bankName");
        Payment payment = new Payment("Bank Transfer", invalidBankTransfer);
        assertEquals(PaymentStatus.REJECTED, payment.getStatus());
    }

    @Test
    void testPaymentWithInvalidVoucherLength() {
        Map<String, String> invalidVoucher = new HashMap<>(voucherData);
        invalidVoucher.put("voucherCode", "ESHOP1234ABCD567");
        Payment payment = new Payment("Voucher", invalidVoucher);
        assertEquals(PaymentStatus.REJECTED, payment.getStatus());
    }

    @Test
    void testPaymentWithInvalidVoucherFormat() {
        Map<String, String> invalidVoucher = new HashMap<>(voucherData);
        invalidVoucher.put("voucherCode", "SHOP1234ABCD5678");
        Payment payment = new Payment("Voucher", invalidVoucher);
        assertEquals(PaymentStatus.REJECTED, payment.getStatus());
    }

    @Test
    void testSetStatusToSuccess() {
        Payment payment = new Payment("Bank Transfer", new HashMap<>(bankTransferData));
        payment.setStatus(PaymentStatus.SUCCESS);
        assertEquals(PaymentStatus.SUCCESS, payment.getStatus());
    }

    @Test
    void testSetStatusToRejected() {
        Payment payment = new Payment("Voucher", new HashMap<>(voucherData));
        payment.setStatus(PaymentStatus.REJECTED);
        assertEquals(PaymentStatus.REJECTED, payment.getStatus());
    }

    @Test
    void testSetStatusToNullThrowsException() {
        Payment payment = new Payment("Voucher", new HashMap<>(voucherData));
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus(null));
    }

    @Test
    void testCreatePaymentWithNullData() {
        Payment payment = new Payment(null, null);
        assertEquals(PaymentStatus.REJECTED, payment.getStatus());
    }
}
