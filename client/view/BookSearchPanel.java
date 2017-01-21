import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This is a View in our MVC pattern, it's shows a form to let the user enter
 * search constraints and 2 buttons, 1 for clearing the form, and 1 for
 * submitting the form.
 */
public class BookSearchPanel extends JPanel {

	private static final long serialVersionUID = -1919259732216456062L;

	private Controller masterController;

	private JTextField txtisbn;
	private JTextField txtMinprice;
	private JTextField txtMaxprice;
	private JTextField txtMinyear;
	private JTextField txtMaxyear;
	private JTextField txtTitle;
	private JTextField txtauthor;
	private JTextField txtpublisher;
	private JTextField txtGenre;
	private JTextField txtseller;
	private JCheckBox chckbxGenrenameexact;
	private JCheckBox chckbxNameexact;
	private JCheckBox chckbxauthorexact;
	private JCheckBox chckbxpublisherexact;
	private JCheckBox chckbxsellerexact;
	//private JCheckBox chckbxforWeb;
	private JButton btnClear;
	/**
	 * Create an ActionListener to add to "home" buttons. when user clicks this button 
	 * the controlller links panel to homepage
	 */
	ActionListener homeHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			BookSearchPanel.this.masterController
					.goHomePage();
		}

	};
	/**
	 * Instantiates this component and all subcomponents and positions them.
	 * Adds action listeners to the buttons.
	 * 
	 * @param controller
	 *            reference to the master controller so we can access callback
	 *            functions.
	 */
	public BookSearchPanel(Controller controller) {
		/*
		 * 
		 * all subcomponents are just added to this panel with layout
		 * constraints. Fixed it so the form looks good, tried to match the
		 * screen shot provided.
		 */
		super();
		setBackground(Color.WHITE);
		this.masterController = controller;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 14, 147, 172 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0 };
		gridBagLayout.columnWeights = new double[] { 0.5, 0.5, 0.5,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel j = new JLabel("Book Search Form");
		j.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_j = new GridBagConstraints();
		gbc_j.insets = new Insets(0, 0, 12, 0);
		gbc_j.anchor = GridBagConstraints.CENTER;
		gbc_j.gridx = 1;
		gbc_j.gridy = 0;
		add(j, gbc_j);

		JLabel lblMin = new JLabel("Min");
		GridBagConstraints gbc_lblMin = new GridBagConstraints();
		gbc_lblMin.anchor = GridBagConstraints.CENTER;
		gbc_lblMin.insets = new Insets(0, 0, 12, 0);
		gbc_lblMin.weightx = 0.5;
		gbc_lblMin.gridx = 1;
		gbc_lblMin.gridy = 1;
		add(lblMin, gbc_lblMin);

		JLabel lblMax = new JLabel("Max");
		GridBagConstraints gbc_lblMax = new GridBagConstraints();
		gbc_lblMax.anchor = GridBagConstraints.CENTER;
		gbc_lblMax.insets = new Insets(0, 0, 12, 0);
		gbc_lblMax.weightx = 0.5;
		gbc_lblMax.gridx = 2;
		gbc_lblMax.gridy = 1;
		add(lblMax, gbc_lblMax);

		JLabel lblisbn = new JLabel("ISBN:");
		GridBagConstraints gbc_lblisbn = new GridBagConstraints();
		gbc_lblisbn.anchor = GridBagConstraints.EAST;
		gbc_lblisbn.insets = new Insets(0, 0, 12, 0);
		gbc_lblisbn.weightx = 0.5;

		gbc_lblisbn.gridx = 0;
		gbc_lblisbn.gridy = 2;
		add(lblisbn, gbc_lblisbn);

		
		txtisbn = new JTextField();
		GridBagConstraints gbc_txtisbn = new GridBagConstraints();
		gbc_txtisbn.insets = new Insets(0, 0, 12, 0);
		gbc_txtisbn.fill = GridBagConstraints.BOTH;
		gbc_txtisbn.weightx = 0.5;
		gbc_txtisbn.gridx = 1;
		gbc_txtisbn.gridy = 2;
		add(txtisbn, gbc_txtisbn);
		txtisbn.setColumns(10);
		
		/*
		txtisbn = new JTextField();
		GridBagConstraints gbc_txtMaxisbn = new GridBagConstraints();
		gbc_txtMaxisbn.insets = new Insets(0, 0, 12, 0);
		gbc_txtMaxisbn.fill = GridBagConstraints.BOTH;
		gbc_txtMaxisbn.weightx = 0.5;
		gbc_txtMaxisbn.gridx = 2;
		gbc_txtMaxisbn.gridy = 2;
		add(txtisbn, gbc_txtMaxisbn);
		txtisbn.setColumns(10);
		 */
		
		JLabel lblPrice = new JLabel("Price:");
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.anchor = GridBagConstraints.EAST;
		gbc_lblPrice.insets = new Insets(0, 0, 12, 0);
		gbc_lblPrice.gridx = 0;
		gbc_lblPrice.gridy = 3;
		add(lblPrice, gbc_lblPrice);

		txtMinprice = new JTextField();
		GridBagConstraints gbc_txtMinprice = new GridBagConstraints();
		gbc_txtMinprice.insets = new Insets(0, 0, 12, 0);
		gbc_txtMinprice.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMinprice.gridx = 1;
		gbc_txtMinprice.gridy = 3;
		add(txtMinprice, gbc_txtMinprice);
		txtMinprice.setColumns(10);

		txtMaxprice = new JTextField();
		GridBagConstraints gbc_txtMaxprice = new GridBagConstraints();
		gbc_txtMaxprice.insets = new Insets(0, 0, 12, 0);
		gbc_txtMaxprice.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMaxprice.gridx = 2;
		gbc_txtMaxprice.gridy = 3;
		add(txtMaxprice, gbc_txtMaxprice);
		txtMaxprice.setColumns(10);

		JLabel lblYear = new JLabel("Year:");
		GridBagConstraints gbc_lblYear = new GridBagConstraints();
		gbc_lblYear.anchor = GridBagConstraints.EAST;
		gbc_lblYear.insets = new Insets(0, 0, 12, 0);
		gbc_lblYear.gridx = 0;
		gbc_lblYear.gridy = 4;
		add(lblYear, gbc_lblYear);

		txtMinyear = new JTextField();
		GridBagConstraints gbc_txtMinyear = new GridBagConstraints();
		gbc_txtMinyear.insets = new Insets(0, 0, 12, 0);
		gbc_txtMinyear.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMinyear.gridx = 1;
		gbc_txtMinyear.gridy = 4;
		add(txtMinyear, gbc_txtMinyear);
		txtMinyear.setColumns(10);

		txtMaxyear = new JTextField();
		GridBagConstraints gbc_txtMaxyear = new GridBagConstraints();
		gbc_txtMaxyear.insets = new Insets(0, 0, 12, 0);
		gbc_txtMaxyear.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMaxyear.gridx = 2;
		gbc_txtMaxyear.gridy = 4;
		add(txtMaxyear, gbc_txtMaxyear);
		txtMaxyear.setColumns(10);

		JLabel lblBookName = new JLabel("Book Name:");
		GridBagConstraints gbc_lblBookName = new GridBagConstraints();
		gbc_lblBookName.anchor = GridBagConstraints.EAST;
		gbc_lblBookName.insets = new Insets(0, 0, 12, 0);
		gbc_lblBookName.gridx = 0;
		gbc_lblBookName.gridy = 5;
		add(lblBookName, gbc_lblBookName);

		txtTitle = new JTextField();
		GridBagConstraints gbc_txtTitle = new GridBagConstraints();
		gbc_txtTitle.insets = new Insets(0, 0, 12, 0);
		gbc_txtTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTitle.gridx = 1;
		gbc_txtTitle.gridy = 5;
		add(txtTitle, gbc_txtTitle);
		txtTitle.setColumns(10);

		chckbxNameexact = new JCheckBox("Exact Match Only");

		chckbxNameexact.setBackground(Color.WHITE);
		GridBagConstraints gbc_lblchckbxNameexact = new GridBagConstraints();
		gbc_lblchckbxNameexact.anchor = GridBagConstraints.CENTER;
		gbc_lblchckbxNameexact.insets = new Insets(0, 0, 12, 0);
		gbc_lblchckbxNameexact.gridx = 2;
		gbc_lblchckbxNameexact.gridy = 5;
		add(chckbxNameexact, gbc_lblchckbxNameexact);

		JLabel lblauthor = new JLabel("Author Name:");
		GridBagConstraints gbc_lblauthor = new GridBagConstraints();
		gbc_lblauthor.anchor = GridBagConstraints.EAST;
		gbc_lblauthor.insets = new Insets(0, 0, 12, 0);
		gbc_lblauthor.gridx = 0;
		gbc_lblauthor.gridy = 6;
		add(lblauthor, gbc_lblauthor);

		txtauthor = new JTextField();
		GridBagConstraints gbc_txtauthor = new GridBagConstraints();
		gbc_txtauthor.insets = new Insets(0, 0, 12, 0);
		gbc_txtauthor.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtauthor.gridx = 1;
		gbc_txtauthor.gridy = 6;
		add(txtauthor, gbc_txtauthor);
		txtauthor.setColumns(10);

		chckbxauthorexact = new JCheckBox("Exact Match Only");

		chckbxauthorexact.setBackground(Color.WHITE);
		GridBagConstraints gbc_lblchckbxauthorexact = new GridBagConstraints();
		gbc_lblchckbxauthorexact.anchor = GridBagConstraints.CENTER;
		gbc_lblchckbxauthorexact.insets = new Insets(0, 0, 12, 0);
		gbc_lblchckbxauthorexact.gridx = 2;
		gbc_lblchckbxauthorexact.gridy = 6;
		add(chckbxauthorexact, gbc_lblchckbxauthorexact);
		
		JLabel lblpublisher = new JLabel("Publisher Name:");
		GridBagConstraints gbc_lblpublisher = new GridBagConstraints();
		gbc_lblpublisher.anchor = GridBagConstraints.EAST;
		gbc_lblpublisher.insets = new Insets(0, 0, 12, 0);
		gbc_lblpublisher.gridx = 0;
		gbc_lblpublisher.gridy = 7;
		add(lblpublisher, gbc_lblpublisher);

		txtpublisher = new JTextField();
		GridBagConstraints gbc_txtpublisher = new GridBagConstraints();
		gbc_txtpublisher.insets = new Insets(0, 0, 12, 0);
		gbc_txtpublisher.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtpublisher.gridx = 1;
		gbc_txtpublisher.gridy = 7;
		add(txtpublisher, gbc_txtpublisher);
		txtpublisher.setColumns(10);

		chckbxpublisherexact = new JCheckBox("Exact Match Only");

		chckbxpublisherexact.setBackground(Color.WHITE);
		GridBagConstraints gbc_lblchckbxpublisherexact = new GridBagConstraints();
		gbc_lblchckbxpublisherexact.anchor = GridBagConstraints.CENTER;
		gbc_lblchckbxpublisherexact.insets = new Insets(0, 0, 12, 0);
		gbc_lblchckbxpublisherexact.gridx = 2;
		gbc_lblchckbxpublisherexact.gridy = 7;
		add(chckbxpublisherexact, gbc_lblchckbxpublisherexact);
		
		JLabel lblGenre = new JLabel("Genre:");
		GridBagConstraints gbc_lblGenre = new GridBagConstraints();
		gbc_lblGenre.anchor = GridBagConstraints.EAST;
		gbc_lblGenre.insets = new Insets(0, 0, 12, 0);
		gbc_lblGenre.gridx = 0;
		gbc_lblGenre.gridy = 8;
		add(lblGenre, gbc_lblGenre);

		txtGenre = new JTextField();
		GridBagConstraints gbc_txtGenre = new GridBagConstraints();
		gbc_txtGenre.insets = new Insets(0, 0, 12, 0);
		gbc_txtGenre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtGenre.gridx = 1;
		gbc_txtGenre.gridy = 8;
		add(txtGenre, gbc_txtGenre);
		txtGenre.setColumns(10);

		chckbxGenrenameexact = new JCheckBox("Exact Match Only");

		chckbxGenrenameexact.setBackground(Color.WHITE);
		GridBagConstraints gbc_lblchckbxGenrenameexact = new GridBagConstraints();
		gbc_lblchckbxGenrenameexact.anchor = GridBagConstraints.CENTER;
		gbc_lblchckbxGenrenameexact.insets = new Insets(0, 0, 12, 0);
		gbc_lblchckbxGenrenameexact.gridx = 2;
		gbc_lblchckbxGenrenameexact.gridy = 8;
		add(chckbxGenrenameexact, gbc_lblchckbxGenrenameexact);
		
		JLabel lblseller = new JLabel("Seller Name:");
		GridBagConstraints gbc_lblseller = new GridBagConstraints();
		gbc_lblseller.anchor = GridBagConstraints.EAST;
		gbc_lblseller.insets = new Insets(0, 0, 12, 0);
		gbc_lblseller.gridx = 0;
		gbc_lblseller.gridy = 9;
		add(lblseller, gbc_lblseller);

		txtseller = new JTextField();
		GridBagConstraints gbc_txtseller = new GridBagConstraints();
		gbc_txtseller.insets = new Insets(0, 0, 12, 0);
		gbc_txtseller.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtseller.gridx = 1;
		gbc_txtseller.gridy = 9;
		add(txtseller, gbc_txtseller);
		txtseller.setColumns(10);

		chckbxsellerexact = new JCheckBox("Exact Match Only");

		chckbxsellerexact.setBackground(Color.WHITE);
		GridBagConstraints gbc_lblchckbxsellerexact = new GridBagConstraints();
		gbc_lblchckbxsellerexact.anchor = GridBagConstraints.CENTER;
		gbc_lblchckbxsellerexact.insets = new Insets(0, 0, 12, 0);
		gbc_lblchckbxsellerexact.gridx = 2;
		gbc_lblchckbxsellerexact.gridy = 9;
		add(chckbxsellerexact, gbc_lblchckbxsellerexact);
		
		//chckbxforWeb = new JCheckBox("Output For Web");
		/*
		chckbxforWeb.setBackground(Color.WHITE);
		GridBagConstraints gbc_lblchckbxforweb = new GridBagConstraints();
		gbc_lblchckbxforweb.anchor = GridBagConstraints.WEST;
		gbc_lblchckbxforweb.insets = new Insets(0, 0, 12, 0);
		gbc_lblchckbxforweb.gridx = 1;
		gbc_lblchckbxforweb.gridy = 10;
		*/
		BookButton homeButton;
		homeButton = new BookButton("Home");
		//homeButton.setBook(b);
		homeButton.addActionListener(homeHandler);
		GridBagConstraints cc = new GridBagConstraints();
		cc.gridx = 0;
		cc.gridy = 10;
		cc.weightx = 0;
		cc.anchor = GridBagConstraints.CENTER;
		cc.weighty = 0.1;
		this.add(homeButton, cc);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * This event handler detects the button click, and currently
				 * uses the controller callback method to show all Books.
				 * 
				 * Fetched the values from the search fields and store in this
				 * Book constraint. Then pass it to the right call back in
				 * the controller
				 */

				try {
					boolean isBookSearchDetailsClean = true;

					BookConstraint sc = new BookConstraint();
					sc.genre = txtGenre.getText();
					if (chckbxGenrenameexact.isSelected())
						if (!txtGenre.getText().contains(""))
							sc.genreExact = true;

					if (!txtMaxprice.getText().isEmpty()) {
						sc.maxPrice = Double.parseDouble(txtMaxprice.getText());
						if (sc.maxPrice < 0) {
							isBookSearchDetailsClean = false;
							JOptionPane
									.showMessageDialog(
											BookSearchPanel.this.masterController.mainFrame,
											"PRICE MUST BE GREATER THAN 0.",
											"BookSearchPanell",
											JOptionPane.ERROR_MESSAGE);
						}
					}

					if (!txtisbn.getText().isEmpty()) {
						sc.isbn = txtisbn.getText();
					}

					if (!txtMaxyear.getText().isEmpty()) {
						sc.maxYear = Integer.parseInt(txtMaxyear.getText());
						if (sc.maxYear < 999 || sc.maxYear > 9999) {
							isBookSearchDetailsClean = false;
							JOptionPane
									.showMessageDialog(
											BookSearchPanel.this.masterController.mainFrame,
											"YEAR MUST BE AN 4 DIGIT INTEGER.",
											"BookSearchPanel",
											JOptionPane.ERROR_MESSAGE);
						}
					}

					if (!txtMinprice.getText().isEmpty()) {
						sc.minPrice = Double.parseDouble(txtMinprice.getText());
						if (sc.minPrice < 0 ) {
							isBookSearchDetailsClean = false;
							JOptionPane
									.showMessageDialog(
											BookSearchPanel.this.masterController.mainFrame,
											"PRICE MUST BE GREATER THAN 0.",
											"BookSearchPanel",
											JOptionPane.ERROR_MESSAGE);
						}
					}
					/*
					if (!txtisbn.getText().isEmpty()) {
						sc.isbnNo = Integer.parseInt(txtisbn.getText());
						if (sc.isbnNo > 999999999 || sc.isbnNo < 100000000) {
							isBookSearchDetailsClean = false;
							JOptionPane
									.showMessageDialog(
											BookSearchPanel.this.masterController.mainFrame,
											"SSN A 9 DIGIT INTEGER THAT DOES NOT START WITH 0.",
											"BookSearchPanel",
											JOptionPane.ERROR_MESSAGE);
						}
					}*/

					if (!txtMinyear.getText().isEmpty()) {
						sc.minYear = Integer.parseInt(txtMinyear.getText());
						if (sc.minYear < 999 || sc.minYear > 9999) {
							isBookSearchDetailsClean = false;
							JOptionPane
									.showMessageDialog(
											BookSearchPanel.this.masterController.mainFrame,
											"YEAR MUST BE AN 4 DIGIT INTEGER.",
											"BookSearchPanel",
											JOptionPane.ERROR_MESSAGE);
						}
					}

					sc.bookTitle = txtTitle.getText();
					if (chckbxNameexact.isSelected())
						sc.btitleExact = true;
					sc.author = txtauthor.getText();
					if (chckbxauthorexact.isSelected())
						sc.btitleExact = true;
					sc.publisher = txtpublisher.getText();
					if (chckbxpublisherexact.isSelected())
						sc.btitleExact = true;
					sc.seller = txtseller.getText();
					if (chckbxsellerexact.isSelected())
						sc.btitleExact = true;
/*
					if (chckbxforWeb.isSelected())
					{
						if(isBookSearchDetailsClean)
						{
						String s =	(sc.isbnNo) + "<BR>"+ Integer.toString(sc.maxISBNNo) + "<BR>"+ sc.minPrice + "<BR>"+ sc.maxPrice + "<BR>"+ sc.minYear + "<BR>"+ sc.maxYear + "<BR>"+ sc.bookTitle + "<BR>"+ sc.author + "<BR>"+ sc.publisher + "<BR>"+ sc.genre + "<BR>";
						}
					;}*/
					if (isBookSearchDetailsClean)
						BookSearchPanel.this.masterController
								.searchAndDisplay(sc);
				} catch (NumberFormatException e1) {
				
					JOptionPane
							.showMessageDialog(
									BookSearchPanel.this.masterController.mainFrame,
									"YEAR MUST BE A 4 DIGIT INTEGER,\nAND PRICE GREATER THAN 0",
									"BookSearchPanel",
									JOptionPane.ERROR_MESSAGE);
				}// end catch
			}
		});

		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.anchor = GridBagConstraints.CENTER;
		gbc_btnSearch.insets = new Insets(0, 0, 12, 0);
		gbc_btnSearch.gridx = 1;
		gbc_btnSearch.gridy = 10;
		add(btnSearch, gbc_btnSearch);

		/**
		 * add an action listener to btnClear that wipes all the fields on the
		 * form. Since we aren't interacting with any underlying data and don't
		 * need to load any other views, this listener doesn't need to call
		 * anything in the controller.
		 */
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//txtisbn.setText("");
				txtisbn.setText("");
				txtMinprice.setText("");
				txtMaxprice.setText("");
				txtMinyear.setText("");
				txtMaxyear.setText("");
				txtTitle.setText("");
				txtauthor.setText("");
				txtpublisher.setText("");
				txtGenre.setText("");
				txtseller.setText("");
				//txtCond.setText("");

				if (chckbxGenrenameexact.isSelected()) {
					chckbxGenrenameexact.setSelected(false);
				}
				if (chckbxNameexact.isSelected()) {
					chckbxNameexact.setSelected(false);
				}
				if (chckbxauthorexact.isSelected()) {
					chckbxauthorexact.setSelected(false);
				}
				if (chckbxpublisherexact.isSelected()) {
					chckbxpublisherexact.setSelected(false);
				}
				if (chckbxsellerexact.isSelected()) {
					chckbxsellerexact.setSelected(false);
				}
				/*
				if (chckbxforWeb.isSelected()) {
					chckbxforWeb.setSelected(false);
				}*/
				
				
				// TEST CODE FOR USERS
				//User u = new User("test", "TestSeller", 1);
				//BookSearchPanel.this.masterController.register(u);
				
				//User u = new User("test", "newpass3", 2);
				//BookSearchPanel.this.masterController.login(u);
				
				//User c = new User("TestSeller", "newpass2", 3);
				//User n = new User("TestSeller", "newpass3", 3);
				//BookSearchPanel.this.masterController.changePassword(c,n);

			}// end actionPerformed
		});// end addActionListener
		GridBagConstraints gbc_btnClear = new GridBagConstraints();
		gbc_btnClear.anchor = GridBagConstraints.CENTER;
		gbc_btnClear.insets = new Insets(0, 0, 12, 0);
		gbc_btnClear.gridx = 2;
		gbc_btnClear.gridy = 10;
		add(btnClear, gbc_btnClear);
		

	}

}
