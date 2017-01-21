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
 * This is a View class in the MVC pattern. It is responsible for taking a
 * single student object and displaying the details in a form. The user can
 * alter the details in any of the form fields except ssn which is disabled,
 * then submit the form to save changes. The ActionListener for the submit
 * button just grabs the details from the form fields, puts them into a student
 * object, and passes that to the master controller's editStudent callback.
 * 
 * This class is fully functional, you should not need to alter it. On
 * successful submission, the controller loads the StudentDisplayPanel, which
 * you need to fix so it actually displays the student. But nothing in this
 * class needs to be changed.
 */
public class BookEditPanel extends JPanel {

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
	private final JButton editButton = new JButton("Edit");
	private File imageFile = null;
	/**
	 * Create an ActionListener to add to "home" buttons. when user clicks this button 
	 * the controlller links panel to homepage
	 */
	ActionListener homeHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			BookEditPanel.this.masterController
					.goHomePage();
		}

	};
	private static final long serialVersionUID = -3748152957839561001L;


	/**
	 * Instantiates and positions all components needed for this panel, adds an
	 * event listener to the "Add" button, and stores a reference to the master
	 * controller so we can access it's callback functions when the user clicks.
	 * 
	 * @param masterController
	 *            Holds reference to master controller object
	 */
	public BookEditPanel(Controller masterController) {
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

		JLabel j = new JLabel("Book Edit Form");
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
		
		
		GridBagConstraints gbc_editButton = new GridBagConstraints();
		gbc_editButton.insets = new Insets(0, 0, 9, 0);
		gbc_editButton.anchor = GridBagConstraints.EAST;
		gbc_editButton.gridx = 1;
		gbc_editButton.gridy = 11;
		editButton.addActionListener(this.submitHandler);
		add(editButton, gbc_editButton);
		
		imageChooser = new JFileChooser();
		GridBagConstraints gbc_imageButton = new GridBagConstraints();
		gbc_imageButton.insets = new Insets(0, 0, 12, 0);
		gbc_imageButton.anchor = GridBagConstraints.EAST;
		gbc_imageButton.gridx = 0;
		gbc_imageButton.gridy = 11;
		imageButton.addActionListener(this.imageHandler);
		imageButton.setEnabled(true);
		add(imageButton, gbc_imageButton);
		
		BookButton homeButton;
		homeButton = new BookButton("home");
		//homeButton.setBook(b);
		homeButton.addActionListener(homeHandler);
		GridBagConstraints cc = new GridBagConstraints();
		cc.gridx = 0;
		cc.gridy = 12;
		cc.weightx = 0;
		cc.anchor = GridBagConstraints.CENTER;
		cc.weighty = 0.1;
		this.add(homeButton, cc);
	}

	/**
	 * This method sets the student edit panels text fields to the values
	 * contained in Student
	 * 
	 * @param s
	 *            the Book details to display
	 */
	public void setBook(Book s) {
		this.isbnField.setText(s.get_ISBNNo());
		this.nameField.setText(s.getBookTitle());
		this.anameField.setText(s.getAuthorName());
		this.pnameField.setText(s.getPublisher());
		this.yearField.setText(Integer.toString(s.getYear()));
		this.priceField.setText(Double.toString(s.getPrice()));
		this.genreField.setText(s.getGenre());
		this.condField.setText(s.getBookCondition());
		editButton.setEnabled(true);

	}
	
