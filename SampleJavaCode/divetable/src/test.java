
public class test {

	/**
	 * @param args
	 * @throws TimeOutOfRangeException 
	 * @throws DepthOutOfRangeException 
	 */
	public static void main(String[] args) throws DepthOutOfRangeException, TimeOutOfRangeException {
		// TODO Auto-generated method stub
   char group = PadiDiveTable.getPressureGroup(40, 140);
   //System.out.println(PadiDiveTable.newpressuregrouprest(0, group));
  // System.out.println(PadiDiveTable.newpressuregrouprest(60, 'L'));
   System.out.println(PadiDiveTable.getresidualnitrogentime(50, 'C'));
	}

}
