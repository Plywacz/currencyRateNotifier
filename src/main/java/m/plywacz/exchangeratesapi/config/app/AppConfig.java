package m.plywacz.exchangeratesapi.config.app;

import m.plywacz.exchangeratesapi.model.Currency;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.util.UriTemplate;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/*
Author: BeGieU
Date: 10.02.2020
*/
@Configuration
public class AppConfig {
    private static final Currency BASE_CURRENCY = Currency.valueOf("PLN");

    @Bean
    public URL apiUrl() throws MalformedURLException {
        var uriTemplate = new UriTemplate("https://api.exchangeratesapi.io/latest?symbols=EUR,USD,GBP,CHF,JPY&base={base}");
        var uriVariables = new HashMap<String, String>();
        uriVariables.put("base", BASE_CURRENCY.toString());

        var expandedURI = uriTemplate.expand(uriVariables);
        return expandedURI.toURL();
    }
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
