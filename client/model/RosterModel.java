import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

/**
 * The bookListModel class defines part of our Model in the MVC design pattern.
 * All interaction with our core data will happen through this class. If we ever
 * needed to change the way data is handled, maybe by storing in a local file or
 * database, we would only need to alter this class. Our controller and views
 * wouldn't need to be altered at all.
 * 
 */
public class RosterModel {

	/**
	 * tmpbookList will hold batches of Book objects that we download from the
	 * server
	 */
	private List<Book> bookList = new ArrayList<Book>();
	/**
	 * used to perform http requests with the web service
	 */
	DefaultHttpClient httpclient = new DefaultHttpClient();

	/**
	 * The IP address of the webservice
	 */
	private static final String SERVERHOST = "localhost";
	/**
	 * The path to the webservice script on the server
	 */
	private static final String SERVERPATH = "/BookBay/BookServer.php";
	/**
	 * The server ip address and path combined as a url address
	 */
	private static final String SERVERURL = "http://127.0.0.1/BookBay/BookServer.php";

	/**
	 * Search the list of local results for the Book with ssn and return it.
	 * 
	 * @param ssn
	 *            the ssn to find
	 * @return the corresponding Book or null if not found locally
	 */
	
	/*
	public Book getBook(Book b) {
		for (Book s : bookList) {
			if (s.equals(b))
				return s;
		}
		return null;
	}*/

	/**
	 * Makes an HTTP GET request to the BookServer to return all Books.
	 * This method is fully functional, you do not need to change it, but can
	 * use it as an example for how to finish getBooks(BookConstraint)
	 * 
	 * @return A list of all Book objects
	 */
	public List<Book> getAllBooks() {
		bookList = new ArrayList<Book>();

		/*
		URIBuilder uriHelper = new URIBuilder();
		uriHelper.setScheme("http");
		uriHelper.setHost(SERVERHOST);
		uriHelper.setPath(SERVERPATH);
		uriHelper.setParameter("getAll", "true");

		HttpGet getRequest;
		Scanner in = null;
		try {
			getRequest = new HttpGet(uriHelper.build().toString());

			HttpResponse resp;

			resp = httpclient.execute(getRequest);

			in = new Scanner(new InputStreamReader(resp.getEntity().getContent()));

			while (in.hasNextLine()) {
				Book tmpBook = new Book();
				tmpBook.set_ISBNNo(in.nextLine());
				tmpBook.setYear(Integer.parseInt(in.nextLine()));
				tmpBook.setPrice(Double.parseDouble(in.nextLine()));
				tmpBook.setBookTitle(in.nextLine());
				tmpBook.setAuthorName(in.nextLine());
				tmpBook.setPublisher(in.nextLine());
				tmpBook.setGenre(in.nextLine());
				bookList.add(tmpBook);
			}

		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (in != null)
			in.close();
		*/
		return bookList;
		
	}

	/**
	 * Makes an HTTP GET request to the BookServer to return all Books
	 * subject to the search constraints.
	 * 
	 * completed this method by instantiating a new array list of Books and
	 * making a GET request to the web service to fetch all Books matching
	 * the constraints. Create Book objects to hold the records returned by
	 * the web service, add them to the arraylist, and return it.
	 * 
	 * @param sc
	 *            The search constraints
	 * @return A list of Book objects matching the search constraints
	 */
	
	public Book getBook(Book b, String display_name) {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("get_one", "true"));
		nameValuePairs.add(new BasicNameValuePair("display_name", display_name));
		nameValuePairs.add(new BasicNameValuePair("isbn", b.get_ISBNNo()));
		nameValuePairs.add(new BasicNameValuePair("seller_name", b.getSellerName()));
		nameValuePairs.add(new BasicNameValuePair("condition", b.getBookCondition()));
		
		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpPost httppost = new HttpPost(SERVERURL);

		//String result = null;
		
		Book tmpBook = null;

		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse resp = httpclient.execute(httppost);
			Scanner in = new Scanner(resp.getEntity().getContent());

			if (in.hasNextLine()) {
				
				tmpBook = new Book();
				tmpBook.comments = new ArrayList<String>();
				tmpBook.set_ISBNNo(in.nextLine());
				tmpBook.setBookTitle(in.nextLine());
				tmpBook.setAuthorName(in.nextLine());
				tmpBook.setYear(Integer.parseInt(in.nextLine()));
				tmpBook.setPublisher(in.nextLine());
				tmpBook.setGenre(in.nextLine());
				tmpBook.setSellerName(in.nextLine());
				tmpBook.setPrice(Double.parseDouble(in.nextLine()));
				tmpBook.setQuantity(Integer.parseInt(in.nextLine()));
				tmpBook.setBookCondition(in.nextLine());
				tmpBook.setBuyNowPrice(Double.parseDouble(in.nextLine()));
				tmpBook.setRating(Integer.parseInt(in.nextLine()));
				String s = in.nextLine();
				while (!s.contentEquals("done")) {
					tmpBook.comments.add(s);
					s = in.nextLine();
				}
				
				//System.out.println(tmpBook.comments);
				/*
				while (in.hasNextLine())
					System.out.println(in.nextLine());
					*/
			}

