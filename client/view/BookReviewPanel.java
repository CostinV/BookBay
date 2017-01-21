
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * This is a View class in the MVC pattern. It is responsible for taking a
 * single student object and displaying the details nicely.
 * 
 * The layout is set up for you already. The only thing you need to do is
 * complete the setStudentDetails(Student s) method below.
 * 
 */
public class BookReviewPanel extends JPanel {

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
	
	JLabel textPane;

	ActionListener reviewHandler = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// Get the ISBN from the event's "Action Command" (which is really
			// just a string)
			
			//System.out.println("pressed review button");
			
			ReviewButton b = (ReviewButton) e.getSource();
			Review r = b.getReview();
			// Tell the controller that the user wants to view the student with
			// this ISBN
			if (r.reviewed_by_seller > 0) {
				r.buyer_stars = Integer.parseInt(b.buyer_stars.getText());
				r.buyer_comments = b.buyer_comments.getText();
			} else {
				r.book_stars = Integer.parseInt(b.book_stars.getText());
				r.book_comments = b.book_comments.getText();
				r.seller_stars = Integer.parseInt(b.seller_stars.getText());
				r.seller_comments = b.seller_comments.getText();
			}
			masterController.addReview(r);
		}

	};

	

	/**
	 * Initially this view just shows a label saying "No results yet" and stores
	 * a reference to the controller. The controller can populate the list view
	 * by calling setResults(List<Student>)
	 * 
	 * @param masterController
	 *            reference to the controller
	 */
	public BookReviewPanel(Controller masterController) {
		super();
		setBackground(Color.white);
		this.masterController = masterController;
		textPane = new JLabel();
		add(textPane);
	}

	public void setGuestDisplay() {
		textPane.setText("Please log in to review your sales and purchases");
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
	public void setResults(List<Review> results1, List<Review> results2, List<Review> results3, List<Review> results4) {
		// First clear this panel of any components that are already added
		this.removeAll();
       //this.setBackground(Color.YELLOW);
       
		// Set the layout manager
		this.setLayout(new GridBagLayout());

		// reviewLabel is used to hold a reference to each JLabel as we loop
		// through to create each one
		JLabel reviewLabel;
		JLabel reviewLabel2;
		JLabel reviewLabel3;
		JLabel reviewLabel4;

		// editButton holds a reference to each new edit Button we create in the
		// loop
		//BookButton editButton;

		// viewButton holds a reference to each new view Button we create in the
		// loop
		ReviewButton reviewButton;
		int i = 0;
		int j = 0;

		// loop
		// c is used to hold references to the constraint objects we create
		GridBagConstraints c;

		/*
		 * The chunk of code below sets up an empty panel that will grow to fill
		 * all "extra" space in the StudentListDisplayPanel. This is one way to
		 * get the layout manager to push all the labels and buttons to the left
		 * side of the panel
		 */
		/*
		c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 0;
		c.weighty = 0.2;
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.EAST;
		JPanel fill = new JPanel();
		fill.setBackground(getBackground());
		add(fill, c);
		*/
		
		/*
		c = new GridBagConstraints();
		c.gridx = 4;
		c.gridy = 0;
		c.weighty = 0.2;
		c.weightx = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.EAST;
		JPanel fill = new JPanel();
		fill.setBackground(getBackground());
		add(fill, c);
		*/
		
		JLabel newsellreviews = new JLabel("New Book Sales to Review");
		newsellreviews.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_newsellreviews = new GridBagConstraints();
		//gbc_newsellreviews.insets = new Insets(0, 0, 9, 0);
		gbc_newsellreviews.anchor = GridBagConstraints.NORTH;
		gbc_newsellreviews.gridwidth = 5;
		gbc_newsellreviews.gridx = 0;
		gbc_newsellreviews.gridy = 0;
		add(newsellreviews, gbc_newsellreviews);

		if (results1.size() == 0) {
			JLabel txtempty = new JLabel("None");
			GridBagConstraints gbc_txtempty = new GridBagConstraints();
			//gbc_txtempty.insets = new Insets(0, 0, 9, 0);
			gbc_txtempty.anchor = GridBagConstraints.NORTH;
			gbc_txtempty.gridx = 0;
			gbc_txtempty.gridwidth = 5;
			i++;
			gbc_txtempty.gridy = i;
			add(txtempty, gbc_txtempty);
		}
		
		// Now we loop through the results and create the labels and buttons we need

		else {
			for (i = 0; i < results1.size(); i++) {

				Review b = results1.get(i);

				c = new GridBagConstraints();
				c.gridx = 0;
				c.gridwidth = 5;
				c.gridy = 2*i+1;
				c.weighty = 0.2;
				//c.weightx = 1;
				c.anchor = GridBagConstraints.WEST;

				
				reviewLabel = new JLabel("ISBN No: "+b.book_isbn + ", Title: " + b.book_title +
						", Book Condition: "+ b.book_condition + ", Buyer: "+ b.buyer_name);
				reviewLabel.setForeground(Color.BLUE);

				reviewLabel.setBackground(getBackground());
	
				this.add(reviewLabel, c);
				
	
				JLabel txtbuyer_stars = new JLabel("Buyer Stars: ");
				c = new GridBagConstraints();
				//c.insets = new Insets(0, 0, 9, 0);
				c.weightx = 1;
				//c.weighty = 0.2;
				c.gridx = 0;
				c.gridy = 2*i+2;
				c.anchor = GridBagConstraints.WEST;
				add(txtbuyer_stars, c);
				
				JTextField buyer_stars = new JTextField();
				
				c = new GridBagConstraints();
				//c.insets = new Insets(0, 0, 9, 0);
				c.weightx = 1;
				c.weighty = 0.2;
				c.gridx = 1;
				c.gridy = 2*i+2;
				c.anchor = GridBagConstraints.WEST;
				c.fill=GridBagConstraints.HORIZONTAL;
				buyer_stars.setColumns(4);
				//buyer_stars.setMinimumSize(buyer_stars.getPreferredSize());
				add(buyer_stars, c);
				
				
				JLabel txtbuyer_comments = new JLabel("Buyer Comments: ");
				c = new GridBagConstraints();
				//c.insets = new Insets(0, 0, 9, 0);
				c.weightx = 1;
				c.weighty = 0.2;
				c.gridx = 2;
				c.gridy = 2*i+2;
				c.anchor = GridBagConstraints.WEST;
				add(txtbuyer_comments, c);
				
				JTextField buyer_comments = new JTextField();
				c = new GridBagConstraints();
				//c.insets = new Insets(0, 0, 9, 0);
				c.weightx = 1;
				c.weighty = 0.2;
				c.gridx = 3;
				c.gridy = 2*i+2;
				c.anchor = GridBagConstraints.WEST;
				c.fill=GridBagConstraints.HORIZONTAL;
				buyer_comments.setColumns(10);
				//buyer_comments.setMinimumSize(buyer_comments.getPreferredSize());
				add(buyer_comments, c);
				
				b.seller_name = masterController.user.getDisplayName();
				b.reviewed_by_seller = 1;
				
				reviewButton = new ReviewButton("Review");
				reviewButton.setReview(b);
				reviewButton.buyer_stars = buyer_stars;
				reviewButton.buyer_comments = buyer_comments;
						
				reviewButton.addActionListener(reviewHandler);
	

				c = new GridBagConstraints();
				//c.weightx = 0;
				c.gridx = 4;
				c.gridy = 2*i+2;
				c.anchor = GridBagConstraints.WEST;
				c.weighty = 0.1;
				this.add(reviewButton, c);
				
			}
		}
			i = 2*i+1;
			JLabel txtoldsell = new JLabel("Old Book Sale Reviews");
			txtoldsell.setFont(new Font("Tahoma", Font.PLAIN, 20));
			GridBagConstraints gbc_txtoldsell = new GridBagConstraints();
			//gbc_txtoldsell.insets = new Insets(0, 0, 9, 0);
			gbc_txtoldsell.anchor = GridBagConstraints.NORTH;
			gbc_txtoldsell.gridx = 0;
			gbc_txtoldsell.gridwidth = 5;
			gbc_txtoldsell.gridy = i;
			add(txtoldsell, gbc_txtoldsell);

			if (results2.size() == 0) {
				JLabel txtoldsellempty = new JLabel("No Reviews to Display");
				GridBagConstraints gbc_txtoldsellempty = new GridBagConstraints();
				//gbc_txtoldsellempty.insets = new Insets(0, 0, 9, 0);
				gbc_txtoldsellempty.anchor = GridBagConstraints.NORTH;
				gbc_txtoldsellempty.gridx = 0;
				gbc_txtoldsellempty.gridwidth = 5;
				i++;
				gbc_txtoldsellempty.gridy = i+1;
				add(txtoldsellempty, gbc_txtoldsellempty);
			}
			
			else {
				for (j = 0; j < results2.size(); j++) {

					Review b = results2.get(j);

					c = new GridBagConstraints();
					c.gridx = 0;
					c.gridwidth = 5;
					c.gridy = 4*j+i+1;
					c.weighty = 0.2;
					//c.weightx = 1;
					c.anchor = GridBagConstraints.WEST;

					
					reviewLabel = new JLabel("ISBN No: "+b.book_isbn + ", Title: " + b.book_title +
							", Book Condition: "+ b.book_condition + ", Buyer: "+ b.buyer_name);
					reviewLabel.setForeground(Color.BLUE);

					reviewLabel.setBackground(getBackground());
		
					this.add(reviewLabel, c);
					
					c = new GridBagConstraints();
					c.gridx = 0;
					c.gridy = 4*j+i+2;
					c.weighty = 0.2;
					//c.weightx = 1;
					c.anchor = GridBagConstraints.WEST;

					
					reviewLabel2 = new JLabel("Buyer Stars: "+ b.buyer_stars + " Buyer Comments: " + b.buyer_comments);
					reviewLabel2.setForeground(Color.BLUE);

					reviewLabel2.setBackground(getBackground());
		
					this.add(reviewLabel2, c);
					
					c = new GridBagConstraints();
					c.gridx = 0;
					c.gridy = 4*j+i+3;
					c.weighty = 0.2;
					//c.weightx = 1;
					c.anchor = GridBagConstraints.WEST;

					
					reviewLabel3 = new JLabel("Book Stars: "+ b.book_stars + " Book Comments: " + b.book_comments);
					reviewLabel3.setForeground(Color.BLUE);

					reviewLabel3.setBackground(getBackground());
		
					this.add(reviewLabel3, c);
					
					c = new GridBagConstraints();
					c.gridx = 0;
					c.gridy = 4*j+i+4;
					c.weighty = 0.2;
					//c.weightx = 1;
					c.anchor = GridBagConstraints.WEST;

					
					reviewLabel4 = new JLabel("Seller Stars: "+ b.seller_stars + " Seller Comments: " + b.seller_comments);
					reviewLabel4.setForeground(Color.BLUE);

					reviewLabel4.setBackground(getBackground());
		
					this.add(reviewLabel4, c);
				}
			}
				
				i = 0;
				j = 0;
				
				
				JLabel newbuyreviews = new JLabel("New Book Purchases to Review");
				newbuyreviews.setFont(new Font("Tahoma", Font.PLAIN, 20));
				GridBagConstraints gbc_newbuyreviews = new GridBagConstraints();
				//gbc_newbuyreviews.insets = new Insets(0, 0, 9, 0);
				gbc_newbuyreviews.anchor = GridBagConstraints.NORTH;
				gbc_newbuyreviews.gridx = 5;
				gbc_newbuyreviews.gridwidth = 5;
				gbc_newbuyreviews.gridy = 0;
				add(newbuyreviews, gbc_newbuyreviews);

				if (results3.size() == 0) {
					JLabel txtempty = new JLabel("None");
					GridBagConstraints gbc_txtempty = new GridBagConstraints();
					//gbc_txtempty.insets = new Insets(0, 0, 9, 0);
					gbc_txtempty.anchor = GridBagConstraints.NORTH;
					gbc_txtempty.gridx = 5;
					gbc_txtempty.gridwidth = 5;
					i++;
					gbc_txtempty.gridy = i;
					add(txtempty, gbc_txtempty);
				}
				
				// Now we loop through the results and create the labels and buttons we need

				else {
					for (i = 0; i < results3.size(); i++) {

						Review b = results3.get(i);

						c = new GridBagConstraints();
						c.gridx = 5;
						c.gridwidth = 5;
						c.gridy = 3*i+1;
						c.weighty = 0.2;
						//c.weightx = 1;
						c.anchor = GridBagConstraints.WEST;

						
						reviewLabel = new JLabel("ISBN No: "+b.book_isbn + ", Title: " + b.book_title +
								", Book Condition: "+ b.book_condition + ", Seller: "+ b.seller_name);
						reviewLabel.setForeground(Color.BLUE);

						reviewLabel.setBackground(getBackground());
			
						this.add(reviewLabel, c);
						
			
						JLabel txtseller_stars = new JLabel("Seller Stars: ");
						c = new GridBagConstraints();
						//c.insets = new Insets(0, 0, 9, 0);
						//c.weightx = 1;
						c.weighty = 0.2;
						c.gridx = 5;
						c.gridy = 3*i+2;
						c.anchor = GridBagConstraints.WEST;
						add(txtseller_stars, c);
						
						JTextField seller_stars = new JTextField();
						
						c = new GridBagConstraints();
						//c.insets = new Insets(0, 0, 9, 0);
						//c.weightx = 1;
						c.weighty = 0.2;
						c.gridx = 6;
						c.gridy = 3*i+2;
						c.anchor = GridBagConstraints.WEST;
						c.fill=GridBagConstraints.HORIZONTAL;
						seller_stars.setColumns(4);
						add(seller_stars, c);
						
						
						JLabel txtseller_comments = new JLabel("Seller Comments: ");
						c = new GridBagConstraints();
						//c.insets = new Insets(0, 0, 9, 0);
						//c.weightx = 1;
						c.weighty = 0.2;
						c.gridx = 7;
						c.gridy = 3*i+2;
						c.anchor = GridBagConstraints.WEST;
						add(txtseller_comments, c);
						
						JTextField seller_comments = new JTextField();
						
						c = new GridBagConstraints();
						//c.insets = new Insets(0, 0, 9, 0);
						//c.weightx = 1;
						c.weighty = 0.2;
						c.gridx = 8;
						c.gridy = 3*i+2;
						c.anchor = GridBagConstraints.WEST;
						c.fill=GridBagConstraints.HORIZONTAL;
						seller_comments.setColumns(10);
						add(seller_comments, c);
						
						JLabel txtbook_stars = new JLabel("Book Stars: ");
						c = new GridBagConstraints();
						//c.insets = new Insets(0, 0, 9, 0);
						//c.weightx = 1;
						c.weighty = 0.2;
						c.gridx = 5;
						c.gridy = 3*i+3;
						c.anchor = GridBagConstraints.WEST;
						add(txtbook_stars, c);
						
						JTextField book_stars = new JTextField();
						c = new GridBagConstraints();
						//c.insets = new Insets(0, 0, 9, 0);
						//c.weightx = 1;
						c.weighty = 0.2;
						c.gridx = 6;
						c.gridy = 3*i+3;
						c.anchor = GridBagConstraints.WEST;
						c.fill=GridBagConstraints.HORIZONTAL;
						book_stars.setColumns(4);
						add(book_stars, c);
						
						
						JLabel txtbook_comments = new JLabel("Book Comments: ");
						c = new GridBagConstraints();
						//c.insets = new Insets(0, 0, 9, 0);
						//c.weightx = 1;
						c.weighty = 0.2;
						c.gridx = 7;
						c.gridy = 3*i+3;
						c.anchor = GridBagConstraints.WEST;
						add(txtbook_comments, c);
						
						JTextField book_comments = new JTextField();
						c = new GridBagConstraints();
						//c.insets = new Insets(0, 0, 9, 0);
						//c.weightx = 1;
						c.weighty = 0.2;
						c.gridx = 8;
						c.gridy = 3*i+3;
						c.anchor = GridBagConstraints.WEST;
						c.fill=GridBagConstraints.HORIZONTAL;
						book_comments.setColumns(10);
						add(book_comments, c);
						
						b.buyer_name = masterController.user.getDisplayName();
						b.reviewed_by_buyer = 1;
						
						reviewButton = new ReviewButton("Review");
						reviewButton.setReview(b);
						reviewButton.seller_stars = seller_stars;
						reviewButton.seller_comments = seller_comments;
						reviewButton.book_stars = book_stars;
						reviewButton.book_comments = book_comments;
								
						reviewButton.addActionListener(reviewHandler);
			

						c = new GridBagConstraints();
						//c.weightx = 0;
						c.gridx = 9;
						c.gridy = 3*i+3;
						c.anchor = GridBagConstraints.WEST;
						c.weighty = 0.1;
						this.add(reviewButton, c);
						
					}
				}
					i = 3*i+1;
					JLabel txtoldbuy = new JLabel("Old Book Purchase Reviews");
					txtoldbuy.setFont(new Font("Tahoma", Font.PLAIN, 20));
					GridBagConstraints gbc_txtoldbuy = new GridBagConstraints();
					gbc_txtoldbuy.insets = new Insets(0, 0, 9, 0);
					gbc_txtoldbuy.anchor = GridBagConstraints.NORTH;
					gbc_txtoldbuy.gridx = 5;
					gbc_txtoldbuy.gridwidth = 5;
					gbc_txtoldbuy.gridy = i;
					add(txtoldbuy, gbc_txtoldbuy);

					if (results4.size() == 0) {
						JLabel txtoldsellempty = new JLabel("No Reviews to Display");
						GridBagConstraints gbc_txtoldsellempty = new GridBagConstraints();
						gbc_txtoldsellempty.insets = new Insets(0, 0, 9, 0);
						gbc_txtoldsellempty.anchor = GridBagConstraints.NORTH;
						gbc_txtoldsellempty.gridx = 5;
						gbc_txtoldsellempty.gridwidth = 5;
						i++;
						gbc_txtoldsellempty.gridy = i+1;
						add(txtoldsellempty, gbc_txtoldsellempty);
					}
					
					else {
						for (j = 0; j < results4.size(); j++) {

							Review b = results4.get(j);

							c = new GridBagConstraints();
							c.gridx = 5;
							c.gridwidth = 5;
							c.gridy = 4*j+i+1;
							c.weighty = 0.2;
							//c.weightx = 1;
							c.anchor = GridBagConstraints.WEST;

							
							reviewLabel = new JLabel("ISBN No: "+b.book_isbn + ", Title: " + b.book_title +
									", Book Condition: "+ b.book_condition + ", Seller: "+ b.seller_name);
							reviewLabel.setForeground(Color.BLUE);

							reviewLabel.setBackground(getBackground());
				
							this.add(reviewLabel, c);
							
							c = new GridBagConstraints();
							c.gridx = 5;
							c.gridy = 4*j+i+2;
							c.weighty = 0.2;
							//c.weightx = 1;
							c.anchor = GridBagConstraints.WEST;

							
							reviewLabel2 = new JLabel("Buyer Stars: "+ b.buyer_stars + " Buyer Comments: " + b.buyer_comments);
							reviewLabel2.setForeground(Color.BLUE);

							reviewLabel2.setBackground(getBackground());
				
							this.add(reviewLabel2, c);
							
							c = new GridBagConstraints();
							c.gridx = 5;
							c.gridy = 4*j+i+3;
							c.weighty = 0.2;
							//c.weightx = 1;
							c.anchor = GridBagConstraints.WEST;

							
							reviewLabel3 = new JLabel("Book Stars: "+ b.book_stars + " Book Comments: " + b.book_comments);
							reviewLabel3.setForeground(Color.BLUE);

							reviewLabel3.setBackground(getBackground());
				
							this.add(reviewLabel3, c);
							
							c = new GridBagConstraints();
							c.gridx = 5;
							c.gridy = 4*j+i+4;
							c.weighty = 0.2;
							//c.weightx = 1;
							c.anchor = GridBagConstraints.WEST;

							
							reviewLabel4 = new JLabel("Seller Stars: "+ b.seller_stars + " Seller Comments: " + b.seller_comments);
							reviewLabel4.setForeground(Color.BLUE);

							reviewLabel4.setBackground(getBackground());
				
							this.add(reviewLabel4, c);
						}
					}
			
			
		

	}

};
