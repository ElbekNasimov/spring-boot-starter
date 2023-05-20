package com.gayratRdarslari.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


//	har sekundda (berilgan vaqtda) hozirgi vaqtni chiqaradi

//	@Scheduled(fixedRate = 1000L)
//	public void startRate(){
//		System.out.println("New rate " + new Date());
//	}


//	amal bajarib bo'lgach berilgan vaqtdan keyin ish bajaradi
//	@Scheduled(fixedDelay = 5000L)
//	public void startDelay(){
//		System.out.println("New Delay: " + new Date());
//	}


//	using cron (sec min hour day month year - work given date
//	@Scheduled(cron = "0 12 17 * * *")
//	public void startCron(){
//		System.out.println("New Cron: " + new Date());
//	}


//	starting after initialDelay and repeatedly in fixedDelay
//	@Scheduled(initialDelay = 2000L, fixedDelay = 1000L)
//	public void startCron(){
//		System.out.println("New Cron: " + new Date());
//	}

}
