package id.ac.ui.cs.advprog.eshop.model;

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
        Payment payment = new Payment("Voucher", voucherData);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentWithBankTransferSuccess() {
        Payment payment = new Payment("Bank Transfer", bankTransferData);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentWithInvalidVoucher() {
        voucherData.put("voucherCode", "SHOP1234ABCD5678");
        Payment payment = new Payment("Voucher", voucherData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentWithInvalidBankTransfer() {
        bankTransferData.remove("bankName");
        Payment payment = new Payment("Bank Transfer", bankTransferData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testPaymentWithInvalidVoucherLength() {
        Map<String, String> invalidVoucher = new HashMap<>(voucherData);
        invalidVoucher.put("voucherCode", "ESHOP1234ABCD567");
        Payment payment = new Payment("Voucher", invalidVoucher);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testPaymentWithInvalidVoucherFormat() {
        Map<String, String> invalidVoucher = new HashMap<>(voucherData);
        invalidVoucher.put("voucherCode", "SHOP1234ABCD5678");
        Payment payment = new Payment("Voucher", invalidVoucher);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSetStatusToSuccess() {
        Payment payment = new Payment("Bank Transfer", bankTransferData);
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetStatusToRejected() {
        Payment payment = new Payment("Voucher", voucherData);
        payment.setStatus("REJECTED");
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        Payment payment = new Payment("Bank Transfer", bankTransferData);
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("MEOW"));
    }
}
