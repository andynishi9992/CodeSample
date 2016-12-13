import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;


public class WeirdFilter implements BufferedImageOp {

	/**
	 * @param args
	 */
	
	private  ArrayList<int []> Npix = new ArrayList<int[]>();
	private  int MAXY;
	private int MAXX;
	private static String X;
	private  int N;
	public WeirdFilter(int tname){
		N =tname;
		
	}
	
	public static void main(String[] args) {
		BufferedImage input=null;
		BufferedImage output;
		BufferedImageOp filter;
       
		try {
			 X = args[0].replaceFirst("[.][^.]+$", "");
			input = ImageIO.read(new File(args[0]));
		} catch (IOException e) {
			System.out.println("Cannot read file ./image.jpg");
			System.exit(1);
		}

		output = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());  

		// Apply Weird filter
		
		filter = new WeirdFilter(100);
		
		filter.filter(input,output);
		
		saveImage(output, X + "_Weird.jpg");
	}

	@Override
	public BufferedImage createCompatibleDestImage(BufferedImage arg0,
			ColorModel arg1) {
	
		return null;
	}

	@Override
	public BufferedImage filter(BufferedImage arg0, BufferedImage arg1) {
		process(arg0,arg1);
		
		return null;
	}

	@Override
	public Rectangle2D getBounds2D(BufferedImage arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point2D getPoint2D(Point2D arg0, Point2D arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RenderingHints getRenderingHints() {
		// TODO Auto-generated method stub
		return null;
	}

	private synchronized void process(BufferedImage inputImage,
			BufferedImage outputImage) {
         MAXX =inputImage.getWidth();
         MAXY = inputImage.getHeight();
         byte[] newRGB = null;
		for (int i = 0; i < MAXX; i++) {
			for (int j = 0; j < MAXY; j++) {				
				newRGB = processPixel(inputImage, i, j);
				outputImage.setRGB(i , j,RGB.bytesToInt(newRGB) );
			}
		}
	}

	private synchronized byte[] processPixel(BufferedImage image, int x, int y) {

		byte[] finalval = new byte[3];
		finalval[0] =0;
		finalval[1] =0;
		finalval[2] =0;
		

		getneighbors(x,y);

		for(int k = 0; k<Npix.size() ; k++){

			int rgb = image.getRGB(Npix.get(k)[0],Npix.get(k)[1] );
			byte[] bytes = RGB.intToBytes(rgb);
			finalval[0] = (byte) (finalval[0] + Math.max(Math.pow(Math.E, bytes[0]), 20) + 10*Math.cos(bytes[0]));
			finalval[1] = (byte) (finalval[1] + Math.min(Math.pow(Math.E, bytes[1]), 50));
			finalval[2] = (byte) (finalval[2] + Math.min(Math.pow(Math.E, bytes[2]), 20));
		}
	
		finalval[0] = (byte) (finalval[0]/Npix.size());
		finalval[1] = (byte) (finalval[1]/Npix.size());
		finalval[2] = (byte) (finalval[2]/Npix.size());
		Npix.removeAll(Npix);
		return finalval;
	}
	
	
	private static  void saveImage(BufferedImage image, String filename){
		try {
			ImageIO.write(image, "jpg", new File(filename));
		} catch (IOException e) {
			System.out.println("Cannot write file "+filename);
			System.exit(1);
		}
	}



public synchronized void getneighbors(int x, int y){
	int[] N = new int[2];
	int[] NE = new int[2];
	int[] E = new int[2];
	int[] SE = new int[2];
	int[] S = new int[2];
	int[] SW = new int[2];
	int[] W = new int[2];
	int[] NW = new int[2];
	
	N[0] = x;
	N[1] = y+1;
	NE[0] = x+1;
	NE[1] = y+1;
	E[0] = x+1;
	E[1] = y;
	SE[0] = x+1;
	SE[1] = y-1;
	S[0] = x;
	S[1] = y-1;
	SW[0] = x-1;
	SW[1] = y-1;
	W[0]= x-1;
	W[1] = y;
	NW[0] = x-1;
	NW[1] = y+1;
	if(N[0]>-1 && N[0]<MAXX && N[1]>-1 && N[1]<MAXY){
		Npix.add(N);
	}
	if(NE[0]>-1 && NE[0]<MAXX && NE[1]>-1 && NE[1]<MAXY){
		Npix.add(NE);
	}
	if(E[0]>-1 && E[0]<MAXX && E[1]>-1 && E[1]<MAXY){
		Npix.add(E);
	}
	if(SE[0]>-1 && SE[0]<MAXX && SE[1]>-1 && SE[1]<MAXY){
		Npix.add(SE);
	}
	if(S[0]>-1 && S[0]<MAXX && S[1]>-1 && S[1]<MAXY){
		Npix.add(S);
	}
	if(SW[0]>-1 && SW[0]<MAXX && SW[1]>-1 && SW[1]<MAXY){
		Npix.add(SW);
	}
	if(W[0]>-1 && W[0]<MAXX && W[1]>-1 && W[1]<MAXY){
		Npix.add(W);
	}
	if(NW[0]>-1 && NW[0]<MAXX && NW[1]>-1 && NW[1]<MAXY){
		Npix.add(NW);
	}
	

}


}
