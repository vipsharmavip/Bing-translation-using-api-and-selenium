import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class FileRead {
   public ArrayList< TripleInput > getData() throws IOException{
	   BufferedReader br = new BufferedReader(new FileReader("data.csv"));
       String line;
       ArrayList<TripleInput>  list = new ArrayList< TripleInput >();
       while( (line = br.readLine() ) != null ){
    	   TripleInput triple = new TripleInput(null, null, null);
    		  String[] b  = line.split(",");
   		      triple.setinputlang(b[0]);
   		      triple.setoutputlang(b[1]);
   		      triple.setinputword(b[2]);
    		  list.add(triple);
       }
       br.close();
       return list;
    }
}
