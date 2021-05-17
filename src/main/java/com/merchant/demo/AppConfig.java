package com.merchant.demo;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class AppConfig {

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper;
	}

	@Bean("vnSrci")
	public String vietnamSrci() throws IOException {
		ClassPathResource resource = new ClassPathResource("static/vn/index.html");
		Path p = Paths.get(resource.getURI());
		byte[] b = Files.readAllBytes(p);
		return new String(b, Charset.forName("utf-8"));
	}
	
	
	@Bean("fm_yyyyMMddHHmmss")
	public DateTimeFormatter formatter() {
		return DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	}

}
