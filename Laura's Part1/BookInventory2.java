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
			int i = 0;

		//input the information from the file into the array
		while(arr.hasNextLine()){

			long isbn = arr.nextLong();
			String title = arr.next();
			int issueYear = arr.nextInt();
			String authorName = arr.next();
			double price = arr.nextDouble();
			int numofPages = arr.nextInt();
			bkArr[i] = new Book(isbn, title, issueYear, authorName, price, numofPages);
			i++;
		}}
		catch(FileNotFoundException e){
			System.out.println("File not Found! Program will now exit!");
			System.exit(0);
		}
		arr.close();

		//getting the parameters for the searches, and doing them
		boolean search = true;
		do{
			Scanner kb = new Scanner(System.in);
			System.out.println("Do you wish to search for a book? (yes or no)");
			String ans = kb.next();
			ans = ans.toUpperCase();
			while(!ans.equals("YES") && !ans.equals("Y") && !ans.equals("NO") && !ans.equals("N")){
				System.out.println("Invalid Answer! Please enter valid answer! (yes or no)");
				ans = kb.next();
				ans = ans.toUpperCase();
			}
			if(ans.equals("YES") || ans.equals("Y")){
				System.out.println("Enter the starting index: (between 0-" + bkArr.length + ")");
				int start = kb.nextInt();
				while(start < 0){
					System.out.println("Invalid! Please enter a valid starting index: (between 0-" + bkArr.length + ")");
					start = kb.nextInt();
				}
				System.out.println("Enter the ending index: (between 0-" + bkArr.length + ")");
				int end = kb.nextInt();
				while(end > bkArr.length - 1){
					System.out.println("Invalid! Please enter a valid ending index: (between 0-" + bkArr.length + ")");
					end = kb.nextInt();
				}
				System.out.println("Enter the isbn:");
				long isbn = kb.nextLong();

				//doing a binary search
				binaryBookSearch(bkArr, start, end, isbn);

				//doing a sequential search
				sequentialBookSearch(bkArr, start, end, isbn);
			}
			else{				
				System.out.println("You have chosen not to search.");
				System.out.println();
				search = false;

				kb.close();
			}
		}
		while(search);

		//turning the array into a binary file
		ObjectOutputStream sortedBinary = null;
		try{
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

		long isbn = 0;
		String title = null;
		int issueYear = 0;
		String authorName = null;
		double price = 0;
		int numofPages = 0;
		String ans = null;
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
				System.out.println("Please enter the title (seperate words by \"_\"):");
				title = kb.next();
				System.out.println("Please enter the issue year:");
				issueYear = kb.nextInt();
				System.out.println("Please enter the author's name:(seperate words by \"_\")");
				authorName = kb.next();
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
		BufferedReader filereader = null;

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

		int middle;
		int high = arr.length - 1;
		int i = 0;

		while(end <= high) {
			i++;

			middle = (start + end) >>> 1; 
			if (isbn > arr[middle].getIsbn()){
				end = middle + 1;
			} else if (isbn < arr[middle].getIsbn()){
				high = middle - 1;
			} else {
				// found the element
				System.out.println("Using binary search, we found this book: " + arr[middle]);
				System.out.println("ISBN found at index: " + i);
				return;
			}
		}

		System.out.println("\nNo book found.");
		return;






		/*--------------------------------------------------------------------
		 * int counter = 0;		
		int search = (start + end) >>> 1;

		System.out.println("Doing a binary search.");

		if(arr[search].getIsbn() <= isbn){
			for(int i = search; i <= end; i++){
				if(arr[i].getIsbn() == isbn){
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
				if(arr[i].getIsbn() == isbn){
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
		------------------------------------------------------------
		 */
	}

	//sequential search to find the desired isbn
	public static void sequentialBookSearch(Book[] arr, int start, int end, long isbn){
		int counter = 0;

		System.out.println("Doing a sequential search.");

		for(int i = start; i <= end; i++){
			if(arr[i].getIsbn() == isbn){
				System.out.println("ISBN found at index: " + i + "\nUusing a sequential search, we found this book: \n" + arr[i]);
				counter++;
				return;
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
		BufferedReader filereader = null;


		try{
			filereader = new BufferedReader(new FileReader(sortedFile));
			String line = filereader.readLine();

			while(line != null)
			{
				line = filereader.readLine();
				numofRecords++;
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

		return numofRecords;
	}

}