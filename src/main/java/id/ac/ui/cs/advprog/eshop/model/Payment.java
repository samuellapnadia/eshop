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

    }

    private String validatePayment() {

        return "";
    }

    public void setStatus(String status) {

    }
}
