package Utility;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import Base.TestBase;

public class TestUtil extends TestBase{

	
	public TestUtil() throws IOException {
		super();
		
	}

	
	//This method will read JSON response and get the value on passes object path
	public static String getValueByJPath(JSONObject responsejson, String jpath){
		Object obj = responsejson;
		for(String s : jpath.split("/")) 
			if(!s.isEmpty()) 
				if(!(s.contains("[") || s.contains("]")))
					obj = ((JSONObject) obj).get(s);
				else if(s.contains("[") || s.contains("]"))
					obj = ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0])).get(Integer.parseInt(s.split("\\[")[1].replace("]", "")));
		return obj.toString();
	}
	
}
