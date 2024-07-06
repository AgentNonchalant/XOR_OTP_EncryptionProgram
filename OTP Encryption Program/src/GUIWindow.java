//IMPORTS
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


/* AUTHOR: AgentNonchalant
 * DATE: Jan 16, 2024  

 * PURPOSE: To describe our GUIWIndow class (This will design the look and feel of the window)
 * It will also describe component actions and their corresponding effects, as well as .txt resource organization, preference implementations, etc. 
 */

public class GUIWindow extends JFrame implements ActionListener{
	
//VARIBLES & OBJECTS
private int x, y; //x-y location of window
private int width, height; //Window dimensions 
private String plaintext, pad, ciphertext; 
private String theme;
private int joke = 0;
	
Color darkGrey = new Color(10,10,25);
Color otherGrey = new Color(16,16,32);
Color textGrey = new Color(42,42,54);
Color softGreen = new Color(55,200,102);
Color softRed = new Color(241,75,84);
Color darkNavy = new Color(13,13,38);
Color navy = new Color(19,19,45);
Color textNavy = new Color(42,42,80);
Color offWhite = new Color(225, 227, 230);
	
private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP); 
private JPanel encryptPanel_1 = new JPanel();
private JPanel btnBar_1 = new JPanel();
private JPanel centerPanel = new JPanel(); 
private JPanel north = new JPanel(); 
private JPanel south = new JPanel();
private JPanel nEast = new JPanel();
private JPanel nWest = new JPanel();
private JPanel sWest = new JPanel();
private JPanel sEast = new JPanel();
private JPanel sEastL = new JPanel();
private JPanel sEastR = new JPanel();
	
private JTextArea plaintextArea = new JTextArea(16,40);
private JTextArea padArea = new JTextArea(16,40);
private JTextArea ciphertextArea = new JTextArea(16, 40);
private JScrollPane scrollPane1 = new JScrollPane(plaintextArea);
private JScrollPane scrollPane2 = new JScrollPane(padArea);
private JScrollPane scrollPane3 = new JScrollPane(ciphertextArea);

private JButton btnGeneratePad =  new JButton("Generate Pad");
private JButton btnEncryptDecrypt = new JButton("Encrypt/Decrypt");
private JButton btnOkay = new JButton("Ok"); 
private JButton btnClear = new JButton("Clear"); 
private JButton btnQuit = new JButton("Quit");
private JRadioButton askSave = new JRadioButton("Save results to project?  ");
private JRadioButton askSpaces = new JRadioButton("Ignore plaintext spaces?");
private JRadioButton askCaps = new JRadioButton("Ignore capitalization?    ");

private JMenuBar menuBar = new JMenuBar(); 
private JMenu fileMenu = new JMenu("File"); 
private JMenu helpMenu = new JMenu("Help"); 
private JMenu themeMenu = new JMenu("Theme");
private JMenuItem newMenuItem = new JMenuItem("New"); 
private JMenuItem clearMenuItem = new JMenuItem("Clear"); 	
private JMenuItem quitMenuItem = new JMenuItem("Quit"); 	
private JMenuItem helpMenuItem = new JMenuItem("Help"); 	
private JMenuItem aboutMenuItem = new JMenuItem("About");
private JMenuItem jokeMenuItem = new JMenuItem("Joke");
private JMenuItem lightMenuItem = new JMenuItem("Light");
private JMenuItem darkMenuItem = new JMenuItem("Dark");
private JMenuItem navyMenuItem = new JMenuItem("Navy");

JLabel ciphertextLabel_1 = new JLabel("Encrypted Message Here (Ciphertext)");
JLabel padLabel_1 = new JLabel("Random Pad Here (Encryption Key)");
JLabel plaintextLabel_1 = new JLabel("Secret Message Here (Plaintext)");


//CONSTRUCTORS
public GUIWindow(){
	super();
	init(); 
}

public GUIWindow(String title){
	super(title);
	init();
}

//METHODS

