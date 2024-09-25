// library used to find out the date and format it
import java.text.SimpleDateFormat;
import java.text.DateFormat;

/*
Project 0 - Grocery Store List

My goal for this project was to learn how to use JFrame, because I am used to C++ and usually don't get the luxury of having a library built in for GUI.
The only past experience I have had with GUI's is ImGui so I think this will be very interesting to see how it works. I will leave comments on where I struggled,
and why I added certain lines of code the way i did


 */

public class main {
	private static int width = 600;
	private static int height = 450;
		
	public static void main(String[] args) 
	{
		// create a object to find out the format
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		// convert the date to a string in the right format
		String todayString = formatter.format(new java.util.Date());
		
		// creates a new object for JFrame in the class we created
		MyFrame frame = new MyFrame(width, height, todayString);
		
		// set frame layout to null
		frame.setLayout(null);
			
		// set the window to visible
		frame.setVisible(true);
		
	}

}
