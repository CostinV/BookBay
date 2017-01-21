import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;
//import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

/**
 * This Class is responsible for displaying a list of student names and ssns. It
 * also generates an "Edit" and a "View" button for each student.
 * 
 * Clicking either of these triggers an event listener, which simply passes the
 * student's ssn to the masterController's callback. (And the controller takes
 * care of things after that)
 * 
 * This class is fully functional, you should not need to alter it. Clicking the
 * "View" button doesn't work because you need to fix the setStudentView(int
 * ssn) method in the controller.
 */
public class BookListDisplayPanel extends JPanel {

	private static final long serialVersionUID = 5493383344375212140L;
	private Controller masterController;
	
	/**
	 * Create an ActionListener to add to "View" buttons. Each button has the
	 * corresponding book's isbn stored in it's "ActionCommand" Field We can
	 * access this in the event handler, which lets us use one instance of the
	 * event handler for ALL of the view buttons instead of creating a different
	 * instance for every button in the list
	 */
	ActionListener viewHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// Get the ISBN from the event's "Action Command" (which is really
			// just a string)
			Book b = ((BookButton) e.getSource()).getBook();
			// Tell the controller that the user wants to view the student with
			// this ISBN
			BookListDisplayPanel.this.masterController.setBookView(b);
		}

	};

	/**
	 * Create an ActionListener to add to "Edit" buttons. Each button has the
	 * corresponding student's ssn stored in it's "ActionCommand" Field We can
	 * access this in the event handler, which lets us use one instance of the
	 * event handler for ALL of the view buttons instead of creating a different
	 * instance for every button in the list
	 */
	ActionListener editHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			Book b = ((BookButton) e.getSource()).getBook();
			BookListDisplayPanel.this.masterController
					.setBookEditView(b);
		}

	};

	/**
	 * Create an ActionListener to add to "bid" buttons. Each button has the
	 * corresponding student's ssn stored in it's "ActionCommand" Field We can
	 * access this in the event handler, which lets us use one instance of the
	 * event handler for ALL of the view buttons instead of creating a different
	 * instance for every button in the list
	 */
	ActionListener bidHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			Book b = ((BookButton) e.getSource()).getBook();
			BookListDisplayPanel.this.masterController
					.setBookBidView(b);
		}

	};

	/**
	 * Create an ActionListener to add to "bid" buttons. Each button has the
	 * corresponding student's ssn stored in it's "ActionCommand" Field We can
	 * access this in the event handler, which lets us use one instance of the
	 * event handler for ALL of the view buttons instead of creating a different
	 * instance for every button in the list
	 */
	ActionListener buyHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			Book b = ((BookButton) e.getSource()).getBook();
			BookListDisplayPanel.this.masterController.addBid(b);
		}

	};
	

	/**
	 * Create an ActionListener to add to "home" buttons. when user clicks this button 
	 * the controlller links panel to homepage
	 */
	
	
	ActionListener homeHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			BookListDisplayPanel.this.masterController
					.goHomePage();
		}

	};
	
	

	/**
	 * Initially this view just shows a label saying "No results yet" and stores
	 * a reference to the controller. The controller can populate the list view
	 * by calling setResults(List<Student>)
	 * 
	 * @param masterController
	 *            reference to the controller
	 */
	public BookListDisplayPanel(Controller masterController) {
		super();
		setBackground(Color.white);
		this.masterController = masterController;
		JLabel textPane;
		textPane = new JLabel();
		textPane.setText("No Results Yet!");
		add(textPane);
		
		BookButton homeButton;
		homeButton = new BookButton("home");
		//homeButton.setBook(b);
		homeButton.addActionListener(homeHandler);
		GridBagConstraints cc = new GridBagConstraints();
		cc.gridx = 0;
		cc.gridy = 0;
		//cc.weightx = 0;
		cc.anchor = GridBagConstraints.CENTER;
		//cc.weighty = 0;
		this.add(homeButton, cc);
		
		
	}

	/**
	 * Dynamically generates a JLabel and  JButtons for each book in results
	 * and adds them to a GridBagLayout. It also adds the appropriate Action
	 * Listener to each button that notifies the controller when the user wants
	 * to view or buy book in the list.
	 * 
	 * @param results
	 *            List of the students to display
	 */
	public void setResults(List<Book> results) {
		// First clear this panel of any components that are already added
		this.removeAll();
		Random ran = new Random();
		
		//Color c1 = new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256));
		Color c1 = new Color(220,220,220);
       this.setBackground(c1);
       
		// Set the layout manager
		this.setLayout(new GridBagLayout());
		
		//JLabel bookLabel;
		JLabel bookLabel1;
		JLabel bookLabel2;
		JLabel bookLabel3;
		JLabel bookLabel4;
		JLabel bookLabel5;
		
		// editButton holds a reference to each new edit Button we create in the
		// loop
		BookButton buyButton;
		// viewButton holds a reference to each new view Button we create in the
		// loop
		BookButton viewButton;
		int i;

		// bidButton holds a reference to each new edit Button we create in the
				// loop
		BookButton bidButton;

		// sellButton holds a reference to each new edit Button we create in the
		// loop
		BookButton sellButton;
		// c is used to hold references to the constraint objects we create
		GridBagConstraints c;

		/*
		 * The chunk of code below sets up an empty panel that will grow to fill
		 * all "extra" space in the StudentListDisplayPanel. This is one way to
		 * get the layout manager to push all the labels and buttons to the left
		 * side of the panel
		 */
		/*
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.weighty = 0.2;
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.EAST;
		JPanel fill = new JPanel();
		fill.setBackground(getBackground());
		add(fill, c);
		*/
		

		// Now we loop through the results and create the labels and buttons we
		// need
		for (i = 0; i < results.size(); i++) {
			// First we get the student to display
			Book b = results.get(i);

			// We create a new constraint for this student's details
			c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = i;
			//c.weighty = 0.2;
			//c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;

			// We create the label, set the string to display, and make sure the
			// background color matches the panel background
			bookLabel1 = new JLabel("ISBN: "+b.get_ISBNNo());
			bookLabel2 = new JLabel("  Book Name: "	+ b.getBookTitle());
			bookLabel3 = new JLabel("  Author: "+b.getAuthorName() );
			bookLabel4 = new JLabel("  Price: $"+ b.getPrice());
			bookLabel5 = new JLabel("  Rating: " + b.getRating());
			bookLabel1.setForeground(Color.BLUE);
			bookLabel2.setForeground(Color.BLUE);
			bookLabel3.setForeground(Color.BLUE);
			bookLabel4.setForeground(Color.BLUE);
			bookLabel5.setForeground(Color.BLUE);
			c = new GridBagConstraints();
			c.gridx = 1;
			c.gridy = i+1;
			c.anchor = GridBagConstraints.CENTER;
			bookLabel1.setBackground(getBackground());
			this.add(bookLabel1, c);
			c = new GridBagConstraints();
			c.gridx = 2;
			c.gridy = i+1;
			c.anchor = GridBagConstraints.CENTER;
			bookLabel2.setBackground(getBackground());
			this.add(bookLabel2, c);
			c = new GridBagConstraints();
			c.gridx = 3;
			c.gridy = i+1;
			c.anchor = GridBagConstraints.CENTER;
			bookLabel3.setBackground(getBackground());
			this.add(bookLabel3, c);
			c = new GridBagConstraints();
			c.gridx = 4;
			c.gridy = i+1;
			c.anchor = GridBagConstraints.CENTER;
			bookLabel4.setBackground(getBackground());
			this.add(bookLabel4, c);
			sellButton = new BookButton("Sell similar Book");

			sellButton.setBook(b);
			sellButton.addActionListener(editHandler);

			// Now we set up the constraint object that will position this
			// button
			c = new GridBagConstraints();
			c.gridx = 5;
			c.gridy = i+1;
			c.anchor = GridBagConstraints.CENTER;
			//c.weightx = 0;
			//c.weighty = 0.1;
			// and we add it
			this.add(sellButton, c);

			// Now we do the same thing for the view button.
			viewButton = new BookButton("View Details");
			viewButton.setBook(b);
			viewButton.addActionListener(viewHandler);
			c = new GridBagConstraints();
			c.gridx = 6;
			c.gridy = i+1;
			//c.weightx = 0;
			c.anchor = GridBagConstraints.CENTER;
			//c.weighty = 0.1;
			this.add(viewButton, c);
			
			// Now we do the same thing for the view button.
			bidButton = new BookButton("Bid Book");
			bidButton.setBook(b);
			bidButton.addActionListener(bidHandler);
			c = new GridBagConstraints();
			c.gridx = 7;
			c.gridy = i+1;
			//c.weightx = 0;
			c.anchor = GridBagConstraints.CENTER;
			//c.weighty = 0.1;
			this.add(bidButton, c);
			
			buyButton = new BookButton("Buy It Now");
			buyButton.setBook(b);
			buyButton.addActionListener(buyHandler);
			c = new GridBagConstraints();
			c.gridx = 8;
			c.gridy = i+1;
			c.anchor = GridBagConstraints.CENTER;
			this.add(bidButton, c);
		}
		
		BookButton homeButton;
		homeButton = new BookButton("home");
		//homeButton.setBook(b);
		homeButton.addActionListener(homeHandler);
		GridBagConstraints cc = new GridBagConstraints();
		cc.gridx = 1;
		cc.gridy = 0;
		//cc.weightx = 0;
		cc.anchor = GridBagConstraints.CENTER;
		//cc.weighty = 0;
		this.add(homeButton, cc);
		
	}

}
