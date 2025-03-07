package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
public class Payment {
    private String id;
    private String method;
    private PaymentStatus status;
    private Map<String, String> paymentData;

    public Payment(String method, Map<String, String> paymentData) {
        this.id = UUID.randomUUID().toString();
        this.method = method;
        this.paymentData = paymentData;
        this.status = validatePayment();
    }

    private PaymentStatus validatePayment() {
        if (paymentData == null || method == null) {
            return PaymentStatus.REJECTED;
        }

        if ("Bank Transfer".equalsIgnoreCase(method)) {
            boolean isValid = paymentData.containsKey("bankName")
                    && paymentData.containsKey("referenceCode")
                    && !paymentData.get("bankName").isEmpty()
                    && !paymentData.get("referenceCode").isEmpty();
            return isValid ? PaymentStatus.SUCCESS : PaymentStatus.REJECTED;
        }

        if ("Voucher".equalsIgnoreCase(method)) {
            String voucherCode = paymentData.get("voucherCode");
            boolean isValid = voucherCode != null &&
                    voucherCode.length() == 16 &&
                    voucherCode.startsWith("ESHOP") &&
                    voucherCode.replaceAll("[^0-9]", "").length() == 8;
            return isValid ? PaymentStatus.SUCCESS : PaymentStatus.REJECTED;
        }

        return PaymentStatus.REJECTED;
    }

    public void setStatus(PaymentStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Invalid payment status: null");
        }
        this.status = status;
    }

}
