//LAURAS VERSION
//LAURAS VERSION
import java.io.*;
import java.util.Scanner;

public class BookInventory1 {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		String newFileName = null;
		//Creates new file name and ensures it isn't already an existing file//
				
		try{
			System.out.println("Please enter the name for the updated inventory: ");		
			String newFile = keyboard.next();
			File fixedBookInvFile = new File(newFile);			//createNewFile method creates a new file with a given provided it doesn't already exists, if
				while (!fixedBookInvFile.createNewFile()){	   //it does it will prompt for a new one
					System.out.println("Error! There is an existing file with the name "+ fixedBookInvFile.getName()+".");
					System.out.println("That file has a size of "+fixedBookInvFile.length());
					System.out.println("Please enter another name: ");
					newFile = keyboard.next();				//Takes string value of the name for the new file, provided it doesn't exist either
					fixedBookInvFile = new File(newFile);				
				}
			
			newFileName = fixedBookInvFile.getName();	//converted file to string in order to use as a parameter for fixInventory method
			fixInventory("Initial_Book_Info.txt", newFileName); //Call fixInventory method to begin sorting ISBNs and writing to newly created file
		}
		catch (IOException e){
			System.out.println("Exception found!");
			System.out.println(e.toString());
		}		
		//Printing out contents to screen using the displayFileContents method
		System.out.println("Here are the contents of the file Initial_Book_Info.txt AFTER copying operation:");
		System.out.println("================================================================================\n");
		displayFileContents("Initial_Book_Info.txt");
		
		System.out.println("\nHere are the contents of the file "+newFileName+":");
		System.out.println("================================================================================\n");
		displayFileContents(newFileName);
		
		
		
	}	
	//Reading the Initial book file with buffered reader class in order to establish
	//size of the future book array.
	private static int NumOfLines(String originalFile){
		int numOfBooks = 0;
		BufferedReader inputStream = null;
		
		try{
			System.out.println("Attempting to open file:"+originalFile+"\n \n ");	//Opening and reading file using bufferedReader class
			inputStream = new BufferedReader(new FileReader(originalFile));
			
				while(inputStream.readLine() != null){		//checking for end of file and adding to counter for the amount of lines/books 
					numOfBooks++;
					}
			
			inputStream.close();
			System.out.println("Detecting number of records in file: "+originalFile+"\n\n");
			
		}
		catch(IOException e){
		System.out.println("File not found!");
		System.exit(0);
		}
			//Assignment requirement if there isn't enough books to compare,either 1 or none, will end program
			if (numOfBooks==0){
					System.out.println("There are no books to sort.\n Thank you, goodbye.");
					System.exit(0);
				}else if (numOfBooks==1){
							System.out.println("There is only "+ numOfBooks +" book to enter. \n Thank you, goodbye");
							System.exit(0);
				}
		
		System.out.println("This file contains "+numOfBooks+" books. \n");
		return numOfBooks;		
	}		
	
	//The fixInventory method is where we initialize the Book array. Calls the sortIsbns method to compare ISBNS 
	//and ensure no ISBNS are repeated and adds that assorted Book Array to a output file.
	public static void fixInventory(String roughBookInvFile, String fixedBookInvFile){	
		Book[] BkArr = new Book[NumOfLines("Initial_Book_Info.txt")];
		Scanner keyboard = new Scanner(System.in);
		Scanner inputStream = null;
		PrintWriter outputStream = null;
			
		//Initializing the Array of books by reading the original file using the scanner class in order to parse the different 
		//parameters one by one and creating new objects in each index.
			try{												
				 inputStream = new Scanner(new FileReader("Initial_Book_Info.txt"));		
						for(int i=0;i<BkArr.length; i++){
								BkArr[i] = new Book(inputStream.nextLong(),inputStream.next(),inputStream.nextInt(),
												inputStream.next(),inputStream.nextDouble(),inputStream.nextInt());	
											}
				}		
			catch(IOException e){
					System.out.println("File not found!");
					System.exit(0);
					}
			
		sortIsbn(BkArr);	//calling method that sort ISBNs to newly created book array
		
		//Here we write to our newly created file, which contains all new and correct ISBNs, by using the Print class
		try{				
			outputStream = new PrintWriter(new FileOutputStream(fixedBookInvFile));			
			}
		catch (IOException e){
			System.out.println("File not found!");
			System.exit(0);
			}
		
		for(int i=0;i<BkArr.length; i++)
			outputStream.println(BkArr[i]);
		
		inputStream.close();
		outputStream.close();	//Closing the Scanner and Print streams for the fixInventory method
		keyboard.close();	
		
	}	
					
	//Comparing ISBNs within the array, if there are any duplicates, prompts user to enter the correct one.This is considering that
	//the first ISBN entered is the correct one
	//Later if the newly entered ISBN is a duplicate once more, then we throw the duplicateIsbnException and prompt again for another ISBN
		
	private static void sortIsbn(Book[] roughBkArr){
		Scanner keyboard = new Scanner(System.in);
		boolean uniqueIsbn;		
		long isSameIsbn;
			
			for(int i=0;i<roughBkArr.length; i++){			//Cycle through array in order to find duplicates
				for (int j=i+1;j<roughBkArr.length; j++){	  						
					if(roughBkArr[i].getIsbn()==roughBkArr[j].getIsbn()){		//Compare ISBNs, if duplicate found prompt user to enter a new ISBN	
						System.out.println("Duplicate ISBN "+roughBkArr[j].getIsbn()+" detected in record #"+j+".");
						System.out.println("Please enter the correct ISBN: ");
						isSameIsbn=keyboard.nextLong();	//Store new ISBN in temporary variable so that we can test it isn't another duplicate
						uniqueIsbn = false;			
						
						while(!uniqueIsbn){			//Commence a while loop in order to know when an ISBN is not a duplicate
							try{	
								for(int k=0;k<roughBkArr.length;k++)		//Compare if newly entered ISBN is a duplicate or not by cycling through array
										if(isSameIsbn==roughBkArr[k].getIsbn())	
											throw new DuplicateIsbnException();												
										
									uniqueIsbn=true;		//If no duplicate found can end while loop
								}																	
							catch(DuplicateIsbnException e){		//DuplicateIsbnException caught, displays which exception found and prompts for a new ISBN
								System.out.println(e.getMessage());	
								System.out.println("Please enter another ISBN: ");	
								isSameIsbn = keyboard.nextLong();	//Takes new ISBN and will restart while loop and ensure it isn't again a duplicate							
							}
						}
						roughBkArr[j].setIsbn(isSameIsbn);		//Stores newly entered ISBN, whether it be the originally entered new ISBN or ISBN that 
					}											//threw the exception
				}
			}		
				keyboard.close();		//closing keyboard Scanner for the sortBooks method
		}

	//displayFileContents method which take a file, read it with the buffered reader class and prints out its contents line by line
	public static void displayFileContents(String fileToDisplay){
		BufferedReader inputStream = null;
		String line;
		
		try{
			inputStream = new BufferedReader(new FileReader(fileToDisplay));
			
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
	
								
}
	

