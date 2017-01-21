import javax.swing.JButton;
import javax.swing.JTextField;


public class ReviewButton extends JButton {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3046581455969036835L;
	private Review review;
	public JTextField buyer_stars;
	public JTextField buyer_comments;
	
	public JTextField book_stars;
	public JTextField book_comments;
	
	public JTextField seller_stars;
	public JTextField seller_comments;
	
	public ReviewButton()
	{
		super();
	}
	
	public ReviewButton(String s) {
		super(s);
	}

	public void setReview(Review b)
	{
		this.review = b;
	}
	
	public Review getReview()
	{
		return this.review;
	}
}
