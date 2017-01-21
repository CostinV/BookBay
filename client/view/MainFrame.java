import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * MainFrame is the top level view in our application. It creates all the
 * subpanels needed and adds them to itself, or a nested container. You should
 * not need to edit this file.
 * @patri
 */
public class MainFrame extends JFrame {

	/**
	 * Generated version id for serialization
	 */
	private static final long serialVersionUID = -4905184030623336230L;

	/**
	 * This holds a reference to the controller.
	 */
	Controller masterController;

	/**
	 * Reference used to access tab pane sub-view
	 */
	JTabbedPane tabPane;
	/**
	 * Reference used to access tab pane sub-view
	 */
	JScrollPane scrollPane;
	/**
	 * Reference used to access search panel sub-view
	 */
	BookSearchPanel searchP;
	/**
	 * Reference used to access Book Display panel sub-view
	 */
	BookDisplayPanel dispP;
	/**
	 * Reference used to access Book Edit panel sub-view
	 */
	BookEditPanel editP;
	/**
	 * Reference used to access Add Book Panel sub-view
	 */
	BookAddPanel addP;
	/**
	 * Reference used to access Book List Display panel sub-view
	 */
	BookListDisplayPanel listP;
	
	BookReviewPanel reviewP;

	CreditMoney creditP;
	/**
	 * Reference used to access Book Bid panel sub-view
	 */
	
	Login loginP;
	Register regP;
	PasswordChangePanel passP;
	BookBidPanel bidP;
	BookSellPanel sellP;
	HomePagePanel homeP;
	AboutUsPanel aboutP;
	AdministratorPanel adminP;
	HistoryPanel historyP;
	/*
	 * Builds Main Application frame, instantiating and positioning all
	 * subcomponents.
	 * 
	 * @param controller
	 *            Reference to the master controller so our views can access the
	 *            callback functions in the controller.
	 */
	public MainFrame(Controller controller) {

		super("Book Bay System");
		this.masterController = controller;
		getContentPane().setBackground(Color.WHITE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setBackground(Color.orange);

		// Instanciate all child views
		tabPane = new JTabbedPane();
		tabPane.setBackground(Color.WHITE);
		homeP = new HomePagePanel(masterController);
		searchP = new BookSearchPanel(masterController);
		dispP = new BookDisplayPanel(masterController);
		editP = new BookEditPanel(masterController);
		creditP = new CreditMoney(masterController);
		bidP = new BookBidPanel(masterController);
		sellP = new BookSellPanel(masterController);
		addP = new BookAddPanel(masterController);
		listP = new BookListDisplayPanel(masterController);
		editP = new BookEditPanel(masterController);
		loginP = new Login(masterController);
		regP = new Register(masterController);
		passP = new PasswordChangePanel(masterController);
		adminP = new AdministratorPanel(masterController);
		aboutP = new AboutUsPanel(masterController);
		reviewP = new BookReviewPanel(masterController);
		historyP = new HistoryPanel(masterController);
		// Nest our list panel inside a scroll pane to get scroll bars
		scrollPane = new JScrollPane(listP,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		// add the child views to the tab pane
		tabPane.addTab("HomePage", homeP);
		tabPane.addTab("Register", regP);
		tabPane.addTab("PasswordChange", passP);
		tabPane.addTab("Login", loginP);
		tabPane.addTab("Search", searchP);
		tabPane.addTab("Display", dispP);
		tabPane.addTab("Edit", editP);
		tabPane.addTab("Add Credits", creditP);
		tabPane.addTab("Bid", bidP);
		tabPane.addTab("View Bids", sellP);
		tabPane.addTab("Add Book", addP);
		tabPane.addTab("List", scrollPane);
		tabPane.addTab("Review Books", reviewP);
		tabPane.addTab("History", historyP);
		tabPane.addTab("Administrator", adminP);
		tabPane.addTab("AboutUs", aboutP);
		// add a listener to the tab pane that notifies the controller....
		// the controller will query the data model for the latest information
		// to insure the data displayed in the newly selected view is fresh.
		tabPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				masterController.notifyTabChanged();
			}
		});

		// Now add the scroll pane to MainFrame
		this.getContentPane().add(tabPane);

		// And make it visible...
		this.setVisible(true);
	}
}
