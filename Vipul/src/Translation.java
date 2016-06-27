import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.Reporter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONException;
import org.xml.sax.SAXException;
import com.memetix.mst.language.Language;
import javax.xml.parsers.ParserConfigurationException;
public class Translation {
	 static ArrayList< String > outputusingselenium = new ArrayList< String >();
     static ArrayList< String > outputusingapi = new ArrayList< String >();

	 @BeforeTest
     public void translate( ) throws IOException, InterruptedException, JSONException, ParserConfigurationException, SAXException {
    	 Apicomputation api = new Apicomputation();
    	 Seleniumcomputation comp = new Seleniumcomputation();
     	 outputusingselenium = comp.getdata();
   	    FileRead File = new FileRead();
   	    ArrayList<TripleInput> list = new ArrayList< TripleInput >();
    	 list = File.getData();
    	 for( int i = 0 ; i <  list.size() ; ++i ){
    		 String in = list.get(i).getinputlang();
    		 String ou = list.get(i).getoutputlang();
    		 String word = list.get(i).getinputword();
    		 Language input , output;
    		 String Output;
    		 if(!in.equals("")){
    		 input = Language.valueOf(in.toUpperCase());
    		 output = Language.valueOf(ou.toUpperCase());
    		 Output = api.translateText(word , input.toString() , output.toString() );
    		 }
    		 else 
    	     Output = outputusingselenium.get(i);
    	  	 outputusingapi.add(Output);
    	 }
	 } 	 
	@DataProvider(name= "Fileobject")
	 public static Object[][] fileobject(){
		 int size = outputusingselenium.size();
	   Object[][] obj = new Object[size][2];
	   for( int i = 0 ; i < size ; ++i ){
		   String usingselenium = outputusingselenium.get(i);
		   String usingapi = outputusingapi.get(i);
		   obj[i][0] = usingselenium;
		   obj[i][1] =  usingapi;
	      
	 }
	   return obj;
	}
	 
 @Test(dataProvider = "Fileobject")
  public void test(String first , String second ){
	 Assert.assertEquals( first , second , "Do not match ");
	  Reporter.log("Match found",true);
 }
}
 