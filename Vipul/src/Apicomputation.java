import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.json.JSONException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
public class Apicomputation {
	public String translateText(String text , String from , String to ) throws JSONException, IOException, ParserConfigurationException, SAXException{		
		String token=null;
   	 Api api = new Api();
   	 try {
		  token  = api.getOauthToken();
		} catch (IOException e) {
			e.printStackTrace();
		}
   	 String headerValue = "Bearer " + token;
   	 String uri = "http://api.microsofttranslator.com/v2/Http.svc/Translate?text="+URLEncoder.encode(text,"UTF-8")+ "&from=" + from + "&to=" + to;
   	     URL url1 = new URL(uri);
			HttpURLConnection Conn = (HttpURLConnection)url1.openConnection();
			Conn.setRequestMethod("GET");
			Conn.setRequestProperty("Authorization", headerValue);
			BufferedReader in = new BufferedReader(new InputStreamReader(Conn.getInputStream()));
		   	String inputLine;
		   	StringBuffer response = new StringBuffer();
		   	while ((inputLine = in.readLine()) != null) 
		   		response.append(inputLine);
		    in.close();
			Conn.disconnect();
			String app = new String(response);
		return xmlToDataRetriever(app);
	}
	public static String xmlToDataRetriever(String xmlString) throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	    InputSource is = new InputSource();
	    is.setCharacterStream(new StringReader(xmlString));
	    org.w3c.dom.Document doc = db.parse(is);
	    org.w3c.dom.Element e= doc.getDocumentElement();
	    String str= e.getTextContent();
		return str;
	}
}
