import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;

import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;

/**
 * This Class is responsible for displaying a form allowing details of a new
 * Book to be input. On form submission, this View just collects the details,
 * puts them into a new Book object, and passes that to the Controller.
 * 
 */
public class BookAddPanel extends JPanel {

	/**
	 * holds a reference to our main controller object
	 */
	private Controller masterController;
	private JTextField nameField;
	private JTextField anameField;
	private JTextField pnameField;
	private JTextField isbnField;
	private JTextField yearField;
	private JTextField genreField;
	private JTextField condField;
	private JTextField quantityField;
	private JTextField priceField;
	private JTextField buyNowField;
	private JFileChooser imageChooser;
	private final JButton imageButton = new JButton("Choose Image");
	private final JButton addButton = new JButton("Add");
	private File imageFile = null;
	private static final long serialVersionUID = -3748152957839561001L;


	/**
	 * Instantiates and positions all components needed for this panel, adds an
	 * event listener to the "Add" button, and stores a reference to the master
	 * controller so we can access it's callback functions when the user clicks.
	 * 
	 * @param masterController
	 *            Holds reference to master controller object
	 */
	public BookAddPanel(Controller masterController) {
		/*
		 * The  components are added with GridBagLayoutConstraints.  Added
		 * GridBagLayoutConstraints so the components are all positioned nicely.
		 */
		super();
		setBackground(Color.WHITE);
		this.masterController = masterController;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 91, 47, 0 };
		gridBagLayout.rowHeights = new int[] { 14, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel j = new JLabel("Book Add Form");
		j.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_j = new GridBagConstraints();
		gbc_j.insets = new Insets(0, 0, 12, 0);
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
		//isbnField.setEditable(false);
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
		GridBagConstraints gbc_majorField = new GridBagConstraints();
		gbc_majorField.insets = new Insets(0, 0, 9, 0);
		gbc_majorField.fill = GridBagConstraints.HORIZONTAL;
		gbc_majorField.gridx = 1;
		gbc_majorField.gridy = 6;
		add(genreField, gbc_majorField);
		genreField.setColumns(10);
		
		JLabel lblcond = new JLabel("Book Condition:");
		GridBagConstraints gbc_lblcond = new GridBagConstraints();
		gbc_lblcond.anchor = GridBagConstraints.EAST;
		gbc_lblcond.insets = new Insets(0, 0, 12, 0);
		gbc_lblcond.gridx = 0;
		gbc_lblcond.gridy = 7;
		add(lblcond, gbc_lblcond);

		condField = new JTextField();
		GridBagConstraints gbc_condField = new GridBagConstraints();
		gbc_condField.insets = new Insets(0, 0, 12, 0);
		gbc_condField.fill = GridBagConstraints.HORIZONTAL;
		gbc_condField.gridx = 1;
		gbc_condField.gridy = 7;
		add(condField, gbc_condField);
		condField.setColumns(10);
		
		JLabel lblquantity = new JLabel("Quantity:");
		GridBagConstraints gbc_lblquantity = new GridBagConstraints();
		gbc_lblquantity.anchor = GridBagConstraints.EAST;
		gbc_lblquantity.insets = new Insets(0, 0, 12, 0);
		gbc_lblquantity.gridx = 0;
		gbc_lblquantity.gridy = 8;
		add(lblquantity, gbc_lblquantity);

		quantityField = new JTextField();
		GridBagConstraints gbc_quantityField = new GridBagConstraints();
		gbc_quantityField.insets = new Insets(0, 0, 12, 0);
		gbc_quantityField.fill = GridBagConstraints.HORIZONTAL;
		gbc_quantityField.gridx = 1;
		gbc_quantityField.gridy = 8;
		add(quantityField, gbc_quantityField);
		quantityField.setColumns(10);
		
		JLabel lblPrice = new JLabel("Starting Price:");
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.anchor = GridBagConstraints.EAST;
		gbc_lblPrice.insets = new Insets(0, 0, 9, 0);
		gbc_lblPrice.gridx = 0;
		gbc_lblPrice.gridy = 9;
		add(lblPrice, gbc_lblPrice);

		priceField = new JTextField();
		GridBagConstraints gbc_priceField = new GridBagConstraints();
		gbc_priceField.insets = new Insets(0, 0, 9, 0);
		gbc_priceField.fill = GridBagConstraints.HORIZONTAL;
		gbc_priceField.gridx = 1;
		gbc_priceField.gridy = 9;
		add(priceField, gbc_priceField);
		priceField.setColumns(10);
		
		JLabel lblbuynow = new JLabel("Buy Now Price:");
		GridBagConstraints gbc_lblbuynow = new GridBagConstraints();
		gbc_lblbuynow.anchor = GridBagConstraints.EAST;
		gbc_lblbuynow.insets = new Insets(0, 0, 12, 0);
		gbc_lblbuynow.gridx = 0;
		gbc_lblbuynow.gridy = 10;
		add(lblbuynow, gbc_lblbuynow);

		buyNowField = new JTextField();
		GridBagConstraints gbc_buyNowField = new GridBagConstraints();
		gbc_buyNowField.insets = new Insets(0, 0, 12, 0);
		gbc_buyNowField.fill = GridBagConstraints.HORIZONTAL;
		gbc_buyNowField.gridx = 1;
		gbc_buyNowField.gridy = 10;
		add(buyNowField, gbc_buyNowField);
		buyNowField.setColumns(10);
		
		imageChooser = new JFileChooser();
		GridBagConstraints gbc_imageButton = new GridBagConstraints();
		gbc_imageButton.insets = new Insets(0, 0, 12, 0);
		gbc_imageButton.anchor = GridBagConstraints.EAST;
		gbc_imageButton.gridx = 0;
		gbc_imageButton.gridy = 11;
		imageButton.addActionListener(this.imageHandler);
		imageButton.setEnabled(true);
		add(imageButton, gbc_imageButton);
		
		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.insets = new Insets(0, 0, 12, 0);
		gbc_addButton.anchor = GridBagConstraints.EAST;
		gbc_addButton.gridx = 1;
		gbc_addButton.gridy = 11;
		addButton.addActionListener(this.submitHandler);
		addButton.setEnabled(true);
		add(addButton, gbc_addButton);

	}

