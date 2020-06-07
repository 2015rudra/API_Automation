package Client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	
	// Get Call Without Header
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException 
	{
		CloseableHttpClient http =  HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); //HTTP get request
		CloseableHttpResponse response =  http.execute(httpget); //Hit the GET URL
		return response;
		
		
		
	}
	
	//Get Call with Header
	public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException 
	{
		CloseableHttpClient http =  HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); //HTTP get request
		
		for(Map.Entry<String, String> entry: headerMap.entrySet()) 
		{
			httpget.addHeader(entry.getKey(), entry.getValue());
		}
		
		CloseableHttpResponse response =  http.execute(httpget); //Hit the GET URL
		return response;
		
	
	}
	
}
