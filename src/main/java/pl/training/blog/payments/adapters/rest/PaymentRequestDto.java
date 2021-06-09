package pl.training.blog.payments.adapters.rest;

import lombok.Data;

import java.util.Map;

@Data
public class PaymentRequestDto {

    private String value;
    private Map<String, String> properties;

}