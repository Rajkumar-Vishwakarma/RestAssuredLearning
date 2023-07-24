package com.rest;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

public class ValidateJSONSchema {
	
	
	@Test
	public void test() {
		given().baseUri("https://postman-echo.com").log().all().
		when().get("/get").
		then().log().all().
		assertThat().
		statusCode(200).body(matchesJsonSchemaInClasspath("EchoGet.json"));
	}

}
