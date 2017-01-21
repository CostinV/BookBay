public class User {

	private String login_name;
	private String password;
	private String display_name;
	private int warnings;
	private double credits;
	private int rating;
	private boolean suspended;
	private boolean changedpass = true;
	private boolean admin = false;
	
	public User() {
	}
	
	public User(String s1, String s2, int i) {
		
		if (i==1) {
			this.login_name = s1;
			this.display_name = s2;
		} else if (i==2) {
			this.login_name = s1;
			this.password = s2;
		} else if (i==3) {
			this.display_name = s1;
			this.password = s2;
		}
	}
	
	public User(String s) {
		this.display_name = s;
	}
	
	public String getLoginName() {
		return this.login_name;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getDisplayName() {
		return this.display_name;
	}
	
	public void setPassword(String s) {
		this.password = s;
	}
	
	public void setLoginName(String s) {
		this.login_name = s;
	}
	
	public void setUser(String s) {
		this.display_name = s;
	}
	
	public int getWarnings() {
		return this.warnings;
	}
	
	public void setWarnings(int w) {
		this.warnings = w;
	}
	
	public int getRating() {
		return this.rating;
	}
	
	public void setRating(int r) {
		this.rating = r;
	}
	
	public double getCredits() {
		return this.credits;
	}
	
	public void setCredits(double c) {
		this.credits = c;
	}
	
	public boolean isSuspended() {
		return this.suspended;
	}
	
	public void setSuspended(boolean s) {
		this.suspended = s;
	}

	public boolean hasChangedPass() {
		return changedpass;
	}

	public void setChangedPass(boolean changedpass) {
		this.changedpass = changedpass;
	}
	
	public boolean isAdmin() {
		return this.admin;
	}
	
	public void setAdmin() {
		this.admin = true;
	}
}
