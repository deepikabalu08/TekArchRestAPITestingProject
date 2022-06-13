package com.tests.TekArchApiCRUD;

import org.testng.annotations.Test;

import com.test.helpers.UserServiceHelper;

import io.restassured.RestAssured;
import io.restassured.response.Response;


public class InvalidLogin {
	@Test
	public void invalidLoginToApi() {
		RestAssured.baseURI = UserServiceHelper.getBaseUri();
		Response response = UserServiceHelper.InvalidLoginToApplication();
		response.prettyPrint();
		response.then().log().ifError();
		response.then().assertThat().statusCode(201);
	}
	

}
