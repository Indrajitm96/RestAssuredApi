package com.crm.genericUtilities;

import io.restassured.response.Response;
/**
 * this class contains rest assured specific re-usable methods
 * @author INDRAJIT
 *
 */

public class RestAssuredLibrary {

	/**
	 * this method will get the json data through json path from response body
	 * @param response
	 * @param path
	 * @return
	 */
	public String getJsonData(Response response,String path)
	{
		String jsonData = response.jsonPath().get(path);
		return jsonData;
	}
}
