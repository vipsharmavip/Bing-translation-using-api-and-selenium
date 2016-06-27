import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;
public class Api {
  private static final String clientId = "vipsharmavip123";
  private static final String clientSecret = "VLa+GRETLgcDY4MfodFptRzd9MKpvdC443ByQN6fkxc=";
  private static final String oauthScope = "http://api.microsofttranslator.com";
  private static String oauthToken = null;

  public String getOauthToken() throws IOException, JSONException {
	   
         URL url1 = new URL("https://datamarket.accesscontrol.windows.net/v2/OAuth2-13");
         HttpURLConnection conn1 = (HttpURLConnection) url1.openConnection();
         conn1.setRequestMethod("POST");
			String urlParameters = "client_id=" + URLEncoder.encode(clientId,"UTF-8")+"&grant_type=client_credentials&client_secret="+URLEncoder.encode(clientSecret,"UTF-8")+"&scope="+oauthScope;
			conn1.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(conn1.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(conn1.getInputStream()));
		   	String inputLine;
		   	StringBuffer response = new StringBuffer();
		   	while ((inputLine = in.readLine()) != null) 
		   		response.append(inputLine);
		in.close();
			conn1.disconnect();
			String app = new String(response);
			JSONObject obj=new JSONObject(app);
			String Str=(String)obj.get("access_token");
    return Str.toString();
  }
}