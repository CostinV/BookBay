import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * This is a View class in the MVC pattern. It is responsible for taking a
 * single student object and displaying the details nicely.
 * 
 * The layout is set up for you already. The only thing you need to do is
 * complete the setStudentDetails(Student s) method below.
 * 
 */
public class BookSellPanel extends JPanel {

	/**
	 * Generated version id for serialization
	 */
	private static final long serialVersionUID = 4222060145653248410L;

	/**
	 * As of now, the BookDisplayPanel doesn't give the user access to any
	 * actions, so we don't really need a reference to our master controller.
	 * But, if we ever wanted to build in more features it might be useful. So
	 * in keeping with our MVC pattern, ALL of our Views get a reference to the
	 * controller.
	 */
	@SuppressWarnings("unused")
	private Controller masterController;
	
	JLabel textPane;

	ActionListener acceptHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// Get the ISBN from the event's "Action Command" (which is really
			// just a string)
			Book b = ((BookButton) e.getSource()).getBook();
			// Tell the controller that the user wants to view the student with
			// this ISBN
			masterController.acceptBid(b);
		}

	};

	
	ActionListener cancelHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// Get the ISBN from the event's "Action Command" (which is really
			// just a string)
			Book b = ((BookButton) e.getSource()).getBook();
			// Tell the controller that the user wants to view the student with
			// this ISBN
			masterController.cancelBid(b);
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
	public BookSellPanel(Controller masterController) {
		super();
		setBackground(Color.white);
		this.masterController = masterController;

		textPane = new JLabel();
		add(textPane);
	}

	public void setGuestDisplay() {
		textPane.setText("Please log in to view bids!");
	}
	
	public void setNormalDisplay() {
		textPane.setText("Highest Bids on your books:");

	}
	/**
	 * Dynamically generates a JLabel and 2 JButtons for each student in results
	 * and adds them to a GridBagLayout. It also adds the appropriate Action
	 * Listener to each button that notifies the controller when the user wants
	 * to view or edit a student in the list.
	 * 
	 * @param results
	 *            List of the students to display
	 */
	public void setResults(List<Book> results1, List<Book> results2) {
		// First clear this panel of any components that are already added
		this.removeAll();
       //this.setBackground(Color.YELLOW);
       
		// Set the layout manager
		this.setLayout(new GridBagLayout());

		// bookLabel is used to hold a reference to each JLabel as we loop
		// through to create each one
		JLabel bookLabel;

		// editButton holds a reference to each new edit Button we create in the
		// loop
		//BookButton editButton;

		// viewButton holds a reference to each new view Button we create in the
		// loop
		BookButton acceptButton;
		BookButton cancelButton;
		int i = 0;
		int j = 0;

		// loop
		// c is used to hold references to the constraint objects we create
		GridBagConstraints c;

		/*
		 * The chunk of code below sets up an empty panel that will grow to fill
		 * all "extra" space in the StudentListDisplayPanel. This is one way to
		 * get the layout manager to push all the labels and buttons to the left
		 * side of the panel
		 */
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0,0,0,0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,	0 };
		gridBagLayout.columnWeights = new double[] { 0.5, 0.5, 0.5,	Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,	0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel txtsell = new JLabel("Highest bids on your books");
		txtsell.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_txtsell = new GridBagConstraints();
		gbc_txtsell.insets = new Insets(0, 0, 12, 0);
		gbc_txtsell.anchor = GridBagConstraints.NORTHWEST;
		gbc_txtsell.gridx = 0;
		gbc_txtsell.gridy = 0;
		add(txtsell, gbc_txtsell);

		if (results1.size() == 0) {
			JLabel txtempty = new JLabel("No bids to display");
			GridBagConstraints gbc_txtempty = new GridBagConstraints();
			gbc_txtempty.insets = new Insets(0, 0, 12, 0);
			gbc_txtempty.anchor = GridBagConstraints.NORTHWEST;
			gbc_txtempty.gridx = 0;
			
			gbc_txtempty.gridy = 1;
			add(txtempty, gbc_txtempty);
		}
		
		// Now we loop through the results and create the labels and buttons we need

		else {
			for (i = 0; i < results1.size(); i++) {
				// First we get the student to display
				Book b = results1.get(i);
				// We create a new constraint for this student's details
				c = new GridBagConstraints();
				c.gridx = 0;
				c.gridy = i+1;
				//c.weighty = 0.2;
				//c.weightx = 0;
				c.anchor = GridBagConstraints.NORTHWEST;
	
				// We create the label, set the string to display, and make sure the
				// background color matches the panel background
				bookLabel = new JLabel("Title: " + b.getBookTitle() +
						",Condition: "+ b.getBookCondition() + ", Buyer: "+b.getSellerName() +", Bid Amount: $"+ b.getPrice());
				bookLabel.setForeground(Color.BLUE);
						/*ISBN No: "+b.get_ISBNNo() + ",+ b.getPublisher() + b.getYear()
						+ b.getGenre() + b.getSellerName() + b.getPrice() + b.getBookCondition());
				*/		
				bookLabel.setBackground(getBackground());
	
				// We add the label to this panel, subject to the constraint c that
				// positions it.
				this.add(bookLabel, c);
	
				// We create the button for editing this student
				acceptButton = new BookButton("Accept Bid");
				// We set the action command for this button to the student's ssn.
				// The "ActionCommand" is just a string that gets passed to
				// our ActionListener as part of the event object. This let's
				// the ActionListener pass the correct ssn to the controller
				acceptButton.setBook(b);
				acceptButton.addActionListener(acceptHandler);
	
				// Now we set up the constraint object that will position this
				// button
				c = new GridBagConstraints();
				c.gridx = 1;
				c.gridy = i+1;
				c.anchor = GridBagConstraints.NORTHWEST;
				//c.weightx = 0;
				//c.weighty = 0.1;
				// and we add it
				this.add(acceptButton, c);
			}
		}
			JLabel txtbuy = new JLabel("Your current bids");
			txtbuy.setFont(new Font("Tahoma", Font.PLAIN, 20));
			GridBagConstraints gbc_txtbuy = new GridBagConstraints();
			gbc_txtbuy.insets = new Insets(0, 0, 12, 0);
			gbc_txtbuy.anchor = GridBagConstraints.NORTHWEST;
			gbc_txtbuy.gridx = 2;
			gbc_txtbuy.gridy = 0;
			add(txtbuy, gbc_txtbuy);

			if (results2.size() == 0) {
				JLabel txtempty = new JLabel("No bids to display");
				GridBagConstraints gbc_txtempty = new GridBagConstraints();
				gbc_txtempty.insets = new Insets(0, 0, 9, 0);
				gbc_txtempty.anchor = GridBagConstraints.NORTHWEST;
				gbc_txtempty.gridx = 2;
				gbc_txtempty.gridy = 1;
				add(txtempty, gbc_txtempty);
			}
			
			else {
				for (j = 0; j < results2.size(); j++) {
					// First we get the student to display
					Book b = results2.get(j);
					// We create a new constraint for this student's details
					c = new GridBagConstraints();
					c.gridx = 2;
					c.gridy = j+1;
					//c.weighty = 0.2;
					//c.weightx = 0;
					c.anchor = GridBagConstraints.NORTHWEST;
		
					// We create the label, set the string to display, and make sure the
					// background color matches the panel background
					String highestBid = null;
					if (b.getQuantity()==1)
						highestBid = "yes";
					else
						highestBid = "no";
					bookLabel = new JLabel( "Title: " + b.getBookTitle() + ", Book Condition: " 
							+ b.getBookCondition() + ", Seller: "+b.getSellerName() +", Bid Amount: $"+ b.getPrice() + ", Highest Bid: "+ highestBid);
					bookLabel.setForeground(Color.BLUE);
							/*"ISBN No: "+b.get_ISBNNo() + ", + b.getPublisher() + b.getYear()
							+ b.getGenre() + b.getSellerName() + b.getPrice() + b.getBookCondition());
					*/		
					bookLabel.setBackground(getBackground());
					this.add(bookLabel, c);
					
					cancelButton = new BookButton("Cancel Bid");
					// We set the action command for this button to the student's ssn.
					// The "ActionCommand" is just a string that gets passed to
					// our ActionListener as part of the event object. This let's
					// the ActionListener pass the correct ssn to the controller
					cancelButton.setBook(b);
					cancelButton.addActionListener(cancelHandler);
		
					// Now we set up the constraint object that will position this
					// button
					c = new GridBagConstraints();
					c.gridx = 3;
					c.gridy = j+1;
					c.anchor = GridBagConstraints.NORTHWEST;
					//c.weightx = 0;
					//c.weighty = 0.1;
					// and we add it
					this.add(cancelButton, c);
				}
			}
			
		

	}

};
