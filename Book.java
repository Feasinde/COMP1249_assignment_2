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
	
}	