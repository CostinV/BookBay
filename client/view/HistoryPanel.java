import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

//import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
public class HistoryPanel extends JPanel {

	private static final long serialVersionUID = 5493383344375212140L;
	private Controller masterController;
	
	/**
	 * Create an ActionListener to add to "View" buttons. Each button has the
	 * corresponding book's isbn stored in it's "ActionCommand" Field We can
	 * access this in the event handler, which lets us use one instance of the
	 * event handler for ALL of the view buttons instead of creating a different
	 * instance for every button in the list
	 */

	/**
	 * Initially this view just shows a label saying "No results yet" and stores
	 * a reference to the controller. The controller can populate the list view
	 * by calling setResults(List<Student>)
	 * 
	 * @param masterController
	 *            reference to the controller
	 */
	public HistoryPanel(Controller masterController) {
		super();
		setBackground(Color.white);
		this.masterController = masterController;
		
		JLabel textPane;
		textPane = new JLabel();
		textPane.setText("No Results Yet!");
		add(textPane);
		
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
	public void setResults(List<Book> bookHistory, List<Book> bidHistory) {
		// First clear this panel of any components that are already added
		this.removeAll();
       //this.setBackground(Color.YELLOW);
       
		// Set the layout manager
		this.setLayout(new GridBagLayout());
		JLabel bookLabel;
		int i;
		GridBagConstraints c;

		JLabel bidhistory = new JLabel("Bid History");
		bidhistory.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_bidhistory = new GridBagConstraints();
		//gbc_bidhistory.insets = new Insets(0, 0, 9, 0);
		gbc_bidhistory.anchor = GridBagConstraints.NORTH;
		//gbc_bidhistory.gridwidth = 5;
		gbc_bidhistory.gridx = 0;
		gbc_bidhistory.gridy = 0;
		add(bidhistory, gbc_bidhistory);

		// Now we loop through the results and create the labels and buttons we
		// need
		for (i = 0; i < bidHistory.size(); i++) {
			// First we get the student to display
			Book b = bidHistory.get(i);

			// We create a new constraint for this student's details
			c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = i+1;
			c.weighty = 0.2;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;

			// We create the label, set the string to display, and make sure the
			// background color matches the panel background
			bookLabel = new JLabel("ISBN: "+b.get_ISBNNo() + ", Book Title: "
					+ b.getBookTitle()+ ", Seller: "+b.getSellerName() +", Bid Amount: $"+ b.getPrice());
			//bookLabel.setForeground(Color.BLUE);
					/*+ b.getPublisher() + b.getYear()
					+ b.getGenre() + b.getSellerName() + b.getPrice() + b.getBookCondition());
	*/		
			//bookLabel.setBackground(getBackground());

			// We add the label to this panel, subject to the constraint c that
			// positions it.
			this.add(bookLabel, c);
		}
		i++;
		JLabel searchhistory = new JLabel("View History");
		searchhistory.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_searchhistory = new GridBagConstraints();
		//gbc_searchhistory.insets = new Insets(0, 0, 9, 0);
		gbc_searchhistory.anchor = GridBagConstraints.NORTH;
		//gbc_searchhistory.gridwidth = 5;
		gbc_searchhistory.gridx = 0;
		gbc_searchhistory.gridy = i;
		add(searchhistory, gbc_searchhistory);
		
		int j;
		for (j = 0; j < bookHistory.size(); j++) {
			// First we get the student to display
			Book b = bookHistory.get(j);

			// We create a new constraint for this student's details
			c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = j+i+1;
			c.weighty = 0.2;
			c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;

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
		
	}
	
	public void setGuestDisplay() {
		this.removeAll();
		this.setLayout(new GridBagLayout());
		JLabel textPane;
		textPane = new JLabel();
		textPane.setText("No Results Yet!");
		add(textPane);
	}

}
