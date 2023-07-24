package com.rest;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AutomatePost {
	
	@BeforeClass
	public void beforeClass() {
				
		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
				setBaseUri("https://api.postman.com").
				addHeader("x-api-key","replaceme").
				log(LogDetail.ALL).
				setContentType(ContentType.JSON);
		RestAssured.requestSpecification = requestSpecBuilder.build();
				
		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
				expectStatusCode(200).expectContentType(ContentType.JSON).log(LogDetail.ALL);
		
		RestAssured.responseSpecification = responseSpecBuilder.build();
				
	}
	
	@Test
	public void test() {
		//string payload
		String payload = "{\r\n"
				+ "    \"workspace\" : {\r\n"
				+ "        \"name\" : \"MyFirstPost1\",\r\n"
				+ "        \"type\" : \"personal\",\r\n"
				+ "        \"visibility\" : \"only-me\"\r\n"
				+ "    }\r\n"
				+ "}";
		
		//file payload
		File file = new File("src/main/resources/CreateWorkspacePayload.json");
		
		//hashmap payload
		HashMap<String, Object> mainObject = new HashMap<String, Object>();
		HashMap<String, String> nestedObject = new HashMap<String, String>();
		nestedObject.put("name", "NewWork");
		nestedObject.put("type", "personal");
		nestedObject.put("description", "updated");
		mainObject.put("workspace", nestedObject);
		
		//bdd style
		/*given().
				body(payload).
		when().
		post("/workspaces").
		then().assertThat().body("workspace.name", equalTo("MyFirstPost"),
				"workspace.id", matchesPattern("^[a-z0-9-]{36}$"));*/
		
		//nonbdd style
		Response response = with().body(mainObject).post("/workspaces");
		assertThat(response.path("workspace.name").toString(), equalTo("NewWork"));
		assertThat(response.path("workspace.id").toString(), matchesPattern("^[a-z0-9-]{36}$"));
	}

}
