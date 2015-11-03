//LAURAS VERSION
//LAURAS VERSION
import java.io.*;
import java.util.Scanner;

public class BookInventory1 {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		
		//Creates new file name and ensures it isn't already an existing file//
				
		try{
			System.out.println("Please enter the name for the updated inventory: ");		
			String newFile = keyboard.next();
			File fixedBookInvFile = new File(newFile);
			while (!fixedBookInvFile.createNewFile()){
				System.out.println("Error! There is an existing file with the name "+ fixedBookInvFile.getName()+".");
				System.out.println("That file has a size of "+fixedBookInvFile.length());
				System.out.println("Please enter another name: ");
				newFile = keyboard.next();
				fixedBookInvFile = new File(newFile);				
			}
			String newFileName = fixedBookInvFile.getName();
			fixInventory("Initial_Book_Info.txt", newFileName);
		}
		catch (IOException e){
			System.out.println("Exception found!");
			System.out.println(e.toString());
		}		
		
		
	}	
		//Reading the Initial book file with buffered reader class in order to establish
		//size of the future book array.
		private static int NumOfLines(String originalFile){
			int numOfBooks = 0;
			
			try{
				System.out.println("Attempting to open file:"+originalFile+"\n \n ");
				BufferedReader inputStream = new BufferedReader(new FileReader(originalFile));
				while(inputStream.readLine() != null){
					numOfBooks++;
				}
				inputStream.close();
				System.out.println("Detecting number of records in file: "+originalFile+"\n\n");
				
			}
			catch(IOException e){
			System.out.println("File not found!");
			}
			//Assignment requirement if there isn't enough books to compare//
			if (numOfBooks==0){
				System.out.println("There are no books to enter.\n Thank you, goodbye.");
				}else if (numOfBooks==1){
					System.out.println("There is only "+ numOfBooks +" book to enter. \n Thank you, goodbye");
				}			
			System.out.println("This file contains "+numOfBooks+" books.");
			return numOfBooks;	
		}		
		
		//The fixInventory method is where we initialize the Book array, compare ISBNS 
		//and ensure no ISBNS are repeated considering the first one entered is the correct ISBN
		public static void fixInventory(String roughBookInvFile, String fixedBookInvFile){	
			Book[] BkArr = new Book[NumOfLines("Initial_Book_Info.txt")];
			Scanner keyboard = new Scanner(System.in);
				try{		
					Scanner inputStream = new Scanner(new FileReader("Initial_Book_Info.txt"));		
							for(int i=0;i<BkArr.length; i++){
									BkArr[i] = new Book(inputStream.nextLong(),inputStream.next(),inputStream.nextInt(),
													inputStream.next(),inputStream.nextDouble(),inputStream.nextInt());	
													}
							inputStream.close();
					}		
				catch(IOException e){
				System.out.println("File not found.");
				}
				
			//Comparing Isbns within the array, if there are any duplicates, prompts user to enter the correct one
			//Later if the newly entered Isbn is a duplicate once more, then we throw the duplicaateIsbnException 
			//AFter completing this much I realized the assignment says we may create a private method to do 
			//sorting which isn't a bad idea.....	
			try{
				for(int i=0;i<BkArr.length; i++){
					for (int j=1;j<BkArr.length; j++){
						if(BkArr[i].getIsbn()==BkArr[j].getIsbn()){		//Comparing two Isbns using Acessor methods
							System.out.println("Duplicate ISBN "+BkArr[j].getIsbn()+" detected in record #"+j+".");
							System.out.println("Please enter the correct ISBN: ");
							long isSameIsbn = keyboard.nextLong();
								for(int k=0;k<BkArr.length; k++){		//Another for loop in order to verify newly submitted ISBN 
									if(isSameIsbn==BkArr[k].getIsbn()) 	//with the help of a new variable 
										throw new DuplicateIsbnException();						     
									}
							BkArr[j].setIsbn(isSameIsbn); //When all is good a verified, initialize the unique ISBN
							}							 //to the right index
						}
				}
				
			}
			catch (DuplicateIsbnException e){
				System.out.println(e.getMessage());
				DuplicateIsbnException.secondTry();
				//Refer to the DUplicateISBNException class for why this isn't filled in
				}
				
		}
}
		//What's Left?
		//outputting both original and corrected arrays to their own files and printing them 
		//Aka the displayFileContents method right after we call the main() Method
		
	


							
		
		

	
//DISREGARD MY LITTLE TEST CORNER :)//


/* TEST??
 * 
 * while(inputStream.readLine() !=null){
 * 
 * 
long isbn;
System.out.println("ISBN?");
isbn=keyboard.nextLong();

Book testBook = new Book(isbn, "HI","BYE", 234,665,342.00);

System.out.println(testBook);
*/

/*else{
BkArr[i] = new Book(inputStream.nextLong(),inputStream.next(),inputStream.nextInt(),
		inputStream.next(),inputStream.nextDouble(),inputStream.nextInt());
	//Here we check
	if(inputStream.nextLong()==BkArr[i-1].getIsbn()){
		throw DuplicateISBNException;
}*/
