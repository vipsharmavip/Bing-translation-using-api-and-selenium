public class TripleInput  {
	  private String inputlang;
	  private String outputlang;
	  private String inputword;  
	  public TripleInput( String inputlang , String  outputlang , String inputword  ){
		   this.inputlang = inputlang;
		   this.outputlang = outputlang;
		   this.inputword =  inputword;
	  }
	  public String getinputlang(){
		  return inputlang;
	  }
	  public String getoutputlang(){
		  return outputlang;
	  }
	  public String getinputword(){
		  return inputword;
	  }
	  public void setinputlang(String inputlang){
		  this.inputlang = inputlang;
	  }
	  public void setoutputlang(String outputlang){
		  this.outputlang = outputlang;
	  }
	  public void setinputword(String inputword){
		  this.inputword = inputword;
	  }

}
