package com.hmhs.voice.idcard;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.hmhs.voice.idcard")
public class IdcardVoiceApplication {

	private static final Logger logger = Logger.getLogger(IdcardVoiceApplication.class.getName());
	
	public static void main(String[] args) {
		logger.info("invoking IdcardVoiceApplication");
		SpringApplication.run(IdcardVoiceApplication.class, args);
	}
}