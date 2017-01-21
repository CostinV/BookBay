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
public class CreditMoney extends JPanel {

	private static final long serialVersionUID = -1919259732216456062L;

	private Controller masterController;

	private JTextField txtamt;
	private JTextField txtpayment;
	private JButton btnClear;
	/**
	 * Create an ActionListener to add to "home" buttons. when user clicks this button 
	 * the controlller links panel to homepage
	 */
	ActionListener homeHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			CreditMoney.this.masterController
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
	public CreditMoney(Controller controller) {
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

		JLabel j = new JLabel("Add Credits");
		j.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_j = new GridBagConstraints();
		gbc_j.insets = new Insets(0, 0, 12, 0);
		gbc_j.anchor = GridBagConstraints.CENTER;
		gbc_j.gridx = 1;
		gbc_j.gridy = 0;
		add(j, gbc_j);

		
		JLabel lbllname = new JLabel("Credit Amount: ");
		GridBagConstraints gbc_lbllname = new GridBagConstraints();
		gbc_lbllname.anchor = GridBagConstraints.EAST;
		gbc_lbllname.insets = new Insets(0, 0, 12, 0);
		gbc_lbllname.weightx = 0.5;

		gbc_lbllname.gridx = 0;
		gbc_lbllname.gridy = 2;
		add(lbllname, gbc_lbllname);

		
		txtamt = new JTextField();
		GridBagConstraints gbc_txtamt = new GridBagConstraints();
		gbc_txtamt.insets = new Insets(0, 0, 12, 0);
		gbc_txtamt.fill = GridBagConstraints.BOTH;
		gbc_txtamt.weightx = 0.5;
		gbc_txtamt.gridx = 1;
		gbc_txtamt.gridy = 2;
		add(txtamt, gbc_txtamt);
		txtamt.setColumns(10);
		
				
		JLabel lblpassword = new JLabel("Credit Card: ");
		GridBagConstraints gbc_lblpassword = new GridBagConstraints();
		gbc_lblpassword.anchor = GridBagConstraints.EAST;
		gbc_lblpassword.insets = new Insets(0, 0, 12, 0);
		gbc_lblpassword.weightx = 0.5;

		gbc_lblpassword.gridx = 0;
		gbc_lblpassword.gridy = 3;
		add(lblpassword, gbc_lblpassword);

		
		txtpayment = new JTextField();
		GridBagConstraints gbc_txtpayment = new GridBagConstraints();
		gbc_txtpayment.insets = new Insets(0, 0, 12, 0);
		gbc_txtpayment.fill = GridBagConstraints.BOTH;
		gbc_txtpayment.weightx = 0.5;
		gbc_txtpayment.gridx = 1;
		gbc_txtpayment.gridy = 3;
		add(txtpayment, gbc_txtpayment);
		txtpayment.setColumns(10);
		
		
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
					boolean isCreditMoneyDetailsClean = true;

					User l = new User();
					l.setUser(masterController.user.getDisplayName());
					
					if (!txtamt.getText().isEmpty()) {
						l.setCredits(Double.parseDouble(txtamt.getText()));
					}
					if (txtamt.getText().isEmpty()) {
						isCreditMoneyDetailsClean = false;
						JOptionPane
						.showMessageDialog(
								CreditMoney.this.masterController.mainFrame,
								"Please enter a valid credit amount",
								"CreditMoney",
								JOptionPane.ERROR_MESSAGE);
						}
					
					if (txtpayment.getText().isEmpty()) {
						isCreditMoneyDetailsClean = false;
						JOptionPane
						.showMessageDialog(
								CreditMoney.this.masterController.mainFrame,
								"Please enter your credit card number",
								"CreditMoney",
								JOptionPane.ERROR_MESSAGE);
						}

					if (isCreditMoneyDetailsClean)
			            masterController.addCredits(l);
			            
				} catch (RuntimeException e1) {
				
					JOptionPane
							.showMessageDialog(
									CreditMoney.this.masterController.mainFrame,
									"Please enter a valid credit amount",
									"CreditMoney",
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
				//txtisbn.setText("");
				txtamt.setText("");
				txtpayment.setText("");
						
				
				
				//User c = new User("TestSeller", "newpass2", 3);
				//User n = new User("TestSeller", "newpass3", 3);
				//BookSearchPanel.this.masterController.changePassword(c,n);

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
