package com.rest;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static io.restassured.RestAssured.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;

public class Filters {
	
	@BeforeClass
	public void beforeClass() throws FileNotFoundException {
		PrintStream ps = new PrintStream(new File("restassured.log"));		
		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
				setBaseUri("https://postman-echo.com").
				addFilter(new RequestLoggingFilter(ps)).
				addFilter(new ResponseLoggingFilter(ps));
		RestAssured.requestSpecification = requestSpecBuilder.build();
				
		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
				expectStatusCode(200);
		
		RestAssured.responseSpecification = responseSpecBuilder.build();
				
	}
	
	@Test
	public void test() throws FileNotFoundException {
		
		
		given().
		
		when().get("/get").
		then().
		
		assertThat().
		statusCode(200);
	}


}
