package com.parser.stripe2db;

import com.parser.stripe2db.service.StripeExportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Calendar;

@SpringBootApplication
@Slf4j
@EnableScheduling
public class Stripe2dbApplication {

	public static void main(String[] args) {
		log.info("   _____ _        _            ___  _____  ____   ");
		log.info("  / ____| |      (_)          |__ \\|  __ \\|  _ \\  ");
		log.info(" | (___ | |_ _ __ _ _ __   ___   ) | |  | | |_) | ");
		log.info("  \\___ \\| __| '__| | '_ \\ / _ \\ / /| |  | |  _ <  ");
		log.info("  ____) | |_| |  | | |_) |  __// /_| |__| | |_) | ");
		log.info(" |_____/ \\__|_|  |_| .__/ \\___|____|_____/|____/  ");
		log.info("                   | |                            ");
		log.info("                   |_|                            ");

		SpringApplication.run(Stripe2dbApplication.class, args);
		log.info("App Started!");
	}

	@Autowired
	private StripeExportService stripeExportService;

	int numberOfDataLoads = 0;

	//Hourly Schedule
	@Scheduled(fixedRate = 60*60*1000*6)
	public void run() {
		numberOfDataLoads++;
		log.info("##########################################################" );
		log.info("Running Stripe Export Number: " + numberOfDataLoads);
		log.info("Current time is :: " + Calendar.getInstance().getTime());
		log.info("##########################################################" );
		stripeExportService.exportStripeTransactions();
	}

}
