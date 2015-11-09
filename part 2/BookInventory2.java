//-----------------------------------------------------------------------
//Assignment 2
//Part 2
//Written by: Sviki Gabbay - 27490968
//COMP 249 - Section D
//1)creates method to add records
//2)creates method that display the contents of the file
//3)puts all the information from the file into an array
//4)does a binary search for an isbn
//5)does a sequential search for the same isbn
//6)puts all the array into a binary file
//-----------------------------------------------------------------------

package part2;

import java.io.*;
import java.util.Scanner;

public class BookInventory2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String fileName = "Initial_Book_Info.txt";
		
		//runs the methods
		addRecords(fileName);
		displayFileContents(fileName);
				
		Book[] bkArr = new Book[numofRecords(fileName)];
		Scanner arr = null;
		
		//exception if file isn't found
		try{
			arr = new Scanner(new FileInputStream(fileName));
		}
		catch(FileNotFoundException e){
			System.out.println("File not Found! Program will now exit!");
			System.exit(0);
		}
				
		//input the information from the file into the array
		for(int i = 0; i < bkArr.length; i++){
			bkArr[i] = new Book(arr.nextLong(), arr.next(), arr.nextInt(), arr.next(), arr.nextDouble(), arr.nextInt());		
		}
		
		arr.close();
		
		//getting the parameters for the searches, and doing them
		Scanner search = new Scanner(System.in);
			
		System.out.println("Enter the starting index: (between 0-" + bkArr.length + ")");
		int start = search.nextInt();
		while(start < 0){
			System.out.println("Invalid! Please enter a valid starting index: (between 0-" + bkArr.length + ")");
			start = search.nextInt();
		}
		System.out.println("Enter the ending index: (between 0-" + bkArr.length + ")");
		int end = search.nextInt();
		while(end > bkArr.length - 1){
			System.out.println("Invalid! Please enter a valid ending index: (between 0-" + bkArr.length + ")");
			end = search.nextInt();
		}
		System.out.println("Enter the isbn:");
		long isbn = search.nextLong();
				
		//doing a binary search
		binaryBookSearch(bkArr, start, end, isbn);
				
		//doing a sequential search
		sequentialBookSearch(bkArr, start, end, isbn);
			
		search.close();	
		
			
		//turning the array into a binary file
		ObjectOutputStream sortedBinary = null;
		try{
			System.out.println("Putting all the information in a binary file named Books.");
			
			sortedBinary = new ObjectOutputStream(new FileOutputStream("Books.dat"));
			
			sortedBinary.writeObject(bkArr);
			
			sortedBinary.close();
		}
		catch(FileNotFoundException e){
			System.out.println("File not Found! Program will now exit!");
			System.exit(0);
		} 
		catch(IOException e){
			System.out.println("Error! Program will now exit!");
			System.exit(0);
		}
	}
	
	//gives the option to the user to add records, and adds them to the file
	public static void addRecords(String sortedFile){
		
		//create variables and input/output objects
		Scanner kb = new Scanner(System.in);
		PrintWriter add = null;
		
		long isbn;
		String title;
		int issueYear;
		String authorName;
		double price;
		int numofPages;
		String ans;
		boolean write = true;
		int counter = 0;
		
		//create output stream for the printwriter, and filenotfoundexception
		try{
			add = new PrintWriter(new FileOutputStream(sortedFile, true));
		}
		
		catch(FileNotFoundException e){
			System.out.println("File not Found! Program will now exit!");
			System.exit(0);
		}
		
		//adds records as long as user responds yes
		//takes in all the information for the record one by one, then prints it to the file
		do{
			System.out.println("Do you wish to enter new book record? (yes or no)");
			ans = kb.next();
			ans = ans.toUpperCase();
			while(!ans.equals("YES") && !ans.equals("Y") && !ans.equals("NO") && !ans.equals("N")){
				System.out.println("Invalid Answer! Please enter valid answer! (yes or no)");
				ans = kb.next();
				ans = ans.toUpperCase();
			}
			if(ans.equals("YES") || ans.equals("Y")){
				System.out.println("Please enter the ISBN:");
				isbn = kb.nextLong();
				System.out.println("Please enter the title:");
				title = kb.nextLine();
				System.out.println("Please enter the issue year:");
				issueYear = kb.nextInt();
				System.out.println("Please enter the author's name:");
				authorName = kb.nextLine();
				System.out.println("Please enter the price:");
				price = kb.nextDouble();
				System.out.println("Please enter the number of pages:");
				numofPages = kb.nextInt();
				
				add.println(isbn + " " + title + " " + issueYear + " " + authorName + " " + price + " " + numofPages);
				
				counter++;
			}
			else{				
				System.out.println("You have chosen not to add anymore records.");
				System.out.println(counter + " records were added to the file.");
				System.out.println();
				write = false;
				
				add.close();
				kb.close();
			}
		}
		while(write);
	}
	
	//displays the contents of the file using the bufferedreader
	public static void displayFileContents(String sortedFile){
		BufferedReader filereader;
		
		try{
			filereader = new BufferedReader(new FileReader(sortedFile));
			
			String line = filereader.readLine();
			while(line != null){
				System.out.println(line);
				line = filereader.readLine();
			}
						
			filereader.close();
		}
		catch(FileNotFoundException e){
			System.out.println("File not Found! Program will now exit!");
			System.exit(0);
		}
		catch(IOException e){
			System.out.println("Error! Program will now exit!");
			System.exit(0);
		}
	}

	//binary search to find the desired isbn
	public static void binaryBookSearch(Book[] arr, int start, int end, long isbn){
		int counter = 0;
		int search = (start + end)/2;
		
		System.out.println("Doing a binary search.");
		
		if(arr[search].getISBN() <= isbn){
			for(int i = search; i <= end; i++){
				if(arr[i].getISBN() == isbn){
					System.out.println("ISBN found at index: " + i);
					counter++;
				}
				else
					counter++;
				if(i == end)
					System.out.println("Did not find ISBN.");
			}
		}
		else{
			for(int i = search - 1; i >= start; i--){
				if(arr[i].getISBN() == isbn){
					System.out.println("ISBN found at index: " + i);
					counter++;
				}
				else
					counter++;
				if(i == start)
					System.out.println("Did not find ISBN.");
			}
		}
		System.out.println(counter + " iterations of the search performed.");
		System.out.println();
	}
	
	//sequential search to find the desired isbn
	public static void sequentialBookSearch(Book[] arr, int start, int end, long isbn){
		int counter = 0;
		
		System.out.println("Doing a sequential search.");
		
		for(int i = start; i <= end; i++){
			if(arr[i].getISBN() == isbn){
				System.out.println("ISBN found at index: " + i);
				counter++;
			}
			else
				counter++;
			if(i == end)
				System.out.println("Did not find ISBN.");
		}
		System.out.println(counter + " iterations of the search performed.");
		System.out.println();
	}
	
	//counts each line using a bufferedreader to determine how many records are on the file
	private static int numofRecords(String sortedFile){
		int numofRecords = 0;
		Scanner filereader = null;
		
		try{
			filereader = new Scanner(new FileInputStream(sortedFile));
			
			while(filereader.hasNextLong() && filereader.hasNext() && filereader.hasNextInt() && filereader.hasNext() && filereader.hasNextDouble() && filereader.hasNextInt()){
				numofRecords++;
				filereader.nextLine();
			}
				
			filereader.close();
		}
		catch(FileNotFoundException e){
			System.out.println("File not Found! Program will now exit!");
			System.exit(0);
		}
		
		return numofRecords;
	}

}