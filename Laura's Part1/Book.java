//LAURAS VERSION
//LAURAS VERSION
public class Book {
	
	private long isbn;
	private String title;
	private String authorName;
	private int issueYear;
	private int numOfPages;
	private double price;
	
	public Book(){
		isbn = 0;
		title = null;
		authorName = null;
		issueYear = 0;
		numOfPages = 0;
		price = 0;
	}
	
	public Book(long isbn, String title, int issueYear, String authorName, double price, int numOfPages){
		this.isbn = isbn;
		this.title =title;
		this.authorName = authorName;
		this.issueYear = issueYear;
		this.numOfPages = numOfPages;
		this.price = price;
	}
	
	public long getIsbn(){
		return this.isbn;
	}
	public void setIsbn(long isbn){
		this.isbn = isbn;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public String getAuthorName(){
		return this.authorName;
	}
	
	public int getIssueYear(){
		return this.issueYear;
	}
	
	public int getNumOfPages(){
		return this.numOfPages;
	}
	
	public double getPrice(){
		return this.price;
	}
	public boolean equals(Book compareIsbn){
		return (this.isbn==compareIsbn.getIsbn());
	}
	
	public String toString(){
		return (this.isbn + " " + this.title +" by " + this.authorName +", "
				+ this.issueYear + ", " + this.price +"$.");
	}
	

}
