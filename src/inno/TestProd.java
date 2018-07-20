package inno;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;

/**
 * 
 * @author hexl
 *
 */
public class TestProd {

	public static void main(String[] args) {
		JSONObject jsonObject = new JSONObject();
        jsonObject.put("uid", "12345");
		jsonObject.put("channel", "weixin");
		jsonObject.put("platform", "android");
        jsonObject.put("pid", "1");
        String result = remoteJsonRequest("http://localhost:8080/partner/prod/info", 5000, jsonObject);
        System.out.println(result);

	}
	 public static String remoteJsonRequest(String url, int timeout, JSONObject object) {
	        URL connect;
	        StringBuffer data = new StringBuffer();
	        try {
	            connect = new URL(url);
	            HttpURLConnection connection = (HttpURLConnection) connect.openConnection();
	            connection.setRequestMethod("POST");
	            connection.setDoOutput(true);
	            connection.setReadTimeout(timeout);
	            connection.setRequestProperty("Content-Type", "application/json");
	            OutputStreamWriter paramout = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
	            paramout.write(object.toString());
	            paramout.flush();
	            InputStream inputStream = connection.getInputStream();
	            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
	            BufferedReader reader = new BufferedReader(inputStreamReader);

	            String line;
	            while ((line = reader.readLine()) != null) {
	                data.append(line);
	            }
	            paramout.close();
	            reader.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return data.toString();
	    }
}
