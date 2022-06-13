package com.test.helpers;

import com.test.constants.Endpoints;
import com.test.models.AddUserPojo;
import com.test.models.DeleteUserDataPojo;
import com.test.models.InvalidLoginPojo;
import com.test.models.LoginPojo;
import com.test.models.UpdateUserDataPojo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import com.test.utils.Utilities;

public class UserServiceHelper {
	static String token;
	protected static Response response;

	public static String getBaseUri() {
		String baseURI = Utilities.getConfigProperty("baseURI");
		return baseURI;
	}

	public static Response LoginToApplication() {
		String username = Utilities.getConfigProperty("username");
		String password = Utilities.getConfigProperty("password");
		LoginPojo obj = new LoginPojo(username, password);
		response = RestAssured.given().contentType(ContentType.JSON).body(obj).log().all().when().post(Endpoints.LOGIN);
		return response;
	}

	public static Response InvalidLoginToApplication() {
		String username = Utilities.getConfigProperty("username");
		String invalidpassword = Utilities.getConfigProperty("invalidpassword");
		InvalidLoginPojo obj = new InvalidLoginPojo(username, invalidpassword);
		response = RestAssured.given().contentType(ContentType.JSON).body(obj).log().all().when().post(Endpoints.LOGIN);
		return response;
	}

	public static String getToken() {
		response = LoginToApplication();
		token = response.jsonPath().get("[0].token");
		return token;
	}

	public static Response getUser() {
		getToken();
		Header header = new Header("token", token);
		response = RestAssured.given().contentType(ContentType.JSON).header(header).log().all().when()
				.get(Endpoints.GET_DATA);
		return response;
	}

	public static Response addUser() {
		getToken();
		Header header = new Header("token", token);
		AddUserPojo user = ReusableMethods.getUserDataToAdd();
		response = RestAssured.given().header(header).contentType(ContentType.JSON).body(user).log().method().when()
				.post(Endpoints.ADD_DATA);
		return response;

	}

	public static Response updateUser() {
		getToken();
		Header header = new Header("token", token);
		UpdateUserDataPojo user = ReusableMethods.getUserDataToUpdate();
		response = RestAssured.given().header(header).contentType(ContentType.JSON).body(user).log().method().when()
				.put(Endpoints.UPDATE_DATA);
		return response;
	}

	public static Response deleteUser() {
		getToken();
		Header header = new Header("token", token);
		DeleteUserDataPojo user = ReusableMethods.getUserDataToDelete();
		response = RestAssured.given().header(header).contentType(ContentType.JSON).body(user).log().headers().when()
				.delete(Endpoints.DELETE_DATA);
		return response;

	}

	public static Response deleteUnAuthorized() {
		getToken();
		Header header = new Header("token", token);
		DeleteUserDataPojo user = ReusableMethods.getUnAuthorizedDataToDelete();
		response = RestAssured.given().header(header).contentType(ContentType.JSON).body(user).log().headers().when()
				.delete(Endpoints.DELETE_DATA);
		return response;

	}

}
