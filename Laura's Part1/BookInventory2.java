import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;




public class BookInventory2 extends BookInventory1 {
	Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args){
		Scanner inputStream = null;
		PrintWriter outputStream = null;


		//Initializing a new Array of books with corrected ISBNs 
		Book[] BkArr2 = new Book[NumOfLines(newFileName)];

		//Reading the corrected file using the scanner class in order to parse the different .
		try{												
			inputStream = new Scanner(new FileReader(newFileName));		
			for(int i=0;i<BkArr2.length; i++){
				BkArr2[i] = new Book(inputStream.nextLong(),inputStream.next(),inputStream.nextInt(),
						inputStream.next(),inputStream.nextDouble(),inputStream.nextInt());	
			}
		}		
		catch(IOException e){
			System.out.println("File not found!");
			System.exit(0);
		}
		// loop that orders the new array in order of increasing ISBN numbers
		for(int i=0;i<BkArr2.length; i++){
			for (int j=i+1;j<BkArr2.length; j++){
				if (BkArr2[i].getIsbn()>BkArr2[j].getIsbn()){
					BkArr2[i] = BkArr2[j];
				}
				else
					continue;
			}
		}

		//Here we write to a new  file, which contains all the sorted ISBNs, by using the Print class
		try{				
			outputStream = new PrintWriter(new FileOutputStream(newFileName));			
		}
		catch (IOException e){
			System.out.println("File not found!");
			System.exit(0);
		}

		for(int i=0;i<BkArr2.length; i++)
			outputStream.println(BkArr2[i]);

		System.out.println("\nHere are the contents of the file "+newFileName+":");
		System.out.println("================================================================================\n");	
		displayFileContents(newFileName);
	}



	public void addRecords(String outputFileName){
		/* I'm going to try to append the data... when I get home....	
		try {
		    Files.write(Paths.get(newFileName), linktotext.getBytes(), something.APPEND);
		}catch (IOException e) {

		}*/
		PrintWriter outputStream = null;


		PrintWriter output = new PrintWriter(outputStream);		

		boolean done = false;

		while (!done) {

			System.out.println("Please enter information for the new book: \n");

			long isbn = 0;
			String title = null;
			String authorName = null;
			int issueYear = 0;
			int numOfPages = 0;
			double price = 0;

			System.out.print("ISBN: ");
			isbn = keyboard.nextLong();

			System.out.print("Title: ");
			title = keyboard.next();

			System.out.print("Issue Year: ");
			issueYear = keyboard.nextInt();

			System.out.print("Author names: ");
			authorName = keyboard.next();

			System.out.print("Price: ");
			price = keyboard.nextDouble();

			System.out.print("Number of Pages: ");
			numOfPages = keyboard.nextInt();


			output.println(isbn + " " + title + " " + issueYear + " " + authorName + " "
					+ price + " " + numOfPages);

			System.out.print("\nAdd another book? Enter 'y' for yes: ");

			if (!keyboard.next().equals("y")) {
				done = true;
				output.close();	
			}
		}


	}

	//method that displays the file contents	
	public static void displayFileContents(String fileStreamName){
		BufferedReader inputStream = null;
		String line;

		try{
			inputStream = new BufferedReader(new FileReader(fileStreamName));

			line = inputStream.readLine();
			while(line != null){				//checks the end of the file
				System.out.println(line);
				line = inputStream.readLine();
			}
			inputStream.close();			//closes the input stream
		}
		catch (IOException e){
			System.out.println("File not found!");
			System.exit(0);
		}


	}


	public void binaryBookSearch(Book[] arr, int start, int end, long isbn){

	}


	public void sequentialBookSearch(Book[] arr, int start, int end, long isbn){


	}


