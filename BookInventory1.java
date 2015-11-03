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
	//fixInventory populates bkArray with objects created using 
	//the input file, checks for duplicate instances of an ISBN
	//number, and then writes the contents of the array to an
	//output file
	public void fixInventory(String inputFileName, String outputFileName){

		Scanner input = new Scanner(System.in);
		String whichBook = null;
		String line = null;

		Book[] bkArray = new Book[numberOfLines("Initial_Book_Info.txt")];
		
		//Begin file input with FileReader object
		try{	
			FileReader fileReader = new FileReader("Initial_Book_Info.txt");

			//Wrap fileReader with BufferedReader so we can read the file
			//line by line
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			//bookIndex increases as bkArray is populated
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
		

				bkArray[bookIndex] = book;
				bookIndex++;
			}
			bufferedReader.close();
		}
		catch(IOException e){
			System.out.println("IO Error: File not found");
		}
		
    	//enhanced for loop iterates over all Book objects in the
    	//array

    	for(Book book : bkArray){
    		
    		Book[] dupISBNbooks = new Book[bkArray.length];
    		boolean isDuplicate = false;
    		long duplicateISBN = 0;
    		int numberOfDuplicates = 0;
    		
    		for(Book comparedBook : bkArray){
    		
    			if(book.getIsbn() == comparedBook.getIsbn()){
    				isDuplicate = true;
    				duplicateISBN = book.getIsbn();
    				dupISBNbooks[numberOfDuplicates] = comparedBook;
    				numberOfDuplicates++;

    			}
    		}
    		if(isDuplicate == true){
    			System.out.println("The ISBN "+duplicateISBN+" was found more than once for the following books: ");
    			for(Book i : dupISBNbooks){
    				System.out.println(i.toString());
    			}
    			System.out.println();

    			for(Book i : dupISBNbooks){
    				Scanner kb = new Scanner(System.in);

    				boolean done = false;
    				while(!done){
    					try{
		    				System.out.println("Enter the correct ISBN of "+i.getTitle()	);
		    				i.setIsbn(kb.nextLong());

		    				for(Book isbnLookup : bkArray){
		    					if(i.getIsbn() == isbnLookup.getIsbn()){
			    					throw new DuplicateISBNException("This ISBN is already assigned to another book!");
			    				}
						    }
							done = true;	
						}
						catch(DuplicateISBNException sadPotato){
							String message = sadPotato.getMessage();
							System.out.println(message);
						}				    
				    }		
    			}

    		}
    	}
	    
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

    				// System.out.println("Duplicate ISBN found!");
    				// System.out.println("The following two books have the same ISBN:");
    				// System.out.println();
    				// System.out.println(book.toString());
    				// System.out.println("and");
    				// System.out.println(comparedBook.toString() + "\n");
    				// System.out.println("Please select which book needs its ISBN modified (1/2): ");
    				// System.out.println();
    				// System.out.println("1 " + book.getTitle());
    				// System.out.println("2 " + comparedBook.getTitle());
    				// whichBook = input.next();
    				// if(whichBook == "1"){
    				// 	System.out.println("Please enter the new ISBN for "+ book.getTitle());
    				// 	long newIsbn = input.nextLong();
    				// 	book.setIsbn(newIsbn);
    				// }
    				// else if(whichBook == "2"){
    				// 	System.out.println("Please enter the new ISBN for "+ comparedBook.getTitle());
    				// 	long newIsbn = input.nextLong(); 
    				// 	book.setIsbn(newIsbn);
    				// }
