package Tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Base.TestBase;
import Client.RestClient;
import Data.Users;
import Utility.TestUtil;

public class postAPITest extends TestBase
{

	public postAPITest() throws IOException 
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
	public void postTest() throws JsonGenerationException, JsonMappingException, IOException 
	{
		restClient = new RestClient();
		
		//Pass header: For multiple headers keep adding in key pair format
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("morpheus", "leader"); //expected users obejct
		
		mapper.writeValue(new File("D:\\Practise\\SalesSuite_API_Automation\\src\\main\\java\\Data\\users.json"), users);
		String usersJsonString = mapper.writeValueAsString(users);
		System.out.println(usersJsonString);
		
		response = restClient.post(finalURL, usersJsonString, headerMap); //call the API
		
		//validate response from API:
		//1. status code:
		int statusCode = response.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testbase.response_code_201);
		
		
		String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
		
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:"+ responseJson);
		
		Users usersResObj = mapper.readValue(responseString, Users.class); //actual users object
		//System.out.println(usersResObj);
		
		Assert.assertTrue(users.getName().equals(usersResObj.getName()));
		
		Assert.assertTrue(users.getJob().equals(usersResObj.getJob()));
		
		System.out.println(usersResObj.getId());
		System.out.println(usersResObj.getCreatedAt());
		
	}

	

}
