package com.parser.stripe2db.config;

import com.stripe.Stripe;
import com.stripe.model.BalanceTransaction;
import com.stripe.model.BalanceTransactionCollection;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class StripeConfig {

    @Getter
    private String stripeApiKey;

    public StripeConfig (@Value("${stripe.apikey}") String stripeApiKey){
        this.stripeApiKey = stripeApiKey;
        StripeIntegrationDemo(stripeApiKey);
    }

    public void StripeIntegrationDemo(String stripeApiKey){
        Stripe.apiKey = stripeApiKey;
        Map<String, Object> params = new HashMap<>();

        try {
            BalanceTransactionCollection balanceTransactions =
                    BalanceTransaction.list(params);
        } catch (Exception e){
            log.error("Exception During Stripe API Call: " + e);
        }
    }
}
