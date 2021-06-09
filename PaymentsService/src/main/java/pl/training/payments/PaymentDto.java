package pl.training.payments;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class PaymentDto {

    private Long requestId;
    private String value;
    private Map<String, String> properties;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String status;

}
