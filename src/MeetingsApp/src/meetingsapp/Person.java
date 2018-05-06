import java.text.NumberFormat;
import java.util.Locale;

public class Person {
	
	private String attendee;
	private double salary;
	
    /************************************************************************
     * Person class
     * @param attendee: name of the person.
     * @param salary: annual salary of attendees in dollars.
     * Description: Overload constructor. Sets all the class variables to specified
     * values at object creation.
     * ***********************************************************************
     */
	public Person(String attendee, double salary) {
		this.attendee = attendee;
		this.salary = salary;
	}

	//@override
	public String toString() {
    	Locale locale = new Locale("en", "US");      
    	NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
		
    	String s = "";
		s += attendee +"\t"+ currencyFormatter.format(this.salary);		
		return s;
	}
	
	public double getSalary() {
		return salary;
	}

	public String getAttendee()
	{
		return attendee;
	}
}
