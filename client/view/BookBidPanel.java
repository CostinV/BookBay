
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is a View class in the MVC pattern. It is responsible for taking a
 * single student object and displaying the details nicely.
 * 
 * The layout is set up for you already. The only thing you need to do is
 * complete the setStudentDetails(Student s) method below.
 * 
 */
public class BookBidPanel extends JPanel {

	private static final long serialVersionUID = -5901854105720824407L;
	
	private Controller masterController;
	private JTextField nameField;
	private JTextField anameField;
	private JTextField pnameField;
	private JTextField isbnField;
	private JTextField yearField;
	private JTextField priceField;
	private JTextField genreField;
	private JTextField condField;
	private JTextField quantityField;
	private JTextField snameField;
	private JTextField minimumBidField;
	private JTextField buyNowField;
	private Book book;
	private double minBid;
	private final JButton bidButton = new JButton("Bid");

	public BookBidPanel(Controller masterController) {
		super();
		setBackground(Color.WHITE);
		this.masterController = masterController;
		/*
		if (!masterController.isLoggedIn) {
			
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[] { 91, 47, 0 };
			gridBagLayout.rowHeights = new int[] { 14, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			gridBagLayout.columnWeights = new double[] { Double.MIN_VALUE };
			gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
			setLayout(gridBagLayout);
			JLabel j = new JLabel("Book Bid Form");
			j.setFont(new Font("Tahoma", Font.PLAIN, 20));
			GridBagConstraints gbc_j = new GridBagConstraints();
			gbc_j.insets = new Insets(0, 0, 9, 0);
			gbc_j.anchor = GridBagConstraints.NORTH;
			gbc_j.gridx = 0;
			gbc_j.gridy = 0;
			add(j, gbc_j);
			
			JLabel lbl1 = new JLabel("Guests may not bid on books");
			GridBagConstraints gbc_lbl1 = new GridBagConstraints();
			//gbc_lbl1.anchor = GridBagConstraints.EAST;
			gbc_lbl1.insets = new Insets(0, 0, 9, 0);
			gbc_lbl1.gridx = 0;
			gbc_lbl1.gridy = 1;
			add(lbl1, gbc_lbl1);
			
			JLabel lbl2 = new JLabel("Please log in first");
			GridBagConstraints gbc_lbl2 = new GridBagConstraints();
			//gbc_lbl2.anchor = GridBagConstraints.EAST;
			gbc_lbl2.insets = new Insets(0, 0, 9, 0);
			gbc_lbl2.gridx = 0;
			gbc_lbl2.gridy = 2;
			add(lbl2, gbc_lbl2);
		} else { */
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 91, 47, 0 };
		gridBagLayout.rowHeights = new int[] { 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);
		JLabel j = new JLabel("Book Bid Form");
		j.setFont(new Font("Tahoma", Font.BOLD, 20));
		//j.setForeground(Color.RED);
		j.setBackground(Color.BLUE);
		GridBagConstraints gbc_j = new GridBagConstraints();
		gbc_j.insets = new Insets(0, 0, 9, 0);
		gbc_j.anchor = GridBagConstraints.NORTH;
		gbc_j.gridx = 1;
		gbc_j.gridy = 0;
		add(j, gbc_j);
		
		JLabel lblBookName = new JLabel("Book Name:");
		GridBagConstraints gbc_lblBookName = new GridBagConstraints();
		gbc_lblBookName.anchor = GridBagConstraints.EAST;
		gbc_lblBookName.insets = new Insets(0, 0, 9, 0);
		gbc_lblBookName.gridx = 0;
		gbc_lblBookName.gridy = 1;
		add(lblBookName, gbc_lblBookName);

		nameField = new JTextField();
		//nameField.setEnabled(false);
		nameField.setEditable(false);
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.insets = new Insets(0, 0, 9, 0);
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.gridx = 1;
		gbc_nameField.gridy = 1;
		add(nameField, gbc_nameField);
		nameField.setColumns(10);
		
		JLabel lblaBookName = new JLabel("Author Name:");
		GridBagConstraints gbc_lblaBookName = new GridBagConstraints();
		gbc_lblaBookName.anchor = GridBagConstraints.EAST;
		gbc_lblaBookName.insets = new Insets(0, 0, 9, 0);
		gbc_lblaBookName.gridx = 0;
		gbc_lblaBookName.gridy = 2;
		add(lblaBookName, gbc_lblaBookName);

