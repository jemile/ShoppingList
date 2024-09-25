
// library used for colors
import java.awt.Color;

// library used to change fonts
import java.awt.Font;

// library used to add images
import javax.swing.ImageIcon;

// library used to make buttons
import javax.swing.JButton;

// library used to draw the frame
import javax.swing.JFrame;

// library used for text labels
import javax.swing.JLabel;

//library used for frame border
import javax.swing.BorderFactory;
import javax.swing.border.Border;

//library used for check if button is used
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// library used for asking questions
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//library used to get random integer
import java.util.Random;

public class MyFrame extends JFrame implements ActionListener {
	// object takes width and height to create a window
	// re using this in the project

	JButton addItem;
	JButton removeItem;
	JButton exitApplication;
	JPanel panel;
	int row = 0;

	groceries list = new groceries();

	MyFrame(int width, int height, String time) {
		// set frame title
		this.setTitle("Project 0 - Grocery List");

		// set the frames default close operation so it fully closes if you use the x
		// 3 is for JFrame.EXIT_ON_CLOSE
		// normal operation is 0 which is hide on close
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// remove the ability to resize the frame
		this.setResizable(false);

		// create a border around the screen
		Border border = BorderFactory.createLineBorder(new Color(30, 10, 1, 10));

		// sets the border for the JFrame
		this.getRootPane().setBorder(border);

		// set frame width & height
		this.setSize(width, height);

		// creating a image icon in order to change the application's symbol
		ImageIcon image = new ImageIcon("icon.png");

		// change icon of frame to "icon.png"
		this.setIconImage(image.getImage());

		// change frames background going to add a dark theme later
		// using new Color(r, g, b) will allow you to add custom colors
		this.getContentPane().setBackground(Color.LIGHT_GRAY);

		// this is the label to figure out todays date, I am having some issues
		// it only appears whenever I swap my GUI to another monitor and back will try
		// to figure this out
		// figured out the issue was I was calling set visible at the start of the code
		// instead of the end
		JLabel label = new JLabel();
		// sets font of text to Times New Roman bold with the size of 20
		label.setFont(new Font("Times New Roman", Font.BOLD, 20));
		label.setText(time);

		// puts text in top center screen
		// add bounds to label (x, y) (x2,y2)
		label.setBounds(245, 0, 480, 30);

		// adding panel as a child object to to store the list
		panel = new JPanel();
		panel.setBackground(Color.gray);
		panel.setBounds(10, 30, 300, 365);
		panel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 150), 1));
		
		// button to add something to the grocery list
		addItem = new JButton();
		addItem.addActionListener(this);
		addItem.setFocusPainted(false);
		addItem.setBounds(320, 30, 255, 115);
		addItem.setBackground(Color.gray);
		addItem.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 150), 1));
		addItem.setText("Add Item");
		
		// button to remove something from the grocery list
		removeItem = new JButton();
		removeItem.addActionListener(this);
		removeItem.setFocusPainted(false);
		removeItem.setBounds(320, 155, 255, 115);
		removeItem.setBackground(Color.gray);
		removeItem.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 150), 1));
		removeItem.setText("Remove Item");
		
		// button to exit the application
		exitApplication = new JButton();
		// found out you can use e -> however still need the actionPerformed for the application to run
		// so I just added the rest there instead of making a function to call
		exitApplication.addActionListener(e -> System.exit(0));
		exitApplication.setFocusPainted(false);
		exitApplication.setBounds(320, 280, 255, 115);
		exitApplication.setBackground(Color.gray);
		exitApplication.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 150), 1));
		exitApplication.setText("Exit Application");

		/* adding objects to frame */

		// string of the date
		this.add(label);
		
		// button to add to list
		this.add(addItem);
		
		// button to remove from list
		this.add(removeItem);
		
		// button to exit application
		this.add(exitApplication);

		// panel for the list box of groceries
		this.add(panel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addItem) {
				
			Random random = new Random();
			// grabbing strings with JOptionPane
			String item = JOptionPane.showInputDialog("Grocery Item:");
			if (item == null || item.isEmpty()) {
		        JOptionPane.showMessageDialog(panel, "Please enter a valid item.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String priceInput = JOptionPane.showInputDialog("Enter the Price");
			// if the user pressed cancel
		    if (priceInput == null) 
		        return; 
		    
		    // have to convert string to float because can't check if float is null
		    // there is a error if someone cancels the JOptionPane so we have to use try
		    float price;
		    try {
		    	price = Float.parseFloat(priceInput);
		    } catch (NumberFormatException ex) {
		        JOptionPane.showMessageDialog(panel, "Please enter a valid price.", "Error", JOptionPane.ERROR_MESSAGE);
		        return; 
		    }
		    
			int randomDigit = random.nextInt(100);
			
			JLabel itemLabel = list.addItem(item, price, randomDigit);
			// Add JLabel to JPanel
			panel.add(itemLabel); 
			// Update panel
            panel.revalidate(); 
            panel.repaint();
            
			row++;
			

		}
		if (e.getSource() == removeItem) {
			// check if user clicks cancel, so error doesn't generate
			try {
				int index = Integer.parseInt(JOptionPane.showInputDialog("Enter index of the item to remove"));
		
				list.removeItem(index, panel);
			} catch (NumberFormatException ex) {
		        JOptionPane.showMessageDialog(panel, "Please enter a valid index.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} 

	}

}
