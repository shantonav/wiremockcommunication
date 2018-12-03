package com.example.rest.wiremock.appintgtestingwiremock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;



@SpringBootApplication
public class AppintgtestingWiremockApplication {

	private final Logger log = LoggerFactory.getLogger(AppintgtestingWiremockApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AppintgtestingWiremockApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder){
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception{
		return args -> {
			try {
				String response =
						restTemplate.getForObject("http://10.63.66.102:9190/dispute-service-app/defend/v1/1701/dispute/989", String.class);
				log.info("Response from wiremock : "+ response);
			}catch(HttpClientErrorException ex){
				String respBody = ex.getResponseBodyAsString();
				if (StringUtils.isEmpty(respBody)){
					throw ex;
				}else{
					log.info("(Error)Response from wiremock (strange!!) : "+ respBody);
				}
			}


		};
	}

}
