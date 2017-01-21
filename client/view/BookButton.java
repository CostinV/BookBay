import javax.swing.JButton;


public class BookButton extends JButton {
	
	private Book book;
	
	public BookButton()
	{
		super();
	}
	
	public BookButton(String s) {
		super(s);
	}

	public void setBook(Book b)
	{
		this.book = b;
	}
	
	public Book getBook()
	{
		return this.book;
	}
}
