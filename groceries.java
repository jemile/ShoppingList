// using j label to add it to the panel
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

// library for array lists
import java.util.ArrayList;

public class groceries {
	// 100 limit on the array
	// using object so I can store strings and bools if checked off
	private Object[][] objArray = new Object[100][4];
	private int row = 0;
	private Boolean boolObj = Boolean.FALSE;
	// used to track labels with indexes (for removeItem()) 
	private ArrayList<JLabel> labels = new ArrayList<>();
	// used to find out which indexes can't be removed cause you already found them
	private ArrayList<Integer> foundItems = new ArrayList<>();
	
	// empty constructor because I am using an array to store the objects
	groceries()
	{
		
	}
	
	public JLabel addItem(String item, float price, int randomDigit)
	{
		for (int i = 0; i < row; i++)
		{	
			if (objArray[i][0].equals(item))
			{
				return null;
			}
		}
		
		// item name
		objArray[row][0] = item;
		// checked off
		objArray[row][1] = boolObj;
		// price
		objArray[row][2] = price;
		// quantity
		objArray[row][3] = randomDigit;
		
		
		JLabel itemLabel = new JLabel("index: " + row + " - " + item + " price: $" + price + " - quantity: " + randomDigit); 
		labels.add(itemLabel);
		// go to next row
		row++;
		
		return itemLabel;
	}
	
	
	public void removeItem(int index, JPanel panel, ArrayList<JCheckBox> checkBoxes)
	{
		// if index out of bound than return
		if (index < 0 || index >= row || foundItems.contains(index)) 
	        return; 
	    

	    // shift elements to the left to fill the gap
	    for (int i = index; i < row - 1; i++) {
	        objArray[i][0] = objArray[i + 1][0];  
	        objArray[i][1] = objArray[i + 1][1];  
	        objArray[i][2] = objArray[i + 1][2];  
	        objArray[i][3] = objArray[i + 1][3];  
	    }
	    
	    // nullify the last row as it's now shifted
	    objArray[row - 1][0] = null;
	    objArray[row - 1][1] = null;
	    objArray[row - 1][2] = null;
	    objArray[row - 1][3] = null;
	    
	    // remove check box from array list
	    checkBoxes.remove(index);
	    row--;  
	    
	    // repaint the list correctly
	    panel.removeAll(); 
	    for (int i = 0; i < row; i++) {
	        String item = (String) objArray[i][0];
	        float price = (float) objArray[i][2];
	        int quantity = (int) objArray[i][3];

	        JLabel itemLabel = new JLabel("index: " + i + " - " + item + " price: $" + price + " quantity: " + quantity);

	        panel.add(itemLabel); 
	        panel.add(checkBoxes.get(i));
	    }

	    panel.revalidate();
	    panel.repaint();
	}
	
	public void checkItemOff(int index)
	{
		objArray[index][1] = Boolean.TRUE;
		foundItems.add(index);
	}
	
	public void checkItemAway(int index)
	{
		objArray[index][1] = Boolean.FALSE;
		foundItems.remove(index);
	}

}
