package com.parser.stripe2db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
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
	}

}