/** Initializes the GUI window for display.*/
public void init(){
System.out.println("Initializing...");
Container cp = this.getContentPane();
	
//Setting up the menu bar
	this.setJMenuBar(menuBar); 
	menuBar.add(fileMenu);
	menuBar.add(helpMenu);
	menuBar.add(themeMenu);
	
	fileMenu.add(newMenuItem);
	fileMenu.add(clearMenuItem);
	fileMenu.addSeparator();
	fileMenu.add(quitMenuItem);
	
	helpMenu.add(helpMenuItem);
	helpMenu.add(aboutMenuItem);
	helpMenu.add(jokeMenuItem);
	
	themeMenu.add(lightMenuItem); lightMenuItem.setBackground(Color.WHITE);
	themeMenu.addSeparator();
	themeMenu.add(darkMenuItem); darkMenuItem.setBackground(textGrey); darkMenuItem.setForeground(Color.WHITE);
	themeMenu.addSeparator();
	themeMenu.add(navyMenuItem); navyMenuItem.setBackground(textNavy); navyMenuItem.setForeground(Color.WHITE);
	
//Setting up button & item actions
	btnOkay.addActionListener(this);
	btnOkay.setActionCommand("OKAY");
	
	btnQuit.addActionListener(this);
	btnQuit.setActionCommand("QUIT");
	quitMenuItem.addActionListener(this);
	quitMenuItem.setActionCommand("QUIT");
	
	btnClear.addActionListener(this);
	btnClear.setActionCommand("CLEAR");
	clearMenuItem.addActionListener(this);
	clearMenuItem.setActionCommand("CLEAR");
	
	newMenuItem.addActionListener(this);
	newMenuItem.setActionCommand("new");
	
	helpMenuItem.addActionListener(this);
	helpMenuItem.setActionCommand("help");
	
	aboutMenuItem.addActionListener(this);
	aboutMenuItem.setActionCommand("about");
	
	jokeMenuItem.addActionListener(this);
	jokeMenuItem.setActionCommand("joke");
	
	btnEncryptDecrypt.addActionListener(this);
	btnEncryptDecrypt.setActionCommand("encrypt/decrypt");
	
	btnGeneratePad.addActionListener(this);
	btnGeneratePad.setActionCommand("generatePad");
	
	askSpaces.addActionListener(this);
	askSpaces.setActionCommand("askSpaces");
	
	askSave.addActionListener(this);
	askSave.setActionCommand("askSave");
	
	askCaps.addActionListener(this);
	askCaps.setActionCommand("askCaps");
	
	lightMenuItem.addActionListener(this);
	lightMenuItem.setActionCommand("lightTheme");
	darkMenuItem.addActionListener(this);
	darkMenuItem.setActionCommand("darkTheme");
	navyMenuItem.addActionListener(this);
	navyMenuItem.setActionCommand("navyTheme");
	
//Adding panels, rules, and buttons
	cp.setLayout(new GridLayout());
	
	plaintextArea.setLineWrap(true); plaintextArea.setWrapStyleWord(true);
	padArea.setLineWrap(true); padArea.setWrapStyleWord(true);
	ciphertextArea.setLineWrap(true); ciphertextArea.setWrapStyleWord(true);
	
	cp.add(tabbedPane);
	tabbedPane.addTab("Encrypt", null, encryptPanel_1, null);
	encryptPanel_1.setLayout(new GridLayout(2,1));
	
	encryptPanel_1.add(north); 
	north.setLayout(new BorderLayout());
	north.add(btnBar_1, BorderLayout.SOUTH);
	btnBar_1.setLayout(new FlowLayout());
	btnBar_1.add(btnEncryptDecrypt); 
	btnBar_1.add(btnGeneratePad);
		btnGeneratePad.setBackground(softGreen);
		btnEncryptDecrypt.setBackground(softGreen);
	north.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout());
		centerPanel.add(nWest);
			nWest.setLayout(new FlowLayout());
			plaintextLabel_1.setFont(new Font("Arial", Font.PLAIN, 17)); plaintextLabel_1.setForeground(Color.WHITE);
			plaintextLabel_1.setVerticalAlignment(SwingConstants.TOP);
			nWest.add(plaintextLabel_1);
			nWest.add(scrollPane1); 
		centerPanel.add(nEast);
			nEast.setLayout(new FlowLayout());
			padLabel_1.setFont(new Font("Arial", Font.PLAIN, 17)); padLabel_1.setForeground(Color.WHITE);
			nEast.add(padLabel_1);
			nEast.add(scrollPane2);
	
	encryptPanel_1.add(south);
	south.setLayout(new GridLayout());
	south.add(sWest);
		sWest.setLayout(new FlowLayout());
			ciphertextLabel_1.setFont(new Font("Arial", Font.PLAIN, 17)); ciphertextLabel_1.setForeground(Color.WHITE);
			ciphertextLabel_1.setVerticalAlignment(SwingConstants.TOP);
			sWest.add(ciphertextLabel_1);
			sWest.add(scrollPane3); 
	south.add(sEast); 
		sEast.setLayout(new BoxLayout(sEast,BoxLayout.X_AXIS));
		sEast.add(sEastL); sEastL.setLayout( new BoxLayout(sEastL,BoxLayout.X_AXIS)); 
			btnOkay.setBackground(softRed); 
			sEastL.add(btnOkay);
			btnClear.setBackground(softRed);
			sEastL.add(btnClear);
			btnQuit.setBackground(softRed);
			sEastL.add(btnQuit);
		sEast.add(sEastR); sEastR.setLayout( new BoxLayout(sEastR,BoxLayout.Y_AXIS)); 
		sEastR.setBorder(BorderFactory.createMatteBorder(0, 75, 0, 0, otherGrey));
			askSave.setBackground(softRed); 
			askSave.setBorder(BorderFactory.createMatteBorder(5, 5, 3, 8, softRed));
			sEastR.add(askSave); 
			askSpaces.setBackground(softRed);
			askSpaces.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 9, softRed));
			sEastR.add(askSpaces); 
			askCaps.setBackground(softRed);
			askCaps.setBorder(BorderFactory.createMatteBorder(3, 5, 5, 16, softRed));
			sEastR.add(askCaps);
			
