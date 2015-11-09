import java.util.Locale;
import java.util.InputMismatchException;
import java.io.*;
import java.util.Scanner;

public class BookInventory2{
	public static void main(String[] args){
		//addRecords("/home/feasinde/Escritorio/new_record.txt");
		//displayFileContents("/home/feasinde/Escritorio/new_record.txt");
		//int index1 = binaryBookSearch();
		//int index2 = sequentialBookSearch();



		//////////////////////////////////////////////////////////////////////////////////////
		//Uncomment the following block to populate an array to test the search methods with//
		//////////////////////////////////////////////////////////////////////////////////////
		// Scanner inputStream = null;
		// Book[] bkArr = new Book[9];

		// try{												
		// 	inputStream = new Scanner(new FileReader("Initial_Book_Info.txt"));
		// 	inputStream.useLocale(Locale.US);	
		// 	for(int i=0;i<bkArr.length; i++){
		// 		bkArr[i] = new Book(inputStream.nextLong(),inputStream.next(),inputStream.nextInt(),
		// 			inputStream.next(),inputStream.nextDouble(),inputStream.nextInt());	
		// 									}
		// }		
		// catch(IOException e){
		// 	System.out.println("File not found!");
		// 	System.exit(0);
		// }
		//////////////////////////////////////////////////////////////////////////////////////
		//
	}

	public static void addRecords(String outputFileName){
		Scanner kb = new Scanner(System.in);
		boolean anotherBook = true;
		PrintWriter outputStream = null;
		try{
			outputStream = new PrintWriter(new FileOutputStream(outputFileName));
		}
		catch(Exception e){
			System.out.println("File not found");
			System.exit(0);
		}
		long isbn = 0;
		String title = null;
		int year = 0;
		String author = null;
		double price = 0.0;
		int pages = 0;
		do{
			System.out.println("Please enter the information of the new book:");
			System.out.print("ISBN: ");
			boolean done = false;
			while(!done){
				try{
					isbn = kb.nextLong();
					done = true;
				}
				catch(InputMismatchException e){
					System.out.println("\nPlease insert a valid ISBN.\n");
					kb.next();
				}
			}
			System.out.print("Title (separated by underscores, eg Title_of_the_book): ");
			done = false;	
			while(!done){
				try{
					title = kb.next();
					done = true;
				}
				catch(InputMismatchException e){
					System.out.println("\nPlease insert a valid title.\n");
					kb.next();
				}
			}
			System.out.print("Year of publication: ");
			done = false;
			while(!done){
				try{
					year = kb.nextInt();
					done = true;
				}
				catch(InputMismatchException e){
					System.out.println("\nPlease insert a valid year of publication.\n");
					kb.next();
				}
			}
			System.out.print("Author: ");
			done = false;
			while(!done){
				try{
					author = kb.next();
					done = true;
				}
				catch(InputMismatchException e){
					System.out.println("\nPlease insert a valid author's name.\n");
					kb.next();
				}
			}
			System.out.print("Price: ");
			done = false;
			while(!done){
				try{
					kb.useLocale(Locale.US);
					price = kb.nextDouble();
					done = true;
				}
				catch(InputMismatchException e){
					System.out.println("\nPlease insert a valid price.\n");
					kb.next();
				}
			}
			System.out.print("Number of pages: ");
			done = false;
			while(!done){
				try{
					pages = kb.nextInt();
					done = true;
				}
				catch(InputMismatchException e){
					System.out.println("\nPlease insert a valid number of pages.\n");
					kb.next();
				}
			}
			System.out.println("Do you wish to enter another record? (y/n)");
			String option = null;
			done = false;
			while(!done){
				try{
					option = kb.next();
					if(option.equals("y")){
						anotherBook = true;
						break;
					}
					else if(option.equals("n")){
						anotherBook = false;
						break;
					}
					else{
						throw new Exception();
					}
				}
				catch(Exception e){
					System.out.println("Please enter 'y' or 'n'");
					kb.next();
				}
			}
			Book record = new Book(isbn, title, year, author, price, pages);
			outputStream.println(record);
		}
		while(anotherBook == true);
		outputStream.close();
	}

	public static void displayFileContents(String inputFileName){
		BufferedReader bufferedReader = null;
		String line = null;
		try{
			bufferedReader = new BufferedReader(new FileReader(inputFileName));
			while ((line = bufferedReader.readLine()) != null){
				System.out.println(line);
			}
		}	
		catch(Exception e){
			e.printStackTrace();
		}		
	}

	public static int binaryBookSearch(Book[] bkArray, int start, int finish, long isbn){
		while(start <= finish){
			int midpoint = (start + finish)/2;
			if (bkArray[midpoint].getIsbn() == isbn){
				return midpoint;
			}
			else if(bkArray[midpoint].getIsbn() < isbn){
				start++;
			}
			else if(bkArray[midpoint].getIsbn() > isbn){
				finish--;
			}
			
		}
		return -1;
	}

	public static int sequentialBookSearch(Book[] bkArray, int start, int finish, long isbn){
		for(int i = start; i < finish; i++){
			// System.out.println(bkArray[i].getIsbn());
			if (bkArray[i].getIsbn() == isbn){
				return i;
			}
		}
		return -1;

	}
}