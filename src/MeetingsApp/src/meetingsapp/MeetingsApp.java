
package meetingsapp;

/**
 * @class: MeetingApp
 * @author: Group D
 * @purpose: This class is the main entry point for the application. 
 */
public class MeetingsApp 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {        
        System.out.println("Hard coded Test");
        Meeting test = new Meeting(15, 2, 1, 50000, "One Year");       
        System.out.println("Total Cost: " + test.calcPay());
        
        Meeting test2 = new Meeting();
        test2.setInfo(60, 5, 1, 50000);
        test2.setTimeScale("One Year");
        System.out.println("Test 2 cost: " + test2.calcPay());
        
    }
    
}
