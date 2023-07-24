package com.rest;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RequestPayloadAsJsonArray {
	
	@BeforeClass
	public void beforeClass() {
				
		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
				setBaseUri("https://b66f6377-bd4c-404f-a391-8970c8fdb8b8.mock.pstmn.io").
				addHeader("x-mock-match-request-body","true").
				//setConfig(config.encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false))).
				setContentType("application/json; charset=utf-8").
				log(LogDetail.ALL);
		RestAssured.requestSpecification = requestSpecBuilder.build();
				
		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
				expectStatusCode(200).expectContentType(ContentType.JSON).log(LogDetail.ALL);
		
		RestAssured.responseSpecification = responseSpecBuilder.build();
				
	}
	
	@Test
	public void test() {
		
		HashMap<String, String> obj5001 = new HashMap<String, String>();
		obj5001.put("id", "5001");
		obj5001.put("type", "None");
		
		HashMap<String, String> obj5002 = new HashMap<String, String>();
		obj5002.put("id", "5002");
		obj5002.put("type", "Glazed");
		
		List<HashMap<String, String>> jsonList = new ArrayList<HashMap<String, String>>();
		jsonList.add(obj5001);
		jsonList.add(obj5002);
		
		given().body(jsonList).when().post("/post").then().assertThat().body("msg", equalTo("success"));
	}

}
