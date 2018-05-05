

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
	public Person(String attendee, int salary) {
		this.attendee = attendee;
		this.salary = salary;
	}

	//@override
	public String toString() {
		String s = "";
		s += attendee +"\t"+ "$"+Double.toString(this.salary);		
		return s;
	}
	
	public double getSalary() {
		return salary;
	}

}
