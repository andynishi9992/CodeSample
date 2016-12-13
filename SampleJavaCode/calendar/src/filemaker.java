import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;
public class filemaker {

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
      out.write("Subject,Start Date,Start Time,End Date,End Time,All Day Event,Description,Location,Private  ");
    		out.newLine();
    		out.write(name + ",");
      System.out.println("What is start date?");
      name = scan.nextLine();
      out.write(name + ",");
      System.out.println("What is start time?");
      name = scan.nextLine();
      out.write(name + ",");
      System.out.println("What is end date?");
      name = scan.nextLine();
      out.write(name + ",");
      System.out.println("What is end time?");
      name = scan.nextLine();
      out.write(name + ",");
      System.out.println("is this all day?");
      name = scan.nextLine();
      if(name.equals("yes")){
    	  out.write("True,");
      }else{
    	  out.write("False,");
      };
      System.out.println("What is description of event");
      name = scan.nextLine();
      out.write(name + ",");
      System.out.println("Where is the location?");
      name = scan.nextLine();
      out.write("\"" +name +"\"" + ",");
      System.out.println("is this private?");
      name = scan.nextLine();
      if(name.equals("yes")){
    	  out.write("True");
      }else{
    	  out.write("False");
      };
      out.close();
      
      
      
      
	}

}
