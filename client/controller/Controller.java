import java.io.File;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * This is the point of entry for our application. It instantiates our views and
 * provides callbacks those views can use to handle actions, like adding a new
 * Book, editing, etc... In those callbacks, our Controller code doesn't act
 * on the data directly, but just calls the appropriate methods in our
 * RosterModel class, then loads the appropriate view.
 */
public class Controller {

	
	/**
	 * this serves as a reference to our top level view, the main frame that
	 * contains all sub panels.
	 */
	MainFrame mainFrame;

	/**
	 * BookConstraint serves to hold a reference to a BookConstraint
	 * object corresponding to the user's current search.
	 */
	BookConstraint BookConstraint;

	/**
	 * this serves as a reference to our dataModel. All calls to interact with
	 * the data on the website will happen through this reference.
	 */
	protected RosterModel dataModel;
	
	public User user;
	public boolean isLoggedIn;

	/**
	 * This is the point of entry for our application. It boot straps a new
	 * instance of Controller class.
	 * 
	 * 
	 * @param args
	 *            unused, no command line args for this application
	 */
	public static void main(String... args) {
		Runnable r = new Runnable() {
			@Override
			public void run() {
				new Controller();
			}
		};
		javax.swing.SwingUtilities.invokeLater(r);
	}

	/**
	 * Creates Instances of The model and top-level view (MainFrame)
	 */
	public Controller() {
		user = new User();
		user.setUser("guest");
		isLoggedIn = false;
		dataModel = new RosterModel();
		mainFrame = new MainFrame(this);
	}

	/**
	 * This is a callback function that will be called by views to indicate the
	 * user changed tabs. The controller checks to see if the selected panel is
	 * the BookListDisplay. If it is, we call on the data model to make sure
	 * we don't display stale results, and set those results in the view. If we
	 * have a BookConstraint saved and the selected panel is the Book List
	 * Panel, then we should re-query the data model and set the results in the
	 * List View to make sure the user sees the freshest data.
	 */
	public void notifyTabChanged() {
		if (this.mainFrame.listP.isShowing()) {
			/*
			List<Book> results = dataModel
					.getBooks(this.BookConstraint);

			mainFrame.listP.setResults(results);
			*/
			mainFrame.tabPane.setSelectedComponent(mainFrame.scrollPane);
		}
		else if (mainFrame.bidP.isShowing()) {
			if (!isLoggedIn)
				mainFrame.bidP.setGuestDisplay();
			else
				mainFrame.bidP.setNormalDisplay();
		}
		
		else if (mainFrame.sellP.isShowing()) {
			if (!isLoggedIn)
				mainFrame.sellP.setGuestDisplay();
			else {
				List<Book> sell = dataModel.getBids(user.getDisplayName(), false);
				List<Book> buy = dataModel.getBids(user.getDisplayName(), true);
				mainFrame.sellP.setResults(sell, buy);
			}
		}
		
		else if (mainFrame.reviewP.isShowing()) {
			if (!isLoggedIn)
				mainFrame.reviewP.setGuestDisplay();
			else {
				List<Review> sell1 = dataModel.getReviews(user.getDisplayName(), false, false);
				List<Review> sell2 = dataModel.getReviews(user.getDisplayName(), false, true);
				List<Review> buy1 = dataModel.getReviews(user.getDisplayName(), true, false);
				List<Review> buy2 = dataModel.getReviews(user.getDisplayName(), true, true);
				mainFrame.reviewP.setResults(sell1, sell2, buy1, buy2);
			}
		}
		
		else if (mainFrame.homeP.isShowing()) {
			if (isLoggedIn) {
				updateCredits();
				mainFrame.homeP.updateUser(true);
				mainFrame.homeP.updateButtons(true);
			} else {
				mainFrame.homeP.updateUser(false);
				mainFrame.homeP.updateButtons(false);
			}
		}
		
		else if (mainFrame.historyP.isShowing()) {
			if (isLoggedIn) {
				mainFrame.historyP.setResults(dataModel.getBrowseHistory(user.getDisplayName()), dataModel.getBidHistory(user.getDisplayName()));
			} else {
				mainFrame.homeP.updateUser(false);
				mainFrame.homeP.updateButtons(false);
			}
		}
		
		else if (mainFrame.adminP.isShowing()) {
			if (user.isAdmin())
				mainFrame.adminP.setDisplay(dataModel.getNewUsers());
			else
				mainFrame.adminP.setGuestDisplay();
		}
	}

