import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class CurrencyConverter {

	 public static final String ACCESS_KEY = "ae499a5e31930e932deecb0e0af27152";
     public static final String BASE_URL = "http://apilayer.net/api/";
     public static final String ENDPOINT = "live";
     
    public static void main(String args[]) throws Exception{
    	findExchangeRateAndConvert("USD","INR",1000);
    	
    	// essential URL structure is built using constants
       
    }
    
    public static void findExchangeRateAndConvert(String from,String to,int conversionAmt) throws Exception{
    	URL obj = new URL(BASE_URL + ENDPOINT + "?access_key=" + ACCESS_KEY+"&currencies=" + to+"&source=" + from);
    	HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    	// optional default is GET
    	con.setRequestMethod("GET");
		//int responseCode = con.getResponseCode();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		
		in.close();
		JSONObject object = (JSONObject) JSONValue.parse(response.toString());
		object = (JSONObject) JSONValue.parse(object.get("quotes").toString());
        double value = (double)object.get(from.toUpperCase()+to.toUpperCase());
        double amt = (double)conversionAmt * value;
    	System.out.println("1 "+ from + " = " + String.valueOf(value) + " " + to);
    	System.out.println("Conversion Amount of "+ String.valueOf(conversionAmt) + " " + from + " to " + to + " is " + amt);
    }
}
