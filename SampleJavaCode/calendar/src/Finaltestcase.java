import junit.framework.TestCase;
import static org.junit.Assert.*;
import org.junit.Test;


public class Finaltestcase extends  userinterface {
    
	//This tests that 12 months load into combo box

	@Test
	public void testmonthes(){
		userinterface.openwindow();
	
		int numberofmonths = userinterface.startmonth.getItemCount();
		assertEquals(12,numberofmonths);
	
		
	}

	
	@Test
	public void testhasallrecurringevents(){
	userinterface.openwindow();
		int x =userinterface.recur.getItemCount();
	assertEquals(4,x);
	}
	
	@Test 
	public void testhours(){
		userinterface.openwindow();
		assertEquals(24,userinterface.endhour.getItemCount());
		assertEquals(24,userinterface.starthour.getItemCount());
		
	}
	@Test
	public void testmins(){
		userinterface.openwindow();
		assertEquals(60,userinterface.endmin.getItemCount());
		assertEquals(60,userinterface.startmin.getItemCount());
	
	}
}
