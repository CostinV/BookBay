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
public class Register extends JPanel {

	private static final long serialVersionUID = -1919259732216456062L;

	private Controller masterController;

	private JTextField txtlname;
	private JTextField txtdname;
	private JTextField txtpassword;
	private JTextField txtpassword2;
	private JButton btnSubmit;
	public JButton btnClear;
	/**
	 * Create an ActionListener to add to "home" buttons. when user clicks this button 
	 * the controlller links panel to homepage
	 */
	ActionListener homeHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			Register.this.masterController
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
	public Register(Controller controller) {
		/*
		 * 
		 * all subcomponents are just added to this panel with layout
		 * constraints. Fixed it so the form looks good, tried to match the
		 * screen shot provided.
		 */
		super();
		setBackground(Color.LIGHT_GRAY);
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

		JLabel j = new JLabel("Register");
		j.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_j = new GridBagConstraints();
		gbc_j.insets = new Insets(0, 0, 12, 0);
		gbc_j.anchor = GridBagConstraints.CENTER;
		gbc_j.gridx = 1;
		gbc_j.gridy = 0;
		add(j, gbc_j);

		
		JLabel lbllname = new JLabel("Login Name:");
		GridBagConstraints gbc_lbllname = new GridBagConstraints();
		gbc_lbllname.anchor = GridBagConstraints.EAST;
		gbc_lbllname.insets = new Insets(0, 0, 12, 0);
		gbc_lbllname.weightx = 0.5;

		gbc_lbllname.gridx = 0;
		gbc_lbllname.gridy = 2;
		add(lbllname, gbc_lbllname);

		
		txtlname = new JTextField();
		GridBagConstraints gbc_txtlname = new GridBagConstraints();
		gbc_txtlname.insets = new Insets(0, 0, 12, 0);
		gbc_txtlname.fill = GridBagConstraints.BOTH;
		gbc_txtlname.weightx = 0.5;
		gbc_txtlname.gridx = 1;
		gbc_txtlname.gridy = 2;
		add(txtlname, gbc_txtlname);
		txtlname.setColumns(10);
		
		JLabel lbldname = new JLabel("Display Name:");
		GridBagConstraints gbc_lbldname = new GridBagConstraints();
		gbc_lbldname.anchor = GridBagConstraints.EAST;
		gbc_lbldname.insets = new Insets(0, 0, 12, 0);
		gbc_lbldname.weightx = 0.5;

		gbc_lbldname.gridx = 0;
		gbc_lbldname.gridy = 3;
		add(lbldname, gbc_lbldname);

		
		txtdname = new JTextField();
		GridBagConstraints gbc_txtdname = new GridBagConstraints();
		gbc_txtdname.insets = new Insets(0, 0, 12, 0);
		gbc_txtdname.fill = GridBagConstraints.BOTH;
		gbc_txtdname.weightx = 0.5;
		gbc_txtdname.gridx = 1;
		gbc_txtdname.gridy = 3;
		add(txtdname, gbc_txtdname);
		txtlname.setColumns(10);
				
		/*JLabel lblpassword = new JLabel("Password:");
		GridBagConstraints gbc_lblpassword = new GridBagConstraints();
		gbc_lblpassword.anchor = GridBagConstraints.WEST;
		gbc_lblpassword.insets = new Insets(0, 0, 12, 0);
		gbc_lblpassword.weightx = 0.5;

		gbc_lblpassword.gridx = 0;
		gbc_lblpassword.gridy = 4;
		add(lblpassword, gbc_lblpassword);
*/
/*		
		txtpassword = new JTextField();
		GridBagConstraints gbc_txtpassword = new GridBagConstraints();
		gbc_txtpassword.insets = new Insets(0, 0, 12, 0);
		gbc_txtpassword.fill = GridBagConstraints.BOTH;
		gbc_txtpassword.weightx = 0.5;
		gbc_txtpassword.gridx = 1;
		gbc_txtpassword.gridy = 2;
		add(txtpassword, gbc_txtpassword);
		txtpassword.setColumns(10);
		
		JLabel lblpassword2 = new JLabel("Password Again:");
		GridBagConstraints gbc_lblpassword2 = new GridBagConstraints();
		gbc_lblpassword2.anchor = GridBagConstraints.WEST;
		gbc_lblpassword2.insets = new Insets(0, 0, 12, 0);
		gbc_lblpassword2.weightx = 0.5;

		gbc_lblpassword2.gridx = 0;
		gbc_lblpassword2.gridy = 4;
		add(lblpassword2, gbc_lblpassword2);

		
		txtpassword2 = new JTextField();
		GridBagConstraints gbc_txtpassword2 = new GridBagConstraints();
		gbc_txtpassword2.insets = new Insets(0, 0, 12, 0);
		gbc_txtpassword2.fill = GridBagConstraints.BOTH;
		gbc_txtpassword2.weightx = 0.5;
		gbc_txtpassword2.gridx = 1;
		gbc_txtpassword2.gridy = 2;
		add(txtpassword2, gbc_txtpassword2);
		txtpassword2.setColumns(10);*/
		BookButton homeButton;
		homeButton = new BookButton("Cancel");
		//homeButton.setBook(b);
		homeButton.addActionListener(homeHandler);
		GridBagConstraints cc = new GridBagConstraints();
		cc.gridx = 0;
		cc.gridy = 4;
		cc.insets = new Insets(0, 0, 12, 0);
		cc.anchor = GridBagConstraints.EAST;
		this.add(homeButton, cc);
		JButton btnSubmit = new JButton("Submit");
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
					boolean isUserDetailsClean = true;

					User l = new User();
					
					if (!txtlname.getText().isEmpty()) 
					{
						l.setLoginName( txtlname.getText());
					}
					
					if (!txtdname.getText().isEmpty()) 
					{
						l.setUser( txtdname.getText());
					}
					if (txtlname.getText().isEmpty()) 
					{
						isUserDetailsClean = false;
						JOptionPane
						.showMessageDialog(
								Register.this.masterController.mainFrame,
								"PLEASE PROVIDE VALID LOGIN NAME",
								"Register",
								JOptionPane.ERROR_MESSAGE);
					}
					if (txtdname.getText().isEmpty()) 
					{
						isUserDetailsClean = false;
						JOptionPane
						.showMessageDialog(
								Register.this.masterController.mainFrame,
								"PLEASE PROVIDE VALID DISPLAY NAME",
								"Register",
								JOptionPane.ERROR_MESSAGE);
					}
				
					if (isUserDetailsClean) {
						masterController.register(l);
						//System.out.println("registering");
					}
				} catch (RuntimeException e1) {
				
					JOptionPane
							.showMessageDialog(
									Register.this.masterController.mainFrame,
									"Please provide valid information",
									"Register",
									JOptionPane.ERROR_MESSAGE);
				}// end catch
			}
		});

		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.anchor = GridBagConstraints.CENTER;
		gbc_btnSearch.insets = new Insets(0, 0, 12, 0);
		gbc_btnSearch.gridx = 1;
		gbc_btnSearch.gridy = 4;
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
				txtlname.setText("");
				txtdname.setText("");
			}// end actionPerformed
		});// end addActionListener
		GridBagConstraints gbc_btnClear = new GridBagConstraints();
		gbc_btnClear.anchor = GridBagConstraints.WEST;
		gbc_btnClear.insets = new Insets(0, 0, 12, 0);
		gbc_btnClear.gridx = 2;
		gbc_btnClear.gridy = 4;
		add(btnClear, gbc_btnClear);

	}

}
