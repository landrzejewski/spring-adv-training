package pl.training.payments;

import lombok.Data;
import org.javamoney.moneta.FastMoney;

import java.util.Map;

@Data
public class Payment {

    private String id;
    private String requestId;
    private FastMoney value;
    private Map<String, String> properties;
    private String status;

}
