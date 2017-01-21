import java.io.Serializable;
import java.util.List;

/**
 * The Student class is part of our Data Model in the MVC design pattern. It
 * includes all fields that constitute a single 'Book' as well as
 * restrictions on those fields. Notice all fields are private, so user's only
 * choice is to use the setter methods, which lets us insure userId and year are
 * valid.
 * 
 */
public class Book implements Serializable {

	private static final long serialVersionUID = -2961068427440769216L;
	private String bookTitle;
	private String isbn_no;
	private String authorName;
	private String publisher;
	private int year;
	private String genre;
	private String sellerName;
	private double price;
	private double buynow;
	private String bookCondition;
	private int quantity;
	private int rating;
	public List<String> comments;
	
	public Book() {

	}

	/**
	 * 
	 * @param bookName
	 * @param price
	 * @param authorName
	 * @param publisher
	 * @param year
	 * @param isbn_no
	 * @param major
	 * @param bookCondition
	 */
	public Book(String isbn, String btitle, String aname, String pname, String genre, int year, String sname, double price, double buynow, int quantity, String bcond) {
		this.isbn_no = isbn;
		this.bookTitle = btitle;
		this.authorName = aname;
		this.publisher = pname;
		this.genre = genre;
		this.year = year;
		this.sellerName = sname;
		this.quantity = quantity;
		this.price = price;
		this.buynow = buynow;
		this.bookCondition = bcond;

	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	

	public String getGenre() {
		return genre;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public int getRating() {
		return this.rating;
	}

	/*
	public void set_ISBNNo(int i) {
		if (i > 999999999999 && i < 10000000000000)
			isbn_no = i;
		else
			throw new IllegalArgumentException("ISBN NO  is not valid.");
	}*/

	public void set_ISBNNo(String s) {
		this.isbn_no = s;
	}
	
	public String get_ISBNNo() {
		return isbn_no;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String title) {
		this.bookTitle = title;
	}


	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String name) {
		this.authorName = name;
	}
	

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String name) {
		this.publisher = name;
	}
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		if (price < 0 )
			throw new IllegalArgumentException(
					"Price must be non-ngetavie value atleast it should be zero (0) inclusive");
		this.price = price;
	}

	public double getBuyNowPrice() {
		return buynow;
	}

	public void setBuyNowPrice(double buynow) {
		if (buynow < 0 )
			throw new IllegalArgumentException(
					"Buy Now Price must be non-ngetavie value atleast it should be zero (0) inclusive");
		this.buynow = buynow;
	}
	
	public void setYear(int year) {
		if (year < 1000)
			throw new IllegalArgumentException("Year must be 4 digits");
		this.year = year;
	}

	public int getYear() {
		return this.year;
	}

	public String getSellerName() {
		return this.sellerName;
	}
	
	public void setSellerName(String name) {
		this.sellerName = name;
	}
	
	public String getBookCondition() {
		return this.bookCondition;
	}
	
	public void setBookCondition(String cond) {
		this.bookCondition = cond;
	}
	
	public boolean equals(Book other) {
		return (this.isbn_no.equals(other.get_ISBNNo()) &&
				this.bookTitle.equals(other.getBookTitle()) &&
				this.authorName.equals(other.getAuthorName()) &&
				this.publisher.equals(other.getPublisher()) &&
				this.genre.equals(other.getGenre()) &&
				this.year==other.getYear() &&
				this.sellerName.equals(other.getSellerName()) &&
				this.bookCondition.equals(other.getBookCondition()) &&
				this.price==other.getPrice());
	}
	
	public Book getBook() {
		return this;
	}
	
	@Override
	public String toString() {
		return bookTitle + " " + authorName + " " + publisher + " " + this.isbn_no + " " + this.genre + " " + this.year + " " + this.sellerName + " " + this.price + " " + this.bookCondition;
	}

}