	/**
	 * Calls on the data model to get a List of Books who match the criteria
	 * in s. Then we call on the BookListDisplayPanel to set the view
	 * contents and the TabPane to display the BookListDisplayPanel.
	 * completed this method by querying the data model to get the search
	 * results, then displaying the BookListDisplayPanel with the results.
	 * 
	 * @param s
	 *            A BookConstraint object representing how the fields of the
	 *            Book data should be searched.
	 */
	public void searchAndDisplay(BookConstraint s) {
		this.BookConstraint = s;
		List<Book> results = dataModel.getBooks(this.BookConstraint);
		mainFrame.listP.setResults(results);
		mainFrame.tabPane.setSelectedComponent(mainFrame.scrollPane);
	}

	/**
	 * Calls on the data model to get a List of all Books in the database.
	 * Then we call on the BookListDisplayPanel to set the view contents and
	 * the TabPane to display the BookListDisplayPanel.
	 */
	public void displayAll() {
		List<Book> results = dataModel.getAllBooks();
		mainFrame.listP.setResults(results);
		System.out.println("displayall" + results.toString());
		mainFrame.tabPane.setSelectedComponent(mainFrame.scrollPane);
	}

	/**
	 * A callback to alert the controller that it should fetch the Book with
	 * the given ssn from the data model, call to the BookDisplayPanel to set
	 * the details, and call to the tab pane to show the Book Display panel
	 * 
	 * complete this method by querying the data model to get the Book, then
	 * displaying the Book in the BookDisplayPanel.
	 * 
	 * @param userId
	 *            ssn of the Book to display
	 */
	public void setBookView(Book b) {
		if (isLoggedIn && mainFrame.listP.isShowing()) {
			mainFrame.dispP.setBookDetails(dataModel.getBook(b,user.getDisplayName()));
			mainFrame.dispP.setBookImage(dataModel.getImage(b.get_ISBNNo()));
		} else {
			mainFrame.dispP.setBookImage(null);
			mainFrame.dispP.setBookDetails(b);
		}
		mainFrame.tabPane.setSelectedComponent(mainFrame.dispP);
	}

