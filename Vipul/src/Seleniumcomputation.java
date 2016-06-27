import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
public class Seleniumcomputation {
    public ArrayList< String > getdata() throws IOException, InterruptedException{
    	 ArrayList< String > string = new ArrayList< String >();
    	 FileRead File = new FileRead();
    	 ArrayList<TripleInput> list = new ArrayList< TripleInput >();
     	 list = File.getData();
     	 FirefoxDriver driver = new FirefoxDriver();
   	     System.out.println("Opening the browser...");
	     driver.get("https://www.bing.com/translator");
	     List< WebElement > ID = driver.findElements(By.id("LS_LangList"));
	     String InputLang , OutputLang , InputWord , OutputWord ;
	     int ROWS = 18;
	     int COLOUMNS = 3;
	     String XML = "//*[@id='LS_LangList']/table/tbody/";
	     String XMLL = ".//*[@class='col translationContainer destinationText']//*[@id='LS_LangList']/table/tbody/";
         for( int i = 0 ; i < list.size() ; ++i ){
        	 InputLang = list.get(i).getinputlang();
        	 OutputLang = list.get(i).getoutputlang();
        	 InputWord  = list.get(i).getinputword();
        	 if(!InputLang.equals("")){
        	 ID.get(0).click();  	   
  		   for( int j = 1 ; j <= ROWS ; ++j ){
				    for( int k = 1 ; k <= COLOUMNS  ; ++k ){
				    String check = XML + "tr[" + j + "]/td[" + k + "]";
				    WebElement language = driver.findElement(By.xpath(check));
				    String Lang = language.getText();
				    if(Lang.equals(InputLang))
				    	language.click();
				  }
	           }
        	 }
	         driver.findElement(By.id("srcText")).sendKeys(InputWord);
		     ID.get(1).click();
	          for( int j = 1 ; j <= ROWS ; ++j ){
		         int Size = COLOUMNS;
		         if( j == ROWS )
		    	    Size -= 1; 
			    for( int k = 1 ; k <= Size  ; ++k ){
			    String check = XMLL + "tr[" + j + "]/td[" + k + "]";
			    WebElement language = driver.findElement(By.xpath(check));
			    String Lang = language.getText();
			    if(Lang.equals(OutputLang))
			    	language.click();
			    }
             }
	     Thread.sleep(500);
	     driver.findElement(By.id("destText")).click();
 	     OutputWord = driver.findElement(By.className("textArea")).getText();
 	      string.add(OutputWord);
 	      if(i < list.size() - 1 )
          driver.findElement(By.id("srcText")).clear();
         }
		return string;
    }
}
