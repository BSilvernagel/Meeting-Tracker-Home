/**

 * @class: meetingGUI.java

 * @author: Group D

 * @purpose: This class provides methods build GUI and catch exceptions.

 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;


public class MeetingApp extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Set variables that will be utilize throughout program
	public static JFrame guiFrame;
	public static JPanel controlPanel;
	public static JPanel calculatePanel;
	public static JLabel attendeeName = new JLabel("Attendee Name:");
  	public static JTextField userInput = new JTextField("Enter Name");
	public static JLabel meetingIntro = new JLabel("Meeting Time Calculator", SwingConstants.CENTER);
	public static JLabel meetingDuration = new JLabel("Meeting Duration:");
  	public static JTextField userDuration = new JTextField("Enter meeting duration in minutes");
	public static JLabel meetingPerWeek = new JLabel("How many times per week do you meet?");
  	public static Integer[] daysOfWeek = {1, 2, 3, 4, 5};
  	public static JComboBox<Integer> perWeek = new JComboBox<Integer>(daysOfWeek);
  	public static JButton govLink = new JButton("Look Up Government Salary Rates");
  	public static JButton conLink = new JButton("Look Up Contractor Salary Rates");
  	public static JLabel meetingSalary = new JLabel("Annual Salary:");
//  	public static Double[] salaries = { 25000.00, 50000.00, 75000.00};
  	public static JTextField typicalSalary = new JTextField("Enter Annual Salary");
  	public static JLabel meetingScale = new JLabel("Calculate over how long?");
  	public static String[] scales = { "One Meeting", "One Week", "One Month", "One Year"};
  	public static JComboBox<String> timeScale = new JComboBox<String>(scales);
  	public static JTextField calculatorResponse = new JTextField("");
  	public static JLabel listLabel = new JLabel("List of Attendees", SwingConstants.CENTER);
  	public static JButton exportButton = new JButton("Export");
  	public static JButton removeButton = new JButton("Remove");
	public static JButton submitButton = new JButton("Calculate Total");
	public static JButton addButton = new JButton("Add Attendee");
	
	public static ArrayList <Person> meetingAttendees = new ArrayList<>(); 
	public static JTextArea attendeeTextArea = new JTextArea();
	public static JComboBox<Person> currentListOfAttendees = new JComboBox<Person>();

    
        //Default constructor
    	public MeetingApp() {
    		
    		guiFrame = new JFrame("Meeting Time Calculator"); //Set Title
    		guiFrame.setSize(500, 500); //Set size
    		BorderLayout overall = new BorderLayout();
    		guiFrame.setLayout(overall);
    		guiFrame.setDefaultCloseOperation(EXIT_ON_CLOSE); //Exit program when GUI closed
    		guiFrame.add(meetingIntro, BorderLayout.NORTH);
    		meetingIntro.setFont(new Font("Serif", Font.BOLD, 35));
    		meetingDuration.setFont(new Font("Serif", Font.BOLD, 17));
    		meetingPerWeek.setFont(new Font("Serif", Font.BOLD, 17));
    		meetingSalary.setFont(new Font("Serif", Font.BOLD, 17));
    		meetingScale.setFont(new Font("Serif", Font.BOLD, 17));
    		calculatorResponse.setFont(new Font("Serif", Font.BOLD, 20));
    		listLabel.setFont(new Font("Serif", Font.BOLD, 25));
    		attendeeName.setFont(new Font("Serif", Font.BOLD, 17));
    		removeButton.setFont(new Font("Serif", Font.BOLD, 17));
    		exportButton.setFont(new Font("Serif", Font.BOLD, 17));
    		submitButton.setFont(new Font("Serif", Font.BOLD, 17));
    		controlPanel = new JPanel(); 
    		JPanel customPanel = new JPanel();
    		guiFrame.add(customPanel, BorderLayout.CENTER);
    		GridLayout custom = new GridLayout(1,1);
    		custom.setHgap(10);
    		customPanel.setLayout(custom);
    		
    		//Create Left Panel
       		GridLayout layout = new GridLayout(9,1);
       		layout.setVgap(20);
       		layout.setHgap(20);
       		controlPanel.setLayout(layout);
       		controlPanel.setBorder(BorderFactory.createEtchedBorder());
       		customPanel.add(controlPanel);
       		
       		//Create Right Panel
       		calculatePanel = new JPanel();
    		BorderLayout layout2 = new BorderLayout();
    		layout2.setVgap(20);
       		layout2.setHgap(20);
       		calculatePanel.setLayout(layout2);
       		calculatePanel.setBorder(BorderFactory.createEtchedBorder());
    		customPanel.add(calculatePanel);


    		
    		//Add Components to Left Panel
    		controlPanel.add(new JLabel());
       		controlPanel.add(new JLabel());
       		controlPanel.add(attendeeName);
       		controlPanel.add(userInput);
       		controlPanel.add(meetingSalary);
       		controlPanel.add(typicalSalary);
       		typicalSalary.setEditable(true); //Allows user to edit field
       		controlPanel.add(govLink);
       		controlPanel.add(conLink);
       		controlPanel.add(new JLabel());
       		controlPanel.add(addButton);
       		controlPanel.add(meetingDuration);
       		controlPanel.add(userDuration);
       		userDuration.setEditable(true); //Allows user to edit field
       		controlPanel.add(meetingPerWeek);
       		controlPanel.add(perWeek);
       		perWeek.setEditable(true); //Allows user to edit field
       		controlPanel.add(meetingScale);
       		controlPanel.add(timeScale);
       		timeScale.setEditable(true); //Allows user to edit field
       		controlPanel.add(new JLabel());
       		controlPanel.add(new JLabel());

     
       		
       		//Add Components to Right Panel
    		calculatePanel.add(listLabel, BorderLayout.NORTH);
    		JScrollPane attendeeList = new JScrollPane(attendeeTextArea);
    		calculatePanel.add(attendeeList, BorderLayout.CENTER);
    		JPanel buttons = new JPanel();
    		GridLayout buttonLayout = new GridLayout(1,1);
    		buttonLayout.setVgap(0);
    		buttonLayout.setHgap(10);
    		buttons.setLayout(buttonLayout);
    		calculatePanel.add(buttons, BorderLayout.SOUTH);
    		buttons.add(removeButton);
    		buttons.add(submitButton);
    		buttons.add(exportButton);
    		    	

       		//Add Actionlisteners
    		addButton.addActionListener(e -> addAttendee());
    		govLink.addActionListener(e -> govLink());
    		conLink.addActionListener(e -> conLink());
    		removeButton.addActionListener (e -> removeAttendee());
    		submitButton.addActionListener (e -> calculateTotal());
    		exportButton.addActionListener (e -> export());
    		    		
    		
    		//Add MouseListeners
    		userInput.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    userInput.setText("");
                }
            });
    		typicalSalary.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                	typicalSalary.setText("");
                }
            });
    		userDuration.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                	userDuration.setText("");
                }
            });
    		
    		//Blank label to keep grid border the right size
    		JLabel blank = new JLabel("              ");
    		blank.setFont(new Font("Serif", Font.BOLD, 35));
    		guiFrame.add(blank, BorderLayout.SOUTH);
    		
    	}
    	
    	private void conLink() {
    		try {
    			openWebpage(new URL("https://www.glassdoor.com/Contractors-Salary/Washington-DC-Government-Contractor-Salary-E346732.htm"));
    		} catch (MalformedURLException e) {
    			webPageError();			}
    	}
    	
    	private void govLink() {
    		try {
    			openWebpage(new URL("https://www.opm.gov/policy-data-oversight/pay-leave/salaries-wages/salary-tables/pdf/2018/DCB.pdf"));
    		} catch (MalformedURLException e) {
    			webPageError();			}
    	}

		private void addAttendee() {
			double salary = 0.0;
			try {
				salary =Double.parseDouble(typicalSalary.getText().replace(",", ""));
			} catch (NumberFormatException f) {
				f.printStackTrace();
		    // handle the error
			}			if(userInput.getText().contains("Enter Name"))
			{
				JOptionPane.showMessageDialog(null, "Attendee name must be entered.");
				return;
			}
			else
			{
				meetingAttendees.add(new Person(userInput.getText(), salary));
			}
			updateAttendeeDisplay();
			JOptionPane.showMessageDialog(null, "Attendee added");
		}

		private void updateAttendeeDisplay() {		
			attendeeTextArea.setText("");
			if (meetingAttendees.size()>0) {
				attendeeTextArea.append("Name\tSalary\n");
				for (Person p: meetingAttendees) {
					attendeeTextArea.append(p.toString()+"\n");
				}			
			}
		}

		private void removeAttendee() {
			String selectedText = attendeeTextArea.getSelectedText().trim();
			if (meetingAttendees.size()>0) {
				for(Iterator<Person> iterator = meetingAttendees.iterator(); iterator.hasNext();)
				{
					Person person = iterator.next();
					if(person.getAttendee().contains(selectedText))
					{
						meetingAttendees.remove(person);
					}
				}
			}
    		updateAttendeeDisplay();
    	}
    
    	private void calculateTotal() {
    		double duration = 0.0;
    		double frequency = 0.0;
    		String tScale = ""; 
    		//Set duration
			try {
				duration =Double.parseDouble(userDuration.getText());
				if(duration < 0 )
				{
					JOptionPane.showMessageDialog(null, "Duration cannot be negative!");
					return;
				}
			} catch (NumberFormatException f) {
				f.printStackTrace();
		    // handle the error
			}

			//Set frequency
			frequency = daysOfWeek[perWeek.getSelectedIndex()];
			
			//Set timescale
			tScale = scales[timeScale.getSelectedIndex()];
    		
			Meeting test2 = new Meeting(duration, frequency, meetingAttendees, tScale);
    				
    		test2.calcCostPerMeeting();
    		JOptionPane.showMessageDialog(null, test2.toString());
    		
		}


		private void export() {
		    JFileChooser fileChooser = new JFileChooser();
		    int retval = fileChooser.showSaveDialog(this);
		    if (retval == JFileChooser.APPROVE_OPTION) {
		      File file = fileChooser.getSelectedFile();
		      if (file == null) {
		        return;
		      }
		      if (!file.getName().toLowerCase().endsWith(".txt")) {
		        file = new File(file.getParentFile(), file.getName() + ".txt");
		      }
		      try {
		    	  attendeeTextArea.write(new OutputStreamWriter(new FileOutputStream(file),
		            "utf-8"));
		        Desktop.getDesktop().open(file);
		      } catch (Exception e) {
		        e.printStackTrace();
		      }
		    }
		}

		private static boolean openWebpage(URL url) {
			try {
				Desktop.getDesktop().browse(url.toURI());
			} catch (IOException e) {
				webPageError();
			} catch (URISyntaxException e) {
				webPageError();
			}
		    return false;
		}

	
    	// Method that makes GUI visible          
    	public void ShowGUI() {
    		guiFrame.pack();
    		guiFrame.setVisible(true);
    	} 
	
    	// Main Method
    	public static void main(String args[]) {
    	new MeetingApp().ShowGUI();
    	}
    	private static void webPageError() {
    		JOptionPane.showMessageDialog(null, "Unable to open link");
    	}
	    
}
