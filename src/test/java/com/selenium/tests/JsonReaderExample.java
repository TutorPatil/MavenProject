package com.selenium.tests;

import java.io.File;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonReaderExample {

	public static void main(String[] args) {

		JSONParser parser = new JSONParser();
		
		try {			
			
			File f = new File("./src/test/data/emp.json");
			FileReader fr = new FileReader(f);			
			
			Object obj = parser.parse(fr);
			
			JSONObject jObject = (JSONObject) obj;
			
			JSONObject jobj =	(JSONObject) jObject.get("employee2");
			
			System.out.println(jobj);
			
			String fName = (String) jobj.get("firstName");
			
			System.out.println(fName);
			
			JSONArray jArray = 	(JSONArray) jobj.get("interests");
			
			System.out.println(jArray);
			
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
	}

}
