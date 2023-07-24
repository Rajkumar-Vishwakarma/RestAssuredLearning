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

public class AutomatePut {
	
	@BeforeClass
	public void beforeClass() {
				
		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
				setBaseUri("https://api.postman.com").
				addHeader("x-api-key","PMAK-62f4163fce6c223128f8daad-d2c7faefe758bc47248ef5f06b28cecd7b").
				log(LogDetail.ALL).
				setContentType(ContentType.JSON);
		RestAssured.requestSpecification = requestSpecBuilder.build();
				
		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
				expectStatusCode(200).expectContentType(ContentType.JSON).log(LogDetail.ALL);
		
		RestAssured.responseSpecification = responseSpecBuilder.build();
				
	}
	
	@Test
	public void test() {
		String workspaceId = "7f47c9d7-1b31-4afb-abfc-373163e9d21e";
		String payload = "{\r\n"
				+ "    \"workspace\": {\r\n"
				+ "        \"id\": \"7f47c9d7-1b31-4afb-abfc-373163e9d21e\",\r\n"
				+ "        \"name\": \"MyFirstPostToPut\",\r\n"
				+ "        \"description\": \"This is updated by put request\"\r\n"
				+ "    }\r\n"
				+ "}";
		//bdd style
		given().
				body(payload).
		when().
		put("/workspaces/").
		then().assertThat().body("workspace.name", equalTo("MyFirstPostToPut"),
				"workspace.id", matchesPattern("^[a-z0-9-]{36}$"),
				"workspace.id", equalTo(workspaceId));
		
		//nonbdd style
		/*
		Response response = with().body(payload).post("/workspaces");
		assertThat(response.path("workspace.name").toString(), equalTo("MyFirstPost1"));
		assertThat(response.path("workspace.id").toString(), matchesPattern("^[a-z0-9-]{36}$"));*/
	}

}