	ActionListener imageHandler = new ActionListener() {
		
		public void actionPerformed(ActionEvent event) {
			 if (event.getSource() == imageButton) {
			        int returnVal = imageChooser.showOpenDialog(BookAddPanel.this);
			        if (returnVal == JFileChooser.APPROVE_OPTION) {
			        	imageFile = imageChooser.getSelectedFile();
			            //This is where a real application would open the file.
			        }
			   }
		}
		
	};
	
	/**
	 * An action listener whose actionPerformed method takes care of getting all
	 * the details about the new Book from the form fields, puts them into a
	 * new Book object, and passes that Book to the master controller's
	 * callback function for adding Books.
	 * 
	 * Shows an error message if ssn, gpa, or year values are out of range or if
	 * name and/or major aren't specified.
	 */
	ActionListener submitHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent event) {
			
			if (!BookAddPanel.this.masterController.isLoggedIn) {
				JOptionPane
				.showMessageDialog(
						BookAddPanel.this.masterController.mainFrame,
						"You must log in before editing your book listings.",
						"BookEditPanel",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			String bookname;
			String aname;
			String pname;
			String isbnNo;
			double price;
			double buynow;
			int year;
			String genre;
			String cond;
			int quantity;
			try {
				boolean isBookAddDetailsClean = true;
				isbnNo = isbnField.getText();
				if (isbnNo.contentEquals("")) {
					isBookAddDetailsClean = false;
					JOptionPane.showMessageDialog(
							BookAddPanel.this.masterController.mainFrame,
							"ISBN IS REQUIRED.", "BookAddPanel",
							JOptionPane.ERROR_MESSAGE);
				}
				/*
				if (isbnNo > 999999999 || isbnNo < 100000000) {
					isBookAddDetailsClean = false;
					JOptionPane
							.showMessageDialog(
									BookAddPanel.this.masterController.mainFrame,
									"USERID IS A 9 DIGIT INTEGER THAT DOES NOT START WITH 0.",
									"BookAddPanel",
									JOptionPane.ERROR_MESSAGE);
				}*/// end if

				price = Double.parseDouble(priceField.getText());
				if (price < 0 ) {
					isBookAddDetailsClean = false;
					JOptionPane.showMessageDialog(
							BookAddPanel.this.masterController.mainFrame,
							" PRICE MUST BE A NUMBER GREATER THAN 0",
							"BookAddPanel", JOptionPane.ERROR_MESSAGE);
				}// end if
				
				buynow = Double.parseDouble(buyNowField.getText());
				if (buynow < 0 ) {
					isBookAddDetailsClean = false;
					JOptionPane.showMessageDialog(
							BookAddPanel.this.masterController.mainFrame,
							"PRICE MUST BE A NUMBER GREATER THAN 0",
							"BookAddPanel", JOptionPane.ERROR_MESSAGE);
				}// end if

				year = Integer.parseInt(yearField.getText());
				if (year < 1) {
					isBookAddDetailsClean = false;

					JOptionPane.showMessageDialog(
							BookAddPanel.this.masterController.mainFrame,
							"YEAR MUST BE AN INTEGER GREATER THAN 0.",
							"BookAddPanel", JOptionPane.ERROR_MESSAGE);
				}// end if

				bookname = nameField.getText();
				if (bookname.contentEquals("")) {
					isBookAddDetailsClean = false;
					JOptionPane.showMessageDialog(
							BookAddPanel.this.masterController.mainFrame,
							"Book NAME IS REQUIRED.", "BookAddPanel",
							JOptionPane.ERROR_MESSAGE);
				}// end if
				
				aname = anameField.getText();
				if (aname.contentEquals("")) {
					isBookAddDetailsClean = false;
					JOptionPane.showMessageDialog(
							BookAddPanel.this.masterController.mainFrame,
							"AUTHOR NAME IS REQUIRED.", "BookAddPanel",
							JOptionPane.ERROR_MESSAGE);
				}// end if
				
				pname = pnameField.getText();
				if (pname.contentEquals("")) {
					isBookAddDetailsClean = false;
					JOptionPane.showMessageDialog(
							BookAddPanel.this.masterController.mainFrame,
							"PUBLISHER NAME IS REQUIRED.", "BookAddPanel",
							JOptionPane.ERROR_MESSAGE);
				}// end if

				genre = genreField.getText();
				if (genre.contentEquals("")) {
					isBookAddDetailsClean = false;
					JOptionPane.showMessageDialog(
							BookAddPanel.this.masterController.mainFrame,
							" MAJOR NAME (GENRE) REQUIRED.", "BookAddPanel",
							JOptionPane.ERROR_MESSAGE);
				}// end if

				cond = condField.getText();
				if (cond.contentEquals("")) {
					isBookAddDetailsClean = false;
					JOptionPane.showMessageDialog(
							BookAddPanel.this.masterController.mainFrame,
							"Book STATUS IS REQUIRED.", "BookAddPanel",
							JOptionPane.ERROR_MESSAGE);
				}// end if
				
				quantity = Integer.parseInt(quantityField.getText());
				if (quantity < 1) {
					isBookAddDetailsClean = false;
					JOptionPane.showMessageDialog(
							BookAddPanel.this.masterController.mainFrame,
							"QUANTITY MUST BE AN INTEGER GREATER THAN 0.",
							"BookAddPanel", JOptionPane.ERROR_MESSAGE);
				}
				
				// all Book details are valid and hence adding to the
				// masterController
				if (isBookAddDetailsClean) {
					Book s = new Book(isbnNo, bookname, aname, pname, genre, year, BookAddPanel.this.masterController.user.getDisplayName(), price, buynow, quantity, cond);
					if (imageFile != null)
						masterController.addBook(s, imageFile);
					else
						masterController.addBook(s);
				}// end if
			}// end try
			catch (NumberFormatException e) {
				JOptionPane
						.showMessageDialog(
								BookAddPanel.this.masterController.mainFrame,
								"YEAR MUST BE A 4 DIGIT INTEGER,\nAND PRICE GREATER THAN 0\nALL FIELDS ARE REQUIRED.",
								"BookAddPanel", JOptionPane.ERROR_MESSAGE);
			}// end catch NumberFromatException
		}// end actionPerformed
	};// end actionListener
}// end class BookAddPanel
