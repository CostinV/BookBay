
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

/**
 * This is a View class in the MVC pattern. It is responsible for taking a
 * single student object and displaying the details nicely.
 * 
 * The layout is set up for you already. The only thing you need to do is
 * complete the setStudentDetails(Student s) method below.
 * 
 */
public class AdministratorPanel extends JPanel {

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
	JTextField bookisbn;
	JTextField sellername;
	JTextField userdisplayname;
	

	public AdministratorPanel(Controller masterController) {
		super();
		setBackground(Color.WHITE);

		this.masterController = masterController;
		
		
		
	}

	/**
	 * Method to be called by controller when the user wants to display a
	 * particular Book.
	 * 
	 * @param s
	 *            The student to be displayed.
	 */
	
	ActionListener acceptHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			String u = ((UserButton) e.getSource()).getUser();
			masterController.acceptUser(u);
		}

	};
	
	ActionListener declineHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			String u = ((UserButton) e.getSource()).getUser();
			masterController.declineUser(u);
		}

	};
	
	ActionListener unsuspendBookHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			boolean clean = true;
			Book b = new Book();
			String isbn = bookisbn.getText();
			if (isbn.equals("")) {
				clean = false;
				JOptionPane
						.showMessageDialog(
								masterController.mainFrame,
								"ISBN IS REQUIRED ",
								"BookEditPanel",
								JOptionPane.ERROR_MESSAGE);
			} else
				b.set_ISBNNo(isbn);
			String seller_name = sellername.getText();
			if (seller_name.equals("")) {
				clean = false;
				JOptionPane
						.showMessageDialog(
								masterController.mainFrame,
								"Seller Name is required ",
								"BookEditPanel",
								JOptionPane.ERROR_MESSAGE);
			} else
				b.setSellerName(seller_name);
			if (clean)
				masterController.unsuspend(b);
		}

	};
	
	ActionListener unsuspendUserHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			boolean clean = true;
			
			String display_name = userdisplayname.getText();
			if (display_name.equals("")) {
				clean = false;
				JOptionPane
						.showMessageDialog(
								masterController.mainFrame,
								"Display Name is required ",
								"BookEditPanel",
								JOptionPane.ERROR_MESSAGE);
			}
			if (clean)
				masterController.unsuspend(display_name);
		}

	};
	
	public void setGuestDisplay() {
		this.removeAll();
		this.setLayout(new GridBagLayout());
		JLabel textPane;
		textPane = new JLabel();
		textPane.setText("Only logged In administrators may view this page!");
		add(textPane);
	}
	
	public void setDisplay(List<String> newUsers) {
		this.removeAll();
		this.setLayout(new GridBagLayout());
		int i = 0;
		GridBagConstraints c;
		JLabel displayname;
		UserButton acceptButton;
		UserButton declineButton;
		
		JLabel newusers = new JLabel("New Users");
		newusers.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_newusers = new GridBagConstraints();
		//gbc_newusers.insets = new Insets(0, 0, 9, 0);
		gbc_newusers.anchor = GridBagConstraints.NORTH;
		gbc_newusers.gridwidth = 3;
		gbc_newusers.gridx = 0;
		gbc_newusers.gridy = 0;
		add(newusers, gbc_newusers);
		
		for (i = 0; i < newUsers.size(); i++) {
			// First we get the student to display
			String d = newUsers.get(i);

			// We create a new constraint for this student's details
			c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = i+1;
			c.weighty = 0.2;
			//c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;

			// We create the label, set the string to display, and make sure the
			// background color matches the panel background
			displayname = new JLabel("Display Name: "+ d + "   ");

			this.add(displayname, c);

			// We create the button for editing this student
			acceptButton = new UserButton("Accept");
			// We set the action command for this button to the student's ssn.
			// The "ActionCommand" is just a string that gets passed to
			// our ActionListener as part of the event object. This let's
			// the ActionListener pass the correct ssn to the controller
			acceptButton.setUser(d);
			acceptButton.addActionListener(acceptHandler);

			// Now we set up the constraint object that will position this
			// button
			c = new GridBagConstraints();
			c.gridx = 1;
			c.gridy = i+1;
			c.anchor = GridBagConstraints.WEST;
			//c.weightx = 0;
			c.weighty = 0.1;
			// and we add it
			this.add(acceptButton, c);

			// Now we do the same thing for the view button.
			declineButton = new UserButton("Decline");
			declineButton.setUser(d);
			declineButton.addActionListener(declineHandler);
			c = new GridBagConstraints();
			c.gridx = 2;
			c.gridy = i+1;
			//c.weightx = 0;
			c.anchor = GridBagConstraints.WEST;
			c.weighty = 0.1;
			this.add(declineButton, c);
		}
		
		i++;
		
		JLabel unsuspendbook = new JLabel("Unsuspend Book");
		unsuspendbook.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_unsuspendbook = new GridBagConstraints();
		//gbc_unsuspendbook.insets = new Insets(0, 0, 9, 0);
		gbc_unsuspendbook.anchor = GridBagConstraints.NORTH;
		gbc_unsuspendbook.gridwidth = 3;
		gbc_unsuspendbook.gridx = 0;
		gbc_unsuspendbook.gridy = i;
		add(unsuspendbook, gbc_unsuspendbook);
		
		i++;
		
		JLabel txtbookisbn = new JLabel("ISBN: ");
		c = new GridBagConstraints();
		//c.insets = new Insets(0, 0, 9, 0);
		//c.weightx = 1;
		c.weighty = 0.2;
		c.gridx = 0;
		c.gridy = i;
		c.anchor = GridBagConstraints.WEST;
		add(txtbookisbn, c);
		
		bookisbn = new JTextField();
		
		c = new GridBagConstraints();
		//c.insets = new Insets(0, 0, 9, 0);
		//c.weightx = 1;
		c.weighty = 0.2;
		c.gridx = 1;
		c.gridy = i;
		c.anchor = GridBagConstraints.WEST;
		c.fill=GridBagConstraints.HORIZONTAL;
		bookisbn.setColumns(10);
		add(bookisbn, c);
		
		i++;
		
		JLabel txtsellername = new JLabel("Seller Name: ");
		c = new GridBagConstraints();
		//c.insets = new Insets(0, 0, 9, 0);
		//c.weightx = 1;
		c.weighty = 0.2;
		c.gridx = 0;
		c.gridy = i;
		c.anchor = GridBagConstraints.WEST;
		add(txtsellername, c);
		
		sellername = new JTextField();
		
		c = new GridBagConstraints();
		//c.insets = new Insets(0, 0, 9, 0);
		//c.weightx = 1;
		c.weighty = 0.2;
		c.gridx = 1;
		c.gridy = i;
		c.anchor = GridBagConstraints.WEST;
		c.fill=GridBagConstraints.HORIZONTAL;
		sellername.setColumns(10);
		add(sellername, c);
		
		JButton unsusBook;
		unsusBook = new JButton("Unsuspend");
		unsusBook.addActionListener(unsuspendBookHandler);
		c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = i;
		//c.weightx = 0;
		c.anchor = GridBagConstraints.WEST;
		c.weighty = 0.1;
		this.add(unsusBook, c);
		
		i++;
		
		JLabel unsuspenduser = new JLabel("Unsuspend User");
		unsuspenduser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_unsuspenduser = new GridBagConstraints();
		//gbc_unsuspenduser.insets = new Insets(0, 0, 9, 0);
		gbc_unsuspenduser.anchor = GridBagConstraints.NORTH;
		gbc_unsuspenduser.gridwidth = 3;
		gbc_unsuspenduser.gridx = 0;
		gbc_unsuspenduser.gridy = i;
		add(unsuspenduser, gbc_unsuspenduser);
		
		i++;
		
		JLabel txtuserdisplayname = new JLabel("Display Name: ");
		c = new GridBagConstraints();
		//c.insets = new Insets(0, 0, 9, 0);
		//c.weightx = 1;
		c.weighty = 0.2;
		c.gridx = 0;
		c.gridy = i;
		c.anchor = GridBagConstraints.WEST;
		add(txtuserdisplayname, c);
		
		userdisplayname = new JTextField();
		
		c = new GridBagConstraints();
		//c.insets = new Insets(0, 0, 9, 0);
		//c.weightx = 1;
		c.weighty = 0.2;
		c.gridx = 1;
		c.gridy = i;
		c.anchor = GridBagConstraints.WEST;
		c.fill=GridBagConstraints.HORIZONTAL;
		userdisplayname.setColumns(10);
		add(userdisplayname, c);
		
		JButton unsusUser;
		unsusUser = new JButton("Unsuspend");
		unsusUser.addActionListener(unsuspendUserHandler);
		c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = i;
		//c.weightx = 0;
		c.anchor = GridBagConstraints.WEST;
		c.weighty = 0.1;
		this.add(unsusUser, c);
	}

}
