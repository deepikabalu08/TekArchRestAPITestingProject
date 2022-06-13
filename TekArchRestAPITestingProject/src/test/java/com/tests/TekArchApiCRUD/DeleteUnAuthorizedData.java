package com.tests.TekArchApiCRUD;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.test.helpers.ReusableMethods;
import com.test.helpers.UserServiceHelper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteUnAuthorizedData {
	@Test
	public void DeleteUnAuthorized() {
	RestAssured.baseURI = UserServiceHelper.getBaseUri();
	Response response = UserServiceHelper.deleteUnAuthorized();
	ReusableMethods.getContentType(response);
	ReusableMethods.getLogIfError(response);
	ReusableMethods.getLogStatus(response);
	String status = ReusableMethods.getJsonPathData(response, "status");
	Assert.assertEquals(status, "success");
	}

}
