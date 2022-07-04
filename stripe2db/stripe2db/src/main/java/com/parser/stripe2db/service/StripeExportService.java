package com.parser.stripe2db.service;

import com.parser.stripe2db.config.DBConfig;
import com.parser.stripe2db.config.StripeConfig;
import com.stripe.Stripe;
import com.stripe.model.BalanceTransaction;
import com.stripe.model.BalanceTransactionCollection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class StripeExportService {
    @Autowired
    private StripeConfig stripeConfig;
    @Autowired
    private DBConfig dbConfig;

    //TODO: Write Stored Procedures for all of this
    public void exportStripeTransactions(){
        Stripe.apiKey = stripeConfig.getStripeApiKey();
        int totalNumberOfTransactions = 0;
        int totalNet = 0;
        Map<String, Object> params = new HashMap<>();

        try {

            BalanceTransactionCollection balanceTransactions = BalanceTransaction.list(params);
            Iterable<BalanceTransaction> balanceTransactionsIterator = balanceTransactions.autoPagingIterable();

            //Clean up old data
            try (Connection con = DriverManager.getConnection(dbConfig.getConnectionString()); Statement stmt = con.createStatement();) {
                String SQL = "TRUNCATE TABLE StripeBalanceTransaction";

                stmt.executeQuery(SQL);
            }

            for (BalanceTransaction balanceTransaction : balanceTransactionsIterator) {
                log.info("***********************************************************");
                log.info("id:" + balanceTransaction.getId());
                log.info("object:" + balanceTransaction.getObject());
                log.info("amount:" + balanceTransaction.getAmount());
                log.info("available_on:" + balanceTransaction.getAvailableOn());
                log.info("created:" + balanceTransaction.getCreated());
                log.info("currency:" + balanceTransaction.getCurrency());
                log.info("description:" + balanceTransaction.getDescription());
                log.info("exchange_rate:" + balanceTransaction.getExchangeRate());
                log.info("fee:" + balanceTransaction.getFee());
                log.info("net:" + balanceTransaction.getNet());
                log.info("reporting_category:" + balanceTransaction.getReportingCategory());
                log.info("source:" + balanceTransaction.getSource());
                log.info("status:" + balanceTransaction.getStatus());
                log.info("type:" + balanceTransaction.getType());

                totalNumberOfTransactions++;
                totalNet+= balanceTransaction.getNet();

                try (Connection con = DriverManager.getConnection(dbConfig.getConnectionString()); Statement stmt = con.createStatement();) {
                    String SQL = "INSERT INTO StripeBalanceTransaction";
                    SQL += " VALUES ("
                            + "'" + balanceTransaction.getId() + "'" + ','
                            + "'" +balanceTransaction.getObject()+ "'" + ','
                            + balanceTransaction.getAmount() + ','
                            + "'" + balanceTransaction.getAvailableOn()+ "'" + ','
                            + "'" + balanceTransaction.getCreated()+ "'" + ','
                            + "'" + balanceTransaction.getCurrency()+ "'" + ','
                            + "'" + balanceTransaction.getDescription().replace('#', ' ').replace('-', ' ')+ "'" + ','
                            + "'" + balanceTransaction.getExchangeRate()+ "'" + ','
                            + balanceTransaction.getFee() + ','
                            + balanceTransaction.getNet() + ','
                            + "'" + balanceTransaction.getReportingCategory()+ "'" + ','
                            + "'" + balanceTransaction.getSource()+ "'" + ','
                            + "'" + balanceTransaction.getStatus()+ "'" + ','
                            + "'" + balanceTransaction.getType()+ "'"
                            + ")";

                    stmt.executeQuery(SQL);
                }
                // Handle any errors that may have occurred.
                catch (SQLException e) {
                    //TODO handle gracefully
                }
            }

            log.info("***********************************************************");
            log.info("Data Export Finished!");
            log.info("***********************************************************");
            log.info("Number Of Transactions Exported: " + totalNumberOfTransactions);
            log.info("Total Net: " + totalNet);

        } catch (Exception e){
            log.error("Exception During Stripe API Call: " + e);
        }
    }
}
