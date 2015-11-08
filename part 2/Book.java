package part2;

import java.io.Serializable;

public class Book implements Serializable{
	
	private long isbn;
	private String title;
	private int issueYear;
	private String authorName;
	private double price;
	private int numofPages;
	
	public Book(){
		isbn = 0;
		title = null;
		issueYear = 0;
		authorName = null;
		price = 0.0;
		numofPages = 0;	
	}
	
	public Book(long iSBN, String thetitle, int issueyear, String authorname, double theprice, int numofpages){
		isbn = iSBN;
		title = thetitle;
		issueYear = issueyear;
		authorName = authorname;
		price = theprice;
		numofPages = numofpages;
	}
	
	public long getISBN(){
		return this.isbn;
	}
}
