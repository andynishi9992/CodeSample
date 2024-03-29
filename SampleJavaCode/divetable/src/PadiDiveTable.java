import org.omg.CORBA.TIMEOUT;
 

 
/**
 
 * Basic Dive Planner
 
 * @author Duane Leong, Andrew Nishimura, Jiajie Li
 
 * @date November 2, 2014
 
 * Warning: This scuba planner is a prototype and cannot be used to plan real dives.
 
 */
 

 
public class PadiDiveTable {
 

 
  private static int residualnitrogentime = 0;
 
        private static int totalnitrogentime = 0;
 
        private static int[][] pressureGroup = new int[][] {
 
                {35, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140},
 
                {10, 8, 7, 6, 5, 4, 4, 3, 3, 3, 3, -1},
 
                {19, 16, 13, 11, 9, 8, 7, 6, 6, 5, 5, 4},
 
                {25, 22, 17, 14, 12, 10, 9, 8, 7, 6, 6, 5},
 
                {29, 25, 19, 16, 13, 11, 10, 9, 8, 7, 7, 6}, 
 
                {32, 27, 21, 17, 15, 13, 11, 10, 9, 8, -1, 7},
 
                {36, 31, 24, 19, 16, 14, 12, 11, 10, 9, 8, 8},
 
                {40, 34, 26, 21, 18, 15, 13, 12, 11, 10, 9},
 
                {44, 37, 28, 23, 19, 17, 15, 13, 12, 11, 10},
 
                {48, 40, 31, 25, 21, 18, 16, 14, 13, -1},
 
                {52, 44, 33, 27, 22, 19, 17, 15, -1, 12},
 
                {57, 48, 36, 29, 24, 21, 18, 16, 14, 13},
 
                {62, 51, 39, 31, 26, 22, 19, 17, 15},
 
                {67, 55, 41, 33, 27, 23, 21, 18, 16},
 
                {73, 60, 44, 35, 29, 25, 22, 19},
 
                {79, 64, 47, 37, 31, 26, 23, 20},
 
                {85, 69, 50, 39, 33, 28, 24},
 
                {92, 74, 53, 42, 35, 29, 25},
 
                {100, 79, 57, 44, 36, 30},
 
                {108, 85, 60, 47, 38},
 
                {117, 91, 63, 49, 40},
 
                {127, 97, 67, 52},
 
                {139, 104, 71, 54},
 
                {152, 11, 75, 55},
 
                {168, 120, 80},
 
                {188, 129},
 
                {205, 140},
 
        };
 
        //times are listed in total minutes
 

 

 
private static int[][][] surfacetable = new int[][][]{
 
        {{0,180}},//A
 
        {{0,47},{48,228}},//B
 
  {{0,21},{22,69},{70,250}},//C
 
  {{0,8},{9,30},{31,78},{79,259}},//D
 
  {{0,7},{8,16},{17,38},{39,87},{88,268}},//E
 
        {{0,7},{8,15},{16,24},{25,46},{47,94},{95,275}},//F
 
        {{0,6},{7,13},{14,22},{23,31},{32,53},{54,101},{102,282}},//G
 
        {{0,5},{6,12},{13,20},{21,28},{29,37},{38,59},{60,107},{108,288}},//H
 
        {{0,5},{6,11},{12,18},{19,26},{27,34},{35,43},{44,65},{66,113},{114,294}},//I
 
        {{0,5},{6,11},{12,17},{18,24},{25,31},{32,40},{41,49},{50,71},{72,119},{120,300}},//J
 
  {{0,4},{5,10},{11,16},{17,22},{23,29},{30,37},{38,45},{46,54},{55,76},{77,124},{125,305}},//K
 
  {{0,4},{5,9},{10,15},{16,21},{22,27},{28,34},{35,42},{43,50},{51,59},{60,81},{82,129},{130,310}},//L
 
        {{0,4},{5,9},{10,14},{15,19},{20,25},{26,32},{33,39},{40,46},{47,55},{56,64},{65,85},{86,134},{135,315}},//M
 
  {{0,3},{4,8},{9,13},{14,18},{19,24},{25,30},{31,36},{37,43},{44,51},{52,59},{60,68},{69,90},{91,138},{139,319}},//N
 
  {{0,3},{4,8},{9,12},{13,17},{18,23},{24,28},{29,34},{35,41},{42,47},{48,55},{56,63},{64,72},{73,94},{95,143},{144,324}},//O
 
  {{0,3},{4,7},{8,12},{13,16},{17,21},{22,27},{28,32},{33,38},{39,45},{46,51},{52,59},{60,67},{68,76},{77,98},{99,147},{148,328}},//P
 
  {{0,3},{4,7},{8,11},{12,16},{17,20},{21,25},{26,30},{31,36},{37,42},{43,48},{49,55},{56,63},{64,71},{72,80},{81,102},{103,150},{151,331}},//Q
 
  {{0,3},{4,7},{8,11},{12,15},{16,19},{20,24},{25,29},{30,34},{35,40},{41,46},{47,52},{53,59},{60,67},{68,75},{76,84},{85,106},{107,154},{155,335}},//R
 
  {{0,3},{4,6},{7,10},{11,14},{15,18},{19,23},{24,27},{28,32},{33,38},{39,43},{44,49},{50,56},{57,63},{64,70},{71,78},{79,87},{88,109},{110,158},{159,339}},//S
 
  {{0,2},{3,6},{7,10},{11,13},{14,17},{18,22},{23,26},{27,31},{32,36},{37,41},{42,47},{48,53},{54,59},{60,66},{67,73},{74,82},{83,91},{92,113},{114,161},{162,342}},//T
 
  {{0,2},{3,6},{7,9},{10,13},{14,17},{18,21},{22,25},{26,29},{30,34},{35,39},{40,44},{45,50},{51,56},{57,62},{63,69},{70,77},{78,85},{86,94},{95,116},{117,164},{165,345}},//U
 
  {{0,2},{3,5},{6,9},{10,12},{13,16},{17,20},{21,24},{25,28},{29,33},{34,37},{38,42},{43,47},{48,53},{54,59},{60,65},{66,72},{73,80},{81,88},{89,97},{98,119},{120,167},{168,348}},//V
 
  {{0,2},{3,5},{6,8},{9,12},{13,15},{16,19},{20,23},{24,27},{28,31},{32,36},{37,40},{41,45},{46,50},{51,56},{57,62},{63,68},{69,75},{76,83},{84,91},{92,100},{101,122},{123,170},{171,351}},//W
 
  {{0,2},{3,5},{6,8},{9,11},{12,15},{16,18},{19,22},{23,26},{27,30},{31,34},{35,39},{40,43},{44,48},{49,53},{54,59},{60,65},{66,71},{72,78},{79,86},{87,94},{95,103},{104,125},{126,173},{174,354}},//X
 
  {{0,2},{3,5},{6,8},{9,11},{12,14},{15,18},{19,21},{22,25},{26,29},{30,33},{34,37},{38,41},{42,46},{47,51},{52,56},{57,62},{63,68},{69,74},{75,81},{82,89},{90,97},{98,106},{107,128},{129,176},{177,357}},//Y
 
  {{0,2},{3,5},{6,8},{9,11},{12,14},{15,17},{18,20},{21,24},{25,28},{29,31},{32,35},{36,40},{41,44},{45,49},{50,54},{55,59},{60,65},{66,71},{72,77},{78,84},{85,91},{92,100},{101,109},{110,131},{132,179},{180,360}}//Z
 
  };
 
 
 

 

 
   
 
        public static char getPressureGroup(int depth, int time) throws DepthOutOfRangeException, TimeOutOfRangeException {
 
                int y = -1;
 
                int x = -1;
 
                
 
                if (depth > 140 || depth < 0) {
 
                        throw new DepthOutOfRangeException();
 
                }
 
                if (time < 0) {
 
                        throw new TimeOutOfRangeException();
 
                }
 
                
 
                for (int i = 0; i < pressureGroup[0].length; i++) {
 
                        if (depth <= pressureGroup[0][i]) {
 
                                x = i;
 
                                break;
 
                        }
 
                }
 
                
 
                try {
 
                        for (int i = 1; i < pressureGroup.length; i++) {
 
                                if (pressureGroup[i][x] >= time) {
 
                                        y = i;
 
                                        break;
 
                                }
 
                        }
 
                } catch (ArrayIndexOutOfBoundsException e) {
 
                        throw new TimeOutOfRangeException();
 
                }
 

 
                char pressureGroup = (char) (y + '@');
 
                return pressureGroup;
 
        }
 
