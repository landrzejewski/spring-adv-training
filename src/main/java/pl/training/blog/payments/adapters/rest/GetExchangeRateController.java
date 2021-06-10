package pl.training.blog.payments.adapters.rest;

import lombok.RequiredArgsConstructor;
import org.javamoney.moneta.FastMoney;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.training.blog.jee.ExchangeRate;

@RestController
@RequiredArgsConstructor
public class GetExchangeRateController {

    private final ExchangeRate exchangeRate;

    @GetMapping("exchange-rate")
    public double getExchangeRate(@RequestParam String money) {
        return  exchangeRate.get(FastMoney.parse(money));
    }

}