ActionListener imageHandler = new ActionListener() {
		
		public void actionPerformed(ActionEvent event) {
			 if (event.getSource() == imageButton) {
			        int returnVal = imageChooser.showOpenDialog(BookEditPanel.this);
			        if (returnVal == JFileChooser.APPROVE_OPTION) {
			        	imageFile = imageChooser.getSelectedFile();
			            //This is where a real application would open the file.
			        }
			   }
		}
		
	};

	ActionListener submitHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent event) {
			
			if (!BookEditPanel.this.masterController.isLoggedIn) {
				JOptionPane
				.showMessageDialog(
						BookEditPanel.this.masterController.mainFrame,
						"You must log in before editing your book listings.",
						"BookEditPanel",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			String name;
			String aname;
			String pname;
			String isbn;
			double price = 0;
			double buynow = 0;
			int year = 0;
			String genre;
			String cond;
			int quantity = -1;

			try {
				boolean isStudentEditDetailsClean = true;

				isbn = isbnField.getText();
				if (isbn.equals("")) {
					isStudentEditDetailsClean = false;
					JOptionPane
							.showMessageDialog(
									BookEditPanel.this.masterController.mainFrame,
									"ISBN IS REQUIRED ",
									"BookEditPanel",
									JOptionPane.ERROR_MESSAGE);
				}// end if
				
				cond = condField.getText();
				if (cond.equals("")) {
					isStudentEditDetailsClean = false;
					JOptionPane
							.showMessageDialog(
									BookEditPanel.this.masterController.mainFrame,
									"BOOK CONDITION IS REQUIRED ",
									"BookEditPanel",
									JOptionPane.ERROR_MESSAGE);
				}
				
				if (!priceField.getText().equals("")) {
					price = Double.parseDouble(priceField.getText());
					if (price < 0 ) {
						isStudentEditDetailsClean = false;
						JOptionPane.showMessageDialog(
								BookEditPanel.this.masterController.mainFrame,
								"PRICE MUST BE GREATER THAN 0",
								"BookEditPanel", JOptionPane.ERROR_MESSAGE);
				}}// end if
				
				if (!buyNowField.getText().equals("")) {
					buynow = Double.parseDouble(buyNowField.getText());
					if (buynow < 0 ) {
						isStudentEditDetailsClean = false;
						JOptionPane.showMessageDialog(
								BookEditPanel.this.masterController.mainFrame,
								"PRICE MUST BE GREATER THAN 0",
								"BookEditPanel", JOptionPane.ERROR_MESSAGE);
				}}
				
				if (!quantityField.getText().equals("")) {
					quantity = Integer.parseInt(quantityField.getText());
					if (quantity < 0) {
						isStudentEditDetailsClean = false;
						JOptionPane.showMessageDialog(
								BookEditPanel.this.masterController.mainFrame,
								"QUANTITY CANNOT BE LESS THAN 0",
								"BookEditPanel", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				if (!yearField.getText().equals("")) {
				year = Integer.parseInt(yearField.getText());
				if (year < 1000 || year > 9999) {
					isStudentEditDetailsClean = false;
					JOptionPane.showMessageDialog(
							BookEditPanel.this.masterController.mainFrame,
							"YEAR MUST BE A 4 DIGIT INTEGER.",
							"BookEditPanel", JOptionPane.ERROR_MESSAGE);
				}}// end if
				
				name = nameField.getText();
				aname = anameField.getText();
				pname = pnameField.getText();
				genre = genreField.getText();
				
		
				// the student details are valid and hence adding to the
				// mastercontroller
				if (isStudentEditDetailsClean) {
					Book s = new Book(isbn, name, aname, pname, genre, year, BookEditPanel.this.masterController.user.getDisplayName(), price, buynow, quantity, cond);
					if (quantity == 0)
						masterController.removeBook(s);
					else if (!name.equals("") || !aname.equals("") || !pname.equals("") || price!=0 || buynow!=0 ||
							quantity!=-1 || year!=0 || !genre.equals("") || imageFile != null) {
						if (imageFile != null)
							masterController.editBook(s, imageFile);
						else
							masterController.editBook(s);
					} else {
						JOptionPane.showMessageDialog(
								BookEditPanel.this.masterController.mainFrame,
								"NOTHING TO CHANGE",
								"BookEditPanel", JOptionPane.INFORMATION_MESSAGE);
					}
						
				}// end if
				
			}// end try
			catch (NumberFormatException e) {
				JOptionPane
						.showMessageDialog(
								BookEditPanel.this.masterController.mainFrame,
								"Number format excedption."
										+ "MORE INFORMATION ABOUT THE BOOK IS REQUIRED.",
								"BookEditPanel", JOptionPane.ERROR_MESSAGE);
			}// end catch NumberFormatException
		}// end actionPerformed
	};// end actionListener
}// end class BookEditPanel
