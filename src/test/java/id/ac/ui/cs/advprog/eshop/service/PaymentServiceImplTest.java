package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    private Order sampleOrder;
    private Map<String, String> validVoucherData;
    private Map<String, String> invalidVoucherData;
    private Map<String, String> validBankTransferData;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        List<Product> productList = new ArrayList<>();
        Product sampleProduct = new Product();
        sampleProduct.setProductId("P123");
        sampleProduct.setProductName("Sample Product");
        sampleProduct.setProductQuantity(1);
        productList.add(sampleProduct);

        sampleOrder = new Order("12345", productList, 1708560000L, "Safira Sudrajat");

        validVoucherData = Map.of("voucherCode", "ESHOP1234ABC5678");
        invalidVoucherData = Map.of("voucherCode", "INVALIDVOUCHER");
        validBankTransferData = Map.of("bankName", "BCA", "referenceCode", "INV123456");
    }

    @Test
    void testAddPaymentWithValidVoucher() {
        Payment expectedPayment = new Payment("Voucher", validVoucherData);
        when(paymentRepository.save(any())).thenReturn(expectedPayment);

        Payment result = paymentService.addPayment(sampleOrder, "Voucher", validVoucherData);

        assertNotNull(result);
        assertEquals(PaymentStatus.SUCCESS, result.getStatus());
        verify(paymentRepository, times(1)).save(any());
    }

    @Test
    void testAddPaymentWithInvalidVoucher() {
        Payment expectedPayment = new Payment("Voucher", invalidVoucherData);
        when(paymentRepository.save(any())).thenReturn(expectedPayment);

        Payment result = paymentService.addPayment(sampleOrder, "Voucher", invalidVoucherData);

        assertNotNull(result);
        assertEquals(PaymentStatus.REJECTED, result.getStatus());
        verify(paymentRepository, times(1)).save(any());
    }

    @Test
    void testSetStatusToSuccess() {
        Payment payment = new Payment("Bank Transfer", validBankTransferData);
        when(paymentRepository.save(any())).thenReturn(payment);

        Payment result = paymentService.setStatus(payment, "SUCCESS");

        assertEquals(PaymentStatus.SUCCESS, result.getStatus());
        verify(paymentRepository, times(1)).save(any());
    }

    @Test
    void testSetStatusToRejected() {
        Payment payment = new Payment("Voucher", validVoucherData);
        when(paymentRepository.save(any())).thenReturn(payment);

        Payment result = paymentService.setStatus(payment, "REJECTED");

        assertEquals(PaymentStatus.REJECTED, result.getStatus());
        verify(paymentRepository, times(1)).save(any());
    }

    @Test
    void testGetPaymentById() {
        Payment payment = new Payment("Voucher", validVoucherData);
        when(paymentRepository.getPayment("123")).thenReturn(payment);

        Payment result = paymentService.getPayment("123");

        assertNotNull(result);
        assertEquals(payment.getId(), result.getId());
        verify(paymentRepository, times(1)).getPayment("123");
    }

    @Test
    void testGetAllPayments() {
        List<Payment> payments = List.of(
                new Payment("Voucher", validVoucherData),
                new Payment("Bank Transfer", validBankTransferData)
        );
        when(paymentRepository.getAllPayments()).thenReturn(payments);

        List<Payment> result = paymentService.getAllPayments();

        assertEquals(2, result.size());
        verify(paymentRepository, times(1)).getAllPayments();
    }
}
