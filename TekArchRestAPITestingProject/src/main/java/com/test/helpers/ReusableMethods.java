package com.test.helpers;

import java.util.concurrent.TimeUnit;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import com.test.models.AddUserPojo;
import com.test.models.DeleteUserDataPojo;
import com.test.models.UpdateUserDataPojo;

public class ReusableMethods {
	public static AddUserPojo add;
	public static UpdateUserDataPojo update;
	public static DeleteUserDataPojo delete;

	public static AddUserPojo getUserDataToAdd() {
		add = new AddUserPojo();
		add.setAccountno("TA-1000990");
		add.setDepartmentno("11");
		add.setSalary("1001");
		add.setPincode("100990");
		return add;
	}

	public static UpdateUserDataPojo getUserDataToUpdate() {
		update = new UpdateUserDataPojo();
		update.setAccountno("TA-1000101");
		update.setDepartmentno(1);
		update.setSalary(100);
		update.setPincode(100100);
		update.setUserid("pHFLMstXPugubAmpW8wL");
		update.setId("w63IKbSYyP4T2OS63guW");
		return update;
	}

	public static DeleteUserDataPojo getUserDataToDelete() {
		delete = new DeleteUserDataPojo();
		delete.setId("w63IKbSYyP4T2OS63guW");
		delete.setUserid("pHFLMstXPugubAmpW8wL");
		return delete;
	}
	public static DeleteUserDataPojo getUnAuthorizedDataToDelete() {
		delete = new DeleteUserDataPojo();
		delete.setId("W8E06ZUqKXKrheNPHJd7");
		delete.setUserid("wV6WMCO2yeVqDezlXdhH");
		return delete;
	}

	public static int getStatusCode(Response response) {
		return response.getStatusCode();
	}

	public static String getContentType(Response response) {
		return response.getContentType();
	}

	public static long getResponseTimeIn(Response response, TimeUnit unit) {
		return response.getTimeIn(unit);
	}

	public static void verifyStatusCode(Response response, int statuscode) {
		response.then().statusCode(statuscode);
	}

	public static String getJsonPathData(Response response, String path) {
		return response.jsonPath().get(path);
	}

	public static String getPrettyPrint(Response response) {
		return response.prettyPrint();
	}

	public static ValidatableResponse getLogIfError(Response response) {
		return response.then().log().ifError();
	}

	public static ValidatableResponse getLogStatus(Response response) {
		return response.then().log().status();
	}

	public static ValidatableResponse getLogHeaders(Response response) {
		return response.then().log().headers();
	}

}