		anameField = new JTextField();
		//anameField.setEnabled(false);
		anameField.setEditable(false);
		GridBagConstraints gbc_anameField = new GridBagConstraints();
		gbc_anameField.insets = new Insets(0, 0, 9, 0);
		gbc_anameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_anameField.gridx = 1;
		gbc_anameField.gridy = 2;
		add(anameField, gbc_anameField);
		anameField.setColumns(10);

		JLabel lblpBookName = new JLabel("Publisher:");
		GridBagConstraints gbc_lblpBookName = new GridBagConstraints();
		gbc_lblpBookName.anchor = GridBagConstraints.EAST;
		gbc_lblpBookName.insets = new Insets(0, 0, 9, 0);
		gbc_lblpBookName.gridx = 0;
		gbc_lblpBookName.gridy = 3;
		add(lblpBookName, gbc_lblpBookName);

		pnameField = new JTextField();
		//pnameField.setEnabled(false);
		pnameField.setEditable(false);
		GridBagConstraints gbc_pnameField = new GridBagConstraints();
		gbc_pnameField.insets = new Insets(0, 0, 9, 0);
		gbc_pnameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_pnameField.gridx = 1;
		gbc_pnameField.gridy = 3;
		add(pnameField, gbc_pnameField);
		pnameField.setColumns(10);
		
		JLabel lblisbn = new JLabel("ISBN No:");
		GridBagConstraints gbc_lblSsn = new GridBagConstraints();
		gbc_lblSsn.anchor = GridBagConstraints.EAST;
		gbc_lblSsn.insets = new Insets(0, 0, 9, 0);
		gbc_lblSsn.gridx = 0;
		gbc_lblSsn.gridy = 4;
		add(lblisbn, gbc_lblSsn);

		isbnField = new JTextField();
		//isbnField.setEnabled(false);
		isbnField.setEditable(false);
		GridBagConstraints gbc_ssnField = new GridBagConstraints();
		gbc_ssnField.insets = new Insets(0, 0, 9, 0);
		gbc_ssnField.fill = GridBagConstraints.HORIZONTAL;
		gbc_ssnField.gridx = 1;
		gbc_ssnField.gridy = 4;
		add(isbnField, gbc_ssnField);
		isbnField.setColumns(10);

		JLabel lblYear = new JLabel("Year:");
		GridBagConstraints gbc_lblYear = new GridBagConstraints();
		gbc_lblYear.anchor = GridBagConstraints.EAST;
		gbc_lblYear.insets = new Insets(0, 0, 9, 0);
		gbc_lblYear.gridx = 0;
		gbc_lblYear.gridy = 5;
		add(lblYear, gbc_lblYear);

		yearField = new JTextField();
		//yearField.setEnabled(false);
		yearField.setEditable(false);
		GridBagConstraints gbc_yearField = new GridBagConstraints();
		gbc_yearField.insets = new Insets(0, 0, 9, 0);
		gbc_yearField.fill = GridBagConstraints.HORIZONTAL;
		gbc_yearField.gridx = 1;
		gbc_yearField.gridy = 5;
		add(yearField, gbc_yearField);
		yearField.setColumns(10);

		JLabel lblMajor = new JLabel("Genre:");
		GridBagConstraints gbc_lblMajor = new GridBagConstraints();
		gbc_lblMajor.anchor = GridBagConstraints.EAST;
		gbc_lblMajor.insets = new Insets(0, 0, 9, 0);
		gbc_lblMajor.gridx = 0;
		gbc_lblMajor.gridy = 6;
		add(lblMajor, gbc_lblMajor);

		genreField = new JTextField();
		//genreField.setEnabled(false);
		genreField.setEditable(false);
		GridBagConstraints gbc_majorField = new GridBagConstraints();
		gbc_majorField.insets = new Insets(0, 0, 9, 0);
		gbc_majorField.fill = GridBagConstraints.HORIZONTAL;
		gbc_majorField.gridx = 1;
		gbc_majorField.gridy = 6;
		add(genreField, gbc_majorField);
		genreField.setColumns(10);
		
		JLabel lblsBookName = new JLabel("Seller Name:");
		GridBagConstraints gbc_lblsBookName = new GridBagConstraints();
		gbc_lblsBookName.anchor = GridBagConstraints.EAST;
		gbc_lblsBookName.insets = new Insets(0, 0, 9, 0);
		gbc_lblsBookName.gridx = 0;
		gbc_lblsBookName.gridy = 7;
		add(lblsBookName, gbc_lblsBookName);

