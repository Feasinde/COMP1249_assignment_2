//import Book;
//import DuplicateISBNException;
import java.util.Scanner;

public class BookInventory1{

	public static void main(String[] args){

		//number of books in input file
		int nBooks;

		Book[] BkArray;
		Scanner kb = new Scanner(System.in);

		//name of the corrected ISBN catalog file to be created
		String correctedFileName = null;
		
		//Ask user to name the output file that contains
		//the corrected ISBN numbers
		System.out.println("Please enter the name of the corrected catalog file:");
		correctedFileName = kb.next();

		//TO DO: check for existing files of that name. In case
		//user gives the name of a preexisting file, reject input,
		//display file size and repeat query

		//TO DO: read file, determine number of books contained and
		//define BkArray.

		//TO DO: a whole lot more…


	}
}