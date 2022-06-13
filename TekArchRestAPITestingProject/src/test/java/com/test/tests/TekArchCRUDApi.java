package com.test.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.test.helpers.ReusableMethods;
import com.test.helpers.UserServiceHelper;
import com.test.models.AddUserDataPojoDeSerialize;
import com.test.models.GetUserDataPojo;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class TekArchCRUDApi extends UserServiceHelper {
	@BeforeMethod
	public void baseUri() {
		RestAssured.baseURI = UserServiceHelper.getBaseUri();
	}

	@Test(priority = 1)
	public static void loginToApi() {
		String token = UserServiceHelper.getToken();
		ReusableMethods.getPrettyPrint(response);
		ReusableMethods.getLogIfError(response);
		ReusableMethods.verifyStatusCode(response, 201);
		System.out.println("Extracted token = " + token);
	}

	@Test(priority = 2)
	public void addUserDataWithPojo() {

		Response response = UserServiceHelper.addUser();
		ReusableMethods.getLogStatus(response);
		ReusableMethods.verifyStatusCode(response, 201);
		AddUserDataPojoDeSerialize status1 = response.as(AddUserDataPojoDeSerialize.class); // Deserialization
		System.out.println("response status =" + status1);
		String status = ReusableMethods.getJsonPathData(response, "status");
		Assert.assertEquals(status, "success");
	}

	@Test(priority = 3)
	public void getUserDataWithPojo() {

		Response response = UserServiceHelper.getUser();
		response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("GetUserDataSchema.json"));
		ReusableMethods.getLogHeaders(response);
		GetUserDataPojo[] listOfUsers = response.as(GetUserDataPojo[].class);
		System.out.println("Total number of records = " + listOfUsers.length);
		System.out.println(listOfUsers[0].getAccountno());
		ReusableMethods.getPrettyPrint(response);
		String accountNumber = response.jsonPath().get("[0].accountno");
		System.out.println("First set account number = " + accountNumber); // body.size()
		String userId = response.jsonPath().get("[0].userid");
		String id = response.jsonPath().get("[0].id");
		System.out.println("First entry userdata userid and id is = " + userId + " and " + id);
//		List<GetUserDataPojo> listOfUsers = UserServiceHelper.getUser(); 
//		System.out.println("First account number = " +listOfUsers.get(0).getAccountNo());	
	}

	@Test(priority = 4)
	public void updateUserData() {

		Response response = UserServiceHelper.updateUser();
		ReusableMethods.verifyStatusCode(response, 200);
		System.out.println("Data Updated");
		ReusableMethods.getLogStatus(response);
		String status = ReusableMethods.getJsonPathData(response, "status");
		Assert.assertEquals(status, "success");

	}

	@Test(priority = 5)
	public void deleteUserData() {

		Response response = UserServiceHelper.deleteUser();
		ReusableMethods.getContentType(response);
		ReusableMethods.getLogIfError(response);
		ReusableMethods.getLogStatus(response);
		String status = ReusableMethods.getJsonPathData(response, "status");
		Assert.assertEquals(status, "success");

	}

}
