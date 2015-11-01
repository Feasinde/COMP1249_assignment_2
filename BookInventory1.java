import java.io.*;
import java.util.Scanner;

public class BookInventory1{

	public static void main(String[] args){

		//number of books in input file
		int nBooks;
		Scanner kb = new Scanner(System.in);

		//name of the corrected ISBN catalog file to be created
		String outputFileName = null;
		
		//Ask user to name the output file that contains
		//the corrected ISBN numbers
		System.out.println("Please enter the name of the corrected catalog file:");
		outputFileName = kb.next();

		//TO DO: check for existing files of that name. In case
		//user gives the name of a preexisting file, reject input,
		//display file susize and repeat query

		

		//TO DO: a whole lot more…


	}
	//fixInventory populates BkArray with objects created using 
	//the input file, checks for duplicate instances of an ISBN
	//number, and then writes the contents of the array to an
	//output file
	public void fixInventory(String inputFileName, String outputFileName){
		String line = null;

		//Begin file input with FileReader object
		try{	
			FileReader fileReader = new FileReader("Initial_Book_Info.txt");

			//Wrap fileReader with BufferedReader so we can read the file
			//line by line
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			Book[] BkArray = new Book[numberOfLines("Initial_Book_Info.txt")];

			//bookIndex increases as BkArray is populated
			int bookIndex = 0;

			//read file line-by-line and create a book object for each line
			while((line = bufferedReader.readLine()) != null) {
				Book book = new Book(

			//each line in the file is split using whitespace as a delimiter
			//and each token is used as a parameter for the book object
			
			//we then use each type's parse<type>() method to convert each 
			//String fragment into the appropriate type for the Book constructor
					Long.parseLong(line.split("\\s+")[0]),
					line.split("\\s+")[1],
					Integer.parseInt(line.split("\\s+")[2]),
					line.split("\\s+")[3],
					Double.parseDouble(line.split("\\s+")[4]),
					Integer.parseInt(line.split("\\s+")[5])
					);
		

		BkArray[bookIndex] = book;
		bookIndex++;
	}
	bufferedReader.close();
		}
		catch(IOException e){
			System.out.println("IO Error: File not found");
		}
		//initialise array of books

		
    	//enhanced for loop iterates over all Book objects in the
    	//array 
    	// for(Book book : BkArray){
    	// 	for(Book comparedBook : BkArray){
    	// 		if(book.getIsbn() == comparedBook.getIsbn()){
    	// 			System.out.println("Duplicate ISBN found. ")
    	// 		}
    	// 		else{

    	// 		}
    	// 	}
    	// }
	}

	//numberOfLines receives a text file location
	//and returns how many lines the input file has
	private static int numberOfLines(String fileLocation){
		int lines = 0;
		try{
				//Begin file input with FileReader object
				FileReader fileReader = new FileReader(fileLocation);

				//Wrap fileReader with BufferedReader so we can read the file
				//line by line
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				
				//readLine() is the method that reads the file one line at the time
				while (bufferedReader.readLine() != null){lines++;}
				bufferedReader.close();
		}
		catch(IOException ex) {

	        System.out.println(
	            "Error reading file '" 
	            + fileLocation + "'");
	    }
	    return lines;		

		
		
	}
}