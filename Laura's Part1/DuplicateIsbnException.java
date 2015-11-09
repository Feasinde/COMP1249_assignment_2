public class DuplicateIsbnException extends Exception {
	
	public DuplicateIsbnException(){
		super("Error! Duplicate ISBN Exception Found! The entered ISBN exists for another record.");
	}
		
	public DuplicateIsbnException(String error){
		super(error);
	}
}
	
	
	


