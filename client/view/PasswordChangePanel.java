import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Arrays;

/**
 * This is a View in our MVC pattern, it's shows a form to let the user enter
 * search constraints and 2 buttons, 1 for clearing the form, and 1 for
 * submitting the form.
 */
public class PasswordChangePanel extends JPanel {

	private static final long serialVersionUID = -1919259732216456062L;

	private Controller masterController;

	private JTextField txtpassword;
	private JTextField txtpassword2;
	public JButton btnClear;
	/**
	 * Create an ActionListener to add to "home" buttons. when user clicks this button 
	 * the controlller links panel to homepage
	 */
	ActionListener homeHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			PasswordChangePanel.this.masterController
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
	public PasswordChangePanel(Controller controller) {
		/*
		 * 
		 * all subcomponents are just added to this panel with layout
		 * constraints. Fixed it so the form looks good, tried to match the
		 * screen shot provided.
		 */
		super();
		setBackground(Color.gray);
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

		JLabel j = new JLabel("Change Password");
		j.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_j = new GridBagConstraints();
		gbc_j.insets = new Insets(0, 0, 12, 0);
		gbc_j.anchor = GridBagConstraints.CENTER;
		gbc_j.gridx = 1;
		gbc_j.gridy = 0;
		add(j, gbc_j);

				
		JLabel lblpassword = new JLabel("Old Password:");
		GridBagConstraints gbc_lblpassword = new GridBagConstraints();
		gbc_lblpassword.anchor = GridBagConstraints.EAST;
		gbc_lblpassword.insets = new Insets(0, 0, 12, 0);
		gbc_lblpassword.weightx = 0.5;

		gbc_lblpassword.gridx = 0;
		gbc_lblpassword.gridy = 1;
		add(lblpassword, gbc_lblpassword);

		
		final JPasswordField txtpassword = new JPasswordField(10);
		GridBagConstraints gbc_txtpassword = new GridBagConstraints();
		gbc_txtpassword.insets = new Insets(0, 0, 12, 0);
		gbc_txtpassword.fill = GridBagConstraints.BOTH;
		gbc_txtpassword.weightx = 0.5;
		gbc_txtpassword.gridx = 1;
		gbc_txtpassword.gridy = 1;
		//txtpassword.setVisible(false);
		add(txtpassword, gbc_txtpassword);
		
		JLabel lblpassword2 = new JLabel("New Password:");
		GridBagConstraints gbc_lblpassword2 = new GridBagConstraints();
		gbc_lblpassword2.anchor = GridBagConstraints.EAST;
		gbc_lblpassword2.insets = new Insets(0, 0, 12, 0);
		gbc_lblpassword2.weightx = 0.5;

		gbc_lblpassword2.gridx = 0;
		gbc_lblpassword2.gridy = 2;
		add(lblpassword2, gbc_lblpassword2);

		
		final JPasswordField txtpassword2 = new JPasswordField(10);
		GridBagConstraints gbc_txtpassword2 = new GridBagConstraints();
		gbc_txtpassword2.insets = new Insets(0, 0, 12, 0);
		gbc_txtpassword2.fill = GridBagConstraints.BOTH;
		gbc_txtpassword2.weightx = 0.5;
		gbc_txtpassword2.gridx = 1;
		gbc_txtpassword2.gridy = 2;
		//txtpassword.setVisible(false);
		add(txtpassword2, gbc_txtpassword2);
		BookButton homeButton;
		homeButton = new BookButton("Cancel");
		//homeButton.setBook(b);
		homeButton.addActionListener(homeHandler);
		GridBagConstraints cc = new GridBagConstraints();
		cc.gridx = 0;
		cc.gridy = 6;
		cc.insets = new Insets(0, 0, 12, 0);
		cc.anchor = GridBagConstraints.EAST;
		this.add(homeButton, cc);
		
		BookButton btnSubmit = new BookButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
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
					boolean isPasswordChangePanelDetailsClean = true;

					User c = new User();
					User n = new User();
						
					c.setUser(masterController.user.getDisplayName());
					JTextField t = new JTextField(String.valueOf(txtpassword.getPassword()));
				
						if (t.getText().isEmpty()) {
								isPasswordChangePanelDetailsClean = false;
								JOptionPane
								.showMessageDialog(
										PasswordChangePanel.this.masterController.mainFrame,
										"Please enter a password in both fields",
										"PasswordChangePanel",
										JOptionPane.ERROR_MESSAGE);
								} else {
									c.setPassword(t.getText());
									Arrays.fill(txtpassword.getPassword(), '0'); 
						            txtpassword.selectAll();
								}
						
						JTextField t2 = new JTextField(String.valueOf(txtpassword2.getPassword()));
						if (t2.getText().isEmpty()) {
							isPasswordChangePanelDetailsClean = false;
							JOptionPane
							.showMessageDialog(
									PasswordChangePanel.this.masterController.mainFrame,
									"Please enter a password in both fields",
									"PasswordChangePanel",
									JOptionPane.ERROR_MESSAGE);
						} else {
							//n.setChangedPass(true);
						n.setPassword( t2.getText());
					Arrays.fill(txtpassword2.getPassword(), '0'); 
		            txtpassword2.selectAll();
				}
					if (isPasswordChangePanelDetailsClean)
						PasswordChangePanel.this.masterController.changePassword(c, n);
					
			          
			            //Zero out the possible password, for security.
			            
			            
				} catch (RuntimeException e1) {
				
					JOptionPane
							.showMessageDialog(
									PasswordChangePanel.this.masterController.mainFrame,
									"PLEASE ENTER VALID DATA FOR THE EMPTY FIELDS",
									"PasswordChangePanel",
									JOptionPane.ERROR_MESSAGE);
				}// end catch
			}
		});

		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.anchor = GridBagConstraints.CENTER;
		gbc_btnSearch.insets = new Insets(0, 0, 12, 0);
		gbc_btnSearch.gridx = 1;
		gbc_btnSearch.gridy = 6;
		add(btnSubmit, gbc_btnSearch);

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
				txtpassword.setText("");
				txtpassword2.setText("");
						
				
				
				//User c = new User("TestSeller", "newpass2", 3);
				//User n = new User("TestSeller", "newpass3", 3);
				//BookSearchPanel.this.masterController.changePassword(c,n);

			}// end actionPerformed
		});// end addActionListener
		GridBagConstraints gbc_btnClear = new GridBagConstraints();
		gbc_btnClear.anchor = GridBagConstraints.WEST;
		gbc_btnClear.insets = new Insets(0, 0, 12, 0);
		gbc_btnClear.gridx = 2;
		gbc_btnClear.gridy = 6;
		add(btnClear, gbc_btnClear);

	}

	
	
}
