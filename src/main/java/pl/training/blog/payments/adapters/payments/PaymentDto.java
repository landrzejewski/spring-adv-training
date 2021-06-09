package pl.training.blog.payments.adapters.payments;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PaymentDto {

    private String requestId;
    private String value;
    private Map<String, String> properties;
    private String status;

}
