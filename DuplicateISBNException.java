public class DuplicateISBNException extends Exception{
	
	//Default constructor
	public DuplicateISBNException(){
		super("Duplicate ISBN Exception!");
	}
	//Parametrised constructor
	public DuplicateISBNException(String message){
		super(message);
	}


	//Error message
	public String getMessage(){
		return super.getMessage();
	}
}