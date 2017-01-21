
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * This is a View class in the MVC pattern. It is responsible for taking a
 * single student object and displaying the details nicely.
 * 
 * The layout is set up for you already. The only thing you need to do is
 * complete the setStudentDetails(Student s) method below.
 * 
 */
public class BookDisplayPanel extends JPanel {

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

	private JLabel header;
	private JLabel lblName;
	private JLabel lblaName;
	private JLabel lblpName;
	private JLabel lblsName;
	private JLabel lblISBN;
	private JLabel lblYear;
	private JLabel lblPrice;
	private JLabel lblGenre;
	private JLabel lblCond;
	private JLabel lblimage;
	private JLabel lblquantity;
	private JLabel lblrating;
	private JLabel lblCoverPage;
	private JLabel txtcomments;
	private BookButton bidButton;
	private JLabel lblcomments;
	private JLabel lblQuantityPrompt;
	private Book book;
	
	ActionListener bidHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			Book b = ((BookButton) e.getSource()).getBook();
			
			masterController.setBookBidView(b);
		}

	};
	
	ActionListener cancelHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			masterController.listPage();
		}

	};
	
	public BookDisplayPanel(Controller masterController) {
		super();
		//setBackground(Color.cyan);
		this.masterController = masterController;
		this.removeAll();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 100, 75, 75, 0 };
		gridBagLayout.rowHeights = new int[] { 18, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,	Double.MIN_VALUE };
		setLayout(gridBagLayout);
		header = new JLabel("Book Display");
		header.setFont(new Font("Tahoma", Font.BOLD, 20));
		GridBagConstraints gbc_header = new GridBagConstraints();
		gbc_header.insets = new Insets(0, 0, 10, 0);
		//header.setForeground(Color.GREEN);
		gbc_header.anchor = GridBagConstraints.CENTER;
		gbc_header.gridx = 1;
		gbc_header.gridy = 0;
		add(header, gbc_header);

		JLabel lblNamePrompt = new JLabel("Book Title:");
		GridBagConstraints gbc_lblNamePrompt = new GridBagConstraints();
		gbc_lblNamePrompt.insets = new Insets(0, 0, 14, 10);
		gbc_lblNamePrompt.anchor = GridBagConstraints.WEST;
		lblNamePrompt.setForeground(Color.BLUE);
		gbc_lblNamePrompt.gridx = 1;
		gbc_lblNamePrompt.gridy = 1;
		add(lblNamePrompt, gbc_lblNamePrompt);

		lblName = new JLabel("");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 10, 0);
		lblName.setForeground(Color.BLUE);
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.gridx = 2;
		gbc_lblName.gridy = 1;
		add(lblName, gbc_lblName);
		
		JLabel lblisbnPrompt = new JLabel("ISBN:");
		GridBagConstraints gbc_lblisbnPrompt = new GridBagConstraints();
		gbc_lblisbnPrompt.insets = new Insets(0, 0, 10, 0);
		gbc_lblisbnPrompt.anchor = GridBagConstraints.WEST;
		lblisbnPrompt.setForeground(Color.BLUE);
		gbc_lblisbnPrompt.gridx = 1;
		gbc_lblisbnPrompt.gridy = 2;
		add(lblisbnPrompt, gbc_lblisbnPrompt);

		lblISBN = new JLabel("");
		GridBagConstraints gbc_lblisbn = new GridBagConstraints();
		gbc_lblisbn.insets = new Insets(0, 0, 10, 0);
		gbc_lblisbn.anchor = GridBagConstraints.WEST;
		lblISBN.setForeground(Color.BLUE);
		gbc_lblisbn.gridx = 2;
		gbc_lblisbn.gridy = 2;
		add(lblISBN, gbc_lblisbn);
		
		JLabel lblaNamePrompt = new JLabel("Author Name:");
		GridBagConstraints gbc_lblaNamePrompt = new GridBagConstraints();
		gbc_lblaNamePrompt.insets = new Insets(0, 0, 10, 0);
		gbc_lblaNamePrompt.anchor = GridBagConstraints.WEST;
		lblaNamePrompt.setForeground(Color.BLUE);
		gbc_lblaNamePrompt.gridx = 1;
		gbc_lblaNamePrompt.gridy = 3;
		add(lblaNamePrompt, gbc_lblaNamePrompt);

		lblaName = new JLabel("");
		GridBagConstraints gbc_lblaName = new GridBagConstraints();
		gbc_lblaName.insets = new Insets(0, 0, 10, 0);
		gbc_lblaName.anchor = GridBagConstraints.WEST;
		lblaName.setForeground(Color.BLUE);
		gbc_lblaName.gridx = 2;
		gbc_lblaName.gridy = 3;
		add(lblaName, gbc_lblaName);
		
		JLabel lblpNamePrompt = new JLabel("Publisher:");
		GridBagConstraints gbc_lblpNamePrompt = new GridBagConstraints();
		gbc_lblpNamePrompt.insets = new Insets(0, 0, 10, 0);
		gbc_lblpNamePrompt.anchor = GridBagConstraints.WEST;
		lblpNamePrompt.setForeground(Color.BLUE);
		gbc_lblpNamePrompt.gridx = 1;
		gbc_lblpNamePrompt.gridy = 4;
		add(lblpNamePrompt, gbc_lblpNamePrompt);

		lblpName = new JLabel("");
		GridBagConstraints gbc_lblpName = new GridBagConstraints();
		gbc_lblpName.insets = new Insets(0, 0, 10, 0);
		gbc_lblpName.anchor = GridBagConstraints.WEST;
		lblpName.setForeground(Color.BLUE);
		gbc_lblpName.gridx = 2;
		gbc_lblpName.gridy = 4;
		add(lblpName, gbc_lblpName);
		
		JLabel lblYearPrompt = new JLabel("Year:");
		GridBagConstraints gbc_lblYearPrompt = new GridBagConstraints();
		gbc_lblYearPrompt.insets = new Insets(0, 0, 10, 0);
		gbc_lblYearPrompt.anchor = GridBagConstraints.WEST;
		lblYearPrompt.setForeground(Color.BLUE);
		gbc_lblYearPrompt.gridx = 1;
		gbc_lblYearPrompt.gridy = 5;
		add(lblYearPrompt, gbc_lblYearPrompt);

		lblYear = new JLabel("");
		GridBagConstraints gbc_lblYear = new GridBagConstraints();
		gbc_lblYear.insets = new Insets(0, 0, 10, 0);
		gbc_lblYear.anchor = GridBagConstraints.WEST;
		lblYear.setForeground(Color.BLUE);
		gbc_lblYear.gridx = 2;
		gbc_lblYear.gridy = 5;
		add(lblYear, gbc_lblYear);

		JLabel lblGenrePrompt = new JLabel("Genre:");
		GridBagConstraints gbc_lblGenrePrompt = new GridBagConstraints();
		gbc_lblGenrePrompt.insets = new Insets(0, 0, 10, 0);
		gbc_lblGenrePrompt.anchor = GridBagConstraints.WEST;
		lblGenrePrompt.setForeground(Color.BLUE);
		gbc_lblGenrePrompt.gridx = 1;
		gbc_lblGenrePrompt.gridy = 6;
		add(lblGenrePrompt, gbc_lblGenrePrompt);

		lblGenre = new JLabel("");
		GridBagConstraints gbc_lblGenre = new GridBagConstraints();
		gbc_lblGenre.insets = new Insets(0, 0, 10, 0);
		gbc_lblGenre.anchor = GridBagConstraints.WEST;
		lblGenre.setForeground(Color.BLUE);
		gbc_lblGenre.gridx = 2;
		gbc_lblGenre.gridy = 6;
		add(lblGenre, gbc_lblGenre);
		
		JLabel lblsNamePrompt = new JLabel("Seller Name:");
		GridBagConstraints gbc_lblsNamePrompt = new GridBagConstraints();
		gbc_lblsNamePrompt.insets = new Insets(0, 0, 10, 0);
		gbc_lblsNamePrompt.anchor = GridBagConstraints.WEST;
		lblsNamePrompt.setForeground(Color.BLUE);
		gbc_lblsNamePrompt.gridx = 1;
		gbc_lblsNamePrompt.gridy = 7;
		add(lblsNamePrompt, gbc_lblsNamePrompt);

		lblsName = new JLabel("");
		GridBagConstraints gbc_lblsName = new GridBagConstraints();
		gbc_lblsName.insets = new Insets(0, 0, 10, 0);
		gbc_lblsName.anchor = GridBagConstraints.WEST;
		lblsName.setForeground(Color.BLUE);
		gbc_lblsName.gridx = 2;
		gbc_lblsName.gridy = 7;
		add(lblsName, gbc_lblsName);
		
		JLabel lblPricePrompt = new JLabel("Price:");
		GridBagConstraints gbc_lblPricePrompt = new GridBagConstraints();
		gbc_lblPricePrompt.insets = new Insets(0, 0, 10, 0);
		gbc_lblPricePrompt.anchor = GridBagConstraints.WEST;
		lblPricePrompt.setForeground(Color.BLUE);
		gbc_lblPricePrompt.gridx = 1;
		gbc_lblPricePrompt.gridy = 8;
		add(lblPricePrompt, gbc_lblPricePrompt);

		lblPrice = new JLabel("");
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.insets = new Insets(0, 0, 10, 0);
		gbc_lblPrice.anchor = GridBagConstraints.WEST;
		lblPrice.setForeground(Color.BLUE);
		gbc_lblPrice.gridx = 2;
		gbc_lblPrice.gridy = 8;
		add(lblPrice, gbc_lblPrice);

		JLabel lblCondPrompt = new JLabel("Book Condition:");
		GridBagConstraints gbc_lblCondPrompt = new GridBagConstraints();
		gbc_lblCondPrompt.insets = new Insets(0, 0, 10, 0);
		gbc_lblCondPrompt.anchor = GridBagConstraints.WEST;
		lblCondPrompt.setForeground(Color.BLUE);
		gbc_lblCondPrompt.gridx = 1;
		gbc_lblCondPrompt.gridy = 9;
		add(lblCondPrompt, gbc_lblCondPrompt);

		lblCond = new JLabel("");
		GridBagConstraints gbc_lblCond = new GridBagConstraints();
		gbc_lblCond.anchor = GridBagConstraints.WEST;
		lblCond.setForeground(Color.BLUE);
		gbc_lblCond.gridx = 2;
		gbc_lblCond.gridy = 9;
		add(lblCond, gbc_lblCond);
		
		lblQuantityPrompt = new JLabel("Quantity Available:");
		GridBagConstraints gbc_lblQuantityPrompt = new GridBagConstraints();
		gbc_lblQuantityPrompt.insets = new Insets(0, 0, 10, 0);
		gbc_lblQuantityPrompt.anchor = GridBagConstraints.WEST;
		lblQuantityPrompt.setForeground(Color.BLUE);
		gbc_lblQuantityPrompt.gridx = 1;
		gbc_lblQuantityPrompt.gridy = 10;
		add(lblQuantityPrompt, gbc_lblQuantityPrompt);
		
		lblquantity = new JLabel("");
		GridBagConstraints gbc_lblquantity = new GridBagConstraints();
		gbc_lblquantity.anchor = GridBagConstraints.WEST;
		lblquantity.setForeground(Color.BLUE);
		gbc_lblquantity.gridx = 2;
		gbc_lblquantity.gridy = 10;
		add(lblquantity, gbc_lblquantity);
		
		JLabel lblratingPrompt = new JLabel("Rating:");
		GridBagConstraints gbc_lblratingPrompt = new GridBagConstraints();
		gbc_lblratingPrompt.insets = new Insets(0, 0, 10, 0);
		gbc_lblratingPrompt.anchor = GridBagConstraints.WEST;
		lblratingPrompt.setForeground(Color.BLUE);
		gbc_lblratingPrompt.gridx = 1;
		gbc_lblratingPrompt.gridy = 11;
		add(lblratingPrompt, gbc_lblratingPrompt);
		
		lblrating = new JLabel("");
		GridBagConstraints gbc_lblrating = new GridBagConstraints();
		gbc_lblrating.anchor = GridBagConstraints.WEST;
		lblrating.setForeground(Color.BLUE);
		gbc_lblrating.gridx = 2;
		gbc_lblrating.gridy = 11;
		add(lblrating, gbc_lblrating);
		
		lblcomments = new JLabel("Comments");
		GridBagConstraints gbc_lblcomments = new GridBagConstraints();
		gbc_lblcomments.insets = new Insets(0, 0, 10, 0);
		gbc_lblcomments.anchor = GridBagConstraints.WEST;
		lblcomments.setForeground(Color.BLUE);
		gbc_lblcomments.gridx = 1;
		gbc_lblcomments.gridy = 13;
		add(lblcomments, gbc_lblcomments);
		
		txtcomments = new JLabel("");
		GridBagConstraints gbc_txtcomments = new GridBagConstraints();
		gbc_txtcomments.anchor = GridBagConstraints.WEST;
		txtcomments.setForeground(Color.BLUE);
		gbc_txtcomments.gridx = 1;
		gbc_txtcomments.gridy = 14;
		add(txtcomments, gbc_txtcomments);
		
		lblimage = new JLabel();
		GridBagConstraints gbc_lblimage = new GridBagConstraints();
		gbc_lblimage.weightx = 2;
		gbc_lblimage.weighty = 2;
		gbc_lblimage.gridx = 3;
		gbc_lblimage.gridy = 3;
		gbc_lblimage.gridheight = 9;
		add(lblimage, gbc_lblimage);
		
		bidButton = new BookButton("Bid");
		bidButton.addActionListener(bidHandler);
		// Now we set up the constraint object that will position this
		// button
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 12;
		c.insets = new Insets(0, 0, 10, 0);
		c.anchor = GridBagConstraints.CENTER;
		this.add(bidButton, c);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(cancelHandler);
		// Now we set up the constraint object that will position this
		// button
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 12;
		c.insets = new Insets(0, 0, 10, 0);
		c.anchor = GridBagConstraints.CENTER;
		this.add(cancelButton, c);
		
	}

	/**
	 * Method to be called by controller when the user wants to display a
	 * particular Book.
	 * 
	 * @param s
	 *            The student to be displayed.
	 */
	public void setBookDetails(Book s) {
		book = s;
		bidButton.setBook(book);
		this.lblName.setText(s.getBookTitle());
		this.lblISBN.setText(s.get_ISBNNo());
		this.lblaName.setText(s.getAuthorName());
		this.lblpName.setText(s.getPublisher());
		this.lblYear.setText(Integer.toString(s.getYear()));
		this.lblGenre.setText(s.getGenre());
		this.lblsName.setText(s.getSellerName());
		this.lblPrice.setText(Double.toString((s.getPrice())));
		this.lblCond.setText(s.getBookCondition());
		this.lblquantity.setText(Integer.toString(s.getQuantity()));
		this.lblrating.setText(Integer.toString(s.getRating()));
		//this.lblCoverPage.setText(s.getCoverpage());	
		if (masterController.isLoggedIn) {
			bidButton.setEnabled(true);
			bidButton.setVisible(true);
			this.lblquantity.setEnabled(true);
			this.lblquantity.setVisible(true);
			this.txtcomments.setEnabled(true);
			this.txtcomments.setVisible(true);
			this.lblcomments.setEnabled(true);
			this.lblcomments.setVisible(true);
			this.lblQuantityPrompt.setEnabled(true);
			this.lblQuantityPrompt.setVisible(true);
		} else {
			bidButton.setEnabled(false);
			bidButton.setVisible(false);
			this.lblquantity.setEnabled(false);
			this.lblquantity.setVisible(false);
			this.txtcomments.setEnabled(false);
			this.txtcomments.setVisible(false);
			this.lblcomments.setEnabled(false);
			this.lblcomments.setVisible(false);
			this.lblQuantityPrompt.setEnabled(false);
			this.lblQuantityPrompt.setVisible(false);
		}
		String c = "<html>";
			if (s.comments != null) {
			for (int i = 0; i < s.comments.size(); i++) {
				c = c + s.comments.get(i) + "<br>";
			}
			c += "</html>";
			this.txtcomments.setText(c);
		}
	}
	
	public void setBookImage(BufferedImage image) {
		if (image == null) {
			image = new BufferedImage(350,500,2);
			Graphics2D g = image.createGraphics();
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
			g.fillRect(0,0,350,500);
			g.dispose();
		}
		lblimage.setIcon(new ImageIcon(image));
		
		GridBagConstraints gbc_lblimage = new GridBagConstraints();
		//gbc_lblimage.fill = GridBagConstraints.BOTH;
		//gbc_lblimage.anchor = GridBagConstraints.EAST;
		gbc_lblimage.weightx = 2;
		gbc_lblimage.weighty = 2;
		gbc_lblimage.gridx = 3;
		gbc_lblimage.gridy = 1;
		gbc_lblimage.gridheight = 15;
		add(lblimage, gbc_lblimage);
		
	}

}