		snameField = new JTextField();
		//snameField.setEnabled(false);
		snameField.setEditable(false);
		GridBagConstraints gbc_snameField = new GridBagConstraints();
		gbc_snameField.insets = new Insets(0, 0, 9, 0);
		gbc_snameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_snameField.gridx = 1;
		gbc_snameField.gridy = 7;
		add(snameField, gbc_snameField);
		snameField.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		GridBagConstraints gbc_lblQuantity = new GridBagConstraints();
		gbc_lblQuantity.anchor = GridBagConstraints.EAST;
		gbc_lblQuantity.insets = new Insets(0, 0, 9, 0);
		gbc_lblQuantity.gridx = 0;
		gbc_lblQuantity.gridy = 8;
		add(lblQuantity , gbc_lblQuantity);

		quantityField = new JTextField();
		quantityField.setEditable(false);
		GridBagConstraints gbc_lblQuantityField = new GridBagConstraints();
		gbc_lblQuantityField.insets = new Insets(0, 0, 9, 0);
		gbc_lblQuantityField.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblQuantityField.gridx = 1;
		gbc_lblQuantityField.gridy = 8;
		add(quantityField, gbc_lblQuantityField);
		anameField.setColumns(10);
		
		JLabel lblCondition = new JLabel("Condition:");
		GridBagConstraints gbc_lblCondition = new GridBagConstraints();
		gbc_lblCondition.anchor = GridBagConstraints.EAST;
		gbc_lblCondition.insets = new Insets(0, 0, 9, 0);
		gbc_lblCondition.gridx = 0;
		gbc_lblCondition.gridy = 9;
		add(lblCondition , gbc_lblCondition);

		condField = new JTextField();
		condField.setEditable(false);
		GridBagConstraints gbc_lblcondField = new GridBagConstraints();
		gbc_lblcondField.insets = new Insets(0, 0, 9, 0);
		gbc_lblcondField.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblcondField.gridx = 1;
		gbc_lblcondField.gridy = 9;
		add(condField, gbc_lblcondField);
		condField.setColumns(10);
		
		JLabel lblHighestBid = new JLabel("Minimum Bid:");
		GridBagConstraints gbc_lblHighestBid = new GridBagConstraints();
		gbc_lblHighestBid.anchor = GridBagConstraints.EAST;
		gbc_lblHighestBid.insets = new Insets(0, 0, 9, 0);
		gbc_lblHighestBid.gridx = 0;
		gbc_lblHighestBid.gridy = 10;
		add(lblHighestBid, gbc_lblHighestBid);

		minimumBidField = new JTextField();
		minimumBidField.setEditable(false);
		GridBagConstraints gbc_minimumBidField = new GridBagConstraints();
		gbc_minimumBidField.insets = new Insets(0, 0, 9, 0);
		gbc_minimumBidField.fill = GridBagConstraints.HORIZONTAL;
		gbc_minimumBidField.gridx = 1;
		gbc_minimumBidField.gridy = 10;
		add(minimumBidField, gbc_minimumBidField);
		minimumBidField.setColumns(10);
		
		JLabel lblbuyNow = new JLabel("Buy Now:");
		GridBagConstraints gbc_lblbuyNow = new GridBagConstraints();
		gbc_lblbuyNow.anchor = GridBagConstraints.EAST;
		gbc_lblbuyNow.insets = new Insets(0, 0, 9, 0);
		gbc_lblbuyNow.gridx = 0;
		gbc_lblbuyNow.gridy = 11;
		add(lblbuyNow, gbc_lblbuyNow);

		buyNowField = new JTextField();
		buyNowField.setEditable(false);
		GridBagConstraints gbc_buyNowField = new GridBagConstraints();
		gbc_buyNowField.insets = new Insets(0, 0, 9, 0);
		gbc_buyNowField.fill = GridBagConstraints.HORIZONTAL;
		gbc_buyNowField.gridx = 1;
		gbc_buyNowField.gridy = 11;
		add(buyNowField, gbc_buyNowField);
		buyNowField.setColumns(10);
		
