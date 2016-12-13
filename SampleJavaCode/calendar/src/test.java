import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;
public class test {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		
      System.out.println("what is the name of the event");
      String name = scan.nextLine();
     File maker = new File(name+".ics");
     BufferedWriter out = new BufferedWriter(new FileWriter(maker));
     out.write("BEGIN:VCALENDAR");
     out.newLine();
     out.write("VERSION:2.0");
     out.newLine();
     out.write("BEGIN:VEVENT");
     out.newLine();
     out.write("SUMMARY:Meeting at Third Workplace in Office #4");
     out.newLine();
     out.write("DTSTART;TZID=California-Los_Angeles;VALUE=DATE-TIME:20140820T160000Z");
     out.newLine();
     out.write("RRULE:FREQ=MONTHLY");
     out.newLine(); 
     out.write("DTEND;TZID=California-Los_Angeles;VALUE=DATE-TIME:2014020T170000Z");
     out.newLine();
     out.write("DTSTAMP;VALUE=DATE-TIME:20140820T084134");
     out.newLine();
     out.write("LOCATION:Office #4 at Third Workplace Contra Costa Centre");
     out.newLine(); 
     out.write("DESCRIPTION:test");
     out.newLine();
     out.write("CLASS:PUBLIC");
     out.newLine();
     out.write("END:VEVENT");
     out.newLine();
     out.write("END:VCALENDAR");
     out.newLine();
     out.close();
	}

}
