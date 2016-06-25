package HelperClasses;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import Modals.Memory;
import Modals.SystemModal;

public class JsonHandler {
	
	public SystemModal getSysteElementsFromJsonString(String json_string)
	{
		try
		{
			List <String> cpu = new ArrayList<>();
			
		
			if(json_string!=null)
			{
	            JSONObject json_object = new JSONObject(json_string.trim());
	            JSONArray cpu_array=json_object.getJSONArray("Cpu");
	            System.out.println("cpu_array"+cpu_array.toString());
	            
	            JSONObject memory_String= new JSONObject(json_object.getString("Memory"));
	            String Total_memory= memory_String.getString("Total");
	            String used_memory=memory_String.getString("Used");
	            String free_memory=memory_String.getString("Free");
	           System.out.println("memory_String"+memory_String.toString());
	           System.out.println("Total_memory"+Total_memory.toString());
	           System.out.println("used_memory"+used_memory.toString());
	           System.out.println("free_memory"+free_memory.toString());
	            
                for (int i=0;i<cpu_array.length();i++)
                {
                	cpu.add(cpu_array.get(i).toString());
                }
                               return new  SystemModal(cpu, new Memory(used_memory, Total_memory, free_memory));

			}else
			{
			return null;	
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}

}