//Reading and implementing preferences
	if (Files.exists(Paths.get("preferences.txt"))) { // Checks if "preferences.txt" exists; if true, then it will attempt to read it
		try (BufferedReader reader = new BufferedReader(new FileReader("preferences.txt"))){
			System.out.println("\"preferences.txt\" file found. Will now attempt to read.");		
			String str = reader.readLine();
			reader.close();
			
			for ( int i = 0; i < 4; i++ ) { //Tests the button booleans in "preferences.txt" to make sure they are properly formatted (as either "true" or "false"). Throws exception otherwise. 
				if (!str.split(";")[0].equals("true")&&!str.split(";")[0].equals("false")) {
					throw new Exception();
				}
			}
		
			askSpaces.setSelected(str.split(";")[0].equals("true")); //Only selected if written as "true"; all else returns the default "false".
			askSave.setSelected(!str.split(";")[1].equals("false")); //Selected in all cases unless written as "false"; all else returns the default "true".
			askCaps.setSelected(str.split(";")[2].equals("true")); //Only selected if written as "true"; all else returns the default "false".
			
			switch (str.split(";")[3]) { //Interprets the string that indicates the theme setting in "preferences.txt". Throws exception if the string cannot be resolved to a theme. 
				case "lightTheme":
					lightMenuItem.doClick();
					break;
				case "darkTheme":
					darkMenuItem.doClick();
					break;
				case "navyTheme":
					navyMenuItem.doClick();
					break;
				default: 
					throw new Exception();
			}
		} catch (Exception e) { //Runs when exception is thrown because "preferences.txt" is improperly formatted.
			System.out.println("Attempted to read from \"preferences.txt\" but an exception was thrown.");
			askSpaces.setSelected(false);
			askSave.setSelected(true);
			askCaps.setSelected(false);
			darkMenuItem.doClick();
		
			String str1 = "The \"preferences.txt\" file is used to remember your settings and allow them to persist across future sessions.\n";
			str1 += "Improperly editing this file prevents the program from interpreting it.\n\n";
			str1 += "For the best experience, please refrain from making changes to this file.";
			JOptionPane.showMessageDialog(this, str1, "ERR: \"preferences.txt\" file improperly formatted!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("ErrorIcon.png"));
		}
	} else { //Runs if "preferences.txt" does NOT exist. It will be created in this case.
		System.out.println("Attempted to read from \"preferences.txt\" but file does not exist. A new one will now be created.");
		askSpaces.setSelected(false);
		askSave.setSelected(true);
		askCaps.setSelected(false);
		darkMenuItem.doClick();
	}
}

