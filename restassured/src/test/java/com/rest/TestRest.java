package com.rest;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.testng.annotations.Test;

import io.restassured.config.LogConfig;
import io.restassured.http.Header;
import io.restassured.http.Headers;

public class TestRest {
	
	
	@Test
	public void test() {
		Set<String> headers = new HashSet<String>();
		headers.add("x-api-key");
		headers.add("Accept");
		given().
				baseUri("https://api.postman.com").
				header("x-api-key","replaceme").
				config(config.logConfig(LogConfig.logConfig().blacklistHeaders(headers))).
				log().all().
				
		when().
				get("/workspaces").
		then().
				log().all().
				assertThat().
				statusCode(200).
				body("workspaces.name", hasItems("My Workspace", "Team Workspace"),
						"workspaces.type", hasItems("personal", "team"));
	}
	
	@Test
	public void test1() {
		Header header = new Header("headerName", "value1");
		Header headerMatch = new Header("x-mock-match-request-headers", "headerName");
		
		Headers headers = new Headers(header,headerMatch);
		given().
				baseUri("https://b66f6377-bd4c-404f-a391-8970c8fdb8b8.mock.pstmn.io").
				headers(headers).
				log().all().
		when().
				get("/get").
		then().
				log().all().
				assertThat().
				statusCode(200);
	}
	
	
	@Test
	public void test2() {
		
		HashMap <String, String> headers = new HashMap<String, String>();
		headers.put("headerName", "value1");
		headers.put("x-mock-match-request-headers", "headerName");
		
		given().
				baseUri("https://b66f6377-bd4c-404f-a391-8970c8fdb8b8.mock.pstmn.io").
				headers(headers).
		when().
				get("/get").
		then().
				log().all().
				assertThat().
				statusCode(200);
	}
	
	@Test
	public void test3() {
		Header header1 = new Header("headerName", "value1");
		Header header2 = new Header("headerName", "value2");
		
		Headers headers = new Headers(header1,header2);
		given().
				baseUri("https://b66f6377-bd4c-404f-a391-8970c8fdb8b8.mock.pstmn.io").
				headers(headers).
				log().all().
		when().
				get("/get").
		then().
				log().all().
				assertThat().
				statusCode(200);
	}
	
	@Test
	public void test4() {
		
		HashMap <String, String> headers = new HashMap<String, String>();
		headers.put("headerName", "value1");
		headers.put("x-mock-match-request-headers", "headerName");
		
		given().
				baseUri("https://b66f6377-bd4c-404f-a391-8970c8fdb8b8.mock.pstmn.io").
				headers(headers).
		when().
				get("/get").
		then().
				log().all().
				assertThat().
				statusCode(200).
				header("X-RateLimit-Limit","120").
				headers("responseHeader", "resValue1",
						"Connection", "keep-alive");
	}
	
	@Test
	public void test5() {
		
		HashMap <String, String> headers = new HashMap<String, String>();
		headers.put("headerName", "value1");
		headers.put("x-mock-match-request-headers", "headerName");
		
		Headers extractedHeaders = given().
				baseUri("https://b66f6377-bd4c-404f-a391-8970c8fdb8b8.mock.pstmn.io").
				headers(headers).
		when().
				get("/get").
		then().
				log().all().
				assertThat().
				statusCode(200).extract().headers();
		
		System.out.println("header name: "+extractedHeaders.get("responseHeader").getName());
		System.out.println("header value: "+extractedHeaders.get("responseHeader").getValue());
		System.out.println("header value: "+extractedHeaders.getValue("responseHeader"));
	}
	
	@Test
	public void test6() {
		
		HashMap <String, String> headers = new HashMap<String, String>();
		headers.put("headerName", "value1");
		headers.put("x-mock-match-request-headers", "headerName");
		
		Headers extractedHeaders = given().
				baseUri("https://b66f6377-bd4c-404f-a391-8970c8fdb8b8.mock.pstmn.io").
				headers(headers).
		when().
				get("/get").
		then().
				log().all().
				assertThat().
				statusCode(200).extract().headers();
		
		List<String> values = extractedHeaders.getValues("multiValueHeader");
		System.out.println(values.size());
		for (String value : values) {
			System.out.println(value);
		}
	}

}