		JLabel lblprice = new JLabel("Bid Amount:");
		GridBagConstraints gbc_lblprice = new GridBagConstraints();
		gbc_lblprice.anchor = GridBagConstraints.EAST;
		gbc_lblprice.insets = new Insets(0, 0, 9, 0);
		gbc_lblprice.gridx = 0;
		gbc_lblprice.gridy = 12;
		add(lblprice, gbc_lblprice);

		priceField = new JTextField();
		GridBagConstraints gbc_priceField = new GridBagConstraints();
		gbc_priceField.insets = new Insets(0, 0, 9, 0);
		gbc_priceField.fill = GridBagConstraints.HORIZONTAL;
		gbc_priceField.gridx = 1;
		gbc_priceField.gridy = 12;
		add(priceField, gbc_priceField);
		priceField.setColumns(10);
		
		GridBagConstraints gbc_bidButton = new GridBagConstraints();
		gbc_bidButton.insets = new Insets(0, 0, 9, 0);
		gbc_bidButton.anchor = GridBagConstraints.EAST;
		gbc_bidButton.gridx = 1;
		gbc_bidButton.gridy = 13;
		bidButton.addActionListener(this.submitHandler);
		bidButton.setEnabled(false);
		add(bidButton, gbc_bidButton);
		
	}

	/**
	 * This method sets the student edit panels text fields to the values
	 * contained in Student
	 * 
	 * @param s
	 *            the Book details to display
	 */
	
	public void setGuestDisplay() {
		this.nameField.setText("Guests may not bid on books");
		nameField.setForeground(Color.RED);
		nameField.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.anameField.setText("Please log in first");
		anameField.setForeground(Color.BLUE);
		anameField.setFont(new Font("Tahoma", Font.BOLD, 16));
	}
	
	public void setNormalDisplay() {
		if (book == null) {
			this.nameField.setText("");
			this.anameField.setText("");
		}
	}
	
	public void setBook(Book s, double bid) {
		book = s;
		this.isbnField.setText(book.get_ISBNNo());
		this.nameField.setText(book.getBookTitle());
		this.anameField.setText(book.getAuthorName());
		this.pnameField.setText(book.getPublisher());
		this.snameField.setText(book.getSellerName());
		this.yearField.setText(Integer.toString(book.getYear()));
		this.quantityField.setText(Integer.toString(book.getQuantity()));
		if (bid==0) {
			minBid = s.getPrice();
			this.minimumBidField.setText(Double.toString(minBid));
		} else {
			minBid = bid;
			this.minimumBidField.setText(Double.toString(minBid));
		}
		this.genreField.setText(book.getGenre());
		this.condField.setText(book.getBookCondition());
		this.buyNowField.setText(Double.toString(book.getBuyNowPrice()));
		bidButton.setEnabled(true);
		
	}

	ActionListener submitHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent event) {
			try {
				boolean isBookBidDetailsClean = true;
				
				if (BookBidPanel.this.masterController.user.getDisplayName().equals(book.getSellerName())) {
					isBookBidDetailsClean = false;
					JOptionPane.showMessageDialog(
							BookBidPanel.this.masterController.mainFrame,
							"You cannot bid on your own books!",
							"BookBidPanel", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				book.setPrice(Double.parseDouble(priceField.getText()));
				if (book.getPrice() < minBid ) {
					isBookBidDetailsClean = false;

					JOptionPane.showMessageDialog(
							BookBidPanel.this.masterController.mainFrame,
							"BID AMOUNT IS LESS THAN THE MINIMUM BID AMOUNT\nPLEASE ENTER A LARGER BID AMOUNT",
							"BookBidPanel", JOptionPane.ERROR_MESSAGE);
				} else if (book.getPrice() > masterController.user.getCredits()) {
					isBookBidDetailsClean = false;
					JOptionPane.showMessageDialog(
							BookBidPanel.this.masterController.mainFrame,
							"You do not have sufficient credits!",
							"BookBidPanel", JOptionPane.ERROR_MESSAGE);
				}
	

				// the student details are valid and hence adding to the
				// mastercontroller
				if (isBookBidDetailsClean) {
					masterController.addBid(book);
					bidButton.setEnabled(false);
				}// end if

			}// end try
			catch (NumberFormatException e) {
				JOptionPane
						.showMessageDialog(
								BookBidPanel.this.masterController.mainFrame,
								"Number format excecption.",
								"BookBidPanel", JOptionPane.ERROR_MESSAGE);
			}// end catch NumberFormatException
		}// end actionPerformed
	};// end actionListener

}
