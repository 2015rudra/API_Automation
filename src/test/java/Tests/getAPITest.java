package Tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.TestBase;
import Client.RestClient;
import Utility.TestUtil;

public class getAPITest extends TestBase
{
	public getAPITest() throws IOException 
	{

	}


	TestBase testbase;
	String hostURL;
	String APIURL;
	String finalURL;
	RestClient restClient;
	CloseableHttpResponse response;
	TestUtil util;




	@BeforeMethod
	public void setup() throws IOException 
	{
		testbase = new TestBase();
		util = new TestUtil();

		hostURL = prop.getProperty("URL");
		APIURL = prop.getProperty("serviceURL");

		finalURL = hostURL+APIURL;
		System.out.println(finalURL);


	}


	@Test
	public void getFirstAPIWithoutHeaderTest() throws ClientProtocolException, IOException 
	{
		restClient = new RestClient();
		response = restClient.get(finalURL);

		//Getting Status Code
		int responseCode = response.getStatusLine().getStatusCode();
		System.out.println("Status Code ---> " +responseCode);
		
		Assert.assertEquals(response_code_200, responseCode, "Response code not matched");


		//Getting JSON String
		String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");


		//NOw convert string to JSON		
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON from API -->" +responseJson);
		
		String perPage = TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println(perPage);
		Assert.assertEquals(Integer.parseInt(perPage),6,"Per Page value not matched");
		
		
		
		
		String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println(totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue),12,"Total value not matched");
		
		
		
		//To get values from data array in response JSON
		String lastName = TestUtil.getValueByJPath(responseJson, "/data[1]/last_name");
		String id = TestUtil.getValueByJPath(responseJson, "/data[1]/id");
		String avatar = TestUtil.getValueByJPath(responseJson, "/data[1]/avatar");
		String firstName = TestUtil.getValueByJPath(responseJson, "/data[1]/first_name");
		String email = TestUtil.getValueByJPath(responseJson, "/data[1]/email");
		
		System.out.println(lastName);
		System.out.println(id);
		System.out.println(avatar);
		System.out.println(firstName);
		System.out.println(email);
		
				
		

		//TO get All Headers of Response JSON

		Header[] headerArray = response.getAllHeaders();

		// Convert Header array to HashMap so that we can access values in Key-Pair format

		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for(Header header: headerArray) 
		{
			allHeaders.put(header.getName(), header.getValue());
		}

		//System.out.println("Headers Array -->" +allHeaders);
	}

	
	
	@Test
	public void getFirstAPIWithHeaderTest() throws ClientProtocolException, IOException 
	{
		restClient = new RestClient();
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		//headerMap.put("username", "Test");
		//headerMap.put("password", "Test@123");
		
		
		response = restClient.get(finalURL,headerMap);

		//Getting Status Code
		int responseCode = response.getStatusLine().getStatusCode();
		System.out.println("Status Code ---> " +responseCode);
		
		Assert.assertEquals(response_code_200, responseCode, "Response code not matched");


		//Getting JSON String
		String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");


		//NOw convert string to JSON		
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON from API -->" +responseJson);
		
		String perPage = TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println(perPage);
		Assert.assertEquals(Integer.parseInt(perPage),6,"Per Page value not matched");
		
		
		
		
		String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println(totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue),12,"Total value not matched");
		
		
		
		//To get values from data array in response JSON
		String lastName = TestUtil.getValueByJPath(responseJson, "/data[1]/last_name");
		String id = TestUtil.getValueByJPath(responseJson, "/data[1]/id");
		String avatar = TestUtil.getValueByJPath(responseJson, "/data[1]/avatar");
		String firstName = TestUtil.getValueByJPath(responseJson, "/data[1]/first_name");
		String email = TestUtil.getValueByJPath(responseJson, "/data[1]/email");
		
		System.out.println(lastName);
		System.out.println(id);
		System.out.println(avatar);
		System.out.println(firstName);
		System.out.println(email);
		
		
		
		
		

		//TO get All Headers of Response JSON

		Header[] headerArray = response.getAllHeaders();

		// Convert Header array to HashMap so that we can access values in Key-Pair format

		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for(Header header: headerArray) 
		{
			allHeaders.put(header.getName(), header.getValue());
		}

		//System.out.println("Headers Array -->" +allHeaders);
	}



}
