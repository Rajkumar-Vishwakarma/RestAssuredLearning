package com.rest;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.specification.SpecificationQuerier;

public class ResponseSpecificationExample {
	//ResponseSpecification responseSpecification;
	@BeforeClass
	public void beforeClass() {
		/*requestSpecification = with().
				baseUri("https://api.postman.com").
				header("x-api-key","replaceme").
				log().all();*/
		
		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
		requestSpecBuilder.setBaseUri("https://api.postman.com");
		requestSpecBuilder.addHeader("x-api-key","replaceme");
		requestSpecBuilder.log(LogDetail.ALL);
		RestAssured.requestSpecification = requestSpecBuilder.build();
		/*responseSpecification = RestAssured.expect().
				statusCode(200).
				contentType(ContentType.JSON).log().all();*/
		
		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
				expectStatusCode(200).expectContentType(ContentType.JSON).log(LogDetail.ALL);
		
		RestAssured.responseSpecification = responseSpecBuilder.build();
				
	}
	
	@Test
	public void test() {
		Response response =  get("/workspaces").
				then().spec(responseSpecification).
				extract().response();
		
		assertThat(response.path("workspaces[0].name").toString(), equalTo("My Workspace"));
		
	}
	
	@Test
	public void queryTest() {
		QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(RestAssured.requestSpecification);
		System.out.println(queryableRequestSpecification.getBaseUri());
		System.out.println(queryableRequestSpecification.getHeaders());
	}

}
