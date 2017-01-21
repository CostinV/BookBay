
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.BorderLayout;
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
public class HomePagePanel extends JPanel {

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

	
	ActionListener searchHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			HomePagePanel.this.masterController.setSearchBook();
		}

	};

	/**
	 * Create an ActionListener to add to "login" button. 
	 */
	ActionListener loginHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
		
			HomePagePanel.this.masterController
					.setLogin();
			
		}

	};
	
	/**
	 * Create an ActionListener to add to "logout" button. 
	 */
	ActionListener logoutHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
		
			HomePagePanel.this.masterController
					.logout();
			
		}

	};

	/**
	 * Create an ActionListener to add to "register" buttons. 
	 */
	ActionListener registerHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			HomePagePanel.this.masterController
					.setRegister();
			
		}

	};

	/**
	 * Create an ActionListener to add to "buy" buttons. this button is used for buying the particular 
	 * book
	 */
	ActionListener buyHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			HomePagePanel.this.masterController.buyBookPage();
		}

	};
	/**
	 * Create an ActionListener to add to "post" buttons. this button is used for posting 
	 * comments on book
	 */
	
	ActionListener postHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			HomePagePanel.this.masterController.listPage();
					
		}

	};

	/**
	 * Create an ActionListener to add to "credit" buttons. This button is used for 
	 * adding money
	 */
	ActionListener creditHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			HomePagePanel.this.masterController.creditPage();
					
		}

	};
	
	/**
	 * Create an ActionListener to add to "cpassward" buttons. this button is used for changing the password
	 */
	ActionListener cpasswordHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			HomePagePanel.this.masterController.cpassPage();
		}

	};
		
	/**
	 * Create an ActionListener to add to "view" button. 
	 */
	ActionListener viewHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			HomePagePanel.this.masterController.viewPage();
		}

	};
	
	/**
	 * Create an ActionListener to add to "review" button. 
	 */
	ActionListener reviewHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			HomePagePanel.this.masterController.reviewPage();
		}

	};
	
	ActionListener historyHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			HomePagePanel.this.masterController.historyPage();
		}

	};
	
	ActionListener adminHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			HomePagePanel.this.masterController.adminPage();
		}

	};
	
	ActionListener editHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			HomePagePanel.this.masterController.editBookPage();
		}

	};
	
	ActionListener bidHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			HomePagePanel.this.masterController.bidBookPage();
		}

	};
	
	ActionListener aboutHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			HomePagePanel.this.masterController.aboutPage();
		}

	};
	
	private JLabel header;
	private JLabel header2;
	private JLabel header3;
	private JLabel lbldname;
	private JLabel lblcredits;
	private JLabel lblrating;
	private JButton searchButton;
	private JButton loginButton;
	private JButton registerButton;
	private JButton addCreditsButton;
	private JButton logoutButton;
	private JButton postCommentsButton;
	private JButton addbookButton;
	private JButton cpasswordButton;
	
	private JButton viewButton ;
	private JButton reviewButton ;
	private JButton historyButton;
	private JButton adminButton;
	private JButton editButton ;
	private JButton bidButton;
	private JButton buyButton ;
	private JButton aboutButton;
	
	 
	private List<String> topUsers;
	private List<Book> topBooks;
	
	
	public HomePagePanel(Controller masterController) {
		super();
		setBackground(new Color(175,238,238));

		this.masterController = masterController;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 187, 75, 0 };
		gridBagLayout.rowHeights = new int[] { 19,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0  };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,0.0, 0.0, 0.0, 0.0, 0.0, 0.0,0.0, 0.0, 0.0, 0.0, 0.0 };
		setLayout(gridBagLayout);
		header = new JLabel("Professional Syndicates");
		header.setFont(new Font("Tahoma", Font.BOLD, 20));
		header.setForeground(Color.red);
		GridBagConstraints gbc_header = new GridBagConstraints();
		gbc_header.insets = new Insets(0, 0, 10, 10);
		gbc_header.anchor = GridBagConstraints.NORTH;
		gbc_header.gridx = 1;
		gbc_header.gridy = 0;
		add(header, gbc_header);

		header2 = new JLabel("WelCome to Book Bay System");
		GridBagConstraints gbc_header2 = new GridBagConstraints();
		header2.setFont(new Font("Tahoma", Font.BOLD, 28));
		header2.setForeground(Color.BLUE);
		gbc_header2.insets = new Insets(0, 0, 10, 10);
		gbc_header.anchor = GridBagConstraints.NORTH;
		gbc_header2.gridx = 1;
		gbc_header2.gridy = 1;
		add(header2, gbc_header2);
		
		header3 = new JLabel("CSC322 Fall 2013 City College of New York");
		GridBagConstraints gbc_header3 = new GridBagConstraints();
		header3.setFont(new Font("Tahoma", Font.BOLD, 12));
		header3.setForeground(Color.GREEN);
		gbc_header3.insets = new Insets(0, 0, 10, 10);
		gbc_header.anchor = GridBagConstraints.NORTH;
		gbc_header3.gridx = 1;
		gbc_header3.gridy = 2;
		add(header3, gbc_header3);
		
		lbldname = new JLabel("Welcome, guest");
		GridBagConstraints gbc_lbldname = new GridBagConstraints();
		gbc_lbldname.insets = new Insets(0, 0, 10, 10);
		gbc_header.anchor = GridBagConstraints.CENTER;
		gbc_lbldname.gridx = 1;
		gbc_lbldname.gridy = 3;
		add(lbldname, gbc_lbldname);

		lblcredits = new JLabel("You have 0 credits");
		GridBagConstraints gbc_lblcredits = new GridBagConstraints();
		gbc_lblcredits.insets = new Insets(0, 0, 10, 0);
		gbc_header.anchor = GridBagConstraints.CENTER;
		gbc_lblcredits.gridx = 1;
		gbc_lblcredits.gridy = 4;
		add(lblcredits, gbc_lblcredits);
		
		lblrating = new JLabel("You have 0 rating");
		GridBagConstraints gbc_lblrating = new GridBagConstraints();
		gbc_lblrating.insets = new Insets(0, 0, 10, 0);
		gbc_header.anchor = GridBagConstraints.CENTER;
		gbc_lblrating.gridx = 1;
		gbc_lblrating.gridy = 5;
		add(lblrating, gbc_lblrating);
		
		searchButton = new JButton("Search Book");
		loginButton = new JButton("Login");
		registerButton = new JButton("Register");
		logoutButton = new JButton("Logout");
		addCreditsButton = new JButton("Add Money");
		addbookButton = new JButton("Add Book");
		postCommentsButton = new JButton("Write Comments");
		cpasswordButton = new JButton("Change Password");
		
		viewButton = new JButton("View Bids");
		reviewButton = new JButton("Review Books");
		historyButton = new JButton("View History");
		adminButton = new JButton("Administrator");
		editButton = new JButton("Update Book");
		bidButton = new JButton("Bid Book");
		buyButton = new JButton("Buy Book");
		aboutButton = new JButton("View AboutUs");
		
		viewButton.addActionListener(viewHandler);
		reviewButton.addActionListener(reviewHandler);
		historyButton.addActionListener(historyHandler);
		adminButton.addActionListener(adminHandler);
		editButton.addActionListener(editHandler);
		bidButton.addActionListener(bidHandler);
		buyButton.addActionListener(buyHandler);
		aboutButton.addActionListener(aboutHandler);
		
		cpasswordButton.addActionListener(cpasswordHandler);
		searchButton.addActionListener(searchHandler);
		loginButton.addActionListener(loginHandler);
		registerButton.addActionListener(registerHandler);
		logoutButton.addActionListener(logoutHandler);
		addCreditsButton.addActionListener(creditHandler);
		// Now we set up the constraint object that will position this
		// button
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 16;
		c.insets = new Insets(0, 0, 10, 0);
		c.anchor = GridBagConstraints.CENTER;
		this.add(searchButton, c);
		
		c.gridx = 2;
		c.gridy = 16;
		c.insets = new Insets(0, 0, 10, 0);
		c.anchor = GridBagConstraints.CENTER;
		this.add(registerButton, c);
		
		c.gridx = 1;
		c.gridy = 16;
		c.insets = new Insets(0, 0, 10, 0);
		c.anchor = GridBagConstraints.CENTER;
		this.add(loginButton, c);
		
		c.gridx = 2;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 10, 0);
		c.anchor = GridBagConstraints.CENTER;
		this.add(logoutButton, c);
		
		c.gridx = 2;
		c.gridy = 16;
		c.insets = new Insets(0, 0, 10, 0);
		c.anchor = GridBagConstraints.CENTER;
		this.add(addCreditsButton, c);
		
		c.gridx = 1;
		c.gridy = 16;
		c.insets = new Insets(0, 0, 10, 0);
		c.anchor = GridBagConstraints.CENTER;
		this.add(addbookButton, c);
		
		c.gridx = 2;
		c.gridy = 17;
		c.insets = new Insets(0, 0, 10, 0);
		c.anchor = GridBagConstraints.CENTER;
		this.add(postCommentsButton, c);
		
		c.gridx = 1;
		c.gridy = 17;
		c.insets = new Insets(0, 0, 10, 0);
		c.anchor = GridBagConstraints.CENTER;
		this.add(cpasswordButton, c);
		
		c.gridx = 0;
		c.gridy = 5;
		c.insets = new Insets(0, 0, 10, 0);
		c.anchor = GridBagConstraints.CENTER;
		this.add(viewButton, c);
		
		c.gridx = 0;
		c.gridy = 17;
		c.insets = new Insets(0, 0, 10, 0);
		c.anchor = GridBagConstraints.CENTER;
		this.add(reviewButton, c);
		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets(0, 0, 10, 0);
		c.anchor = GridBagConstraints.CENTER;
		this.add(historyButton, c);
		
		c.gridx = 2;
		c.gridy = 4;
		c.insets = new Insets(0, 0, 10, 0);
		c.anchor = GridBagConstraints.CENTER;
		this.add(adminButton, c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(0, 0, 10, 0);
		c.anchor = GridBagConstraints.CENTER;
		this.add(aboutButton, c);
		
		c.gridx = 2;
		c.gridy = 5;
		c.insets = new Insets(0, 0, 10, 0);
		c.anchor = GridBagConstraints.CENTER;
		this.add(editButton, c);
		
		/*
 		JTextField printField = new JTextField();
 		printField.setText(this.masterController.user.getDisplayName());
 		JScrollPane scroll = new JScrollPane(printField, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
         GridBagConstraints gbc_scroll = new GridBagConstraints();
         gbc_scroll.insets = new Insets(0, 0, 5, 0);
         gbc_scroll.gridx = 0;
         gbc_scroll.gridy = 10;
         gbc_scroll.fill = GridBagConstraints.VERTICAL;
         gbc_scroll.gridheight = 2;
         gbc_scroll.gridwidth = 3;
         add(scroll, gbc_scroll);
         add(printField);
        
         JScrollBar scrollbar = new JScrollBar();
         GridBagConstraints gbc_scrollbar = new GridBagConstraints();
         gbc_scrollbar.insets = new Insets(0, 0, 5, 0);
         gbc_scrollbar.gridx = 0;
         gbc_scrollbar.gridy = 11;
         gbc_scrollbar.fill = GridBagConstraints.REMAINDER;
         gbc_scrollbar.gridheight = 1;
         gbc_scrollbar.gridwidth = 3;
         add(scrollbar, gbc_scrollbar);
         */
		updateButtons(false);
		setTopBooksAndUsers(masterController.getTopBooks(), masterController.getTopUsers());
	}
	
	public void updateUser(boolean b) {
		if (b) {
			lbldname.setText("Welcome, " + masterController.user.getDisplayName());
			lblcredits.setText("You have " + masterController.user.getCredits() + " credits");
			lblrating.setText("You have " + masterController.user.getRating() + " rating");
			JLabel lblPrintField = new JLabel();
			lblPrintField.setText(masterController.toString());
		} else {
			lbldname.setText("Welcome, Guest");
			lblcredits.setText("You have 0 credits");
			lblrating.setText("You have 0 rating");
		}
	}
	
	public void setTopBooksAndUsers(List<Book> topBooks, List<String> topUsers) {
		
		JLabel lbltopbooks = new JLabel("Top Books");
		lbltopbooks.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lbltopbooks = new GridBagConstraints();
		//gbc_lbltopbooks.insets = new Insets(0, 0, 9, 0);
		gbc_lbltopbooks.anchor = GridBagConstraints.NORTH;
		//gbc_lbltopbooks.gridwidth = 5;
		gbc_lbltopbooks.gridx = 1;
		gbc_lbltopbooks.gridy = 18;
		add(lbltopbooks, gbc_lbltopbooks);
		
		JLabel bookLabel;
		int i, j;
		GridBagConstraints c;
		
		for (i = 0; i < topBooks.size(); i++) {
			// First we get the student to display
			Book b = topBooks.get(i);

			// We create a new constraint for this student's details
			c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = 19+i;
			c.gridwidth = 3;
			c.anchor = GridBagConstraints.CENTER;

			// We create the label, set the string to display, and make sure the
			// background color matches the panel background
			bookLabel = new JLabel("ISBN: "+b.get_ISBNNo() + ", Book Title: "
					+ b.getBookTitle()+ ", Author: "+b.getAuthorName() +", Year: " + b.getYear() + ", Publisher: " + b.getPublisher()
					+ ", Genre: " + b.getGenre());
			//bookLabel.setForeground(Color.BLUE);
					/*+ b.getPublisher() + b.getYear()
					+ b.getGenre() + b.getSellerName() + b.getPrice() + b.getBookCondition());
	*/		
			//bookLabel.setBackground(getBackground());

			// We add the label to this panel, subject to the constraint c that
			// positions it.
			this.add(bookLabel, c);
		}
		
		JLabel lbltopusers = new JLabel("Top Users");
		lbltopusers.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lbltopusers = new GridBagConstraints();
		//gbc_lbltopusers.insets = new Insets(0, 0, 9, 0);
		gbc_lbltopusers.anchor = GridBagConstraints.NORTH;
		//gbc_lbltopusers.gridwidth = 5;
		gbc_lbltopusers.gridx = 1;
		gbc_lbltopusers.gridy = 19+i;
		add(lbltopusers, gbc_lbltopusers);
		i++;
		
		for (j = 0; j < topUsers.size(); j++) {
			// First we get the student to display
			String u = topUsers.get(j);

			// We create a new constraint for this student's details
			c = new GridBagConstraints();
			c.gridx = 1;
			c.gridy = 19+j+i;
			c.anchor = GridBagConstraints.CENTER;

			bookLabel = new JLabel(u);

			this.add(bookLabel, c);
		}
	}
	
	public void updateButtons(boolean b) {
		if (b) {
			registerButton.setVisible(false);
			registerButton.setEnabled(false);
			
			loginButton.setVisible(false);
			loginButton.setEnabled(false);
			
			logoutButton.setVisible(true);
			logoutButton.setEnabled(true);
			
			cpasswordButton.setVisible(true);
			cpasswordButton.setEnabled(true);
			
			editButton.setEnabled(true);
			editButton.setVisible(true);
			addCreditsButton.setEnabled(true);
			addCreditsButton.setVisible(true);
			bidButton.setEnabled(true);
			bidButton.setVisible(true);
			buyButton.setEnabled(true);
			buyButton.setVisible(true);
			addbookButton.setVisible(true);
			addbookButton.setEnabled(true);
			viewButton.setVisible(true);
			viewButton.setEnabled(true);
			addbookButton.setEnabled(true);
			addbookButton.setVisible(true);
			reviewButton.setVisible(true);
			reviewButton.setEnabled(true);
			historyButton.setVisible(true);
			historyButton.setEnabled(true);
			adminButton.setVisible(true);
			adminButton.setEnabled(true);
			aboutButton.setVisible(true);
			aboutButton.setEnabled(true);
			postCommentsButton.setVisible(true);
			postCommentsButton.setEnabled(true);
		}
		else {
			registerButton.setVisible(true);
			loginButton.setVisible(true);
			registerButton.setEnabled(true);
			loginButton.setEnabled(true);
			
			cpasswordButton.setVisible(false);
			cpasswordButton.setEnabled(false);
			
			logoutButton.setVisible(false);
			logoutButton.setEnabled(false);
			
			editButton.setEnabled(false);
			editButton.setVisible(false);
			addCreditsButton.setEnabled(false);
			addCreditsButton.setVisible(false);
			bidButton.setEnabled(false);
			bidButton.setVisible(false);
			buyButton.setEnabled(false);
			buyButton.setVisible(false);
			addbookButton.setVisible(false);
			addbookButton.setEnabled(false);
			viewButton.setVisible(false);
			viewButton.setEnabled(false);
			addbookButton.setEnabled(false);
			addbookButton.setVisible(false);
			reviewButton.setVisible(false);
			reviewButton.setEnabled(false);
			historyButton.setVisible(false);
			historyButton.setEnabled(false);
			adminButton.setVisible(false);
			adminButton.setEnabled(true);
			aboutButton.setVisible(false);
			aboutButton.setEnabled(false);
			postCommentsButton.setVisible(false);
			postCommentsButton.setEnabled(false);
		}
		
	}
	
}
