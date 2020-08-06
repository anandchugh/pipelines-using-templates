package com.pipelines.multiplicationservice.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pipelines.multiplicationservice.MultiplicationserviceApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MultiplicationserviceApplication.class,
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MultiplicationServiceControllerITTests {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();
	
	@Test
	public void testMultiplicationService() throws JSONException {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		int num1= 2;
		int num2= 4;
		String url = "http://localhost:"+port+"/api/process?num1="+ num1 + "&num2="+ num2;
		ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.GET, entity, String.class);
		String expected = "{\"result\":{\"operation\":\"multiply\",\"value\":8},\"input\":{\"num1\":2,\"num2\":4}}";
		JSONAssert.assertEquals(expected, response.getBody(),false);
	}
	
	@Test
	public void testMultiplicationServiceBadRequest() throws JSONException {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		int num1= 2;
		String url = "http://localhost:"+port+"/api/process?num1="+ num1 + "&num2=abcd";
		ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.GET, entity, String.class);
		String errorMessage = "";
		ObjectMapper objMapper = new ObjectMapper();
		try {
			JsonNode jsonErrorNode = objMapper.readTree(response.getBody()).get("error");
			if(jsonErrorNode != null) {
				errorMessage = jsonErrorNode.asText();
			}
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(errorMessage, "Bad Request");
	}

}
