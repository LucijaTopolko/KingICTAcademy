package com.academy.kingictacademy;

import org.springframework.boot.SpringApplication;

public class TestKingIctAcademyApplication {

	public static void main(String[] args) {
		SpringApplication.from(KingIctAcademyApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
