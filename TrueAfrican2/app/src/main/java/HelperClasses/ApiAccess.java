package HelperClasses;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiAccess {
	
	
	
    public String GetJSONDataFromAPI(String response_url)
    {
        try {
            URL url = new URL(response_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String output;
            String jsonString=null;
            System.out.println("JSON Reading from  "+response_url+" .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                jsonString=output;
            }
            return jsonString;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("JSONResponse#sendJSON exception : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}
