package com.rest;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.testng.annotations.Test;


public class UploadDownload {
	
	@Test
	public void uploadTest() {
		
		String attributes = "{\"name\":\"upload.txt\",\"author\":\"RK\"}";
		
		given().
		baseUri("https://postman-echo.com").
		multiPart("file", new File("upload.txt")).
		multiPart("attributes", attributes, "application/json").
		log().all().
		when().
		post("/post").
		then().
		log().all();
		
	}
	
	@Test
	public void downloadTestAsByteArray() throws IOException {
		
		byte[] bytes = 
		given().
		baseUri("https://raw.githubusercontent.com").
		log().all().
		when().
		get("/appium-boneyard/sample-code/master/sample-code/apps/ApiDemos/bin/ApiDemos-debug.apk").
		then().
		log().all().extract().response().asByteArray();
		
		OutputStream os = new FileOutputStream(new File("ApiDemos-debug.apk"));
		os.write(bytes);
		os.close();
	}
	
	
	@Test
	public void downloadTestAsInputStream() throws IOException {
		
		InputStream is = 
		given().
		baseUri("https://raw.githubusercontent.com").
		log().all().
		when().
		get("/appium-boneyard/sample-code/master/sample-code/apps/ApiDemos/bin/ApiDemos-debug.apk").
		then().
		log().all().extract().response().asInputStream();
		
		OutputStream os = new FileOutputStream(new File("ApiDemos-debug.apk"));
		byte[] bytes = new byte[is.available()];
		is.read(bytes);
		os.write(bytes);
		os.close();
	}

}
