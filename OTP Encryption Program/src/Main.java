import javax.swing.ImageIcon;
import javax.swing.JFrame;

/* AUTHOR: AgentNonchalant
 * DATE: Jan 16, 2024  

 * Purpose: To initialize a GUI window (We will actually use this file very little).
 */

public class Main {

	public static void main(String[] args) {
		GUIWindow win = new GUIWindow("One-Time Pad Encryption Program");
		win.setLocationSize(450,100,1000,800); 
		win.setVisible(true);
		win.setResizable(false);
		win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		ImageIcon icon = new ImageIcon("Icon.png");
		win.setIconImage(icon.getImage());
	}
}
