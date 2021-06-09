package pl.training.blog.payments.adapters.rest;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class PaymentRequestDto {

    private String value;
    private Map<String, String> properties;

}