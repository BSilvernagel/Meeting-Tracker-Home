import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * @class: Meeting
 * @author: Group D
 * @purpose: This class provides methods to calculate the time and cost of the
 * meetings as a product of the user entered parameters. Additional methods are
 * provided to store and retrieve the users data.
 */
public class Meeting 
{
    //class variable definition
    ArrayList <Person> meetingAttendees;
    double meetingDuration;
    double meetingFrequency;
    String timeScale;
    double totalMeetTime;
    double meetingCost;
    
    /************************************************************************
     * Meeting class
     * paramIn list; none.
     * paramOut list; none.
     * Description: This is the default class constructor to initialize all
     * class variables to good values.
     * ***********************************************************************
     */
    public Meeting()
    {
        meetingDuration = 0.0;
        meetingFrequency = 0.0;
        totalMeetTime = 0.0;
    }
    
    /************************************************************************
     * Meeting class
     * @param duration: meeting duration in minutes.
     * @param freq: number of meetings per week.
     * @param tScale: String value for time scale (meeting, week, month, year)
     * paramOut list; none.
     * Description: Overload constructor. Sets all the class variables to specified
     * values at object creation.
     * ***********************************************************************
     */
    public Meeting(double duration, double freq, ArrayList<Person> attendees, String tScale)
    {
        meetingDuration = duration;
        meetingFrequency = freq;
        meetingAttendees = attendees;
        timeScale = tScale;
    }
    
    /************************************************************************
     * getMeetingCost
     * paramIn none.
     * @return meetingCost: returns cost value.
     * Description: This function returns the class variable for meeting cost.
     * ***********************************************************************
     */
    public double getMeetingCost()
    {
        return meetingCost;
    }
    
    /************************************************************************
     * setMeetingCost
     * @param cost: meeting cost
     * Description: This function sets the class variable for meeting cost.
     * ***********************************************************************
     */
    public void setMeetingCost(double cost)
    {
        meetingCost = cost;
    }
    
    /************************************************************************
     * getTimeScale
     * paramIn none.
     * @return timeScale: returns string value for meeting time scale.
     * Description: This function returns the class variable for time scale.
     * ***********************************************************************
     */
    public String getTimeScale()
    {
        return timeScale;
    }
    
    /************************************************************************
     * setTimeScale
     * @param tScale: time scale.
     * Description: This function sets the class variable for time scale.
     * ***********************************************************************
     */
    public void setTimeScale(String tScale)
    {
        timeScale = tScale;
    }
    
    /************************************************************************
     * setInfo
     * @param duration: meeting duration.
     * @param freq: meeting frequency.
     * @param numAttend: number of meeting attendees.
     * @param pGrade: attendee pay grade.
     * Description: This function sets the class variables for meeting duration, 
     * frequency, number of attendees and pay grade.
     * ***********************************************************************
     */
    public void setInfo(double duration, double freq)
    {
        meetingDuration = duration;
        meetingFrequency = freq;
    }
    
    /************************************************************************
     * getInfo
     * paramIn none.
     * @return info: returns array of class variables.
     * Description: This function returns the class variables for meeting
     * duration, frequency, number of attendees and pay grade.
     * ***********************************************************************
     */
    public double [] getInfo()
    {   
        double[] info;
        info = new double[2];
        info[0] = meetingDuration;
        info[1] = meetingFrequency;
        
        return info;
    }
    
//    /************************************************************************
//     * calcMeetingTime
//     * paramIn none.
//     * paramOut none.
//     * Description: This function calculates the total time of meetings entered
//     * by the user based on the meeting frequency and time scale. The time is
//     * calculated as the total time in minutes.
//     * ***********************************************************************
//     */
//    public void calcMeetingTime()
//    {
//        switch (timeScale) {
//            case "One Meeting":
//                totalMeetTime = meetingDuration * 1;
//                break;
//            case "One Week":
//                totalMeetTime = meetingDuration * meetingFrequency;
//                break;
//            case "One Month":
//                //assuming 52 week work year.
//                totalMeetTime = meetingDuration * meetingFrequency * (52/12);
//                break;
//            case "One Year":
//                totalMeetTime = meetingDuration * meetingFrequency * 52;
//                break;
//            default:
//                //place holder for error handling should user not enter time scale value
//                System.out.println("No time scale value entered");
//                break;
//        }
//      
//    }
    /************************************************************************
     * calcCostPerMeeting
     * paramIn none.
     * paramOut none.
     * Description: This function calculates total cost of a single meeting based
     * on the user input for all attendees. 
     * ***********************************************************************
     */
    public void calcCostPerMeeting()
    {       
        //local variable for hourly rate
        double hourlyRate;
        
        //reset meetingCost
        meetingCost = 0.0;
        
        //verify data is valid prior to performing calculation.
        if(validateInfo()) {
            for (int i = 0; i< meetingAttendees.size(); i++) {
            	//get hourly rate
            	hourlyRate = (meetingAttendees.get(i).getSalary()/ 2080);
            	//calculate total cost of meeting for each attendee
            	meetingCost += meetingDuration/60 * hourlyRate;
            }
        }
        else {
        	System.out.println("Data was invalid! Must enter non-zero values for all fields");        	
        }
    }
    
    private boolean validateInfo()
    {
        double[] info = getInfo();
        //verify all values are valid
        for(int i = 0; i < info.length; i++)
        {
            if(info[i] <= 0)
                return false;                
        }
        
        //verify valid data for time scale.
        if(getTimeScale().equals(""))
            return false;
        
        return true;
    }
    /************************************************************************
     * toString
     * paramIn none.
     * paramOut none.
     * Description: This outputs a string formatted to give a message to the user.
     * ***********************************************************************
     */
    
    //@override
    public String toString(){
    	
    	String result = "";
    	double singleMeetingCost = 0.0;
    	double weeklyMeetingCost = 0.0;
    	double monthlyMeetingCost = 0.0;
    	double yearlyMeetingCost = 0.0;
    	
    	singleMeetingCost = meetingCost;
    	weeklyMeetingCost =  meetingFrequency* meetingCost;
    	monthlyMeetingCost =  meetingFrequency * (52/12)* meetingCost; //assuming 52 week work year.
    	yearlyMeetingCost = meetingFrequency * 52* meetingCost; 
    	
    	Locale locale = new Locale("en", "US");      
    	NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
    	
    	result = "Single Meeting Cost\n";
    	result += currencyFormatter.format(singleMeetingCost)+ "\n";
    	result += "Weekly Meeting Cost\n";
    	result += currencyFormatter.format(weeklyMeetingCost)+ "\n";
    	result += "Monthly Meeting Cost\n";
    	result += currencyFormatter.format(monthlyMeetingCost)+ "\n";
    	result += "Yearly Meeting Cost\n";
    	result += currencyFormatter.format(yearlyMeetingCost) + "\n";
    	return result;
    }
}
