public class Book{
	private long isbn;
	private String title;
	private int issue_year;
	private String authors_name;
	private double price;
	private int number_of_pages;

	//default constructor with no parameters
	public Book(){
		this.isbn = 0;
		this.title = null;
		this.issue_year = 0;
		this.authors_name = null;
		this.price = 0;
		this.number_of_pages = 0;
	}
	//parametrised constructor
	public Book(long isbn, String title, int issue_year, String authors_name, double price, int number_of_pages){
		this.isbn = isbn;
		this.title = title;
		this.issue_year = issue_year;
		this.authors_name = authors_name;
		this.price = price;
		this.number_of_pages = number_of_pages;	
	}

	//What follows is all the accessors and mutaror methods
	//for each of the class attributes

	public long getIsbn(){
		return this.isbn;
	}
	public void setIsbn(long isbn){
		this.isbn = isbn;
	}

	public String getTitle(){
		return this.title;
	}
	public int getIssueYear(){
		return this.issue_year;
	}
	public String getAuthorsName(){
		return this.authors_name;
	}
	public double getPrice(){
		return this.price;
	}
	public int getNumberOfPages(){
		return this.number_of_pages;
	}
	public String toString(){
		return this.isbn + " "+ this.title +" "+ this.issue_year + " "+ this.authors_name+" "+this.price+" "+this.number_of_pages;
	}
}	