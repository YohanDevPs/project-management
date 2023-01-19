package com.api.management;

import com.api.management.db.DB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ManagementApplication {

	public static void main(String[] args) {
		DB.getConnection();
	}
}
