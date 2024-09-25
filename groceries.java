// using j label to add it to the panel
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
		
	// empty constructor because I am using an array to store the objects
	groceries()
	{
		
	}
	
	public JLabel addItem(String item, float price, int randomDigit)
	{
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
	
	
	public void removeItem(int index, JPanel panel)
	{
		if (index < 0 || index >= row || labels.isEmpty())
			return;
		
		panel.remove(labels.get(index));
		labels.remove(index);
		
		// name erased
		objArray[index][0] = null;
		// checked off
		objArray[index][1] = null;
		// price
		objArray[index][2] = null;
		// quantity
		objArray[index][3] = null;
		
		// shift remaining items
		for (int i = index; i < row - 1; i++)
		{
			objArray[i] = objArray[i + 1];
			// Shift labels
            labels.set(i, labels.get(i + 1)); 
		}
		row--;
		panel.revalidate();
		panel.repaint();
	}
	
	public void checkItemOff(int index)
	{
		objArray[index][1] = Boolean.TRUE;
	}

}
