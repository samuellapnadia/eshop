package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
public class Payment {
    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;

    public Payment(String method, Map<String, String> paymentData) {
        this.id = UUID.randomUUID().toString();
        this.method = method;
        this.paymentData = paymentData;
        this.status = validatePayment();
    }

    private String validatePayment() {
        if (paymentData == null) {
            return "REJECTED";
        }

        if ("Bank Transfer".equalsIgnoreCase(this.method)) {
            boolean isValid = paymentData.containsKey("bankName")
                    && paymentData.containsKey("referenceCode")
                    && !paymentData.get("bankName").isEmpty()
                    && !paymentData.get("referenceCode").isEmpty();
            return isValid ? "SUCCESS" : "REJECTED";
        }

        if ("Voucher".equalsIgnoreCase(this.method)) {
            String voucherCode = paymentData.get("voucherCode");
            boolean isValid = voucherCode != null &&
                    voucherCode.length() == 16 &&
                    voucherCode.startsWith("ESHOP") &&
                    voucherCode.replaceAll("[^0-9]", "").length() == 8;
            return isValid ? "SUCCESS" : "REJECTED";
        }

        return "REJECTED";
    }

    public void setStatus(String status) {
        if (!"SUCCESS".equals(status) && !"REJECTED".equals(status)) {
            throw new IllegalArgumentException("Invalid payment status: " + status);
        }
        this.status = status;
    }
}