	/**
	 * A callback to alert the controller that it should fetch the Book with
	 * the given ssn from the data model, call to the BookEditPanel to set
	 * the details, and call to the tab pane to show the Book Display panel
	 * 
	 * @param userId
	 *            ssn of the Book to display
	 */
	public void setBookEditView(Book b) {
		if (isLoggedIn) {
			mainFrame.editP.setBook(b);
		mainFrame.tabPane.setSelectedComponent(mainFrame.editP);
	} else {
		JOptionPane
		.showMessageDialog(mainFrame.dispP,
				"You must log in to access this feature!",
				"Controller",
				JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void setBookBidView(Book b) {
		if (isLoggedIn) {
			double bid = dataModel.getHighestBid(b);
			mainFrame.bidP.setBook(b, bid);
			mainFrame.tabPane.setSelectedComponent(mainFrame.bidP);
		} else {
		JOptionPane
		.showMessageDialog(mainFrame.dispP,
				"You must log in before you can bid!",
				"Controller",
				JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * A callback for editing a Book. We will call on the dataModel to update
	 * the Book, and if successful, call the BookDisplayPanel to set the
	 * Book details and display it. If the update fails, we display an error
	 * message.
	 * 
	 * @param s
	 *            A Book object containing the altered Book's details.
	 */
	public void editBook(Book s) {
		s.setSellerName(user.getDisplayName());
		if (dataModel.update(s)) {
			setBookView(dataModel.getBook(s, user.getDisplayName()));
		} else {
			JOptionPane
					.showMessageDialog(mainFrame.dispP,
							"Update operation failed!\nPlease check the ISBN and\nthat you own the book.",
							"Controller/BookDisplayPanel",
							JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void editBook(Book s, File imageFile) {
		s.setSellerName(user.getDisplayName());
		if (dataModel.update(s, imageFile)) {
			setBookView(dataModel.getBook(s, user.getDisplayName()));
		} else {
			JOptionPane
					.showMessageDialog(mainFrame.dispP,
							"Update operation failed!\nPlease check the ISBN and\nthat you own the book.",
							"Controller/BookDisplayPanel",
							JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void removeBook(Book s) {
		if (dataModel.remove(s)) {
			JOptionPane
					.showMessageDialog(mainFrame.dispP,
							"Book Successfully Removed",
							"Controller",
							JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * A callback for creating a Book. We will call on the dataModel to add
	 * the Book, and if successful, call the BookDisplayPanel to set the
	 * Book details and display it. If add operation fails, we display an
	 * error message.
	 * 
	 * @param s
	 *            A Book object containing the new Book's details.
	 */
	public void addBook(Book s) {
		if (dataModel.add(s)) // Call the data model and check result here.
		{
			setBookView(s);
		} else {
			JOptionPane
					.showMessageDialog(mainFrame.dispP,
							"Uploading new Book failed, sorry :(",
							"Controller/BookDisplayPanel",
							JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void addBook(Book s, File imageFile) {
		if (dataModel.add(s, imageFile)) // Call the data model and check result here.
		{
			setBookView(s);
		} else { /*
			JOptionPane
					.showMessageDialog(mainFrame.dispP,
							"Uploading new Book failed, sorry :(",
							"Controller/BookDisplayPanel",
							JOptionPane.ERROR_MESSAGE);
							*/
		}
	}
	
	public void addBid(Book s) {
		if (dataModel.addBid(s, user.getDisplayName())) {
			mainFrame.tabPane.setSelectedComponent(mainFrame.sellP);
		} else {
			JOptionPane
			.showMessageDialog(mainFrame.dispP,
					"Error posting bid!\nYou have already bid on this item",
					"Controller",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void cancelBid(Book b) {
		if (dataModel.cancelBid(b, user.getDisplayName())) {
			mainFrame.tabPane.setSelectedComponent(mainFrame.dispP);
			mainFrame.tabPane.setSelectedComponent(mainFrame.sellP);
		} else {
			JOptionPane
			.showMessageDialog(mainFrame.dispP,
					"Error cancelling bid",
					"Controller",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void acceptBid(Book b) {
		String buyer_name = b.getSellerName();
		b.setSellerName(user.getDisplayName());
		if (dataModel.acceptBid(b, buyer_name)) {
			mainFrame.tabPane.setSelectedComponent(mainFrame.dispP);
			mainFrame.tabPane.setSelectedComponent(mainFrame.sellP);
		} else {
			JOptionPane
			.showMessageDialog(mainFrame.dispP,
					"Error accepting bid",
					"Controller",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void unsuspend(Book b) {
		if (!dataModel.unsuspendBook(b)) {
			JOptionPane
			.showMessageDialog(mainFrame.dispP,
					"Error unsuspending book",
					"Controller",
					JOptionPane.ERROR_MESSAGE);
		}
			
	}
	
	public void unsuspend(String display_name) {
		if (!dataModel.unsuspendUser(display_name)) {
			JOptionPane
			.showMessageDialog(mainFrame.dispP,
					"Error unsuspending user",
					"Controller",
					JOptionPane.ERROR_MESSAGE);
		}
			
	}
	
	public void addReview(Review r) {
		dataModel.addReview(r);
		mainFrame.tabPane.setSelectedComponent(mainFrame.homeP);
		mainFrame.tabPane.setSelectedComponent(mainFrame.reviewP);
	}
	
	public void updateCredits() {
		user.setCredits(dataModel.getCredits(user.getDisplayName()));
	}
	
	public void register(User u) {
		String r = dataModel.register(u);
		if (r.equals("1")){
			JOptionPane
			.showMessageDialog(mainFrame.dispP,
					"Error: Login Name in Use\nPlease Try Again",
					"Controller",
					JOptionPane.ERROR_MESSAGE);
		} else if (r.equals("2")) {
			JOptionPane
			.showMessageDialog(mainFrame.dispP,
					"Error: Display Name in Use\nPlease Try Again",
					"Controller",
					JOptionPane.ERROR_MESSAGE);
		}else if (r.isEmpty()) {
			JOptionPane
			.showMessageDialog(mainFrame.dispP,
					"Error: some fields are empty\nPlease Try Again",
					"Controller",
					JOptionPane.ERROR_MESSAGE);
		}  
		else {
			JOptionPane
			.showMessageDialog(mainFrame.dispP,
					"Registration Successful!\nHere is your temporary password:\n" + r,
					"Controller",
					JOptionPane.INFORMATION_MESSAGE);
			mainFrame.regP.btnClear.doClick();
			goHomePage();
		}
		
		
	}
	
	public void login(User u) {
		User ret = dataModel.login(u);
		if (ret.getDisplayName().equals("false")) {
			JOptionPane
			.showMessageDialog(mainFrame.dispP,
					"Login Unsuccessful\nYour account has not been\napproved by an administrator\nor the provided information\nis incorrect.",
					"Controller",
					JOptionPane.ERROR_MESSAGE);
		} else {
			user = ret;
			isLoggedIn=true;
			if (!user.hasChangedPass()) {
				JOptionPane
				.showMessageDialog(mainFrame.dispP,
						"Login Successful!\nWelcome, " + user.getDisplayName() + "\nPlease change your password",
						"Controller",
						JOptionPane.INFORMATION_MESSAGE);
				mainFrame.loginP.btnClear.doClick();
			}
			else {
				JOptionPane
				.showMessageDialog(mainFrame.dispP,
						"Login Successful!\nWelcome Back, " + user.getDisplayName(),
						"Controller",
						JOptionPane.INFORMATION_MESSAGE);
				mainFrame.loginP.btnClear.doClick();
			}
			
			goHomePage();
		}
	}
	/*
	public void loginAdmin(User u) {
		if (!dataModel.loginAdmin(u)) {
			JOptionPane
			.showMessageDialog(mainFrame.dispP,
					"Error: Invalid Login Information\nPlease Try Again",
					"Controller",
					JOptionPane.ERROR_MESSAGE);
		} else {
			isAdmin = true;
			JOptionPane
			.showMessageDialog(mainFrame.dispP,
					"Admin Login Successful!",
					"Controller",
					JOptionPane.INFORMATION_MESSAGE);
			adminPage();
		}
	}
	
	*/
	
	public void changePassword(User c, User n) {
		String r = dataModel.changePassword(c,n);
		if (r.equals("false")) {
			JOptionPane
			.showMessageDialog(mainFrame.dispP,
					"Error: Invalid Password\nPlease Try Again",
					"Controller",
					JOptionPane.ERROR_MESSAGE);
		} else if (r.equals("error")) {
				JOptionPane
				.showMessageDialog(mainFrame.dispP,
						"Database Error\nPlease Try Again",
						"Controller",
						JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane
			.showMessageDialog(mainFrame.dispP,
					"Password Change Successful!",
					"Controller",
					JOptionPane.INFORMATION_MESSAGE);
			mainFrame.passP.btnClear.doClick();
			goHomePage();
		}
	}
	
	public void addCredits(User u) {
		if (!dataModel.addCredits(u)) {
			JOptionPane
			.showMessageDialog(mainFrame.dispP,
					"Adding Credits Failed",
					"Controller",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane
			.showMessageDialog(mainFrame.dispP,
					"Added Credits Successfully",
					"Controller",
					JOptionPane.INFORMATION_MESSAGE);
			mainFrame.tabPane.setSelectedComponent(mainFrame.homeP);
		}
		
	}
	

	
	public void acceptUser(String display_name) {
		dataModel.acceptUser(display_name);
		mainFrame.tabPane.setSelectedComponent(mainFrame.homeP);
		mainFrame.tabPane.setSelectedComponent(mainFrame.adminP);
	}
	
	public void declineUser(String display_name) {
		dataModel.declineUser(display_name);
		mainFrame.tabPane.setSelectedComponent(mainFrame.homeP);
		mainFrame.tabPane.setSelectedComponent(mainFrame.adminP);
	}
	
	public void setSearchBook()
	{
		mainFrame.tabPane.setSelectedComponent(mainFrame.searchP);
	}
	public void setRegister()
	{
		mainFrame.tabPane.setSelectedComponent(mainFrame.regP);
	}
	public void setLogin()
	{
		mainFrame.tabPane.setSelectedComponent(mainFrame.loginP);
	}
	
	public List<Book> getTopBooks() {
		return dataModel.getTopBooks();
	}
	
	public List<String> getTopUsers() {
		return dataModel.getTopUsers();
	}
	
	public void logout()
	{
		isLoggedIn = false;
		user = new User();
		user.setUser("Guest");
		mainFrame.tabPane.setSelectedComponent(mainFrame.loginP);
		mainFrame.tabPane.setSelectedComponent(mainFrame.homeP);
		mainFrame.loginP.btnClear.doClick();
	}
	
	public void goHomePage()
	{
		mainFrame.tabPane.setSelectedComponent(mainFrame.homeP);
	}
	public void adminPage()
	{
		mainFrame.tabPane.setSelectedComponent(mainFrame.adminP);
	}
	
	public void viewPage()
	{
		mainFrame.tabPane.setSelectedComponent(mainFrame.sellP);
	}
	public void reviewPage()
	{
		mainFrame.tabPane.setSelectedComponent(mainFrame.reviewP);
	}
	
	public void historyPage()
	{
		mainFrame.tabPane.setSelectedComponent(mainFrame.historyP);
	}
	public void addbookPage()
	{
		mainFrame.tabPane.setSelectedComponent(mainFrame.addP);
	}
	public void listPage()
	{
		mainFrame.tabPane.setSelectedComponent(mainFrame.scrollPane);
	}
	public void creditPage()
	
	{
		mainFrame.tabPane.setSelectedComponent(mainFrame.creditP);
	}
	public void buyBookPage()
	{
		mainFrame.tabPane.setSelectedComponent(mainFrame.bidP);
	}
	public void bidBookPage()
	
	{
		mainFrame.tabPane.setSelectedComponent(mainFrame.bidP);
	}
public void editBookPage()
	
	{
		mainFrame.tabPane.setSelectedComponent(mainFrame.editP);
	}

public void aboutPage() {
	mainFrame.tabPane.setSelectedComponent(mainFrame.aboutP);	
}
public void cpassPage() {
	mainFrame.tabPane.setSelectedComponent(mainFrame.passP);	
}	
}
