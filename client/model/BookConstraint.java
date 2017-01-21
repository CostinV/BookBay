/**
 * This very simple class just represents a set of constraints to be used when
 * searching for Books. So, it is a very small piece of our Data Model in the
 * MVC pattern. The default values correspond to searching for all Books.
 * 
 */
public class BookConstraint {
	/**
	 * The ISBN for Books in this search
	 */
	public String isbn = "";
	/**
	 * The maximum ISBNNo for Books in this search. A value of -1 implies we
	 * don't want to have an upper limit on ISBNNo
	 */

	/**
	 * The book name, or part of a book we are searching for.
	 */
	public String bookTitle = "";
	/**
	 * Set to true if the book name string must be an exact match. If false, the web
	 * service returns books whose names include BookConstraint.bookname as a
	 * substring
	 */
	public boolean btitleExact = false;
	/**
	 * Minimum Price for books included in our search results throws an error message 
	 * if the value entered is < 0.
	 */
	public double minPrice = 0;
	/**
	 * Maximum price for Books included in our search results, A value of -1 implies we
	 * don't want to have an upper limit on price
	 */
	public double maxPrice = -1;
	/**
	 * Minimum Year for Books included in our search results
	 */
	public int minYear = 0;
	/**
	 * Maximum Year for books included in our search results, A value of -1 implies we
	 * don't want to have an upper limit on maxYear
	 */
	public int maxYear = -1;
	/**
	 * Major, or part of the major for Books included in our search results
	 */
	public String genre = "";
	/**
	 * Set to true if the genre string must be an exact match. If false, the web
	 * service returns Books whose genre include BookConstraint.majorName
	 * as a substring
	 */
	public boolean genreExact = false;
	
	public  String publisher = "";
	/**
	 * Set to true if the book publisher name string must be an exact match. If false, the web
	 * service returns books whose names include BookConstraint.bookname as a
	 * substring
	 */
	public boolean publisherExact = false;
	
	
	public String author = "";
	/**
	 * Set to true if the book author name string must be an exact match. If false, the web
	 * service returns books whose names include BookConstraint.bookname as a
	 * substring
	 */
	public boolean authorExact = false;
	
	public String seller = "";
	
	public boolean sellerExact = false;
	
}