/** Describes actions to be performed after specific triggering events.
 * @param e - The object created after a component-specific action triggers its action listener. It contains an action command which triggers a subsequent event. 
 */
public void actionPerformed (ActionEvent e){
	String command = e.getActionCommand(); 
	System.out.println("Action performed: " + command);
	
	switch (command) { //Interprets the action to be performed based on the value of the ActionEvent's action command.
	
		case "OKAY": //Does almost nothing. Critically important to the program.
			String str = "You pressed a button! Nice! \n";
			str += ("This button does nothing in particular.\n");
			str += ("Have a nice day :)\n");
			ImageIcon icon = new ImageIcon("albert2.jpg");
			JOptionPane.showMessageDialog(this, str, "OK: Okay? Okay!", JOptionPane.INFORMATION_MESSAGE, icon);
			break;
			
		case "QUIT": //Terminates the program, closing all active windows.
			int ans = JOptionPane.showConfirmDialog(this,
					"Are you sure you want to exit? (This will close all windows)", 
					"Exit",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					new ImageIcon("ExitIcon.png"));
			if (ans == JOptionPane.YES_OPTION){System.exit(0);}	
			break;
			
		case "CLEAR": //Clears all text areas (has no effect on the .txt files outside the program).
			int ans1 = JOptionPane.showConfirmDialog(this,
				"Are you super-duper sure you want to clear all inputed data?", 
				"Clear the data! Complete anarachy!",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				new ImageIcon("ClearIcon.png")); 
			if (ans1 == JOptionPane.YES_OPTION){
				plaintextArea.setText("");
				padArea.setText("");
				ciphertextArea.setText("");
			}	
			break;
		
		case "generatePad": //Calls the generatePad method in OTP.java to return a random hexadecimal pad that is at least as long as the plaintext when in decimal form.
			plaintext = plaintextArea.getText();
			if (askSpaces.isSelected()){
				plaintext=ignoreSpaces(plaintext);
			}
			if (!plaintext.isBlank()){
				padArea.setText(OTP.generatePad(plaintext.length()));
			} else {
				String str1 = "In order to generate a pad, we need a message: \n";
				str1 += ("Please enter a message in the plaintext field to generate a pad.\n");  
				JOptionPane.showMessageDialog(this, str1, "ERR: Plaintext field is empty!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("ErrorIcon.png"));
			}
			break;
			
		case "encrypt/decrypt": //Calls the encrypt method in OTP.java to encrypt a message, or the decrypt method to decrypt a message. The method that is called is selected logically based on which text areas are populated and which are not.
			ciphertext = ciphertextArea.getText();
			plaintext = plaintextArea.getText();
			pad = padArea.getText();	
			
			if (!pad.isBlank()&&!plaintext.isBlank()) { //Runs the encryption protocol when the padTextArea and plainTextArea are populated (excluding just whitespace).
				if (askSpaces.isSelected()) {plaintext=ignoreSpaces(plaintext);}
				if (askCaps.isSelected()) {plaintext=ignoreCaps(plaintext);}
				
				//Verifies that the pad is long enough to encrypt the plaintext. Runs if false, and breaks out of the loop.
				if (!appropriatePadSize(pad.length()/2, plaintext.length())) { //The pad, being in hexadecimal, will appear to be twice as long as the ASCII plaintext. Dividing its length by two is appropriate in this case. 
					padArea.setText("");
					String str1 = "Whoopsie! ";
					str1 += ("Your pad is too small to support the size of your message.\n\n"
							+"This is likely due to a change in the size of your message, which\n"
							+"now has more characters than the pad can account for, or by deselecting\n"
							+"the \"Ignore plaintext spaces?\" button after a pad has been generated.\n\n"
							+"Please regenerate a new pad value to continue.");
					JOptionPane.showMessageDialog(this, str1, "Almost an ERR: Pad is now invalid!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("ErrorIcon.png"));
					break;
				}
								
				try {
					ciphertext = OTP.encrypt(plaintext, pad);				
					ciphertextArea.setText(ciphertext);
					if (askSave.isSelected()) {saveAll();};
				} catch (NumberFormatException n) { //Runs when the pad is not properly formatted into hexadecimal during encryption in OTP.encrypt.
					padArea.setText("");
					String str1 = "Whoopsie! ";
					str1 += ("This program only supports pad values in hexadecimal format for user \n"
							+"readability/printability. Your pad is improperly formatted in hexadecimal.\n\n"
							+"All characters should be in the ranges '0-9' and 'A-F'.\n\n"
							+"Please regenerate a new pad value to continue.");
					JOptionPane.showMessageDialog(this, str1, "ERR: Pad improperly formatted!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("ErrorIcon.png"));
				}
				
			} else if (!ciphertext.isBlank()&&!pad.isBlank()&&plaintext.isBlank()){ //Runs the decryption protocol when the padTextArea and cipherTextArea are populated, but only if the plainTextArea is NOT populated (excluding just whitespace).
				//Verifies that the pad is long enough to decrypt the ciphertext. Runs if false, and breaks out of the loop.
				if (!appropriatePadSize((padArea.getText().length()), ciphertextArea.getText().length())) { //Both the pad and the ciphertext are in hexadecimal format, so their size should be the same (the pad does not need to be divided by 2).   
					String str1 = "Whoopsie! ";
					str1 += ("Your pad is too small to support the size of your ciphertext.\n\n"
							+"This is likely due to a change in the size of the pad, which may no\n"
							+"longer be the same as the original.\n\n"
							+"Please re-enter the original hexadecimal pad and ciphertext to continue.");
					JOptionPane.showMessageDialog(this, str1, "Almost an ERR: Pad is now invalid!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("ErrorIcon.png"));
					break;
				}
				
				try {
				plaintext = OTP.decrypt(pad, ciphertext);
				if (askSpaces.isSelected()) {plaintext=ignoreSpaces(plaintext);}
				if (askCaps.isSelected()) {plaintext=ignoreCaps(plaintext);}
				plaintextArea.setText(plaintext);				
				if (askSave.isSelected()) {saveAll();};
				} catch (NumberFormatException n) {//Runs when either the pad or the ciphertext is improperly formatted in hexadecimal (or both).
					if (n.getMessage().equals("Pad improperly formatted")) { //Runs when the cause of the exception is improper pad format.
						String str1 = "Whoopsie! ";
						str1 += ("This program only supports pad values in hexadecimal format for user \n"
								+"readability/printability. Your pad is improperly formatted in hexadecimal.\n\n"
								+"All characters should be in the ranges '0-9' and 'A-F'.\n\n"
								+"Please re-enter the original hexadecimal pad to continue.");
						JOptionPane.showMessageDialog(this, str1, "ERR: Pad improperly formatted!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("ErrorIcon.png"));
					} else if (n.getMessage().equals("Ciphertext improperly formatted")) { //Runs when the cause of the exception is improper ciphertext format.
						String str1 = "Whoopsie! ";
						str1 += ("This program only supports ciphertext values in hexadecimal format for user \n" //TODO changed for grammar
								+"readability/printability. Your ciphertext is improperly formatted in hexadecimal.\n\n"
								+"All characters should be in the ranges '0-9' and 'A-F'.\n\n"
								+"Please re-enter the original hexadecimal ciphertext to continue.");
						JOptionPane.showMessageDialog(this, str1, "ERR: Ciphertext improperly formatted!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("ErrorIcon.png"));					
					}
				}
				
			} else if (plaintext.isBlank()){ //Runs when the plainTextArea is unpopulated (excluding just whitespace).
				String str1 = "In order to encrypt a message, we need a message: \n";
				str1 += ("Please enter a message in the plaintext field to encrypt a message.\n");  
				JOptionPane.showMessageDialog(this, str1, "ERR: Plaintext field is empty!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("ErrorIcon.png"));
				
			} else if (pad.isBlank()){ //Runs when the padArea is unpopulated (excluding just whitespace).
				String str1 = "In order to encrypt this message, we need a pad value: \n";
				str1 += ("Please click \"Generate Pad\" to make a pad (Or paste one already generated by this program).\n");  
				JOptionPane.showMessageDialog(this, str1, "ERR: Pad field is empty!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("ErrorIcon.png"));
			}
			break; 
			
		case "askSpaces": //Updates "preferences.txt" to account for the change, but also verifies that the pad is long enough to support the plaintext.
			pad = padArea.getText(); 
			plaintext = plaintextArea.getText();
			
			if ((!pad.isBlank())&&(!appropriatePadSize((pad.length())/2, plaintext.length()))) { //The pad, being in hexadecimal, will appear to be twice as long as the ASCII plaintext. Dividing its length by two is appropriate in this case. 
				padArea.setText("");
				String str1 = "Whoopsie! ";
				str1 += ("You changed your preferences to account for spaces.\n\n");
				str1 += ("This has invalidated the size of your pad, which \n"
						+ "now has fewer characters than is needed.\n\n");
				str1 += ("Please regenerate a pad to continue.\n");
				JOptionPane.showMessageDialog(this, str1, "Almost an ERR: Pad is now invalid!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("ErrorIcon.png"));
			}
			updatePref();
			break;
			
		case "askSave": //Updates "preferences.txt" to account for the change.
			updatePref();
			break;
			
		case "askCaps": //Updates "preferences.txt" to account for the change.
			updatePref();
			break;
			
		case "new": //Initializes a new GUI window. 
			Main.main(null);
			break;
			
		case "about": //Displays an OptionPane with background information about the program.
			String str1 = "About the One-Time Pad Encryption Program and procedures:\n\n";
			str1 += ("This program is used to ensure the security of messsages using exclusive OR (XOR) bitwise one-time pad encryption.\n"
					+ "A \"plaintext\" (the actual message) is entered along with a random hexadecimal \"pad\", and each character is\n"
					+ "sequentially manipulated according to its corresponding hexadecimal character in the pad. A resulting ecrypted\n"
					+ "ciphertext is displayed.\n\n"
					+ "\tEx. \"Hello World\" (message) + \"3D27BDB312F830E055F811\" (pad) = \"7542D1DF7DD8678F279475\" (ciphertext)\n\n"
					+ "A pad consists of randomly generated characters within the pool of 240 possible ASCII values. It is displayed in \n"
					+ "hexadecimal format only for readability, because many ASCII characters are not printable and cannot be easily copied.\n"
					+ "During encryption/decryption however, it is converted and recognized in the original ASCII format.\n\n"
					+ "\tEx. The hexadecimal string \"7b 5c 40 55 25\" actually represents \"{ \\ @ U %\" in ASCII notation.\n\n"
					+ ""
					+ "Version: 2024-01 (2.1.00)\n"
					+ "Build id: 00000000-0002\n\n"
					+ "(c) Copyright Harbringer Technologies 2024.  All rights reserved.");
			JOptionPane.showMessageDialog(this, str1, "About One-Time Pad Encryption Program", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Icon.png"));
			break;
			
		case "help": //Displays an OptionPane with program instructions.
			String str2 = "One-Time Pad Encryption Program instructions:\n\n";
			str2 += ("In order to encrypt a message, both a plaintext (message) and pad must be present. This program\n"
					+ "can automatically generate pads by entering your message and pressing \"Generate Pad\".\n\n"
					+ "Once your message and pad are present, you may press \"Encrypt/Decrypt\" to encrypt your message.\n\n"
					+ "This program also supports decryption by entering a resulting ciphertext with its cooresponding\n"
					+ "pad in order to return it to its original plaintext form. As long as the \"Save results to project?\"\n"
					+ "radio button was selected before encryption/decryption, previous plaintexts, pads, and ciphertexts may\n"
					+ "be found within the folder containing this program.\n\n"
					+ "To exclude spaces in the resulting ciphertext, select \"Ignore plaintext spaces?\" before encryption.\n\n"
					+ "To clear all text fields of their data, click any one of the two \"Clear\" options inside this program.\n\n"
					+ "Themes may be changed by visiting the \"Themes\" menu and clicking any of the three available options.\n\n"
					+ "New windows may be opened for side-by-side encryption by visiting the \"File\" menu and hitting \"New\"\n\n\n"
					+ "In order to maintain the integrity of your messages, it is reccomended to not reuse pads more than once.");
			JOptionPane.showMessageDialog(this, str2, "Help", JOptionPane.INFORMATION_MESSAGE);	
			break;
			
		case "joke": //Displays an OptionPane with a written joke. 
			joke++;
			String str3 = "";
			if (joke==6) {joke=1;} //Resets the joke counter
			switch (joke) {
				case 1:
					str3 = "I fear for the humble calendar, for its days are surely numbered.";
					break;
				case 2:
					str3 = "Imagine if you walked into a bar and there was a long line of people waiting to take a swing at you.\n\n"
						+ "That right there would be the punch line.\n\n";
					break;
				case 3:
					str3 = "I ordered a chicken and an egg from Amazon.\n\n"
						+ "I'll let you know.";
					break;
				case 4:
					str3 = "I threw a boomerang a few years ago.\n\n"
						+ "I now live in a state of constant anxiety.";
					break;
				case 5:
					str3 = "Apparently, someone in London gets stabbed every 52 seconds.\n\n"
						+ "Poor bastard.";
					break;
			}
			JOptionPane.showMessageDialog(this, str3, "Joke", JOptionPane.INFORMATION_MESSAGE); //Displays the joke
			break;
					
		case "lightTheme": //Changes program theme to "light", then updates "preferences.txt".
			ciphertextLabel_1.setForeground(Color.BLACK);
			plaintextLabel_1.setForeground(Color.BLACK);
			padLabel_1.setForeground(Color.BLACK);
			sEastR.setBorder(BorderFactory.createMatteBorder(0, 75, 0, 0, Color.white));
			btnBar_1.setBackground(Color.lightGray);
			north.setBackground(Color.white); 
			south.setBackground(Color.white);
			nEast.setBackground(Color.white);
			nWest.setBackground(Color.white);
			sEast.setBackground(Color.white);
			sWest.setBackground(Color.white);
			plaintextArea.setBackground(offWhite); plaintextArea.setForeground(Color.BLACK); plaintextArea.setCaretColor(Color.BLACK);
			padArea.setBackground(offWhite); padArea.setForeground(Color.BLACK); padArea.setCaretColor(Color.BLACK);
			ciphertextArea.setBackground(offWhite); ciphertextArea.setForeground(Color.BLACK); ciphertextArea.setCaretColor(Color.BLACK);
			theme = "lightTheme";
			updatePref();
			break;
			
		case "darkTheme": //Changes program theme to "dark", then updates "preferences.txt".
			ciphertextLabel_1.setForeground(Color.WHITE);
			plaintextLabel_1.setForeground(Color.WHITE);
			padLabel_1.setForeground(Color.WHITE);
			sEastR.setBorder(BorderFactory.createMatteBorder(0, 75, 0, 0, otherGrey));
			btnBar_1.setBackground(darkGrey);
			north.setBackground(otherGrey); 
			south.setBackground(otherGrey);
			nEast.setBackground(otherGrey);
			nWest.setBackground(otherGrey);
			sEast.setBackground(otherGrey);
			sWest.setBackground(otherGrey);
			plaintextArea.setBackground(textGrey); plaintextArea.setForeground(Color.WHITE); plaintextArea.setCaretColor(Color.WHITE);
			padArea.setBackground(textGrey); padArea.setForeground(Color.WHITE); padArea.setCaretColor(Color.WHITE);
			ciphertextArea.setBackground(textGrey); ciphertextArea.setForeground(Color.WHITE); ciphertextArea.setCaretColor(Color.WHITE);
			theme = "darkTheme";
			updatePref();
			break;
			
		case "navyTheme": //Changes program theme to "navy", then updates "preferences.txt".
			ciphertextLabel_1.setForeground(Color.WHITE);
			plaintextLabel_1.setForeground(Color.WHITE);
			padLabel_1.setForeground(Color.WHITE);
			sEastR.setBorder(BorderFactory.createMatteBorder(0, 75, 0, 0, navy));
			btnBar_1.setBackground(darkNavy);
			north.setBackground(navy); 
			south.setBackground(navy);
			nEast.setBackground(navy);
			nWest.setBackground(navy);
			sEast.setBackground(navy);
			sWest.setBackground(navy);
			plaintextArea.setBackground(textNavy); plaintextArea.setForeground(Color.WHITE); plaintextArea.setCaretColor(Color.WHITE);
			padArea.setBackground(textNavy); padArea.setForeground(Color.WHITE); padArea.setCaretColor(Color.WHITE);
			ciphertextArea.setBackground(textNavy); ciphertextArea.setForeground(Color.WHITE); ciphertextArea.setCaretColor(Color.WHITE);
			theme = "navyTheme";
			updatePref();
			break;
	}
}
/** Verifies that the pad is long enough to encrypt the plaintext or decrypt the ciphertext.
 * @param padLength - The int representing the pad length currently populating the padTextArea.
 * @param textLength - The int representing the text length, which may represent either the plaintext or ciphertext.
 * @return True if the pad length is sufficient, false if otherwise.
 * */
public boolean appropriatePadSize(int padLength, int textLength) {
	if (textLength>padLength) {
		return false;
	} else {
		return true;
	}
}

/** Attempts to save the plaintext, pad, and ciphertext strings to .txt files for user access. Catches IOException.*/
public void saveAll(){
	try {
		toFile(plaintext, "plaintext.txt");
		toFile(ciphertext, "ciphertext.txt");
		toFile(pad, "pad.txt");
        System.out.println("Saving all");
    } catch (IOException e) {
    	String str1 = "Oops! An error occured while saving your results to the project folder: \n";
		str1 += ("Tell no one of this blunder. It never happeneded.\n");  
		JOptionPane.showMessageDialog(this, str1, "ERR: Files unable to be saved!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("ErrorIcon.png"));
        }
}

/** Attempts to save user preferences to a .txt file that can be read so they may persist across future sessions. Catches IOException.*/
public void updatePref(){
	String pref;
	pref = askSpaces.isSelected()+";";
	pref += askSave.isSelected()+";";
	pref += askCaps.isSelected()+";";
	pref += theme;
	try {
        if (Files.exists(Paths.get("preferences.txt"))) {
        	Files.setAttribute(Paths.get("preferences.txt"), "dos:hidden", false); //Makes the file visible (not hidden) if it exists so it can be accessed
        	} 
        toFile(pref, "preferences.txt");
        Files.setAttribute(Paths.get("preferences.txt"), "dos:hidden", true); //Sets the file to hidden to avoid unwanted access
    } catch (IOException e) {
    	String str1 = "Oops! An error occured while saving your preferences to the project folder: \n";
		str1 += ("Tell no one of this blunder. It never happeneded.\n");  
		JOptionPane.showMessageDialog(this, str1, "ERR: Preferences unable to be saved!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("ErrorIcon.png"));
        }
}

/** Takes in text and writes it to the provided path 
 * @param text - The text you want to write to a file.
 * @param path - The path to the file you want to save.
 * @throws IOException Exception is raised when file can't be overwritten, or can't be created at the specified path because it doesn't yet exist.
 */
public static void toFile(String text, String path) throws IOException{
    try (FileWriter writer = new FileWriter(path);) {
        writer.write(text);
        writer.close();
        }      
}

/** Re-formats the string to exclude whitespaces (the Tab or Space characters).*/
public String ignoreSpaces(String str) {
	String mess = "";
	for (int i=0; i < str.length(); i++) {
		if (str.charAt(i)!=' '&&str.charAt(i)!='	') {
			mess+=str.charAt(i);
		}
	}
	return mess;
}

/** Re-formats the string to be case-insensitive. Only accounts for traditional capital characters with decimal values between 65 and 90, not any extended ASCII characters with multiple cases.*/
public String ignoreCaps(String str) {
	String smsStyle = "";
	for (int i=0; i < str.length(); i++) {
		if (str.charAt(i)>64&&str.charAt(i)<91) {
			smsStyle+=(char)((int) str.charAt(i)+32);
		} else {
			smsStyle+=str.charAt(i);
		}
	}
	return smsStyle;
}

/** Sets the location and size of the window within the parent's coordinate space.
 * @param x - The x-coordinate of the new location's top-left corner in the parent's coordinate space.
 * @param y - The y-coordinate of the new location's top-left corner in the parent's coordinate space.
 * @param width - The new width of the window in pixels.
 * @param height - The new height of the window in pixels.
 */
public void setLocationSize(int x, int y, int width, int height){
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
	this.setLocation(x, y);
	this.setSize(width, height);
}
}
