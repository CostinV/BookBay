import javax.swing.JButton;


public class UserButton extends JButton {
	
	private String display_name;
	
	public UserButton()
	{
		super();
	}
	
	public UserButton(String s) {
		super(s);
	}

	public void setUser(String b)
	{
		this.display_name = b;
	}
	
	public String getUser()
	{
		return this.display_name;
	}
}