        //this function looks at the surface table to determine new letter group after a dive
 
        // the rest time refers to time out of water
 
        // the pressuregroup refers to the group entered after dive.  
 
        // returns 'a' if the rest is longer than the last column
 
        public static char newpressuregrouprest(int resttime, char pressuregroup) throws TimeOutOfRangeException, PressureGroupOutOfRangeException{
 
                if (resttime < 0) {
 
                        throw new TimeOutOfRangeException();
 
                }
 
                if (pressuregroup < 'A' || pressuregroup > 'Z') {
 
                        throw new PressureGroupOutOfRangeException();
 
                }
 

 

 
                int grouptocheck = pressuregroup-'@' -1;
 
        
 
                int grouplength = surfacetable[grouptocheck].length;
 
                
 
                char value = 'X';
 
                
 
                if(resttime>surfacetable[grouptocheck][grouplength-1][1]){
 
                        residualnitrogentime = 0;
 
                
 
                value = 'A'; //when the rest is greater than the last column value in the group
 
        } else{
 
                  for(int i=0;i<grouplength;i++){
 
                          if(((surfacetable[grouptocheck][i][0] <= resttime ) && (surfacetable[grouptocheck][i][1] >= resttime))
 
                                          ){
 
                                  
 
                                  value = (char) (Math.abs(i-(grouplength-1)) + '@' +1);
 
                          }
 
                  }
 
        }
 
                return value;
 
        
 
}
 
   
 
        //this function gives the residual nitrogen time when a person plans for another dive
 
        //assuming depth is an actual depth listed in the array
 
        public static int getresidualnitrogentime(int depth, char lettergroup){
 
                int y = lettergroup - '@';
 
                int x =0;
 
                for (int i = 0; i < pressureGroup[0].length; i++) {
 
                        if (depth <= pressureGroup[0][i]) {
 
                                x = i;
 
                                break;
 
                        }
 
                }
 
                int returnValue = pressureGroup[y][x];
 
                if (returnValue == -1) {
 
                        return pressureGroup[y+1][x];
 
                }
 
                else {
 
                        return pressureGroup[y][x];
 
                }
 

 
        }
 

 
        public static void main(String[] args) {
 
                try {
 
                        System.out.println(newpressuregrouprest(3, 'Q'));
 
                        System.out.println(newpressuregrouprest(0, 'A'));
 
                        System.out.println(newpressuregrouprest(10, '?'));
 
                } catch (Exception e) {
 
                        e.printStackTrace();
 
                }
 
        }
 
}
 

 
@SuppressWarnings("serial")
 
class DepthOutOfRangeException extends Exception {
 
}
 

 
@SuppressWarnings("serial")
 
class TimeOutOfRangeException extends Exception {
 
        
 
}
 

 
@SuppressWarnings("serial")
 
class PressureGroupOutOfRangeException extends Exception {
 

 
}
 
