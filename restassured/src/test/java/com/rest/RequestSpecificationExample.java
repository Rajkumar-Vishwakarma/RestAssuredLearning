package com.rest;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationExample {
	
	RequestSpecification requestSpecification;
	
	@BeforeClass
	public void beforeClass() {
		/*requestSpecification = with().
				baseUri("https://api.postman.com").
				header("x-api-key","PMAK-62f4163fce6c223128f8daad-d2c7faefe758bc47248ef5f06b28cecd7b").
				log().all();*/
		
		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
		requestSpecBuilder.setBaseUri("https://api.postman.com");
		requestSpecBuilder.addHeader("x-api-key","PMAK-62f4163fce6c223128f8daad-d2c7faefe758bc47248ef5f06b28cecd7b");
		requestSpecBuilder.log(LogDetail.ALL);
		requestSpecification = requestSpecBuilder.build();
	}
	
	@Test
	public void test() {
		
		//given(requestSpecification) OR
		//given().spec(requestSpecification).
		Response response = given().spec(requestSpecification).
				header("abc","hgf").
				get("/workspaces").then().log().all().extract().response();
		assertThat(response.statusCode(), is(equalTo(200)));
		assertThat(response.path("workspaces[0].name").toString(), equalTo("My Workspace"));
		
	}

}
