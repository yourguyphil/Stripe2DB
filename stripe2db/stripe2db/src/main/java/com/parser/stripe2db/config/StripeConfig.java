package com.parser.stripe2db.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StripeConfig {
    @Getter
    private String stripeApiKey;

    public StripeConfig (@Value("${stripe.apikey}") String stripeApiKey){
        this.stripeApiKey = stripeApiKey;
        log.debug("Using API Key: " + stripeApiKey);
    }
}