			in.close();
		
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tmpBook;
		
	}
	
	public boolean unsuspendBook(Book b) {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("unsuspend_book", "true"));;
		nameValuePairs.add(new BasicNameValuePair("isbn", b.get_ISBNNo()));
		nameValuePairs.add(new BasicNameValuePair("seller_name", b.getSellerName()));
		
		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpPost httppost = new HttpPost(SERVERURL);

		String result = null;
		
		

		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse resp = httpclient.execute(httppost);
			Scanner in = new Scanner(resp.getEntity().getContent());

			if (in.hasNext())
				result = in.next();

			in.close();
		
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		if (result != null && result.contentEquals("true"))
			return true;

		return false;
		
	}
	
	public List<Book> getBooks(BookConstraint sc) {
		bookList = new ArrayList<Book>();

		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("search", "true"));
		//uriHelper.setParameter("minISBNNo", Integer.toString(sc.minISBNNo));
		nameValuePairs.add(new BasicNameValuePair("isbn", sc.isbn));
		nameValuePairs.add(new BasicNameValuePair("title", sc.bookTitle));
		if (sc.btitleExact == true)
			nameValuePairs.add(new BasicNameValuePair("titleExact", "true"));
		nameValuePairs.add(new BasicNameValuePair("author", sc.author));
		if (sc.authorExact == true)
			nameValuePairs.add(new BasicNameValuePair("authorExact", "true"));
		nameValuePairs.add(new BasicNameValuePair("publisher", sc.publisher));
		if (sc.publisherExact == true)
			nameValuePairs.add(new BasicNameValuePair("publisherExact", "true"));
		nameValuePairs.add(new BasicNameValuePair("minPrice", Double.toString(sc.minPrice)));
		nameValuePairs.add(new BasicNameValuePair("maxPrice", Double.toString(sc.maxPrice)));
		nameValuePairs.add(new BasicNameValuePair("minYear", Integer.toString(sc.minYear)));
		nameValuePairs.add(new BasicNameValuePair("maxYear", Integer.toString(sc.maxYear)));
		nameValuePairs.add(new BasicNameValuePair("genre", sc.genre));
		if (sc.genreExact == true)
			nameValuePairs.add(new BasicNameValuePair("genreExact", "true"));
		nameValuePairs.add(new BasicNameValuePair("seller_name", sc.seller));
		if (sc.sellerExact == true)
			nameValuePairs.add(new BasicNameValuePair("sellerExact", "true"));

		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpPost httppost = new HttpPost(SERVERURL);

		//String result = null;

		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse resp = httpclient.execute(httppost);
			Scanner in = new Scanner(resp.getEntity().getContent());

			while (in.hasNextLine()) {
				
				
				Book tmpBook = new Book();
				tmpBook.set_ISBNNo(in.nextLine());
				tmpBook.setBookTitle(in.nextLine());
				tmpBook.setAuthorName(in.nextLine());
				tmpBook.setYear(Integer.parseInt(in.nextLine()));
				tmpBook.setPublisher(in.nextLine());
				tmpBook.setGenre(in.nextLine());
				tmpBook.setSellerName(in.nextLine());
				tmpBook.setPrice(Double.parseDouble(in.nextLine()));
				tmpBook.setQuantity(Integer.parseInt(in.nextLine()));
				tmpBook.setBookCondition(in.nextLine());
				tmpBook.setBuyNowPrice(Double.parseDouble(in.nextLine()));
				tmpBook.setRating(Integer.parseInt(in.nextLine()));
				bookList.add(tmpBook);
				
				//System.out.println(in.nextLine());
			}

			in.close();
		
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bookList;
	}
	
	public List<Book> getTopBooks() {
		bookList = new ArrayList<Book>();

		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("get_top_books", "true"));
		
		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpPost httppost = new HttpPost(SERVERURL);

		//String result = null;

		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse resp = httpclient.execute(httppost);
			Scanner in = new Scanner(resp.getEntity().getContent());

			while (in.hasNextLine()) {
				
				Book tmpBook = new Book();
				tmpBook.set_ISBNNo(in.nextLine());
				tmpBook.setBookTitle(in.nextLine());
				tmpBook.setAuthorName(in.nextLine());
				tmpBook.setYear(Integer.parseInt(in.nextLine()));
				tmpBook.setPublisher(in.nextLine());
				tmpBook.setGenre(in.nextLine());
				tmpBook.setSellerName(in.nextLine());
				tmpBook.setPrice(Double.parseDouble(in.nextLine()));
				tmpBook.setQuantity(Integer.parseInt(in.nextLine()));
				tmpBook.setBookCondition(in.nextLine());
				tmpBook.setBuyNowPrice(Double.parseDouble(in.nextLine()));
				tmpBook.setRating(Integer.parseInt(in.nextLine()));
				bookList.add(tmpBook);
				
				//System.out.println(in.nextLine());
			}

			in.close();
		
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bookList;
	}
	
	public List<Book> getBids(String display_name, boolean buysell) {
		bookList = new ArrayList<Book>();

		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("get_bids", "true"));
		if (buysell)
			nameValuePairs.add(new BasicNameValuePair("buysell", "buy"));
		else
			nameValuePairs.add(new BasicNameValuePair("buysell", "sell"));
		nameValuePairs.add(new BasicNameValuePair("display_name", display_name));

		
		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpPost httppost = new HttpPost(SERVERURL);

		//String result = null;

		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse resp = httpclient.execute(httppost);
			Scanner in = new Scanner(resp.getEntity().getContent());
			
			if (buysell) {
			
				while (in.hasNextLine()) {
					
					Book tmpBook = new Book();
					tmpBook.set_ISBNNo(in.nextLine());
					tmpBook.setBookTitle(in.nextLine());
					tmpBook.setSellerName(in.nextLine());
					tmpBook.setBookCondition(in.nextLine());
					tmpBook.setPrice(Double.parseDouble(in.nextLine()));
					String hb = in.nextLine();
					if (hb.equals("1"))
						tmpBook.setQuantity(1);
					else
						tmpBook.setQuantity(0);
					bookList.add(tmpBook);
					
					//System.out.println(in.nextLine());
				}
			} else {
				while (in.hasNextLine()) {
					
					Book tmpBook = new Book();
					tmpBook.set_ISBNNo(in.nextLine());
					tmpBook.setBookTitle(in.nextLine());
					tmpBook.setSellerName(in.nextLine());
					tmpBook.setBookCondition(in.nextLine());
					tmpBook.setPrice(Double.parseDouble(in.nextLine()));
					bookList.add(tmpBook);
					
					//System.out.println(in.nextLine());
				}
			}
			/*
			while (in.hasNextLine())
				System.out.println(in.nextLine());
			*/
			in.close();
		
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bookList;
	}
	
	public List<Book> getBidHistory(String display_name) {
		bookList = new ArrayList<Book>();

		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("get_bid_history", "true"));
		nameValuePairs.add(new BasicNameValuePair("display_name", display_name));

		
		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpPost httppost = new HttpPost(SERVERURL);

		//String result = null;

		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse resp = httpclient.execute(httppost);
			Scanner in = new Scanner(resp.getEntity().getContent());
			
				while (in.hasNextLine()) {
					
					Book tmpBook = new Book();
					tmpBook.set_ISBNNo(in.nextLine());
					tmpBook.setBookTitle(in.nextLine());
					tmpBook.setSellerName(in.nextLine());
					tmpBook.setBookCondition(in.nextLine());
					tmpBook.setPrice(Double.parseDouble(in.nextLine()));
					bookList.add(tmpBook);
					
					//System.out.println(in.nextLine());
				}

			/*
			while (in.hasNextLine())
				System.out.println(in.nextLine());
			*/
			in.close();
		
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bookList;
	}
	
	public boolean addCredits(User u) {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("add_credits", "true"));
		nameValuePairs.add(new BasicNameValuePair("display_name", u.getDisplayName()));
		nameValuePairs.add(new BasicNameValuePair("credits", Double.toString(u.getCredits())));

		
		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpPost httppost = new HttpPost(SERVERURL);

		String result = null;

		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse resp = httpclient.execute(httppost);
			Scanner in = new Scanner(resp.getEntity().getContent());
			
			if (in.hasNext()) 
				result = in.next();
				
			/*
			while (in.hasNextLine())
				System.out.println(in.nextLine());
			*/
			in.close();
		
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (result != null && result.contentEquals("true"))
			return true;

		return false;
	}
	
	public boolean unsuspendUser(String display_name) {

		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("unsuspend_user", "true"));
		nameValuePairs.add(new BasicNameValuePair("display_name", display_name));

		
		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpPost httppost = new HttpPost(SERVERURL);

		String result = null;

		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse resp = httpclient.execute(httppost);
			Scanner in = new Scanner(resp.getEntity().getContent());
			
			if (in.hasNext()) 
				result = in.next();
			 
			/*
			while (in.hasNextLine())
				System.out.println(in.nextLine());
			*/
			in.close();
		
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (result != null && result.contentEquals("true"))
			return true;

		return false;
	}
	
	public List<Book> getBrowseHistory(String display_name) {
		bookList = new ArrayList<Book>();

		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("get_browse_history", "true"));
		nameValuePairs.add(new BasicNameValuePair("display_name", display_name));

		
		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpPost httppost = new HttpPost(SERVERURL);

		//String result = null;

		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse resp = httpclient.execute(httppost);
			Scanner in = new Scanner(resp.getEntity().getContent());
			
				while (in.hasNextLine()) {
					
					Book tmpBook = new Book();
					tmpBook.set_ISBNNo(in.nextLine());
					tmpBook.setBookTitle(in.nextLine());
					tmpBook.setAuthorName(in.nextLine());
					tmpBook.setYear(Integer.parseInt(in.nextLine()));
					tmpBook.setPublisher(in.nextLine());
					tmpBook.setGenre(in.nextLine());
					tmpBook.setSellerName(in.nextLine());
					bookList.add(tmpBook);
					
					//System.out.println(in.nextLine());
				}

			/*
			while (in.hasNextLine())
				System.out.println(in.nextLine());
			*/
			in.close();
		
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bookList;
	}
	
	public List<Review> getReviews(String display_name, boolean buysell, boolean reviewed) {
		List<Review> reviewList = new ArrayList<Review>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("get_reviews", "true"));
		if (buysell)
			nameValuePairs.add(new BasicNameValuePair("buysell", "buy"));
		else
			nameValuePairs.add(new BasicNameValuePair("buysell", "sell"));
		if (reviewed)
			nameValuePairs.add(new BasicNameValuePair("reviewed", "1"));
		else
			nameValuePairs.add(new BasicNameValuePair("reviewed", "0"));
		nameValuePairs.add(new BasicNameValuePair("display_name", display_name));

		
		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpPost httppost = new HttpPost(SERVERURL);

		//String result = null;

		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse resp = httpclient.execute(httppost);
			Scanner in = new Scanner(resp.getEntity().getContent());
			
			if (buysell) {
				if (reviewed) {
					while (in.hasNextLine()) {
						Review review = new Review();
						review.book_isbn = in.nextLine();
						review.book_title = in.nextLine();
						review.book_condition = in.nextLine();
						review.seller_name = in.nextLine();
						review.book_stars = Integer.parseInt(in.nextLine());
						
						String comments = "";
						String s = in.nextLine();
						while (!s.contentEquals("break")){
							comments += s;
							s = in.nextLine();
						}	
						review.book_comments = comments;
						
						review.seller_stars = Integer.parseInt(in.nextLine());
						
						comments = "";
						s = in.nextLine();
						while (!s.contentEquals("break")){
							comments += s;
							s = in.nextLine();
						}	
						review.seller_comments = comments;
						
						review.buyer_stars = Integer.parseInt(in.nextLine());
						
						comments = "";
						s = in.nextLine();
						while (!s.contentEquals("break")){
							comments += s;
							s = in.nextLine();
						}	
						review.buyer_comments = comments;
						
						reviewList.add(review);
						//System.out.println(in.nextLine());
					}
				} else {
					while (in.hasNextLine()) {
						Review review = new Review();
						review.book_isbn = in.nextLine();
						review.book_title = in.nextLine();
						review.book_condition = in.nextLine();
						review.seller_name = in.nextLine();
						reviewList.add(review);
					}
				}
			} else {
				if (reviewed) {
					while (in.hasNextLine()) {
						Review review = new Review();
						review.book_isbn = in.nextLine();
						review.book_title = in.nextLine();
						review.book_condition = in.nextLine();
						review.buyer_name = in.nextLine();
						review.book_stars = Integer.parseInt(in.nextLine());
						
						String comments = "";
						String s = in.nextLine();
						while (!s.contentEquals("break")) {
							comments += s;
							s = in.nextLine();
						}	
						review.book_comments = comments;
						
						review.seller_stars = Integer.parseInt(in.nextLine());
						
						comments = "";
						s = in.nextLine();
						while (!s.contentEquals("break")){
							comments += s;
							s = in.nextLine();
						}	
						review.seller_comments = comments;
						
						review.buyer_stars = Integer.parseInt(in.nextLine());
						
						comments = "";
						s = in.nextLine();
						while (!s.contentEquals("break")){
							comments += s;
							s = in.nextLine();
						}	
						review.buyer_comments = comments;
						
						reviewList.add(review);
						//System.out.println(in.nextLine());
						
					}
				} else {
					while (in.hasNextLine()) {
						Review review = new Review();
						review.book_isbn = in.nextLine();
						review.book_title = in.nextLine();
						review.book_condition = in.nextLine();
						review.buyer_name = in.nextLine();
						reviewList.add(review);
					}
				}
			}
			
			/*
			while (in.hasNextLine())
				System.out.println(in.nextLine());
			*/
			in.close();
		
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return reviewList;
	}


	/**
	 * Makes an HTTP POST request to alter the info for the Book s. This
	 * method is fully functional and doesn't need to be changed. But, you can
	 * use this as an example on how to make a POST request and finish the
	 * add(Book) method.
	 * 
	 * @param s
	 *            The Book's information
	 * @return true on success, false on failure
	 */
	public boolean update(Book s) {

		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("isbn", s.get_ISBNNo()));
		nameValuePairs.add(new BasicNameValuePair("title", s.getBookTitle()));
		nameValuePairs.add(new BasicNameValuePair("author", s.getAuthorName()));
		nameValuePairs.add(new BasicNameValuePair("year", Integer.toString(s.getYear())));
		nameValuePairs.add(new BasicNameValuePair("publisher", s.getPublisher()));
		nameValuePairs.add(new BasicNameValuePair("genre", s.getGenre()));
		nameValuePairs.add(new BasicNameValuePair("seller_name", s.getSellerName()));
		nameValuePairs.add(new BasicNameValuePair("price", Double.toString(s.getPrice())));
		nameValuePairs.add(new BasicNameValuePair("buynow", Double.toString(s.getBuyNowPrice())));
		nameValuePairs.add(new BasicNameValuePair("quantity", Integer.toString(s.getQuantity())));
		nameValuePairs.add(new BasicNameValuePair("condition", s.getBookCondition()));
		nameValuePairs.add(new BasicNameValuePair("edit", "true"));

		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpPost httppost = new HttpPost(SERVERURL);

		String result = null;

		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse resp = httpclient.execute(httppost);
			Scanner in = new Scanner(resp.getEntity().getContent());

			if (in.hasNext())
				result = in.next();
			/*
			while (in.hasNextLine())
				System.out.println(in.nextLine());
			*/
			
			in.close();
			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (result != null && result.contentEquals("true"))
			return true;

		return false;
	}
	
	public boolean update(Book s, File imageFile) {
		String r = null;
		if (!update(s))
			return false;
		r = setImage(s, imageFile);
		if (r != null && (r.contentEquals("Already Exists") || r.contentEquals("Done Uploading")))
			return true;
		return true;
	}
	
	public boolean remove(Book s) {

		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("seller_name", s.getSellerName()));
		nameValuePairs.add(new BasicNameValuePair("isbn", s
				.get_ISBNNo()));
		nameValuePairs.add(new BasicNameValuePair("remove", "true"));

		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpPost httppost = new HttpPost(SERVERURL);

		String result = null;

		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse resp = httpclient.execute(httppost);
			Scanner in = new Scanner(resp.getEntity().getContent());

			if (in.hasNext())
				result = in.next();

			in.close();
			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (result != null) {
			if (result.contentEquals("true"))
				return true;
			else if (result.contentEquals("gone")) {
				removeImage(s);
				return true;
			}
		}
		return false;
	}
	
	

	/**
	 * Makes an HTTP POST request to add a new Book to the BookServer.
	 * 
	 * complete this method by making the appropriate POST request to the web
	 * service, checking the result, and returning true if it succeeded, false
	 * otherwise.
	 * 
	 * @param s
	 *            The Book's information
	 * @return true on success, false on failure
	 */
	public boolean add(Book s) {

		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("isbn", s.get_ISBNNo()));
		nameValuePairs.add(new BasicNameValuePair("title", s.getBookTitle()));
		nameValuePairs.add(new BasicNameValuePair("author", s.getAuthorName()));
		nameValuePairs.add(new BasicNameValuePair("year", Integer.toString(s.getYear())));
		nameValuePairs.add(new BasicNameValuePair("publisher", s.getPublisher()));
		nameValuePairs.add(new BasicNameValuePair("genre", s.getGenre()));
		nameValuePairs.add(new BasicNameValuePair("seller_name", s.getSellerName()));
		nameValuePairs.add(new BasicNameValuePair("price", Double.toString(s.getPrice())));
		nameValuePairs.add(new BasicNameValuePair("buynow", Double.toString(s.getBuyNowPrice())));
		nameValuePairs.add(new BasicNameValuePair("quantity", Integer.toString(s.getQuantity())));
		nameValuePairs.add(new BasicNameValuePair("condition", s.getBookCondition()));
		nameValuePairs.add(new BasicNameValuePair("add", "true"));

		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpPost httppost = new HttpPost(SERVERURL);

		String result = null;

		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse resp = httpclient.execute(httppost);
			Scanner in = new Scanner(resp.getEntity().getContent());
			
			if (in.hasNext())
				result = in.next();
			/*
			while (in.hasNextLine())
				System.out.println(in.nextLine());
			*/
			//System.out.println(result);

			in.close();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (result != null && result.contentEquals("true"))
			return true;

		return false;
	}
	
	public String setImage(Book s, File imageFile) {
		String r = null;
		try {
			HttpURLConnection httpUrlConnection = (HttpURLConnection)new URL("http://127.0.0.1/BookBay/ImageServer.php?isbn="+s.get_ISBNNo()+"&add=true").openConnection();
            httpUrlConnection.setDoOutput(true);
        	httpUrlConnection.setRequestMethod("POST");
        	OutputStream os = httpUrlConnection.getOutputStream();
			BufferedInputStream fis = new BufferedInputStream(new FileInputStream(imageFile));
			long totalByte = imageFile.length();
			int byteTransferred = 0;
			for (int i = 0; i < totalByte; i++) {
	            os.write(fis.read());
	            byteTransferred = i + 1;
	        }
			fis.close();
			os.close();
			BufferedReader in2 = new BufferedReader(
	                new InputStreamReader(
	                httpUrlConnection.getInputStream()));
			r = in2.readLine();
			/*
	        while ((r = in2.readLine()) != null) {
	            //System.out.println(r);
	        }*/
	        in2.close();
	       
			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return r;
	}
	
	public boolean removeImage(Book s) {

		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("isbn", s.get_ISBNNo()));
		nameValuePairs.add(new BasicNameValuePair("remove", "true"));

		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpPost httppost = new HttpPost("http://127.0.0.1/BookBay/ImageServer.php");

		String result = null;

		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse resp = httpclient.execute(httppost);
			Scanner in = new Scanner(resp.getEntity().getContent());

			if (in.hasNext())
				result = in.next();
			//System.out.println(result);

			in.close();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (result != null && result.contentEquals("true"))
			return true;

		return false;
	}
	
	public boolean add(Book s, File imageFile) {
		//System.out.println("adding with image");
		
		String r = null;
		if (!add(s))
			return false;
		r = setImage(s, imageFile);
		if (r != null && (r.contentEquals("Already Exists") || r.contentEquals("Done Uploading")))
			return true;

		return false;
	}
	
	public boolean addReview(Review review) {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("isbn", review.book_isbn));
		//nameValuePairs.add(new BasicNameValuePair("title", review.book_title));
		nameValuePairs.add(new BasicNameValuePair("seller_name", review.seller_name));
		nameValuePairs.add(new BasicNameValuePair("condition",review.book_condition));
		nameValuePairs.add(new BasicNameValuePair("buyer_name", review.buyer_name));
		if (review.book_stars != 0)
			nameValuePairs.add(new BasicNameValuePair("book_stars", Integer.toString(review.book_stars)));
		if (review.seller_stars != 0)
			nameValuePairs.add(new BasicNameValuePair("seller_stars", Integer.toString(review.seller_stars)));
		if (review.buyer_stars != 0)
			nameValuePairs.add(new BasicNameValuePair("buyer_stars", Integer.toString(review.buyer_stars)));
		if (review.book_comments != null)
			nameValuePairs.add(new BasicNameValuePair("book_comments", review.book_comments));
		if (review.seller_comments != null)
			nameValuePairs.add(new BasicNameValuePair("seller_comments", review.seller_comments));
		if (review.buyer_comments != null)
			nameValuePairs.add(new BasicNameValuePair("buyer_comments", review.buyer_comments));
		if (review.reviewed_by_buyer != 0)
			nameValuePairs.add(new BasicNameValuePair("reviewed_by_buyer", Integer.toString(review.reviewed_by_buyer)));
		if (review.reviewed_by_seller != 0)
			nameValuePairs.add(new BasicNameValuePair("reviewed_by_seller", Integer.toString(review.reviewed_by_seller)));
		nameValuePairs.add(new BasicNameValuePair("add_review", "true"));

		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpPost httppost = new HttpPost(SERVERURL);
		
		String result = null;
		
		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse resp = httpclient.execute(httppost);
			Scanner in = new Scanner(resp.getEntity().getContent());
			
			if (in.hasNextLine())
				result = in.nextLine();
			/*
			while (in.hasNextLine())
				System.out.println(in.nextLine());
			*/
			//System.out.println(result);
			in.close();
			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (result != null && result.contentEquals("true"))
			return true;

		return false;
	}
	
	public boolean addBid(Book b, String buyer) {
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("isbn", b.get_ISBNNo()));
		nameValuePairs.add(new BasicNameValuePair("title", b.getBookTitle()));
		nameValuePairs.add(new BasicNameValuePair("seller_name", b.getSellerName()));
		nameValuePairs.add(new BasicNameValuePair("condition", b.getBookCondition()));
		nameValuePairs.add(new BasicNameValuePair("buyer_name", buyer));
		nameValuePairs.add(new BasicNameValuePair("bid_amount", Double.toString(b.getPrice())));
		if (b.getPrice() == b.getBuyNowPrice())
			nameValuePairs.add(new BasicNameValuePair("buy_now", "true"));
		else
			nameValuePairs.add(new BasicNameValuePair("buy_now", "false"));
		nameValuePairs.add(new BasicNameValuePair("post_bid", "true"));

		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpPost httppost = new HttpPost(SERVERURL);
		
		String result = null;
		
		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse resp = httpclient.execute(httppost);
			Scanner in = new Scanner(resp.getEntity().getContent());
			
			if (in.hasNextLine())
				result = in.nextLine();
			/*
			while (in.hasNextLine())
				System.out.println(in.nextLine());
			*/
			//System.out.println(result);
			in.close();
			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (result == null)
			return false;
		else if (result.contentEquals("gone")) {
			removeImage(b);
			return true;
		} else if (result.contentEquals("false"))
			return false;
		else
			return true;
	}
	
	public boolean acceptBid(Book b, String buyer) {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("isbn", b.get_ISBNNo()));
		nameValuePairs.add(new BasicNameValuePair("seller_name", b.getSellerName()));
		nameValuePairs.add(new BasicNameValuePair("condition", b.getBookCondition()));
		nameValuePairs.add(new BasicNameValuePair("buyer_name", buyer));
		nameValuePairs.add(new BasicNameValuePair("bid_amount", Double.toString(b.getPrice())));
		nameValuePairs.add(new BasicNameValuePair("accept_bid", "true"));

		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpPost httppost = new HttpPost(SERVERURL);
		
		String result = null;
		
		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse resp = httpclient.execute(httppost);
			Scanner in = new Scanner(resp.getEntity().getContent());
			
			if (in.hasNextLine())
				result = in.nextLine();
			/*
			while (in.hasNextLine())
				System.out.println(in.nextLine());
			*/
			//System.out.println(result);
			in.close();
			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (result == null || result.contentEquals("false"))
			return false;
		else
			return true;
	}
	
	public boolean cancelBid(Book b, String buyer) {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("isbn", b.get_ISBNNo()));
		nameValuePairs.add(new BasicNameValuePair("seller_name", b.getSellerName()));
		nameValuePairs.add(new BasicNameValuePair("condition", b.getBookCondition()));
		nameValuePairs.add(new BasicNameValuePair("buyer_name", buyer));
		nameValuePairs.add(new BasicNameValuePair("bid_amount", Double.toString(b.getPrice())));
		nameValuePairs.add(new BasicNameValuePair("cancel_bid", "true"));

		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpPost httppost = new HttpPost(SERVERURL);
		
		String result = null;
		
		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse resp = httpclient.execute(httppost);
			Scanner in = new Scanner(resp.getEntity().getContent());
			
			if (in.hasNextLine())
				result = in.nextLine();
			/*
			while (in.hasNextLine())
				System.out.println(in.nextLine());
			*/
			//System.out.println(result);
			in.close();
			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (result == null || result.contentEquals("false"))
			return false;
		else
			return true;
	}
	
	
	public double getHighestBid(Book b) {
		double bid = 0;
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("isbn", b.get_ISBNNo()));
		nameValuePairs.add(new BasicNameValuePair("seller_name", b.getSellerName()));
		nameValuePairs.add(new BasicNameValuePair("condition", b.getBookCondition()));
		nameValuePairs.add(new BasicNameValuePair("getHighestBid", "true"));

		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpPost httppost = new HttpPost(SERVERURL);

		String result = null;

		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse resp = httpclient.execute(httppost);
			Scanner in = new Scanner(resp.getEntity().getContent());
			
			if (in.hasNextLine())
				result = in.nextLine();
			/*
			while (in.hasNextLine())
				System.out.println(in.nextLine());
			*/	
			
			in.close();
			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (result != null && !result.contentEquals("none") && !result.contentEquals("false"))
			bid = Double.parseDouble(result);
			
			
		return bid;
	}
	
	public double getCredits (String display_name) {
		double credits = 0;
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("display_name", display_name));
		nameValuePairs.add(new BasicNameValuePair("get_credits", "true"));

		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpPost httppost = new HttpPost(SERVERURL);

		String result = null;

		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse resp = httpclient.execute(httppost);
			Scanner in = new Scanner(resp.getEntity().getContent());
			
			if (in.hasNextLine())
				result = in.nextLine();
			/*
			while (in.hasNextLine())
				System.out.println(in.nextLine());
			*/
			
			in.close();
			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (result != null && !result.contentEquals("false"))
			credits = Double.parseDouble(result);
			
			
		return credits;
	}
	
	public BufferedImage getImage(String isbn) {
		BufferedImage image = null;
		BufferedImage returnImage = null;
		URL url;
		try {
			 url = new URL("http://127.0.0.1/BookBay/images/"+isbn+".jpg");
			 HttpURLConnection.setFollowRedirects(false);
			 HttpURLConnection check = (HttpURLConnection) url.openConnection();
			 check.setRequestMethod("HEAD");
			 if(check.getResponseCode() == HttpURLConnection.HTTP_OK) {
				 image = ImageIO.read(url);
				 returnImage = new BufferedImage(350,500,2);
				 final Graphics2D graphics2D = returnImage.createGraphics();
			     graphics2D.setComposite(AlphaComposite.Src);
			     graphics2D.drawImage(image, 0, 0, 350, 500, null);
			     graphics2D.dispose();
			 }
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnImage;
	}
	
	public List<String> getTopUsers() {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("get_top_users", "true"));
		
		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpPost httppost = new HttpPost(SERVERURL);

		List<String> userList = new ArrayList<String>();

		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse resp = httpclient.execute(httppost);
			Scanner in = new Scanner(resp.getEntity().getContent());
			
			while (in.hasNextLine()) {
				userList.add(in.nextLine());
			}
			/*
			while (in.hasNextLine()) {
				System.out.println(in.nextLine());
			}
			*/
			
			in.close();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	public List<String> getNewUsers() {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("get_new_users", "true"));
		
		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpPost httppost = new HttpPost(SERVERURL);

		List<String> userList = new ArrayList<String>();

		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse resp = httpclient.execute(httppost);
			Scanner in = new Scanner(resp.getEntity().getContent());
			
			while (in.hasNextLine()) {
				userList.add(in.nextLine());
			}
			/*
			while (in.hasNextLine()) {
				System.out.println(in.nextLine());
			}
			*/
			
			in.close();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	public boolean acceptUser(String display_name) {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("display_name", display_name));
		nameValuePairs.add(new BasicNameValuePair("accept_user", "true"));
		
		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpPost httppost = new HttpPost(SERVERURL);
		
		String result = null;

		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse resp = httpclient.execute(httppost);
			Scanner in = new Scanner(resp.getEntity().getContent());
			
			if (in.hasNext()) 
				result = in.next();
			
			/*
			while (in.hasNextLine()) {
				System.out.println(in.nextLine());
			}
			*/
			
			in.close();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		if (result != null && result.contentEquals("true"))
			return true;

		return false;
	}
	
	public boolean declineUser(String display_name) {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("display_name", display_name));
		nameValuePairs.add(new BasicNameValuePair("decline_user", "true"));
		
		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpPost httppost = new HttpPost(SERVERURL);
		
		String result = null;

		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse resp = httpclient.execute(httppost);
			Scanner in = new Scanner(resp.getEntity().getContent());
			
			if (in.hasNext()) 
				result = in.next();
			
			/*
			while (in.hasNextLine()) {
				System.out.println(in.nextLine());
			}
			*/
			
			in.close();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (result != null && result.contentEquals("true"))
			return true;

		return false;
	}
	
	public String register(User u) {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("login_name", u.getLoginName()));
		nameValuePairs.add(new BasicNameValuePair("display_name", u.getDisplayName()));
		nameValuePairs.add(new BasicNameValuePair("register", "true"));
		
		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpPost httppost = new HttpPost(SERVERURL);

		String result = "3";

		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse resp = httpclient.execute(httppost);
			Scanner in = new Scanner(resp.getEntity().getContent());
		
			if (in.hasNext())
				result = in.next();
			/*
			while (in.hasNextLine()) {
				System.out.println(in.nextLine());
			}*/
			
			in.close();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public User login(User u) {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("login_name", u.getLoginName()));
		nameValuePairs.add(new BasicNameValuePair("password", u.getPassword()));
		nameValuePairs.add(new BasicNameValuePair("login", "true"));
		
		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpPost httppost = new HttpPost(SERVERURL);

		User user = new User();

		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse resp = httpclient.execute(httppost);
			Scanner in = new Scanner(resp.getEntity().getContent());
			
			if (in.hasNextLine()) {
				user.setUser(in.nextLine());
				if (user.getDisplayName().equals("false")) {
					in.close();
					return user;
				}
				user.setWarnings(Integer.parseInt(in.nextLine()));
				user.setCredits(Double.parseDouble(in.nextLine()));
				String susp = in.nextLine();
				if (susp.equals("1"))
					user.setSuspended(true);
				else
					user.setSuspended(false);
				user.setRating(Integer.parseInt(in.nextLine()));
				String cp = in.nextLine();
				if (cp.equals("0"))
					user.setChangedPass(false);
				 cp = in.nextLine();
					if (cp.equals("1"))
						user.setAdmin();
			}
			/*
			while (in.hasNextLine()) {
				System.out.println(in.nextLine());
			}
			*/
			
			in.close();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return user;
	}
	

	/*
	public boolean loginAdmin(User u) {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("login_name", u.getLoginName()));
		nameValuePairs.add(new BasicNameValuePair("password", u.getPassword()));
		nameValuePairs.add(new BasicNameValuePair("login_admin", "true"));
		
		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpPost httppost = new HttpPost(SERVERURL);
		
		String result = null;

		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse resp = httpclient.execute(httppost);
			Scanner in = new Scanner(resp.getEntity().getContent());
			
			if (in.hasNext()) 
				result = in.next();
			
			/*
			while (in.hasNextLine()) {
				System.out.println(in.nextLine());
			}
			
			
			in.close();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (result != null && result.contentEquals("true"))
			return true;

		return false;
	}
	*/
	
	public String changePassword(User c, User n) {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("display_name", c.getDisplayName()));
		nameValuePairs.add(new BasicNameValuePair("current_password", c.getPassword()));
		nameValuePairs.add(new BasicNameValuePair("new_password", n.getPassword()));
		nameValuePairs.add(new BasicNameValuePair("changepass", "true"));
		
		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpPost httppost = new HttpPost(SERVERURL);

		String result = "error";

		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse resp = httpclient.execute(httppost);
			Scanner in = new Scanner(resp.getEntity().getContent());
			
			if (in.hasNext())
				result = in.next();
			/*
			while (in.hasNextLine()) {
				System.out.println(in.nextLine());
			}*/
			
			
			in.close();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
