
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
/**
 * This is a View class in the MVC pattern. It is responsible for taking a
 * single student object and displaying the details nicely.
 * 
 * The layout is set up for you already. The only thing you need to do is
 * complete the setStudentDetails(Student s) method below.
 * 
 */
public class AboutUsPanel extends JPanel{
	private JLabel text;
   
    public AboutUsPanel(Controller masterController) {
    	String s = "<html>We are a group of students at City College of New York.<br> We are all currently in our junior year of college and are looking to graduate with a degree in computer science. <br> Anmol is interested in statistical analysis and embedded systems engineering. <br> Kashif is looking to get into financial analysis or opening up his own software development company. <br> Raj and Costin are both interested scientific programming (Chemical processes simulation) and game design. Our collaboration in this project makes us a much better team. <br> Given more time, we would love to continue working on this project and continue adding more functionality. <br> If there are any concerns regarding password information, false feedback, or any other general inquiries, please email us at support@bookbay.com </html>";
    	text = new JLabel(s);
    	add(text);
    }// end class

}

	
